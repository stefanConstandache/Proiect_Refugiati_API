package com.proiect_refugiati_api.controllers;

import com.proiect_refugiati_api.models.Refugee;
import com.proiect_refugiati_api.repositories.RefugeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RestController
@RequestMapping("/api/")
public class RefugeeController {

    @Autowired
    private RefugeeRepository refugeeRepository;

    @PostMapping("/createRefugee")
    public Refugee createUser(@RequestBody Refugee refugee) {
        System.out.println(refugee);
        return refugeeRepository.save(refugee);
    }

    @GetMapping("/getRefugee/{email}")
    public Refugee getRefugee(@PathVariable String email) {
        return refugeeRepository.getRefugeeByEmail(email);
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
