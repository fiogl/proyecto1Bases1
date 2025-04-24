package com.postgresql.proyecto1.repo;

import com.postgresql.proyecto1.model.Taxon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface TaxonRepo extends JpaRepository<Taxon, Integer> {
    // Para buscar si otros taxones decienden del actual
    List<Taxon> findByAntecesorId(int antecesorId);
}
