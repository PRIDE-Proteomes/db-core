package uk.ac.ebi.pride.proteomes.db.core.api.peptide.group;

import org.hibernate.annotations.Where;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.Peptide;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.GeneGroup;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

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
    @JoinColumn(name = "PEPTIDE_ID", referencedColumnName = "PEPTIDE_ID", nullable = false, insertable=false, updatable=false)
    private Peptide peptide;

//    @ManyToOne
//    @JoinColumn(name = "PROT_GROUP_ID", referencedColumnName = "PROT_GROUP_ID", nullable = false, insertable=false, updatable=false)
//    private ProteinGroup proteinGroup;

    @ManyToOne
    @JoinColumn(name = "PROT_GROUP_ID", referencedColumnName = "PROT_GROUP_ID", nullable = false, insertable=false, updatable=false)
    @Where(clause = "PROT_GROUP_TYPE = 'GENE'")  //This is necessary :(
    private GeneGroup geneGroup;



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

//     public  ProteinGroup getProteinGroup() {
//        return proteinGroup;
//    }
//
//    public void setProteinGroup(ProteinGroup proteinGroup) {
//        this.proteinGroup = proteinGroup;
//    }

    public GeneGroup getGeneGroup() {
        return geneGroup;
    }

    public void setGeneGroup(GeneGroup geneGroup) {
        this.geneGroup = geneGroup;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeptideGroup)) return false;
        PeptideGroup that = (PeptideGroup) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(uniqueness, that.uniqueness);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uniqueness);
    }

    @Override
    public String toString() {
        return "PeptideProtein{" +
                "id=" + id +
                ", uniqueness=" + uniqueness +
                ", peptide=" + peptide +
//                ", proteinGroup=" + proteinGroup +
                '}';
    }
}
