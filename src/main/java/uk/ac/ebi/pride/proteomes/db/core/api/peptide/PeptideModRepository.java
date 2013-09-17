package uk.ac.ebi.pride.proteomes.db.core.api.peptide;

import org.springframework.data.repository.CrudRepository;
import uk.ac.ebi.pride.proteomes.db.core.api.modification.Modification;

/**
 * User: ntoro
 * Date: 17/09/2013
 * Time: 17:25
 */
public interface PeptideModRepository  extends CrudRepository<PeptideModification, Integer> {
    public PeptideModification findByPeptideAndModificationAndPosition(
            Peptide peptide,
            Modification modification,
            Integer position
    );
}
