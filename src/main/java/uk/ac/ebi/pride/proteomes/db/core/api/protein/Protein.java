package uk.ac.ebi.pride.proteomes.db.core.api.protein;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.Peptide;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.ProteinGroup;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.gene.Gene;
import uk.ac.ebi.pride.proteomes.db.core.api.quality.Score;

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
@Table(name = "PROTEIN", schema = "PRIDEPROT")
public class Protein {

    @Id
    @Column(name = "PROTEIN_ACCESSION", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
    private String proteinAccession;

    @Basic
    @Column(name = "SEQUENCE", nullable = false, insertable = true, updatable = true)
    private String sequence;

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 90, precision = 0)
    private String description;

    @Basic
    @Column(name = "TAXID", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    private Integer taxid;

    @OneToMany(mappedBy = "protein")
    private Collection<ProteinModification> proteinModifications;

    @ManyToMany
    @JoinTable(name = "PROTEIN_GENE", schema = "PRIDEPROT",
            joinColumns = @JoinColumn(name = "PROTEIN_FK_PK"),
            inverseJoinColumns = @JoinColumn(name = "GENE_FK_PK"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Gene> genes;

    @ManyToMany
    @JoinTable(name = "PROT_PEP", schema = "PRIDEPROT",
            joinColumns = @JoinColumn(name = "PROTEIN_FK_PK"),
            inverseJoinColumns = @JoinColumn(name = "PEPTIDE_FK_PK"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Peptide> peptides;

    @ManyToMany
    @JoinTable(name = "PROT_PGRP", schema = "PRIDEPROT",
            joinColumns = @JoinColumn(name = "PROT_FK_PK"),
            inverseJoinColumns = @JoinColumn(name = "P_GROUP_FK_PK"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<ProteinGroup> proteinGroups;

    @OneToOne
    @JoinColumn(name = "SCORE_FK", referencedColumnName = "SCORE_PK", nullable = false)
    private Score score;


    public String getProteinAccession() {
        return proteinAccession;
    }

    public void setProteinAccession(String proteinAccession) {
        this.proteinAccession = proteinAccession;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getTaxid() {
        return taxid;
    }

    public void setTaxid(Integer taxid) {
        this.taxid = taxid;
    }

    public Collection<ProteinModification> getProteinModifications() {
        return proteinModifications;
    }

    public void setProteinModifications(Collection<ProteinModification> proteinModifications) {
        this.proteinModifications = proteinModifications;
    }

    public Collection<Gene> getGenes() {
        return genes;
    }

    public void setGenes(Collection<Gene> genes) {
        this.genes = genes;
    }

    public Collection<Peptide> getPeptides() {
        return peptides;
    }

    public void setPeptides(Collection<Peptide> peptides) {
        this.peptides = peptides;
    }


    public Collection<ProteinGroup> getProteinGroups() {
        return proteinGroups;
    }

    public void setProteinGroups(Collection<ProteinGroup> proteinGroups) {
        this.proteinGroups = proteinGroups;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score scoreByScoreFk) {
        this.score = scoreByScoreFk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Protein)) return false;

        Protein protein = (Protein) o;

        if (description != null ? !description.equals(protein.description) : protein.description != null) return false;
        if (genes != null ? !genes.equals(protein.genes) : protein.genes != null) return false;
        if (peptides != null ? !peptides.equals(protein.peptides) : protein.peptides != null) return false;
        if (!proteinAccession.equals(protein.proteinAccession)) return false;
        if (proteinGroups != null ? !proteinGroups.equals(protein.proteinGroups) : protein.proteinGroups != null)
            return false;
        if (proteinModifications != null ? !proteinModifications.equals(protein.proteinModifications) : protein.proteinModifications != null)
            return false;
        if (score != null ? !score.equals(protein.score) : protein.score != null) return false;
        if (!sequence.equals(protein.sequence)) return false;
        if (!taxid.equals(protein.taxid)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = proteinAccession.hashCode();
        result = 31 * result + sequence.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + taxid.hashCode();
        result = 31 * result + (proteinModifications != null ? proteinModifications.hashCode() : 0);
        result = 31 * result + (genes != null ? genes.hashCode() : 0);
        result = 31 * result + (peptides != null ? peptides.hashCode() : 0);
        result = 31 * result + (proteinGroups != null ? proteinGroups.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        return result;
    }
}
