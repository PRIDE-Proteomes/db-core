package uk.ac.ebi.pride.proteomes.db.core.api.peptide.group;

import uk.ac.ebi.pride.proteomes.db.core.api.peptide.Peptide;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.ProteinGroup;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
@Entity
@Table(name = "PEP_PGROUP", schema = "PRIDEPROT")
public class PeptideGroup implements Serializable {

    @EmbeddedId
    private PeptideGroupPK id;

    @Basic
    @Column(name = "UNIQUENESS", nullable = false, insertable = false, updatable = false, length = 22, precision = 0)
    private Integer uniqueness;

    @ManyToOne
    @JoinColumn(name = "PEPTIDE_FK_PK", referencedColumnName = "PEPTIDE_PK", nullable = false, insertable=false, updatable=false)
    private Peptide peptide;

    @ManyToOne
    @JoinColumn(name = "P_GROUP_FK_PK", referencedColumnName = "PROT_GROUP_PK", nullable = false, insertable=false, updatable=false)
    private ProteinGroup proteinGroup;

    public PeptideGroupPK getId() {
        return id;
    }

    public void setId(PeptideGroupPK id) {
        this.id = id;
    }

    public Integer getUniqueness() {
        return uniqueness;
    }

    public void setUniqueness(Integer uniqueness) {
        this.uniqueness = uniqueness;
    }

    public Peptide getPeptide() {
        return peptide;
    }

    public void setPeptide(Peptide peptideByPeptideFkPk) {
        this.peptide = peptideByPeptideFkPk;
    }

     public  ProteinGroup getProteinGroup() {
        return proteinGroup;
    }

    public void setProteinGroup(ProteinGroup proteinGroup) {
        this.proteinGroup = proteinGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeptideGroup)) return false;

        PeptideGroup that = (PeptideGroup) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "PeptideProtein{" +
                "id=" + id +
                ", uniqueness=" + uniqueness +
                ", peptide=" + peptide +
                ", proteinGroup=" + proteinGroup +
                '}';
    }
}
