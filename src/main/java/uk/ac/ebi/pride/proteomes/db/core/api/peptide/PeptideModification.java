package uk.ac.ebi.pride.proteomes.db.core.api.peptide;

import uk.ac.ebi.pride.proteomes.db.core.api.modification.Modification;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "PEPTIDE_MOD", schema = "PRIDEPROT")
public class PeptideModification {
//    private String modCvTerm;
//    private Integer peptideId;

    @Id
    @Column(name = "POSITION", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    private Integer position;

    @ManyToOne
    @JoinColumn(name = "MOD_FK_PK", referencedColumnName = "CV_TERM", nullable = false)
    private Modification modification;

    @ManyToOne
    @JoinColumn(name = "PEPTIDE_FK_PK", referencedColumnName = "PEPTIDE_PK", nullable = false)
    private Peptide peptide;

//    @Column(name = "MOD_FK_PK", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
//    @Id
//    public String getModificationId() {
//        return modCvTerm;
//    }
//
//    public void setModificationId(String modFkPk) {
//        this.modCvTerm = modFkPk;
//    }

//    @Column(name = "PEPTIDE_FK_PK", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
//    @Id
//    public Integer getPeptideId() {
//        return peptideId;
//    }
//
//    public void setPeptideId(Integer peptideFkPk) {
//        this.peptideId = peptideFkPk;
//    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Modification getModification() {
        return modification;
    }

    public void setModification(Modification modByModFkPk) {
        this.modification = modByModFkPk;
    }

    public Peptide getPeptide() {
        return peptide;
    }

    public void setPeptide(Peptide peptideByPeptideFkPk) {
        this.peptide = peptideByPeptideFkPk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeptideModification)) return false;

        PeptideModification that = (PeptideModification) o;

        if (!modification.equals(that.modification)) return false;
        if (!peptide.equals(that.peptide)) return false;
        if (!position.equals(that.position)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = position.hashCode();
        result = 31 * result + modification.hashCode();
        result = 31 * result + peptide.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PeptideModification{" +
                "position=" + position +
                ", modification=" + modification +
                ", peptide=" + peptide +
                '}';
    }
}
