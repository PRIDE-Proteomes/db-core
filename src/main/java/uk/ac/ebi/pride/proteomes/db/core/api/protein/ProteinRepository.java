package uk.ac.ebi.pride.proteomes.db.core.api.protein;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ebi.pride.proteomes.db.core.api.ProteomesRepository;

import java.util.List;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
@Repository
@Transactional(readOnly = true)
public interface ProteinRepository extends JpaRepository<Protein, String>, QueryDslPredicateExecutor<Protein>, ProteomesRepository<Protein> {

    Protein findByProteinAccession(String proteinAccession);
    Long countByProteinAccession(String proteinAccession);

    List<Protein> findByDescriptionContaining(String searchTerm);
    List<Protein> findByDescriptionContaining(String searchTerm, Pageable pageable);
    long countByDescriptionContaining(String searchTerm);

    List<Protein> findByTaxidAndDescriptionContaining(Integer taxid, String searchTerm);
    List<Protein> findByTaxidAndDescriptionContaining(Integer taxid, String searchTerm, Pageable pageable);
    long countByTaxidAndDescriptionContaining(int taxid, String searchTerm);

    @Query("SELECT p FROM Protein p WHERE LOWER(p.sequence) LIKE LOWER(CONCAT('%',:searchTerm,'%'))")
    List<Protein> findBySequenceContaining(@Param("searchTerm") String searchTerm);

    /* Stats queries */
    @Query("SELECT COUNT (DISTINCT p.proteinAccession) from Protein p where p.taxid = :taxid AND p.peptides.size > 0")
    long countByMappedProteins(@Param("taxid") Integer taxid);



}
