package uk.ac.ebi.pride.proteomes.db.core.api.modification;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "MOD", schema = "PRIDEPROT")
public class Modification {

    @Id
    @Column(name = "CV_TERM", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
    private String cvTerm;


    @Basic
    @Column(name = "CV_NAME", nullable = false, insertable = true, updatable = true, length = 400, precision = 0)
    private String cvName;

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


    public String getCvTerm() {
        return cvTerm;
    }

    public void setCvTerm(String cvTerm) {
        this.cvTerm = cvTerm;
    }

    public String getCvName() {
        return cvName;
    }

    public void setCvName(String cvName) {
        this.cvName = cvName;
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
        if (o == null || getClass() != o.getClass()) return false;

        Modification modification = (Modification) o;

        if (biologicalSignificant != null ? !biologicalSignificant.equals(modification.biologicalSignificant) : modification.biologicalSignificant != null)
            return false;
        if (cvName != null ? !cvName.equals(modification.cvName) : modification.cvName != null) return false;
        if (cvTerm != null ? !cvTerm.equals(modification.cvTerm) : modification.cvTerm != null) return false;
        if (description != null ? !description.equals(modification.description) : modification.description != null)
            return false;
        if (monoDelta != null ? !monoDelta.equals(modification.monoDelta) : modification.monoDelta != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cvTerm != null ? cvTerm.hashCode() : 0;
        result = 31 * result + (cvName != null ? cvName.hashCode() : 0);
        result = 31 * result + (monoDelta != null ? monoDelta.hashCode() : 0);
        result = 31 * result + (biologicalSignificant != null ? biologicalSignificant.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

}
