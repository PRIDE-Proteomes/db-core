package uk.ac.ebi.pride.proteomes.db.core.api.assay;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Where;
import uk.ac.ebi.pride.proteomes.db.core.api.param.CellType;
import uk.ac.ebi.pride.proteomes.db.core.api.param.Disease;
import uk.ac.ebi.pride.proteomes.db.core.api.param.Tissue;
import uk.ac.ebi.pride.proteomes.db.core.api.reprocessed.Reprocessed;

import javax.persistence.*;
import java.util.Collection;
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

    @ManyToMany(targetEntity = CellType.class)
    @JoinTable(
            name = "ASSAY_CV", schema = "PRIDEPROT",
            joinColumns = {@JoinColumn( name = "ASSAY_FK_PK" )},
            inverseJoinColumns = {@JoinColumn( name = "CV_PARAM_FK_PK" )}
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    @Where(clause = "CV_TYPE = 'CELL_TYPE'")  //This is necessary :(
    private Set<CellType> cellTypes;

    @ManyToMany(targetEntity = Disease.class)
    @JoinTable(
            name = "ASSAY_CV", schema = "PRIDEPROT",
            joinColumns = {@JoinColumn( name = "ASSAY_FK_PK" )},
            inverseJoinColumns = {@JoinColumn( name = "CV_PARAM_FK_PK" )}
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    @Where(clause = "CV_TYPE = 'DISEASE'")  //This is necessary :(
    private Set<Disease> diseases;

    @ManyToMany(targetEntity = Tissue.class)
    @JoinTable(
            name = "ASSAY_CV", schema = "PRIDEPROT",
            joinColumns = {@JoinColumn( name = "ASSAY_FK_PK" )},
            inverseJoinColumns = {@JoinColumn( name = "CV_PARAM_FK_PK" )}
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    @Where(clause = "CV_TYPE = 'TISSUE'")  //This is necessary :(
    private Set<Tissue> tissues;

    @OneToMany(mappedBy = "assay")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Reprocessed> reprocessed;


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

//    public Collection<Peptide> getPeptides() {
//        return peptides;
//    }
//
//    public void setPeptides(Collection<Peptide> pepAssaysByAssayAccession) {
//        this.peptides = pepAssaysByAssayAccession;
//    }

    public Collection<Reprocessed> getReprocessed() {
        return reprocessed;
    }

    public void setReprocessed(Collection<Reprocessed> reprocessed) {
        this.reprocessed = reprocessed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Assay)) return false;

        Assay assay = (Assay) o;

        if (!assayAccession.equals(assay.assayAccession)) return false;
        if (diseases != null ? !diseases.equals(assay.diseases) : assay.diseases != null)
            return false;
        if (cellTypes != null ? !cellTypes.equals(assay.cellTypes) : assay.cellTypes != null)
            return false;
        if (tissues != null ? !tissues.equals(assay.tissues) : assay.tissues != null)
            return false;
//        if (peptides != null ? !peptides.equals(assay.peptides) : assay.peptides != null) return false;
        if (projectAccession != null ? !projectAccession.equals(assay.projectAccession) : assay.projectAccession != null)
            return false;
        if (reprocessed != null ? !reprocessed.equals(assay.reprocessed) : assay.reprocessed != null) return false;
        if (taxid != null ? !taxid.equals(assay.taxid) : assay.taxid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = assayAccession.hashCode();
        result = 31 * result + (projectAccession != null ? projectAccession.hashCode() : 0);
        result = 31 * result + (taxid != null ? taxid.hashCode() : 0);
        result = 31 * result + (cellTypes != null ? cellTypes.hashCode() : 0);
        result = 31 * result + (diseases != null ? diseases.hashCode() : 0);
        result = 31 * result + (tissues != null ? tissues.hashCode() : 0);
//        result = 31 * result + (peptides != null ? peptides.hashCode() : 0);
        result = 31 * result + (reprocessed != null ? reprocessed.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Assay{" +
                "assayAccession='" + assayAccession + '\'' +
                ", projectAccession='" + projectAccession + '\'' +
                ", taxid=" + taxid +
                ", cvParamSamples=" + cellTypes +
                ", diseases=" + diseases +
                ", tissues=" + tissues +
//                ", peptides=" + peptides +
                ", reprocessed=" + reprocessed +
                '}';
    }
}
