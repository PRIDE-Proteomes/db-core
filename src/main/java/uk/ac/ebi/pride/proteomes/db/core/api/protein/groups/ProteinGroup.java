package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.Protein;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
@Entity
@Table(name = "PROT_GROUP", schema = "PRIDEPROT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PROT_GROUP_TYPE", discriminatorType = DiscriminatorType.STRING )
public abstract class ProteinGroup implements Serializable {

    @Id
    @Column(name = "PROT_GROUP_ID", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
    @NotNull
    private String id;

    @Column(name = "DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 1000, precision = 0)
    @Basic
    private String description;

    @Basic
    @Column(name = "TAXID", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    @NotNull
    private Integer taxid;

    @ManyToMany
    @JoinTable(name = "PROT_PGRP", schema = "PRIDEPROT",
            inverseJoinColumns = @JoinColumn(name = "PROTEIN_ID"),
            joinColumns = @JoinColumn(name = "PROT_GROUP_ID"))
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<Protein> proteins;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Set<Protein> getProteins() {
        return proteins;
    }

    public void setProteins(Set<Protein> proteins) {
        this.proteins = proteins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProteinGroup)) return false;

        ProteinGroup that = (ProteinGroup) o;

        if (!id.equals(that.id)) return false;
        if (!taxid.equals(that.taxid)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + taxid.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ProteinGroup{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", taxid=" + taxid +
                ", proteins=" + proteins +
                '}';
    }
}
