package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.Protein;

import javax.persistence.*;
import java.util.Collection;

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
    private Collection<Protein> geneProteins;

    public Collection<Protein> getGeneProteins() {
        return geneProteins;
    }

    public void setGeneProteins(Collection<Protein> proteins) {
        this.geneProteins = proteins;
    }
}
