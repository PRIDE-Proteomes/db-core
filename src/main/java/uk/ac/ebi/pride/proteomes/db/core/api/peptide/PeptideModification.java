package uk.ac.ebi.pride.proteomes.db.core.api.peptide;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
//TODO needs refactoring because of the compound primary key
@Embeddable
//@Table(name = "PEPTIDE_MOD", schema = "PRIDEPROT")
public class PeptideModification implements Serializable {

//    @Id
//    @Column(name = "MOD_FK_PK", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
//    private String modCvTerm;
//
//    @Id
//    @Column(name = "PEPTIDE_FK_PK", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
//    private Integer peptideId;
//
//    @Id
    @Column(name = "POSITION")
    private Integer position;

//    @ManyToOne
//    @JoinColumn(name = "MOD_FK_PK", referencedColumnName = "CV_TERM", nullable = false)
    @Column(name = "MOD_FK_PK")
    private String modCvTerm;

//    @ManyToOne
//    @JoinColumn(name = "PEPTIDE_FK_PK", referencedColumnName = "PEPTIDE_PK", nullable = false)
//    private Peptide peptide;

//    public String getModificationId() {
//        return modCvTerm;
//    }
//
//    public void setModificationId(String modFkPk) {
//        this.modCvTerm = modFkPk;
//    }
//
//
//    public Integer getPeptideId() {
//        return peptideId;
//    }
//
//    public void setPeptideId(Integer peptideFkPk) {
//        this.peptideId = peptideFkPk;
//    }
//
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

//    public Peptide getPeptide() {
//        return peptide;
//    }
//
//    public void setPeptide(Peptide peptideByPeptideFkPk) {
//        this.peptide = peptideByPeptideFkPk;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeptideModification)) return false;

        PeptideModification that = (PeptideModification) o;

        if (!modCvTerm.equals(that.modCvTerm)) return false;
//        if (!peptide.equals(that.peptide)) return false;
        if (!position.equals(that.position)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = position.hashCode();
        result = 31 * result + modCvTerm.hashCode();
//        result = 31 * result + peptide.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PeptideModification{" +
                "position=" + position +
                ", modification=" + modCvTerm +
//                ", peptide=" + peptide +
                '}';
    }
}
