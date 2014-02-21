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
    public List<String> findAllDistinctSequenceByTaxid(@Param("taxid") Integer taxid);
    @Query("select distinct p.sequence from Peptide p where p.taxid = :taxid")
    public List<String> findAllDistinctSequenceByTaxid(@Param("taxid") Integer taxid, Pageable pageable);



    /***** FIND PEPTIDES (SYMBOLIC AND/OR VARIANTS) *****/

    public Peptide findByPeptideRepresentation(String representation);

    //If we need it, we can change the List by or Pages
    public List<Peptide> findPeptideBySequence(String sequence);



    /***** FIND PEPTIDE VARIANTS *****/

//    @Query("select p from Peptiform p")
//    public List<Peptiform> findAllPeptiforms();
//    @Query("select p from Peptiform p")
//    public List<Peptiform> findAllPeptiforms(Pageable pageable);

    public List<Peptiform> findPeptiformBySequence(String sequence);

    public List<Peptiform> findPeptiformBySequenceAndTaxid(String sequence, Integer taxid);

    public List<Peptiform> findAllPeptiformsByTaxidAndPeptideIdBetween(Integer taxid, Long minValue, Long maxValue);



    /***** FIND SYMBOLIC PEPTIDES *****/

//    @Query("select p from SymbolicPeptide p")
//    public List<SymbolicPeptide> findAllSymbolicPeptides();
//    @Query("select p from SymbolicPeptide p")
//    public List<SymbolicPeptide> findAllSymbolicPeptides(Pageable pageable);

//    public List<SymbolicPeptide> findSymbolicPeptideByTaxid(Integer taxid);
//    public List<SymbolicPeptide> findSymbolicPeptideByTaxid(Integer taxid, Pageable pageable);

    //As the symbolic peptide is a artificial representation of the peptide, it has to be only one by species.
    public List<SymbolicPeptide> findSymbolicPeptideBySequence(String sequence);

    //As the symbolic peptide is a artificial representation of the peptide, it has to be only one by species.
    public SymbolicPeptide findSymbolicPeptideBySequenceAndTaxid(String sequence, Integer taxid);

	public List<SymbolicPeptide> findAllSymbolicPeptidesByTaxidAndPeptideIdBetween(Integer taxid, Long minValue, Long maxValue);


//    /***** COUNT METHODS *****/
//
//    // count all peptides (peptiforms + symbolic)
//    long countByTaxid(int taxid);
//
//    // count symbolic peptides
//    @Query("select count(p) from SymbolicPeptide p")
//    long countSymbolicPeptide();
//    @Query("select count(p) from SymbolicPeptide p where taxid = :taxid")
//    long countSymbolicPeptideByTaxid(@Param("taxid") Integer taxid);
//    @Query("select count(p) from SymbolicPeptide p")
//    long countSymbolicPeptide(Predicate predicate);
//
//    // count peptiforms
//    @Query("select count(p) from Peptiform p")
//    long countPeptiforms();
//    @Query("select count(p) from Peptiform p where taxid = :taxid")
//    long countPeptiformsByTaxid(@Param("taxid") Integer taxid);
//    @Query("select count(p) from Peptiform p")
//    long countPeptiforms(Predicate predicate);



}
