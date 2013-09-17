package uk.ac.ebi.pride.proteomes.db.core.api.modification;

import org.hibernate.annotations.Type;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.PeptideModification;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.ProteinModification;

import javax.persistence.*;
import java.util.Collection;

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

    private String cvTerm;
    private String cvName;
    private Double monoDelta;
    private Boolean biologicalSignificant;
    private String description;

    private Collection<PeptideModification> peptideModifications;
    private Collection<ProteinModification> proteinModifications;

    @Column(name = "CV_TERM", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
    @Id
    public String getCvTerm() {
        return cvTerm;
    }

    public void setCvTerm(String cvTerm) {
        this.cvTerm = cvTerm;
    }

    @Column(name = "CV_NAME", nullable = false, insertable = true, updatable = true, length = 400, precision = 0)
    @Basic
    public String getCvName() {
        return cvName;
    }

    public void setCvName(String cvName) {
        this.cvName = cvName;
    }

    @Column(name = "MONO_DELTA", nullable = true, insertable = true, updatable = true, length = 22, precision = 0)
    @Basic
    public Double getMonoDelta() {
        return monoDelta;
    }

    public void setMonoDelta(Double monoDelta) {
        this.monoDelta = monoDelta;
    }

    @Column(name = "BIOLOGICAL_SIGNIFICANT", nullable = true, insertable = true, updatable = true, length = 1, precision = 0)
    @Type(type="true_false")
    @Basic
    public Boolean getBiologicalSignificant() {
        return biologicalSignificant;
    }

    public void setBiologicalSignificant(Boolean biologicalSignificant) {
        this.biologicalSignificant = biologicalSignificant;
    }

    @Column(name = "DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 1000, precision = 0)
    @Basic
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    @OneToMany(mappedBy = "modification")
//    public Collection<PeptideModification> getPeptideModifications() {
//        return peptideModifications;
//    }
//
//    public void setPeptideModifications(Collection<PeptideModification> peptideModsByCvTerm) {
//        this.peptideModifications = peptideModsByCvTerm;
//    }

    @OneToMany(mappedBy = "modification")
    public Collection<ProteinModification> getProteinModifications() {
        return proteinModifications;
    }

    public void setProteinModifications(Collection<ProteinModification> proteinModsByCvTerm) {
        this.proteinModifications = proteinModsByCvTerm;
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
