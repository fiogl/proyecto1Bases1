package com.postgresql.proyecto1.repo;

import com.postgresql.proyecto1.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ImageRepo extends JpaRepository<Image, Integer> {
}
