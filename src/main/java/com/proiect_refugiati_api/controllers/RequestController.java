package com.proiect_refugiati_api.controllers;

import com.proiect_refugiati_api.amqp_config.AMQPConfig;
import com.proiect_refugiati_api.models.RabbitMessage;
import com.proiect_refugiati_api.models.Request;
import com.proiect_refugiati_api.repositories.RequestRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RestController
@RequestMapping("/api/")
public class RequestController {

    @Autowired
    public RequestRepository requestRepository;

    @Autowired
    public RabbitTemplate template;

    @PostMapping("/createRequest")
    public Request createRequest(@RequestBody Request request) {
        System.out.println(request);
        return requestRepository.save(request);
    }

    @GetMapping("/getAllRequests")
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @GetMapping("/getRequestsForUser/{email}")
    public List<Request> getRequestsForUser(@PathVariable String email) {
        return requestRepository.getRequestsForUser(email);
    }

    @PutMapping("/acceptRequest")
    public ResponseEntity<Request> acceptRequest(@RequestBody Request requestDetails) {
        Request request = requestRepository.getRequestById(requestDetails.getId());

        request.setAcceptedBy(requestDetails.getAcceptedBy());
        request.setRequestStatus(requestDetails.getRequestStatus());

        Request requestUpdated = requestRepository.save(request);

        RabbitMessage rabbitMessage = new RabbitMessage("accepted", "Your request has been accepted. \nThank you for using our services.",
                requestUpdated.getRefugeeEmail(), requestUpdated.getUserNotes(),
                requestUpdated.getAcceptedBy(), requestUpdated.getRequestType());
        template.convertAndSend(AMQPConfig.EXCHANGE, AMQPConfig.ROUTING_KEY, rabbitMessage);

        return ResponseEntity.ok(requestUpdated);
    }

    @PutMapping("/rejectRequest/{message}")
    public ResponseEntity<Request> rejectRequest(@RequestBody Request requestDetails, @PathVariable String message) {
        System.out.println(message);
        System.out.println(requestDetails);
        Request request = requestRepository.getRequestById(requestDetails.getId());

        request.setAcceptedBy(requestDetails.getAcceptedBy());
        request.setRequestStatus(requestDetails.getRequestStatus());

        Request requestUpdated = requestRepository.save(request);

        RabbitMessage rabbitMessage = new RabbitMessage("rejected", message,
                requestUpdated.getRefugeeEmail(), requestUpdated.getUserNotes(),
                requestUpdated.getAcceptedBy(), requestUpdated.getRequestType());
        template.convertAndSend(AMQPConfig.EXCHANGE, AMQPConfig.ROUTING_KEY, rabbitMessage);

        return ResponseEntity.ok(requestUpdated);
    }

    @PutMapping("/completeRequest/{message}")
    public ResponseEntity<Request> completeRequest(@RequestBody Request requestDetails, @PathVariable String message) {
        Request request = requestRepository.getRequestById(requestDetails.getId());

        request.setRequestStatus(requestDetails.getRequestStatus());

        Request requestUpdated = requestRepository.save(request);

        RabbitMessage rabbitMessage = new RabbitMessage("completed", message,
                requestUpdated.getRefugeeEmail(), requestUpdated.getUserNotes(),
                requestUpdated.getAcceptedBy(), requestUpdated.getRequestType());
        template.convertAndSend(AMQPConfig.EXCHANGE, AMQPConfig.ROUTING_KEY, rabbitMessage);

        return ResponseEntity.ok(requestUpdated);
    }
}
