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

    public Application submitApplication(Application application) {
        application.setCreatedAt(new Date());
        if (application.getStatus() == null || application.getStatus().isEmpty()) {
            application.setStatus("PENDING_PAYMENT");
        }
        return repo.save(application);
    }

    public List<Application> getAllApplications() {
        return repo.findAll();
    }
}
