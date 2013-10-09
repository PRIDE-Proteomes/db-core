package uk.ac.ebi.pride.proteomes.db.core.api.peptide;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Where;
import uk.ac.ebi.pride.proteomes.db.core.api.assay.Assay;
import uk.ac.ebi.pride.proteomes.db.core.api.param.CellType;
import uk.ac.ebi.pride.proteomes.db.core.api.param.Disease;
import uk.ac.ebi.pride.proteomes.db.core.api.param.Tissue;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.Protein;
import uk.ac.ebi.pride.proteomes.db.core.api.quality.Score;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */

//We split in two different subclasses the Peptide concept for simplicity when we want to retrieve only the
//symbolic peptides. Maybe in the future we prefer to remove the SymbolicPeptide class, add the discriminator
//value in Peptide and then customized a query for retrieving only the symbolic peptides
@Entity
@Table(name = "PEPTIDE", schema = "PRIDEPROT", uniqueConstraints = @UniqueConstraint(columnNames={"REPRESENTATION"}))
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "SYMBOLIC", discriminatorType = DiscriminatorType.STRING )
@SequenceGenerator(name="PEPTIDE_SEQ", schema = "PRIDEPROT", sequenceName="PRIDEPROT.PEPTIDE_PEPTIDE_PK_SEQ")
public abstract class Peptide implements Serializable {

    @Id
    @Column(name = "PEPTIDE_PK", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    @GeneratedValue(generator = "PEPTIDE_SEQ", strategy = GenerationType.SEQUENCE)
    private Long peptideId;

    @Basic
    @Column(name = "SEQUENCE", nullable = false, insertable = true, updatable = true, length = 1000, precision = 0)
    @NotNull     // validation constrain
    @Size(min = 6, max = 100)   // validation constrain
    @Pattern(regexp = "[GPAVLIMCFYWHKRQNEDST]{6,100}")  // validation constrain only valid amino acids
    private String sequence;

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 90, precision = 0)
    private String description;

    @Basic
    @Column(name = "TAXID", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    private Integer taxid;

    @NotNull     // validation constrain
    @Basic
    @Column(name = "REPRESENTATION", nullable = false, insertable = true, updatable = true, length = 1000, precision = 0)
    private String peptideRepresentation;


    //Unidirectional relationship
    @ManyToMany
    @JoinTable(
            name = "PEP_ASSAY", schema = "PRIDEPROT",
            joinColumns = {@JoinColumn( name = "PEPTIDE_FK_PK")},
            inverseJoinColumns = {@JoinColumn(name = "ASSAY_FK_PK")}
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Assay> assays;

    @ManyToMany(targetEntity = CellType.class, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "PEP_CV", schema ="PRIDEPROT" ,
            joinColumns = {@JoinColumn( name = "PEPTIDE_FK_PK")},
            inverseJoinColumns = {@JoinColumn( name = "CV_PARAM_FK_PK")}
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    @Where(clause = "CV_TYPE = 'CELL_TYPE'")  //This is necessary :(
    private Collection<CellType> cellTypes;

    @ManyToMany(targetEntity = Disease.class, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "PEP_CV", schema ="PRIDEPROT",
            joinColumns = {@JoinColumn( name = "PEPTIDE_FK_PK")},
            inverseJoinColumns = {@JoinColumn( name = "CV_PARAM_FK_PK")}
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    @Where(clause = "CV_TYPE = 'DISEASE'")  //This is necessary :(
    private Collection<Disease> diseases;

    @ManyToMany(targetEntity = Tissue.class, cascade = CascadeType.MERGE)
    @JoinTable(

            name = "PEP_CV", schema ="PRIDEPROT",
            joinColumns = {@JoinColumn( name = "PEPTIDE_FK_PK")},
            inverseJoinColumns = {@JoinColumn( name = "CV_PARAM_FK_PK")}
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    @Where(clause = "CV_TYPE = 'TISSUE'") // This is necessary :(
    private Collection<Tissue> tissues;


    @ManyToMany(mappedBy = "peptides")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Protein> proteins;

    @OneToOne
    @JoinColumn(name = "SCORE_FK", referencedColumnName = "SCORE_PK")
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

    public String getPeptideRepresentation() {
        return peptideRepresentation;
    }

    public void setPeptideRepresentation(String peptideRepresentation) {
        this.peptideRepresentation = peptideRepresentation;
    }

    public Collection<Assay> getAssays() {
        return assays;
    }

    public void setAssays(Collection<Assay> assays) {
        this.assays = assays;
    }

    public Collection<CellType> getCellTypes() {
        return cellTypes;
    }

    public void setCellTypes(Collection<CellType> cellType) {
        this.cellTypes = cellType;
    }

    public Collection<Tissue> getTissues() {
        return tissues;
    }

    public void setTissues(Collection<Tissue> tissues) {
        this.tissues = tissues;
    }

    public Collection<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(Collection<Disease> disease) {
        this.diseases = disease;
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
        if (o == null || getClass() != o.getClass()) return false;

        Peptide peptide = (Peptide) o;

        if (!peptideRepresentation.equals(peptide.peptideRepresentation)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return peptideRepresentation.hashCode();
    }

    @Override
    public String toString() {
        return "Peptide{" +
                "peptideId=" + peptideId +
                ", sequence='" + sequence + '\'' +
                ", description='" + description + '\'' +
                ", taxid=" + taxid +
                ", assays=" + assays +
                ", cellTypes=" + cellTypes +
                ", diseases=" + diseases +
                ", tissues=" + tissues +
                ", proteins=" + proteins +
                ", score=" + score +
                '}';
    }
}
