package uk.ac.ebi.pride.proteomes.db.core.api.peptide.protein;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
@Repository
public interface PeptideProteinRepository extends JpaRepository<PeptideProtein, PeptideProteinPK> {

    public List<PeptideProtein> findByPeptidePeptideIdAndProteinProteinAccession(Long peptideId, String proteinAccession);


}
