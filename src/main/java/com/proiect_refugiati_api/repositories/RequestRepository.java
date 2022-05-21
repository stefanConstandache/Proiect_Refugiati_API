package com.proiect_refugiati_api.repositories;

import com.proiect_refugiati_api.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query("SELECT r FROM Request r where r.id = :id")
    Request getRequestById(@Param("id") Long id);

    @Query("SELECT r FROM Request r where r.refugeeEmail = :email")
    List<Request> getRequestsForUser(@Param("email") String email);
}
