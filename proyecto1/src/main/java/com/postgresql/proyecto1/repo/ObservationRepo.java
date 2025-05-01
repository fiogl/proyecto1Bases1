package com.postgresql.proyecto1.repo;

import com.postgresql.proyecto1.model.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface ObservationRepo extends JpaRepository<Observation, Integer> {

    @Query("SELECT o FROM Observation o JOIN FETCH o.taxon JOIN FETCH o.user JOIN FETCH o.image")
    List<Observation> findAllWithTaxonAndUser();

    @Query("SELECT o FROM Observation o JOIN FETCH o.taxon JOIN FETCH o.user JOIN FETCH o.image WHERE o.id_observation = :id")
    Observation findByIdWithDetails(@Param("id") Integer id);

    @Query("SELECT o FROM Observation o JOIN FETCH o.taxon")
    List<Observation> findAllWithTaxon();
}
