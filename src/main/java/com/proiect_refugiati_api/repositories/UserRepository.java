package com.proiect_refugiati_api.repositories;

import com.proiect_refugiati_api.models.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer> {
}
