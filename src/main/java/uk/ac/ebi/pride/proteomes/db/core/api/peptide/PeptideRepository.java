package uk.ac.ebi.pride.proteomes.db.core.api.peptide;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
public interface PeptideRepository extends JpaRepository<Peptide, Long> {

    public Peptide findByPeptideRepresentation(String representation);

    //If we need it, we can change the List by or Pages
    public List<Peptide> findPeptideBySequence(String sequence);
    public List<PeptideVariant> findPeptideVariantBySequence(String sequence);

    //As the symbolic peptide is a artificial representation of the peptide, it has to be only one by species.
    public List<SymbolicPeptide> findSymbolicPeptideBySequence(String sequence);

    //As the symbolic peptide is a artificial representation of the peptide, it has to be only one by species.
    public SymbolicPeptide findSymbolicPeptideBySequenceAndTaxid(String sequence, Integer taxid);

    @Query("select p from SymbolicPeptide p")
    public List<SymbolicPeptide> findAllSymbolicPeptides();

    @Query("select p from PeptideVariant p")
    public List<PeptideVariant> findAllPeptideVariants();

    @Query("select distinct p.sequence from Peptide p where p.taxid = :taxid")
    List<String> findAllDistinctSequenceByTaxid(@Param("taxid") Integer taxid);
}
