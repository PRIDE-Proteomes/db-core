package uk.ac.ebi.pride.proteomes.db.core.api.peptide;

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
public interface PeptideRepository extends JpaRepository<Peptide, Long>, QueryDslPredicateExecutor<Peptide>, PeptideRepositoryCustom {

    /***** FIND SEQUENCES ONLY  *****/

    @Query("select distinct p.sequence from Peptide p where p.taxid = :taxid")
    List<String> findAllDistinctSequenceByTaxid(@Param("taxid") Integer taxid);

    @Query("select distinct p.sequence from Peptide p where p.taxid = :taxid")
    List<String> findAllDistinctSequenceByTaxid(@Param("taxid") Integer taxid, Pageable pageable);



    /***** FIND PEPTIDES (SYMBOLIC AND/OR VARIANTS) *****/

    Peptide findByPeptideRepresentation(String representation);

    //If we need it, we can change the List by or Pages
    List<Peptide> findPeptideBySequence(String sequence);



    /***** FIND PEPTIDE VARIANTS *****/
    List<Peptiform> findPeptiformBySequence(String sequence);

    List<Peptiform> findPeptiformBySequenceAndTaxid(String sequence, Integer taxid);

    List<Peptiform> findAllPeptiformsByTaxidAndPeptideIdBetween(Integer taxid, Long minValue, Long maxValue);



    /***** FIND SYMBOLIC PEPTIDES *****/
    //As the symbolic peptide is a artificial representation of the peptide, it has to be only one by species.
    List<SymbolicPeptide> findSymbolicPeptideBySequence(String sequence);

    //As the symbolic peptide is a artificial representation of the peptide, it has to be only one by species.
    SymbolicPeptide findSymbolicPeptideBySequenceAndTaxid(String sequence, Integer taxid);

    List<SymbolicPeptide> findAllSymbolicPeptidesByTaxidAndPeptideIdBetween(Integer taxid, Long minValue, Long maxValue);

}
