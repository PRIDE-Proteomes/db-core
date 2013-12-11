package uk.ac.ebi.pride.proteomes.db.core.api.protein;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
@Repository
public interface ProteinRepository extends JpaRepository<Protein, String> {

    Protein findByProteinAccession(String proteinAccession);

    List<Protein> findByDescriptionContaining(String searchTerm);

    @Query("SELECT p FROM Protein p WHERE LOWER(p.sequence) LIKE LOWER(CONCAT('%',:searchTerm,'%'))")
    List<Protein> findBySequenceContaining(@Param("searchTerm") String searchTerm);


}
