package uk.ac.ebi.pride.proteomes.db.core.api.protein;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Where;
import uk.ac.ebi.pride.proteomes.db.core.api.modification.ModificationLocation;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.Peptide;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.PeptideVariant;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.SymbolicPeptide;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.ProteinGroup;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.gene.Gene;
import uk.ac.ebi.pride.proteomes.db.core.api.quality.Score;

import javax.persistence.*;
import java.util.Set;

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

    @ElementCollection
    @CollectionTable(
            name = "PROTEIN_MOD", schema = "PRIDEPROT",
            joinColumns = @JoinColumn(name = "PROTEIN_FK_PK", referencedColumnName = "PROTEIN_ACCESSION")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<ModificationLocation> modificationLocations;


    @ManyToMany
    @JoinTable(name = "PROTEIN_GENE", schema = "PRIDEPROT",
            joinColumns = @JoinColumn(name = "PROTEIN_FK_PK"),
            inverseJoinColumns = @JoinColumn(name = "GENE_FK_PK"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Gene> genes;

    @ManyToMany
    @JoinTable(name = "PROT_PEP", schema = "PRIDEPROT",
            joinColumns = @JoinColumn(name = "PROTEIN_FK_PK"),
            inverseJoinColumns = @JoinColumn(name = "PEPTIDE_FK_PK"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Peptide> peptides;


    @ManyToMany
    @JoinTable(name = "PROT_PEP", schema = "PRIDEPROT",
            joinColumns = @JoinColumn(name = "PROTEIN_FK_PK"),
            inverseJoinColumns = @JoinColumn(name = "PEPTIDE_FK_PK"))
    @LazyCollection(LazyCollectionOption.FALSE)
    @Where(clause = "SYMBOLIC = 'FALSE'")  //This is necessary :(
    private Set<PeptideVariant> peptideVariants;


    @ManyToMany
    @JoinTable(name = "PROT_PEP", schema = "PRIDEPROT",
            joinColumns = @JoinColumn(name = "PROTEIN_FK_PK"),
            inverseJoinColumns = @JoinColumn(name = "PEPTIDE_FK_PK"))
    @LazyCollection(LazyCollectionOption.FALSE)
    @Where(clause = "SYMBOLIC = 'TRUE'")  //This is necessary :(
    private Set<SymbolicPeptide> symbolicPeptides;


    @ManyToMany
    @JoinTable(name = "PROT_PGRP", schema = "PRIDEPROT",
            joinColumns = @JoinColumn(name = "PROT_FK_PK"),
            inverseJoinColumns = @JoinColumn(name = "P_GROUP_FK_PK"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<ProteinGroup> proteinGroups;

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

    public Set<ModificationLocation> getModificationLocations() {
        return modificationLocations;
    }

    public void setModificationLocations(Set<ModificationLocation> modificationLocations) {
        this.modificationLocations = modificationLocations;
    }

    public Set<Gene> getGenes() {
        return genes;
    }

    public void setGenes(Set<Gene> genes) {
        this.genes = genes;
    }

    public Set<Peptide> getPeptides() {
        return peptides;
    }

    public void setPeptides(Set<Peptide> peptides) {
        this.peptides = peptides;
    }

    public Set<PeptideVariant> getPeptideVariants() {
        return peptideVariants;
    }

    public void setPeptideVariants(Set<PeptideVariant> peptideVariants) {
        this.peptideVariants = peptideVariants;
    }

    public Set<SymbolicPeptide> getSymbolicPeptides() {
        return symbolicPeptides;
    }

    public void setSymbolicPeptides(Set<SymbolicPeptide> symbolicPeptides) {
        this.symbolicPeptides = symbolicPeptides;
    }

    public Set<ProteinGroup> getProteinGroups() {
        return proteinGroups;
    }

    public void setProteinGroups(Set<ProteinGroup> proteinGroups) {
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
        if (o == null || getClass() != o.getClass()) return false;

        Protein protein = (Protein) o;

        if (!proteinAccession.equals(protein.proteinAccession)) return false;
        if (!taxid.equals(protein.taxid)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = proteinAccession.hashCode();
        result = 31 * result + taxid.hashCode();
        return result;
    }
}
