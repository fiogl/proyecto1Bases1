package com.postgresql.proyecto1.repo;

import com.postgresql.proyecto1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
