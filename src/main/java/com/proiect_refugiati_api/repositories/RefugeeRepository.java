package com.proiect_refugiati_api.repositories;

import com.proiect_refugiati_api.models.Refugee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RefugeeRepository extends JpaRepository<Refugee, String> {

    @Query("SELECT r from Refugee r WHERE r.email = :email")
    Refugee getRefugeeByEmail(@Param("email") String email);

}
