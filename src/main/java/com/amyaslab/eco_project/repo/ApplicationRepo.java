package com.amyaslab.eco_project.repo;

import com.amyaslab.eco_project.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, Integer> {

    // Spring Data JPA auto-generates the SQL from the method name:
    // findByEmail => SELECT * FROM application WHERE email = ?
    List<Application> findByEmail(String email);
}
