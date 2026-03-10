package com.amyaslab.eco_project.controller;

import com.amyaslab.eco_project.model.Application;
import com.amyaslab.eco_project.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApplicationController {

    @Autowired
    private ApplicationService service;

    // POST /api/applications — Submit a new product application (from the Apply form)
    @PostMapping("/applications")
    public ResponseEntity<?> submitApplication(@RequestBody Application application) {
        try {
            Application saved = service.submitApplication(application);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET /api/applications — Retrieve all applications (used by Admin Dashboard)
    @GetMapping("/applications")
    public ResponseEntity<List<Application>> getAllApplications() {
        return new ResponseEntity<>(service.getAllApplications(), HttpStatus.OK);
    }

    // GET /api/applications/track?email=... — Look up applications by customer email
    @GetMapping("/applications/track")
    public ResponseEntity<?> trackByEmail(@RequestParam String email) {
        List<Application> apps = service.getApplicationsByEmail(email);
        if (apps.isEmpty()) {
            return new ResponseEntity<>("No applications found for that email.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(apps, HttpStatus.OK);
    }

    // PUT /api/applications/{id}/status — Admin updates the status of an application
    @PutMapping("/applications/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable int id, @RequestBody Map<String, String> body) {
        try {
            String newStatus = body.get("status");
            Application updated = service.updateStatus(id, newStatus);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
