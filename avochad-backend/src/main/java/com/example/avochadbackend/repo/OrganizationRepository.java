package com.example.avochadbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.avochadbackend.models.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Organization findByTitle(String title);
}
    
