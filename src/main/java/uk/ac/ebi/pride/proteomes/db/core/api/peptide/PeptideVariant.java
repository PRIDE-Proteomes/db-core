package uk.ac.ebi.pride.proteomes.db.core.api.peptide;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import uk.ac.ebi.pride.proteomes.db.core.api.modification.ModificationLocation;

import javax.persistence.*;
import java.util.Set;

/**
 * User: ntoro
 * Date: 15/08/2013
 * Time: 14:06
 */
@Entity
@DiscriminatorValue(value = "FALSE")
public class PeptideVariant extends Peptide {

    @ElementCollection(targetClass=ModificationLocation.class)
    @CollectionTable(
            name = "PEPTIDE_MOD", schema = "PRIDEPROT",
            joinColumns = @JoinColumn(name = "PEPTIDE_FK_PK", referencedColumnName = "PEPTIDE_PK")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<ModificationLocation> modificationLocations;

    public Set<ModificationLocation> getModificationLocations() {
        return modificationLocations;
    }

    public void setModificationLocations(Set<ModificationLocation> modificationLocations) {
        this.modificationLocations = modificationLocations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "PeptideVariant{" +
                "modificationLocations=" + modificationLocations +
                '}';
    }
}
