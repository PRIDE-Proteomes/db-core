package uk.ac.ebi.pride.proteomes.db.core.api.modification;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
@Entity
@Table(name = "MOD", schema = "PRIDEPROT")
public class Modification {

    @Id
    @NotNull
    @Column(name = "MOD_ID", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
    private String modId;

    @Basic
    @Column(name = "MOD_NAME", nullable = false, insertable = true, updatable = true, length = 400, precision = 0)
    private String modName;

    @Basic
    @Column(name = "MONO_DELTA", nullable = true, insertable = true, updatable = true, length = 22, precision = 0)
    private Double monoDelta;

    @Basic
    @Column(name = "BIOLOGICAL_SIGNIFICANT", nullable = true, insertable = true, updatable = true, length = 1, precision = 0)
    @Type(type="true_false")
    private Boolean biologicalSignificant;

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 1000, precision = 0)
    private String description;


    public String getModId() {
        return modId;
    }

    public void setModId(String cvTerm) {
        this.modId = cvTerm;
    }

    public String getModName() {
        return modName;
    }

    public void setModName(String cvName) {
        this.modName = cvName;
    }

    public Double getMonoDelta() {
        return monoDelta;
    }

    public void setMonoDelta(Double monoDelta) {
        this.monoDelta = monoDelta;
    }

    public Boolean getBiologicalSignificant() {
        return biologicalSignificant;
    }

    public void setBiologicalSignificant(Boolean biologicalSignificant) {
        this.biologicalSignificant = biologicalSignificant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Modification)) return false;

        Modification that = (Modification) o;

        if (!modId.equals(that.modId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return modId.hashCode();
    }

    @Override
    public String toString() {
        return "Modification{" +
                "cvTerm='" + modId + '\'' +
                ", cvName='" + modName + '\'' +
                ", monoDelta=" + monoDelta +
                ", biologicalSignificant=" + biologicalSignificant +
                ", description='" + description + '\'' +
                '}';
    }
}
