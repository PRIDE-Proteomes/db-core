package uk.ac.ebi.pride.proteomes.db.core.api.peptide;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;

/**
 * User: ntoro
 * Date: 15/08/2013
 * Time: 14:06
 */
@Entity
@DiscriminatorValue( value = "false")
public class PeptideVariants extends Peptide {

    @OneToMany(mappedBy = "peptide")
    private Collection<PeptideModification> peptideModifications;

    public Collection<PeptideModification> getPeptideModifications() {
        return peptideModifications;
    }

    public void setPeptideModifications(Collection<PeptideModification> peptideModsByPeptidePk) {
        this.peptideModifications = peptideModsByPeptidePk;
    }

}
