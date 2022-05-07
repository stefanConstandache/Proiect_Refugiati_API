package com.proiect_refugiati_api.security;

import com.proiect_refugiati_api.models.PrivilegeModel;
import com.proiect_refugiati_api.models.RoleModel;
import com.proiect_refugiati_api.models.UserModel;
import com.proiect_refugiati_api.repositories.PrivilegeRepository;
import com.proiect_refugiati_api.repositories.RoleRepository;
import com.proiect_refugiati_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Component
public class SetupSecurity implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        PrivilegeModel readPrivilegeModel
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        PrivilegeModel writePrivilegeModel
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<PrivilegeModel> adminPrivilegeModels = Arrays.asList(
                readPrivilegeModel, writePrivilegeModel);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivilegeModels);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilegeModel));

        RoleModel adminRoleModel = roleRepository.findByName("ROLE_ADMIN");
        UserModel userModel = new UserModel();
        userModel.setFirstName("Test");
        userModel.setLastName("Test");
        userModel.setPassword(passwordEncoder.encode("test"));
        userModel.setEmail("test@test.com");
        userModel.setRoleModels(Arrays.asList(adminRoleModel));
        userRepository.save(userModel);

        alreadySetup = true;
    }

    @Transactional
    PrivilegeModel createPrivilegeIfNotFound(String name) {

        PrivilegeModel privilegeModel = privilegeRepository.findByName(name);
        if (privilegeModel == null) {
            privilegeModel = PrivilegeModel.builder().name(name).build();
            privilegeRepository.save(privilegeModel);
        }
        return privilegeModel;
    }

    @Transactional
    RoleModel createRoleIfNotFound(
            String name, Collection<PrivilegeModel> privilegeModels) {

        RoleModel roleModel = roleRepository.findByName(name);
        if (roleModel == null) {
            roleModel = RoleModel.builder().name(name).build();
            roleModel.setPrivilegeModels(privilegeModels);
            roleRepository.save(roleModel);
        }
        return roleModel;
    }
}
