package com.proiect_refugiati_api.controllers;

import com.proiect_refugiati_api.models.Request;
import com.proiect_refugiati_api.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/")
public class RequestController {

    @Autowired
    public RequestRepository requestRepository;

    @PostMapping("/createRequest")
    public Request createRequest(@RequestBody Request request) {
        return requestRepository.save(request);
    }

    @GetMapping("/getAllRequests")
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @PutMapping("/acceptRequest")
    public ResponseEntity<Request> acceptRequest(@RequestBody Request requestDetails) {
        Request request = requestRepository.getRequestById(requestDetails.getId());

        request.setAcceptedBy(requestDetails.getAcceptedBy());
        request.setRequestStatus("Accepted");

        Request requestUpdated = requestRepository.save(request);

        return ResponseEntity.ok(requestUpdated);
    }

    @PutMapping("/rejectRequest")
    public ResponseEntity<Request> rejectRequest(@RequestBody Request requestDetails) {
        Request request = requestRepository.getRequestById(requestDetails.getId());

        request.setAcceptedBy(requestDetails.getAcceptedBy());
        request.setRequestStatus("Rejected");

        Request requestUpdated = requestRepository.save(request);

        return ResponseEntity.ok(requestUpdated);
    }

    @PutMapping("/completeRequest")
    public ResponseEntity<Request> completeRequest(@RequestBody Request requestDetails) {
        Request request = requestRepository.getRequestById(requestDetails.getId());

        request.setAcceptedBy(requestDetails.getAcceptedBy());
        request.setRequestStatus("Completed");

        Request requestUpdated = requestRepository.save(request);

        return ResponseEntity.ok(requestUpdated);
    }
}
