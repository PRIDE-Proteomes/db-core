package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.Protein;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.gene.Gene;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "PROT_GROUP", schema = "PRIDEPROT")
@Entity
public class ProteinGroup {

    private Long id;
    private String description;
    private Collection<Protein> proteins;

    @Column(name = "PROTEIN_GROUP_PK", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 1000, precision = 0)
    @Basic
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(mappedBy = "proteinGroups")
    @LazyCollection(LazyCollectionOption.FALSE)
    public Collection<Protein> getProteins() {
        return proteins;
    }

    public void setProteins(Collection<Protein> proteins) {
        this.proteins = proteins;
    }


    @ManyToMany(mappedBy = "proteinGroup")
    @LazyCollection(LazyCollectionOption.FALSE)
    public Collection<Gene> getGenes() {
        return genes;
    }

    public void setGenes(Collection<Gene> genes) {
        this.genes = genes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProteinGroup proteinGroup = (ProteinGroup) o;

        if (description != null ? !description.equals(proteinGroup.description) : proteinGroup.description != null)
            return false;
        if (id != null ? !id.equals(proteinGroup.id) : proteinGroup.id != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    private Collection<Gene> genes;


}
