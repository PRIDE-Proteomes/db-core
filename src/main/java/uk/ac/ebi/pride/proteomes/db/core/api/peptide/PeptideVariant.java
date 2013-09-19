package uk.ac.ebi.pride.proteomes.db.core.api.peptide;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;

/**
 * User: ntoro
 * Date: 15/08/2013
 * Time: 14:06
 */
@Entity
@DiscriminatorValue(value = "FALSE")
public class PeptideVariant extends Peptide {

    @ElementCollection
    @CollectionTable(
            name = "PEPTIDE_MOD", schema = "PRIDEPROT",
            joinColumns = @JoinColumn(name = "PEPTIDE_FK_PK", referencedColumnName = "PEPTIDE_PK")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<PeptideModification> peptideModifications;


    public Collection<PeptideModification> getPeptideModifications() {
        return peptideModifications;
    }

    public void setPeptideModifications(Collection<PeptideModification> peptideModifications) {
        this.peptideModifications = peptideModifications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeptideVariant)) return false;
        if (!super.equals(o)) return false;

        PeptideVariant that = (PeptideVariant) o;

        if (peptideModifications != null ? !peptideModifications.equals(that.peptideModifications) : that.peptideModifications != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (peptideModifications != null ? peptideModifications.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PeptideVariant{" +
                "peptideModifications=" + peptideModifications +
                '}';
    }
}
