package com.proiect_refugiati_api.repositories;

import com.proiect_refugiati_api.models.RoleModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<RoleModel, Integer> {

    RoleModel findByName(String name);

}
