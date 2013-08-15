package uk.ac.ebi.pride.proteomes.db.core.api.peptide;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import uk.ac.ebi.pride.proteomes.db.core.api.assay.Assay;
import uk.ac.ebi.pride.proteomes.db.core.api.param.CvParamDisease;
import uk.ac.ebi.pride.proteomes.db.core.api.param.CvParamSample;
import uk.ac.ebi.pride.proteomes.db.core.api.param.CvParamTissue;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.Protein;
import uk.ac.ebi.pride.proteomes.db.core.api.quality.Score;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "PEPTIDE", schema = "PRIDEPROT")
@DiscriminatorColumn(name = "CANONICAL", discriminatorType = DiscriminatorType.STRING, length = 1)
public class Peptide {

    @Id
    @Column(name = "PEPTIDE_PK", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    private Integer peptideId;

    @Basic
    @Lob
    @Column(name = "SEQUENCE", nullable = false, insertable = true, updatable = true, length = 1000, precision = 0)
    private String sequence;

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 90, precision = 0)
    private String description;

    @Basic
    @NotNull
    @Type(type = "true_false")
    @Column(name = "CANONICAL", nullable = false, insertable = true, updatable = true, length = 1, precision = 0)
    private Boolean canonical;    //TODO: Need to be a bool

    @Basic
    @Column(name = "TAXID", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    private Integer taxid;

    @OneToMany(mappedBy = "peptide")
    private Collection<PeptideModification> peptideModifications;

    @ManyToMany(mappedBy = "peptides")
    @LazyCollection(LazyCollectionOption.FALSE)private Collection<Assay> assays;

    @ManyToMany
    @JoinTable(
            name = "PEP_CV",
            joinColumns = {@JoinColumn( name = "PEPTIDE_FK_PK" )},
            inverseJoinColumns = {@JoinColumn( name = "CV_PARAM_FK_PK" )}
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<CvParamSample> cvParamSamples;

    @ManyToMany
    @JoinTable(
            name = "PEP_CV",
            joinColumns = {@JoinColumn( name = "PEPTIDE_FK_PK" )},
            inverseJoinColumns = {@JoinColumn( name = "CV_PARAM_FK_PK" )}
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<CvParamDisease> cvParamDiseases;

    @ManyToMany
    @JoinTable(
            name = "PEP_CV",
            joinColumns = {@JoinColumn( name = "PEPTIDE_FK_PK" )},
            inverseJoinColumns = {@JoinColumn( name = "CV_PARAM_FK_PK" )}
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<CvParamTissue> cvParamTissues;


    @ManyToMany(mappedBy = "peptides")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Protein> proteins;

    @OneToOne
    @JoinColumn(name = "SCORE_FK", referencedColumnName = "SCORE_PK", nullable = false)
    private Score score;


    public Integer getPeptideId() {
        return peptideId;
    }

    public void setPeptideId(Integer peptidePk) {
        this.peptideId = peptidePk;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCanonical() {
        return canonical;
    }

    public void setCanonical(Boolean canonical) {
        this.canonical = canonical;
    }

    public Integer getTaxid() {
        return taxid;
    }

    public void setTaxid(Integer taxid) {
        this.taxid = taxid;
    }

    public Collection<Assay> getAssays() {
        return assays;
    }

    public void setAssays(Collection<Assay> pepAssaysByPeptidePk) {
        this.assays = pepAssaysByPeptidePk;
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


    public Collection<Protein> getProteins() {
        return proteins;
    }

    public void setProteins(Collection<Protein> proteins) {
        this.proteins = proteins;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Peptide)) return false;

        Peptide peptide = (Peptide) o;

        if (assays != null ? !assays.equals(peptide.assays) : peptide.assays != null) return false;
        if (canonical != null ? !canonical.equals(peptide.canonical) : peptide.canonical != null) return false;
        if (cvParamDiseases != null ? !cvParamDiseases.equals(peptide.cvParamDiseases) : peptide.cvParamDiseases != null)
            return false;
        if (cvParamSamples != null ? !cvParamSamples.equals(peptide.cvParamSamples) : peptide.cvParamSamples != null)
            return false;
        if (cvParamTissues != null ? !cvParamTissues.equals(peptide.cvParamTissues) : peptide.cvParamTissues != null)
            return false;
        if (description != null ? !description.equals(peptide.description) : peptide.description != null) return false;
        if (!peptideId.equals(peptide.peptideId)) return false;
        if (peptideModifications != null ? !peptideModifications.equals(peptide.peptideModifications) : peptide.peptideModifications != null)
            return false;
        if (proteins != null ? !proteins.equals(peptide.proteins) : peptide.proteins != null) return false;
        if (score != null ? !score.equals(peptide.score) : peptide.score != null) return false;
        if (sequence != null ? !sequence.equals(peptide.sequence) : peptide.sequence != null) return false;
        if (!taxid.equals(peptide.taxid)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = peptideId.hashCode();
        result = 31 * result + (sequence != null ? sequence.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (canonical != null ? canonical.hashCode() : 0);
        result = 31 * result + taxid.hashCode();
        result = 31 * result + (peptideModifications != null ? peptideModifications.hashCode() : 0);
        result = 31 * result + (assays != null ? assays.hashCode() : 0);
        result = 31 * result + (cvParamSamples != null ? cvParamSamples.hashCode() : 0);
        result = 31 * result + (cvParamDiseases != null ? cvParamDiseases.hashCode() : 0);
        result = 31 * result + (cvParamTissues != null ? cvParamTissues.hashCode() : 0);
        result = 31 * result + (proteins != null ? proteins.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Peptide{" +
                "peptideId=" + peptideId +
                ", sequence=" + sequence +
                ", description='" + description + '\'' +
                ", canonical='" + canonical + '\'' +
                ", taxid=" + taxid +
                ", peptideModifications=" + peptideModifications +
                ", assays=" + assays +
                ", cvParamSamples=" + cvParamSamples +
                ", cvParamDiseases=" + cvParamDiseases +
                ", cvParamTissues=" + cvParamTissues +
                ", proteins=" + proteins +
                ", score=" + score +
                '}';
    }
}
