package uk.ac.ebi.pride.proteomes.db.core.api.peptide;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Where;
import uk.ac.ebi.pride.proteomes.db.core.api.assay.Assay;
import uk.ac.ebi.pride.proteomes.db.core.api.param.CvParamCellType;
import uk.ac.ebi.pride.proteomes.db.core.api.param.CvParamDisease;
import uk.ac.ebi.pride.proteomes.db.core.api.param.CvParamTissue;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.Protein;
import uk.ac.ebi.pride.proteomes.db.core.api.quality.Score;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */

//We split in two different subclasses the Peptide concept for simplicity if we want to retrieve only the
//symbolic peptides. Maybe in the future we prefer to remove the SymbolicPeptide class, add the discriminator
//value in Peptide and then customized a query for retrieving only the symbolic peptides
@Entity
@Table(name = "PEPTIDE", schema = "PRIDEPROT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "SYMBOLIC", discriminatorType = DiscriminatorType.STRING )
@SequenceGenerator(name="PEPTIDE_SEQ", schema = "PRIDEPROT", sequenceName="PRIDEPROT.PEPTIDE_PEPTIDE_PK_SEQ")
public abstract class Peptide {

    @Id
    @Column(name = "PEPTIDE_PK", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    @GeneratedValue(generator = "PEPTIDE_SEQ", strategy = GenerationType.SEQUENCE)
    private Long peptideId;

    @Basic
    @Lob
    @Column(name = "SEQUENCE", nullable = false, insertable = true, updatable = true, length = 1000, precision = 0)
    private String sequence;

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 90, precision = 0)
    private String description;

    @Basic
    @Column(name = "TAXID", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    private Integer taxid;

    //Unidirectional relationship
    @ManyToMany
    @JoinTable(
            name = "PEP_ASSAY", schema = "PRIDEPROT",
            joinColumns = {@JoinColumn( name = "PEPTIDE_FK_PK")},
            inverseJoinColumns = {@JoinColumn(name = "ASSAY_FK_PK")}
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Assay> assays;

    @ManyToMany(targetEntity = CvParamCellType.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "PEP_CV", schema ="PRIDEPROT" ,
            joinColumns = {@JoinColumn( name = "PEPTIDE_FK_PK")},
            inverseJoinColumns = {@JoinColumn( name = "CV_PARAM_FK_PK")}
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    @Where(clause = "CV_TYPE = 'CELL_TYPE'")  //This is necessary :(
    private Set<CvParamCellType> cvParamCellType;

    @ManyToMany(targetEntity = CvParamDisease.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "PEP_CV", schema ="PRIDEPROT",
            joinColumns = {@JoinColumn( name = "PEPTIDE_FK_PK")},
            inverseJoinColumns = {@JoinColumn( name = "CV_PARAM_FK_PK")}
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    @Where(clause = "CV_TYPE = 'DISEASE'")  //This is necessary :(
    private Set<CvParamDisease> cvParamDisease;

    @ManyToMany(targetEntity = CvParamTissue.class, cascade = CascadeType.ALL)
    @JoinTable(

            name = "PEP_CV", schema ="PRIDEPROT",
            joinColumns = {@JoinColumn( name = "PEPTIDE_FK_PK")},
            inverseJoinColumns = {@JoinColumn( name = "CV_PARAM_FK_PK")}
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    @Where(clause = "CV_TYPE = 'TISSUE'") // This is necessary :(
    private Set<CvParamTissue> cvParamTissue;


    @ManyToMany(mappedBy = "peptides")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Protein> proteins;

    @OneToOne
    @JoinColumn(name = "SCORE_FK", referencedColumnName = "SCORE_PK", nullable = false)
    private Score score;


    public Long getPeptideId() {
        return peptideId;
    }

    public void setPeptideId(Long peptidePk) {
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

    public Integer getTaxid() {
        return taxid;
    }

    public void setTaxid(Integer taxid) {
        this.taxid = taxid;
    }

    public Collection<Assay> getAssays() {
        return assays;
    }

    public void setAssays(Set<Assay> assays) {
        this.assays = assays;
    }

    public Set<CvParamCellType> getCvParamCellType() {
        return cvParamCellType;
    }

    public void setCvParamCellType(Set<CvParamCellType> cvParamCellType) {
        this.cvParamCellType = cvParamCellType;
    }

    public Set<CvParamTissue> getCvParamTissue() {
        return cvParamTissue;
    }

    public void setCvParamTissue(Set<CvParamTissue> cvParamTissues) {
        this.cvParamTissue = cvParamTissues;
    }

    public Set<CvParamDisease> getCvParamDisease() {
        return cvParamDisease;
    }

    public void setCvParamDisease(Set<CvParamDisease> cvParamDisease) {
        this.cvParamDisease = cvParamDisease;
    }

    public Set<Protein> getProteins() {
        return proteins;
    }

    public void setProteins(Set<Protein> proteins) {
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

//        if (assays != null ? !assays.equals(peptide.assays) : peptide.assays != null) return false;
        //TODO change the comparison of the collections
//        if (cvParamDisease != null ? !cvParamDisease.equals(peptide.cvParamDisease) : peptide.cvParamDisease != null)
//            return false;
//        if (cvParamCellType != null ? !cvParamCellType.equals(peptide.cvParamCellType) : peptide.cvParamCellType != null)
//            return false;
//        if (cvParamTissue != null ? !cvParamTissue.equals(peptide.cvParamTissue) : peptide.cvParamTissue != null)
//            return false;
//        if (description != null ? !description.equals(peptide.description) : peptide.description != null) return false;
        if (!peptideId.equals(peptide.peptideId)) return false;
//        if (peptideModifications != null ? !peptideModifications.equals(peptide.peptideModifications) : peptide.peptideModifications != null)
//            return false;
//        if (proteins != null ? !proteins.equals(peptide.proteins) : peptide.proteins != null) return false;
//        if (score != null ? !score.equals(peptide.score) : peptide.score != null) return false;
        if (sequence != null ? !sequence.equals(peptide.sequence) : peptide.sequence != null) return false;
        if (!taxid.equals(peptide.taxid)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = peptideId.hashCode();
        result = 31 * result + (sequence != null ? sequence.hashCode() : 0);
//        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + taxid.hashCode();
//        result = 31 * result + (peptideModifications != null ? peptideModifications.hashCode() : 0);
//        result = 31 * result + (assays != null ? assays.hashCode() : 0);
//        result = 31 * result + (cvParamCellType != null ? cvParamCellType.hashCode() : 0);
//        result = 31 * result + (cvParamDisease != null ? cvParamDisease.hashCode() : 0);
//        result = 31 * result + (cvParamTissue != null ? cvParamTissue.hashCode() : 0);
//        result = 31 * result + (proteins != null ? proteins.hashCode() : 0);
//        result = 31 * result + (score != null ? score.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Peptide{" +
                "peptideId=" + peptideId +
                ", sequence=" + sequence +
                ", description='" + description + '\'' +
                ", taxid=" + taxid +
//                ", peptideModifications=" + peptideModifications +
                ", assays=" + assays +
                ", cvParamCellType=" + cvParamCellType +
                ", cvParamDiseases=" + cvParamDisease +
                ", cvParamTissues=" + cvParamTissue +
                ", proteins=" + proteins +
                ", score=" + score +
                '}';
    }
}
