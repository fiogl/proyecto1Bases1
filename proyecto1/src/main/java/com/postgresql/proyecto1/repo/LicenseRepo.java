package com.postgresql.proyecto1.repo;

import com.postgresql.proyecto1.model.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LicenseRepo extends JpaRepository<License, Integer> {
}
