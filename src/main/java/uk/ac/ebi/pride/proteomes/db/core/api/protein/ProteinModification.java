package uk.ac.ebi.pride.proteomes.db.core.api.protein;

import uk.ac.ebi.pride.proteomes.db.core.api.modification.Modification;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "PROTEIN_MOD", schema = "PRIDEPROT", catalog = "")
@Entity
public class ProteinModification {

//    private String modCvTerm;
//    private String proteinAccession;

    @Id
    @Column(name = "POSITION", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    private Integer position;

    @ManyToOne
    @JoinColumn(name = "MOD_FK_PK", referencedColumnName = "CV_TERM", nullable = false)
    private Modification modification;

    @ManyToOne
    @JoinColumn(name = "PROTEIN_FK_PK", referencedColumnName = "PROTEIN_ACCESSION", nullable = false)
    private Protein protein;


//    @Column(name = "MOD_FK_PK", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
//    @Id
//    public String getModCvTerm() {
//        return modCvTerm;
//    }
//
//    public void setModCvTerm(String modFkPk) {
//        this.modCvTerm = modFkPk;
//    }

//    @Column(name = "PROTEIN_FK_PK", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
//    @Id
//    public String getProteinAccession() {
//        return proteinAccession;
//    }
//
//    public void setProteinAccession(String proteinFkPk) {
//        this.proteinAccession = proteinFkPk;
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

     public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein proteinByProteinFkPk) {
        this.protein = proteinByProteinFkPk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProteinModification)) return false;

        ProteinModification that = (ProteinModification) o;

        if (!modification.equals(that.modification)) return false;
        if (!position.equals(that.position)) return false;
        if (!protein.equals(that.protein)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = position.hashCode();
        result = 31 * result + modification.hashCode();
        result = 31 * result + protein.hashCode();
        return result;
    }
}
