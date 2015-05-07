package uk.ac.ebi.pride.proteomes.db.core.api.peptide.protein;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
public interface PeptideProteinRepository extends JpaRepository<PeptideProtein, PeptideProteinPK> {

    // the combination Peptide id + protein accession has to be unique in the peptide-protein table
    // so we can expect a single result at max
    public PeptideProtein findByPeptidePeptideIdAndProteinProteinAccession(Long peptideId, String proteinAccession);

    // to find all peptide-protein mappings for a give peptide
    public List<PeptideProtein> findByPeptidePeptideId(Long peptideId);

    public List<PeptideProtein> findByProteinProteinAccession(String proteinAccession);

    @Query("select distinct pp.id.proteinAccession from PeptideProtein pp where pp.protein.taxid = :taxid")
    public List<String> findDistinctMappedProteinsByTaxId(@Param("taxid") Integer taxid);

}
