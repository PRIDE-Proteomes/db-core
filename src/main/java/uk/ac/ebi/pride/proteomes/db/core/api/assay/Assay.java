package uk.ac.ebi.pride.proteomes.db.core.api.assay;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.Peptide;
import uk.ac.ebi.pride.proteomes.db.core.api.reprocessed.Reprocessed;
import uk.ac.ebi.pride.proteomes.db.core.api.param.CvParamDisease;
import uk.ac.ebi.pride.proteomes.db.core.api.param.CvParamSample;
import uk.ac.ebi.pride.proteomes.db.core.api.param.CvParamTissue;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
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

    @ManyToMany
    @JoinTable(
            name = "ASSAY_CV",
            joinColumns = {@JoinColumn( name = "ASSAY_FK_PK" )},
            inverseJoinColumns = {@JoinColumn( name = "CV_PARAM_FK_PK" )}
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<CvParamSample> cvParamSamples;

    @ManyToMany
    @JoinTable(
            name = "ASSAY_CV",
            joinColumns = {@JoinColumn( name = "ASSAY_FK_PK" )},
            inverseJoinColumns = {@JoinColumn( name = "CV_PARAM_FK_PK" )}
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<CvParamDisease> cvParamDiseases;

    @ManyToMany
    @JoinTable(
            name = "ASSAY_CV",
            joinColumns = {@JoinColumn( name = "ASSAY_FK_PK" )},
            inverseJoinColumns = {@JoinColumn( name = "CV_PARAM_FK_PK" )}
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<CvParamTissue> cvParamTissues;

    @ManyToMany
    @JoinTable(
            name = "PEP_ASSAY",
            joinColumns = {@JoinColumn( name = "ASSAY_FK_PK")},
            inverseJoinColumns = {@JoinColumn(name = "PEPTIDE_FK_PK")}
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Peptide> peptides;

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

    public Collection<CvParamSample> getCvParamSamples() {
        return cvParamSamples;
    }

    public void setCvParamSamples(Collection<CvParamSample> cvParamSamples) {
        this.cvParamSamples = cvParamSamples;
    }

    public Collection<CvParamDisease> getCvParamDiseases() {
        return cvParamDiseases;
    }

    public void setCvParamDiseases(Collection<CvParamDisease> cvParamDiseases) {
        this.cvParamDiseases = cvParamDiseases;
    }

    public Collection<CvParamTissue> getCvParamTissues() {
        return cvParamTissues;
    }

    public void setCvParamTissues(Collection<CvParamTissue> cvParamTissues) {
        this.cvParamTissues = cvParamTissues;
    }

    public Collection<Peptide> getPeptides() {
        return peptides;
    }

    public void setPeptides(Collection<Peptide> pepAssaysByAssayAccession) {
        this.peptides = pepAssaysByAssayAccession;
    }

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
        if (cvParamDiseases != null ? !cvParamDiseases.equals(assay.cvParamDiseases) : assay.cvParamDiseases != null)
            return false;
        if (cvParamSamples != null ? !cvParamSamples.equals(assay.cvParamSamples) : assay.cvParamSamples != null)
            return false;
        if (cvParamTissues != null ? !cvParamTissues.equals(assay.cvParamTissues) : assay.cvParamTissues != null)
            return false;
        if (peptides != null ? !peptides.equals(assay.peptides) : assay.peptides != null) return false;
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
        result = 31 * result + (cvParamSamples != null ? cvParamSamples.hashCode() : 0);
        result = 31 * result + (cvParamDiseases != null ? cvParamDiseases.hashCode() : 0);
        result = 31 * result + (cvParamTissues != null ? cvParamTissues.hashCode() : 0);
        result = 31 * result + (peptides != null ? peptides.hashCode() : 0);
        result = 31 * result + (reprocessed != null ? reprocessed.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Assay{" +
                "assayAccession='" + assayAccession + '\'' +
                ", projectAccession='" + projectAccession + '\'' +
                ", taxid=" + taxid +
                ", cvParamSamples=" + cvParamSamples +
                ", cvParamDiseases=" + cvParamDiseases +
                ", cvParamTissues=" + cvParamTissues +
                ", peptides=" + peptides +
                ", reprocessed=" + reprocessed +
                '}';
    }
}
