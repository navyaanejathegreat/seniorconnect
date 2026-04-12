package com.seniorconnect.seniorconnect.controller;

import com.seniorconnect.seniorconnect.model.MentorshipRequest;
import com.seniorconnect.seniorconnect.model.User;
import com.seniorconnect.seniorconnect.repository.MentorshipRequestRepository;
import com.seniorconnect.seniorconnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.seniorconnect.seniorconnect.model.MentorshipRequestDTO;

import java.util.List;

@RestController
@RequestMapping("/api/mentorship")
@CrossOrigin(origins = "*")
public class MentorshipRequestController {

    @Autowired
    private MentorshipRequestRepository mentorshipRequestRepository;

    @Autowired
    private UserRepository userRepository;

    // Junior sends a request to a senior
    @PostMapping("/request")
    public ResponseEntity<?> sendRequest(@RequestBody MentorshipRequestDTO dto) {
        User junior = userRepository.findById(dto.getJuniorId()).orElse(null);
        User senior = userRepository.findById(dto.getSeniorId()).orElse(null);

        if (junior == null || senior == null) {
            return ResponseEntity.badRequest().body("Invalid user IDs");
        }

        MentorshipRequest request = new MentorshipRequest();
        request.setJunior(junior);
        request.setSenior(senior);
        request.setMessage(dto.getMessage());
        request.setStatus("PENDING");

        mentorshipRequestRepository.save(request);
        return ResponseEntity.ok("Request sent successfully!");
    }

    // Get all requests received by a senior
    @GetMapping("/received/{seniorId}")
    public List<MentorshipRequest> getReceivedRequests(@PathVariable Long seniorId) {
        User senior = userRepository.findById(seniorId).orElse(null);
        return mentorshipRequestRepository.findBySenior(senior);
    }

    // Senior accepts or rejects a request
    @PutMapping("/respond/{requestId}")
    public ResponseEntity<?> respondToRequest(@PathVariable Long requestId,
                                              @RequestParam String status) {
        MentorshipRequest request = mentorshipRequestRepository.findById(requestId).orElse(null);
        if (request == null) return ResponseEntity.notFound().build();

        request.setStatus(status); // "ACCEPTED" or "REJECTED"
        mentorshipRequestRepository.save(request);
        return ResponseEntity.ok("Request " + status);
    }
    @GetMapping("/accepted/{juniorId}")
    public List<MentorshipRequest> getAccepted(@PathVariable Long juniorId) {
        User junior = userRepository.findById(juniorId).orElse(null);
        return mentorshipRequestRepository.findByJuniorAndStatus(junior, "ACCEPTED");
    }
}