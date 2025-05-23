package com.postgresql.proyecto1.repo;

import com.postgresql.proyecto1.model.Taxon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface TaxonRepo extends JpaRepository<Taxon, Integer> {

    // Consulta para encontrar la taxonomía de un taxón en particular.
    @Query(value = "WITH RECURSIVE antecesores AS (" +
            "    SELECT id_taxon, common_name, scientific_name, id_ancestor" +
            "    FROM taxon" +
            "    WHERE id_taxon = :id" +
            "    UNION ALL" +
            "    SELECT t.id_taxon, t.common_name, t.scientific_name, t.id_ancestor" +
            "    FROM taxon t" +
            "    INNER JOIN antecesores a ON t.id_taxon = a.id_ancestor" +
            ")" +
            "SELECT * FROM antecesores",
            nativeQuery = true)
    List<Object[]> findTaxonomy(@Param("id") Integer id);

    // Consulta para encontrar el reino específico de un taxón.
    @Query(value = "WITH RECURSIVE ancestor AS ( " +
            "    SELECT id_taxon, id_ancestor, scientific_name, common_name FROM taxon WHERE id_taxon = :id " +
            "    UNION ALL " +
            "    SELECT t.id_taxon, t.id_ancestor, t.scientific_name, t.common_name FROM taxon t INNER JOIN taxon ta ON t.id_taxon = ta.id_ancestor " +
            ") " +
            "SELECT * FROM ancestor WHERE id_ancestor IS NULL;",
            nativeQuery = true)
    Taxon findKingdom(@Param("id") Integer id);

    // Consulta para verificar si dado un taxón, si otros taxones descienden de el.
    @Query("SELECT COUNT(t) > 0 FROM Taxon t WHERE t.ancestor.id_taxon = :id")
    boolean hasAncestors(@Param("id") Integer id);
}
