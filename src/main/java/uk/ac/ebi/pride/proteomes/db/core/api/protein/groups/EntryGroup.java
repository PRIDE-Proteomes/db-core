package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.Protein;

import javax.persistence.*;
import java.util.Set;

/**
 * User: ntoro
 * Date: 06/11/2013
 * Time: 13:18
 */
//This group identified all the proteins and isoform under the same Uniprot Entry
@Entity
@DiscriminatorValue(value = "ENTRY")
public class EntryGroup extends ProteinGroup {

    @ManyToMany
    @JoinTable(name = "PROT_PGRP", schema = "PRIDEPROT",
            inverseJoinColumns = @JoinColumn(name = "PROTEIN_ID"),
            joinColumns = @JoinColumn(name = "PROT_GROUP_ID"))
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<Protein> entryProteins;

    public Set<Protein> getEntryProteins() {
        return entryProteins;
    }

    public void setEntryProteins(Set<Protein> proteins) {
        this.entryProteins = proteins;
    }
}
