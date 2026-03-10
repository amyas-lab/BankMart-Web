package com.amyaslab.eco_project.service;

import com.amyaslab.eco_project.model.Application;
import com.amyaslab.eco_project.repo.ApplicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepo repo;

    // Submit a new application — sets the timestamp and default status
    public Application submitApplication(Application application) {
        application.setCreatedAt(new Date());
        if (application.getStatus() == null || application.getStatus().isEmpty()) {
            application.setStatus("PENDING_PAYMENT");
        }
        return repo.save(application);
    }

    // Get ALL applications — used by the Admin Dashboard
    public List<Application> getAllApplications() {
        return repo.findAll();
    }

    // Look up applications by email — used for the Status Tracking page
    public List<Application> getApplicationsByEmail(String email) {
        return repo.findByEmail(email);
    }

    // Update an application's status — used by Admin to approve/reject
    public Application updateStatus(int id, String newStatus) {
        Application app = repo.findById(id).orElseThrow(
            () -> new RuntimeException("Application not found with id: " + id)
        );
        app.setStatus(newStatus);
        return repo.save(app);
    }
}
