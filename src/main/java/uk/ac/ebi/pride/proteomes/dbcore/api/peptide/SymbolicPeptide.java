package uk.ac.ebi.pride.proteomes.db.core.api.peptide;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ntoro
 * Date: 13/09/2013
 * Time: 09:53
 */
@Entity
@DiscriminatorValue("TRUE")
public class SymbolicPeptide extends Peptide {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "SymbolicPeptide{ " +
                super.toString() +
                '}';
    }

}
