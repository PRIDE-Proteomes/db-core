package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.Peptide;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.Protein;

import javax.persistence.*;
import java.util.Set;

/**
 * User: ntoro
 * Date: 06/11/2013
 * Time: 13:18
 */
@Entity
@DiscriminatorValue(value = "GENE")
public class GeneGroup extends ProteinGroup {

    @ManyToMany
    @JoinTable(name = "PROT_PGRP", schema = "PRIDEPROT",
            inverseJoinColumns = @JoinColumn(name = "PROTEIN_ID"),
            joinColumns = @JoinColumn(name = "PROT_GROUP_ID"))
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<Protein> geneProteins;

    @ManyToMany
    @JoinTable(name = "PEP_PGROUP", schema = "PRIDEPROT",
            inverseJoinColumns = @JoinColumn(name = "PEPTIDE_ID"),
            joinColumns = @JoinColumn(name = "PROT_GROUP_ID"))
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<Peptide> genePeptides;

    public Set<Protein> getGeneProteins() {
        return geneProteins;
    }

    public void setGeneProteins(Set<Protein> proteins) {
        this.geneProteins = proteins;
    }

    public Set<Peptide> getGenePeptides() {
        return genePeptides;
    }

    public void setGenePeptides(Set<Peptide> genePeptides) {
        this.genePeptides = genePeptides;
    }
}
