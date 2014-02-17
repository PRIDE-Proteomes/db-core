package uk.ac.ebi.pride.proteomes.db.core.api.peptide;

import org.springframework.data.domain.Pageable;
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
public interface PeptideRepository extends JpaRepository<Peptide, Long> {

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

    @Query("select p from Peptiform p")
    public List<Peptiform> findAllPeptiforms();
    @Query("select p from Peptiform p")
    public List<Peptiform> findAllPeptiforms(Pageable pageable);

    public List<Peptiform> findPeptiformBySequence(String sequence);

    public List<Peptiform> findPeptiformBySequenceAndTaxid(String sequence, int taxid);

    public List<Peptiform> findAllPeptiformsByTaxidAndPeptideIdBetween(Integer taxId, Long minValue, Long maxValue);



    /***** FIND SYMBOLIC PEPTIDES *****/

    @Query("select p from SymbolicPeptide p")
    public List<SymbolicPeptide> findAllSymbolicPeptides();
    @Query("select p from SymbolicPeptide p")
    public List<SymbolicPeptide> findAllSymbolicPeptides(Pageable pageable);

    public List<SymbolicPeptide> findSymbolicPeptideByTaxid(int taxid);
    public List<SymbolicPeptide> findSymbolicPeptideByTaxid(int taxid, Pageable pageable);

    //As the symbolic peptide is a artificial representation of the peptide, it has to be only one by species.
    public List<SymbolicPeptide> findSymbolicPeptideBySequence(String sequence);

    //As the symbolic peptide is a artificial representation of the peptide, it has to be only one by species.
    public SymbolicPeptide findSymbolicPeptideBySequenceAndTaxid(String sequence, Integer taxid);

//    @Query("select p from SymbolicPeptide p where p.taxid = :taxid and p.id")
	public List<SymbolicPeptide> findAllSymbolicPeptidesByTaxidAndPeptideIdBetween(Integer taxId, Long minValue, Long maxValue);
}
