package uk.ac.ebi.pride.proteomes.db.core.api.modification;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Objects;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
@Embeddable
public class ModificationLocation implements Serializable, Comparable {

    @Column(name = "POSITION")
    private Integer position;

    @Column(name = "MOD_ID")
    @Pattern(regexp = "(MOD|UNIMOD|CHEMMOD|SUBST|PRDMOD):([^\\|]+)(\\|\\[([^,]+)?,([^,]+)?,([^,]+),([^,]*)\\])?")
    private String modId;

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getModId() {
        return modId;
    }

    public void setModId(String cvTerm) {
        this.modId = cvTerm;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModificationLocation)) return false;
        ModificationLocation that = (ModificationLocation) o;
        return Objects.equals(position, that.position) &&
                Objects.equals(modId, that.modId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, modId);
    }

    @Override
    public String toString() {
        return "ModificationLocation{" +
                "position=" + position +
                ", modification=" + modId +
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
            return modId.compareTo(that.getModId());
        }
        return comparison;
    }
}
