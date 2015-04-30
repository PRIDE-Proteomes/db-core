package uk.ac.ebi.pride.proteomes.db.core.api.assay;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Where;
import uk.ac.ebi.pride.proteomes.db.core.api.param.CellType;
import uk.ac.ebi.pride.proteomes.db.core.api.param.Disease;
import uk.ac.ebi.pride.proteomes.db.core.api.param.Tissue;
import uk.ac.ebi.pride.proteomes.db.core.api.reprocessed.Reprocessed;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
@Table(name = "ASSAY", schema = "PRIDEPROT")
@Entity
public class Assay {

    @Id
    @Column(name = "ASSAY_ACCESSION", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
    private String assayAccession;

    @Basic
    @Column(name = "PROJECT_ACCESSION", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
    private String projectAccession;

    @Basic
    @Column(name = "TAXID", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    private Integer taxid;

    //Unidirectional relationship
    @ManyToMany(targetEntity = CellType.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "ASSAY_CV", schema = "PRIDEPROT",
            joinColumns = @JoinColumn( name = "ASSAY_ACCESSION" ),
            inverseJoinColumns = @JoinColumn( name = "CV_TERM" )
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    @Where(clause = "CV_TYPE = 'CELL_TYPE'")  //This is necessary :(
    private Set<CellType> cellTypes;

    //Unidirectional relationship
    @ManyToMany(targetEntity = Disease.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "ASSAY_CV", schema = "PRIDEPROT",
            joinColumns = @JoinColumn( name = "ASSAY_ACCESSION" ),
            inverseJoinColumns = @JoinColumn( name = "CV_TERM" )
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    @Where(clause = "CV_TYPE = 'DISEASE'")  //This is necessary :(
    private Set<Disease> diseases;

    //Unidirectional relationship
    @ManyToMany(targetEntity = Tissue.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "ASSAY_CV", schema = "PRIDEPROT",
            joinColumns = @JoinColumn( name = "ASSAY_ACCESSION" ),
            inverseJoinColumns = @JoinColumn( name = "CV_TERM" )
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    @Where(clause = "CV_TYPE = 'TISSUE'")  //This is necessary :(
    private Set<Tissue> tissues;

    @ElementCollection
    @CollectionTable(
            name = "REPRO", schema = "PRIDEPROT",
            joinColumns = @JoinColumn(name = "ASSAY_ACCESSION", referencedColumnName = "ASSAY_ACCESSION")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Reprocessed> reprocessed;

    public String getAssayAccession() {
        return assayAccession;
    }

    public void setAssayAccession(String assayAccession) {
        this.assayAccession = assayAccession;
    }

    public String getProjectAccession() {
        return projectAccession;
    }

    public void setProjectAccession(String projectAccession) {
        this.projectAccession = projectAccession;
    }

    public Integer getTaxid() {
        return taxid;
    }

    public void setTaxid(Integer taxid) {
        this.taxid = taxid;
    }

    public Set<CellType> getCellTypes() {
        return cellTypes;
    }

    public void setCellTypes(Set<CellType> cvParamSamples) {
        this.cellTypes = cvParamSamples;
    }

    public Set<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(Set<Disease> diseases) {
        this.diseases = diseases;
    }

    public Set<Tissue> getTissues() {
        return tissues;
    }

    public void setTissues(Set<Tissue> tissues) {
        this.tissues = tissues;
    }

    public Set<Reprocessed> getReprocessed() {
        return reprocessed;
    }

    public void setReprocessed(Set<Reprocessed> reprocessed) {
        this.reprocessed = reprocessed;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Assay assay = (Assay) o;
//
//        if (!assayAccession.equals(assay.assayAccession)) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = assayAccession.hashCode();
//        return result;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Assay)) return false;
        Assay assay = (Assay) o;
        return Objects.equals(assayAccession, assay.assayAccession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assayAccession);
    }

    @Override
    public String toString() {
        return "Assay{" +
                "assayAccession='" + assayAccession + '\'' +
                ", projectAccession='" + projectAccession + '\'' +
                ", taxid=" + taxid +
                ", cellTypes=" + cellTypes +
                ", diseases=" + diseases +
                ", tissues=" + tissues +
                ", reprocessed=" + reprocessed +
                '}';
    }
}
