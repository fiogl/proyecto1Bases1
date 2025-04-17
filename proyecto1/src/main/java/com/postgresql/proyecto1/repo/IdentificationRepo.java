package com.postgresql.proyecto1.repo;

import com.postgresql.proyecto1.model.Identification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface IdentificationRepo extends JpaRepository<Identification, Integer> {
}