package com.seniorconnect.seniorconnect.controller;


import java.util.HashMap;
import com.seniorconnect.seniorconnect.model.SeniorProfile;
import com.seniorconnect.seniorconnect.model.User;
import com.seniorconnect.seniorconnect.repository.SeniorProfileRepository;
import com.seniorconnect.seniorconnect.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SeniorProfileController {

    private final SeniorProfileRepository profileRepository;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> createProfile(@RequestBody Map<String, String> req) {
        User user = userRepository.findByEmail(req.get("email")).orElse(null);
        if (user == null) return ResponseEntity.badRequest().body("User not found");

        SeniorProfile profile = new SeniorProfile();
        profile.setUser(user);
        profile.setCompany(req.get("company"));
        profile.setRole(req.get("role"));
        profile.setDomain(req.get("domain"));
        profile.setCgpa(Float.parseFloat(req.get("cgpa")));
        profile.setBranch(req.get("branch"));
        profile.setSkills(req.get("skills"));
        profile.setPlacementYear(Integer.parseInt(req.get("placementYear")));
        profile.setBio(req.get("bio"));
        profileRepository.save(profile);

        return ResponseEntity.ok("Profile created successfully");
    }

    @GetMapping
    public ResponseEntity<List<SeniorProfile>> getAllProfiles(
            @RequestParam(required = false) String company,
            @RequestParam(required = false) String domain) {

        if (company != null)
            return ResponseEntity.ok(profileRepository.findByCompanyContainingIgnoreCase(company));
        if (domain != null)
            return ResponseEntity.ok(profileRepository.findByDomainContainingIgnoreCase(domain));

        return ResponseEntity.ok(profileRepository.findAll());
    }
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestParam String email) {

        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        SeniorProfile profile = profileRepository.findByUser(user).orElse(null);

        if (profile == null) {
            return ResponseEntity.status(404).body("Profile not found");
        }

        return ResponseEntity.ok(profile);
    }
}