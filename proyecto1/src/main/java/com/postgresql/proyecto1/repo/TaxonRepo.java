package com.postgresql.proyecto1.repo;

import com.postgresql.proyecto1.model.Taxon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface TaxonRepo extends JpaRepository<Taxon, Integer> {
    // Para buscar si otros taxones decienden del actual
    @Query("SELECT t FROM Taxon t WHERE t.ancestor = :ancestor")
    List<Taxon> findByAncestor(@Param("ancestor") Taxon ancestor);

}
