package com.postgresql.proyecto1.repo;

import com.postgresql.proyecto1.model.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ObservationRepo extends JpaRepository<Observation, Integer> {

    // Esta consulta busca una observación específica por su ID (id_observation),
    // cargando también las relaciones de taxón, usuario e imagen asociada a esa observación.
    // JOIN FETCH asegura que los datos relacionados se carguen al mismo tiempo que la observación.
    // El parámetro "id" se utiliza para pasar el ID de la observación a buscar.
    @Query("SELECT o FROM Observation o JOIN FETCH o.taxon JOIN FETCH o.user JOIN FETCH o.image WHERE o.id_observation = :id")
    Observation findByIdWithDetails(@Param("id") Integer id);
}
