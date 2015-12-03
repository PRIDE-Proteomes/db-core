package uk.ac.ebi.pride.proteomes.db.core.api.peptide;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ntoro
 * Date: 15/08/2013
 * Time: 14:06
 */
@Entity
@DiscriminatorValue(value = "FALSE")
public class Peptiform extends Peptide {

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
        return "Peptiform{ " +
                super.toString() +
                '}';
    }
}
