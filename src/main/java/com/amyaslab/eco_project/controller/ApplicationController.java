package com.amyaslab.eco_project.controller;

import com.amyaslab.eco_project.model.Application;
import com.amyaslab.eco_project.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApplicationController {

    @Autowired
    private ApplicationService service;

    @PostMapping("/applications")
    public ResponseEntity<?> submitApplication(@RequestBody Application application) {
        try {
            Application savedApplication = service.submitApplication(application);
            return new ResponseEntity<>(savedApplication, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/applications")
    public ResponseEntity<List<Application>> getAllApplications() {
        return new ResponseEntity<>(service.getAllApplications(), HttpStatus.OK);
    }
}
