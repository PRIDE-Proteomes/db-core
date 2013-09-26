package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.gene;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.Protein;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.ProteinGroup;

import javax.persistence.*;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "GENE", schema = "PRIDEPROT")
@Entity
public class Gene {

    @Id
    @Column(name = "GENE_ACCESSION", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
    private String geneAccession;

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 400, precision = 0)
    private String description;

    @Basic
    @Column(name = "TAXID", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    private Integer taxid;


    @ManyToMany(mappedBy = "genes")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Protein> proteins;

    @ManyToMany
    @JoinTable(name = "GENE_PGRP", schema = "PRIDEPROT",
            joinColumns = @JoinColumn(name = "GENE_FK_PK"),
            inverseJoinColumns = @JoinColumn(name = "P_GROUP_FK_PK"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<ProteinGroup> proteinGroups;

    public String getGeneAccession() {
        return geneAccession;
    }

    public void setGeneAccession(String geneAccession) {
        this.geneAccession = geneAccession;
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

    public Set<ProteinGroup> getProteinGroups() {
        return proteinGroups;
    }

    public void setProteinGroups(Set<ProteinGroup> proteinGroup) {
        this.proteinGroups = proteinGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gene gene = (Gene) o;

        if (!geneAccession.equals(gene.geneAccession)) return false;
        if (!taxid.equals(gene.taxid)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = geneAccession.hashCode();
        result = 31 * result + taxid.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Gene{" +
                "geneAccession='" + geneAccession + '\'' +
                ", description='" + description + '\'' +
                ", taxid=" + taxid +
                ", proteins=" + proteins +
                ", proteinGroups=" + proteinGroups +
                '}';
    }
}
