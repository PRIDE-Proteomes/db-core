package uk.ac.ebi.pride.proteomes.db.core.api.modification;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
@Embeddable
public class ModificationLocation implements Serializable, Comparable {

    @Column(name = "POSITION")
    private Integer position;

    @Column(name = "MOD_FK_PK")
    private String modCvTerm;

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getModCvTerm() {
        return modCvTerm;
    }

    public void setModCvTerm(String cvTerm) {
        this.modCvTerm = cvTerm;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModificationLocation)) return false;

        ModificationLocation that = (ModificationLocation) o;

        if (!modCvTerm.equals(that.modCvTerm)) return false;
        if (!position.equals(that.position)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = position.hashCode();
        result = 31 * result + modCvTerm.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ModificationLocation{" +
                "position=" + position +
                ", modification=" + modCvTerm +
                '}';
    }

    @Override
    public int compareTo(Object o) {

        //TODO: REVIEW

        if (this == o) return 0;

        ModificationLocation that = (ModificationLocation) o;
        if(this.equals(that)) return 0;

        int comparison = position.compareTo(that.getPosition());
        if(comparison == 0){
            return modCvTerm.compareTo(that.getModCvTerm());
        }
        return comparison;
    }
}
