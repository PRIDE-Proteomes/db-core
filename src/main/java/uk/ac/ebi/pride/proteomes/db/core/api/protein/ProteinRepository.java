package uk.ac.ebi.pride.proteomes.db.core.api.protein;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
@Repository
@Transactional(readOnly = true)
public interface ProteinRepository extends JpaRepository<Protein, String>, QueryDslPredicateExecutor<Protein>, ProteinRepositoryCustom {

    Protein findByProteinAccession(String proteinAccession);
    Long countByProteinAccession(String proteinAccession);

    List<Protein> findByNameContaining(String searchTerm);
    List<Protein> findByNameContaining(String searchTerm, Pageable pageable);
    long countByNameContaining(String searchTerm);

    List<Protein> findByTaxidAndNameContaining(Integer taxid, String searchTerm);
    List<Protein> findByTaxidAndNameContaining(Integer taxid, String searchTerm, Pageable pageable);
    long countByTaxidAndNameContaining(int taxid, String searchTerm);

    List<Protein> findByTaxidAndEvidence(Integer taxid, Integer evidence);
    long countByTaxidAndEvidence(Integer taxid, Integer evidence);

    @Query("SELECT p FROM Protein p WHERE LOWER(p.sequence) LIKE LOWER(CONCAT('%',:searchTerm,'%'))")
    List<Protein> findBySequenceContaining(@Param("searchTerm") String searchTerm);

}
