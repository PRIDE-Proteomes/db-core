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
@Entity
@Table(name = "PROT_GROUP", schema = "PRIDEPROT")
@SequenceGenerator(name="PROT_GROUP_SEQ", schema = "PRIDEPROT", sequenceName="PRIDEPROT.PROT_GROUP_PROT_GROUP_PK_SEQ")
public class ProteinGroup {

    @Id
    @Column(name = "PROT_GROUP_PK", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    @GeneratedValue(generator = "PROT_GROUP_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 1000, precision = 0)
    @Basic
    private String description;

    @Basic
    @Column(name = "TAXID", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    private Integer taxid;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "PROT_GROUP_TYPE", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
    private ProteinGroupType type;

    @ManyToMany(mappedBy = "proteinGroups")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Protein> proteins;


    @ManyToMany(mappedBy = "proteinGroups")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Gene> genes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public ProteinGroupType getType() {
        return type;
    }

    public void setType(ProteinGroupType type) {
        this.type = type;
    }

    public Collection<Protein> getProteins() {
        return proteins;
    }

    public void setProteins(Collection<Protein> proteins) {
        this.proteins = proteins;
    }

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

        ProteinGroup that = (ProteinGroup) o;

        if (proteins != null ? !proteins.equals(that.proteins) : that.proteins != null) return false;
        if (type != that.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + (proteins != null ? proteins.hashCode() : 0);
        return result;
    }
}
