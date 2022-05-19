package com.proiect_refugiati_api.controllers;

import com.proiect_refugiati_api.models.Refugee;
import com.proiect_refugiati_api.repositories.RefugeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/")
public class RefugeeController {

    @Autowired
    private RefugeeRepository refugeeRepository;

    @PostMapping("/createRefugee")
    public Refugee createUser(@RequestBody Refugee refugee) {
        return refugeeRepository.save(refugee);
    }

    @GetMapping("/getRefugee")
    public Refugee getRefugee(@RequestBody Refugee refugeeEmail) {
        return refugeeRepository.getRefugeeByEmail(refugeeEmail.getEmail());
    }

    @PutMapping("/updateRefugee")
    public ResponseEntity<Refugee> updateRefugee(@RequestBody Refugee refugeeDetails) {
        Refugee refugee = refugeeRepository.getRefugeeByEmail(refugeeDetails.getEmail());

        refugee.setFirstName(refugeeDetails.getFirstName());
        refugee.setLastName(refugeeDetails.getLastName());
        refugee.setTelephone(refugeeDetails.getTelephone());

        Refugee updatedRefugee = refugeeRepository.save(refugee);

        return ResponseEntity.ok(updatedRefugee);
    }

}
