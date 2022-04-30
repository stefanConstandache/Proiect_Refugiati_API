package com.proiect_refugiati_api.repositories;

import com.proiect_refugiati_api.models.PrivilegeModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends CrudRepository<PrivilegeModel, Integer> {

    PrivilegeModel findByName(String name);

}
