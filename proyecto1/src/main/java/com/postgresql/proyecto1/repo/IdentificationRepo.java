package com.postgresql.proyecto1.repo;

import com.postgresql.proyecto1.model.Identification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

// Repositorio tipo RESTful.(Expone endpoints REST para CRUD)
@RepositoryRestResource
public interface IdentificationRepo extends JpaRepository<Identification, Integer> {

    //Consulta para encontrar cuantas veces se repite la identificación en general y particular de un taxón
    @Query(value = """
    SELECT t.common_name, COUNT(DISTINCT i.id_identification), COUNT(DISTINCT ui.id_user)
    FROM identification i
    INNER JOIN observation o
    ON i.observation_id = o.id_observation
    INNER JOIN taxon t
    ON i.taxon_id = t.id_taxon
    INNER JOIN users ui\s
    ON i.user_id = ui.id_user
    INNER JOIN users uo\s
    ON o.user_id = uo.id_user
    GROUP BY t.common_name
    ORDER BY COUNT(DISTINCT i.id_identification) DESC
   """, nativeQuery = true)
    List<Object[]> getConsult();
}