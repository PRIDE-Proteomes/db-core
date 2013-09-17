package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.gene;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.Protein;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.ProteinGroup;

import javax.persistence.*;
import java.util.Collection;

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
    private Collection<Protein> proteins;

    @ManyToMany
    @JoinTable(name = "GENE_PGRP", schema = "PRIDEPROT",
            joinColumns = @JoinColumn(name = "GENE_FK_PK"),
            inverseJoinColumns = @JoinColumn(name = "P_GROUP_FK_PK"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<ProteinGroup> proteinGroup;

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

    public Collection<Protein> getProteins() {
        return proteins;
    }

    public void setProteins(Collection<Protein> proteins) {
        this.proteins = proteins;
    }

    public Collection<ProteinGroup> getProteinGroup() {
        return proteinGroup;
    }

    public void setProteinGroup(Collection<ProteinGroup> proteinGroup) {
        this.proteinGroup = proteinGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gene)) return false;

        Gene gene = (Gene) o;

        if (description != null ? !description.equals(gene.description) : gene.description != null) return false;
        if (!geneAccession.equals(gene.geneAccession)) return false;
        if (proteinGroup != null ? !proteinGroup.equals(gene.proteinGroup) : gene.proteinGroup != null) return false;
        if (proteins != null ? !proteins.equals(gene.proteins) : gene.proteins != null) return false;
        if (taxid != null ? !taxid.equals(gene.taxid) : gene.taxid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = geneAccession.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (taxid != null ? taxid.hashCode() : 0);
        result = 31 * result + (proteins != null ? proteins.hashCode() : 0);
        result = 31 * result + (proteinGroup != null ? proteinGroup.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Gene{" +
                "geneAccession='" + geneAccession + '\'' +
                ", description='" + description + '\'' +
                ", taxid=" + taxid +
                ", proteins=" + proteins +
                ", proteinGroup=" + proteinGroup +
                '}';
    }
}
