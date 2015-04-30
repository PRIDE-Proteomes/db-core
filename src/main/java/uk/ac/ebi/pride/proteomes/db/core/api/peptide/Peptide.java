package uk.ac.ebi.pride.proteomes.db.core.api.peptide;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Where;
import uk.ac.ebi.pride.proteomes.db.core.api.assay.Assay;
import uk.ac.ebi.pride.proteomes.db.core.api.cluster.Cluster;
import uk.ac.ebi.pride.proteomes.db.core.api.modification.ModificationLocation;
import uk.ac.ebi.pride.proteomes.db.core.api.param.CellType;
import uk.ac.ebi.pride.proteomes.db.core.api.param.Disease;
import uk.ac.ebi.pride.proteomes.db.core.api.param.Tissue;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.protein.PeptideProtein;
import uk.ac.ebi.pride.proteomes.db.core.api.quality.Score;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */

//We split in two different subclasses the Peptide concept for simplicity when we want to retrieve only the
//symbolic peptides. Maybe in the future we prefer to remove the SymbolicPeptide class, add the discriminator
//value in Peptide and then customized a query for retrieving only the symbolic peptides
@Entity
@Table(name = "PEPTIDE", schema = "PRIDEPROT", uniqueConstraints = @UniqueConstraint(columnNames = {"REPRESENTATION"}))
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "SYMBOLIC", discriminatorType = DiscriminatorType.STRING)
@SequenceGenerator(name = "PEPTIDE_SEQ", schema = "PRIDEPROT", sequenceName = "PRIDEPROT.PEPTIDE_PEPTIDE_ID_SEQ")
public abstract class Peptide implements Serializable {

	@Id
	@Column(name = "PEPTIDE_ID", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
	@GeneratedValue(generator = "PEPTIDE_SEQ", strategy = GenerationType.SEQUENCE)
	private Long peptideId;

	@Basic
	@Column(name = "SEQUENCE", nullable = false, insertable = true, updatable = true, length = 1000, precision = 0)
	@NotNull     // validation constrain
	@Size(min = 6, max = 100)   // validation constrain
	@Pattern(regexp = "[GPAVLIMCFYWHKRQNEDST]{6,100}")  // validation constrain only valid amino acids
	private String sequence;

    @Basic
    @Column(name = "MISSED_CLEAVAGES", nullable = true, insertable = true, updatable = true, length = 22 ,precision = 0)
    private Integer missedCleavages;

	@Basic
	@Column(name = "DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 90, precision = 0)
	private String description;

	@Basic
	@Column(name = "TAXID", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
	private Integer taxid;

	@NotNull     // validation constrain
	@Basic
	@Column(name = "REPRESENTATION", nullable = false, insertable = true, updatable = true, length = 1000, precision = 0, unique = true)
	private String peptideRepresentation;

    @OrderColumn
    @ElementCollection(targetClass=ModificationLocation.class)
    @CollectionTable(
            name = "PEP_MOD", schema = "PRIDEPROT",
            joinColumns = @JoinColumn(name = "PEPTIDE_ID", referencedColumnName = "PEPTIDE_ID")
    )
    @OrderBy("position ASC" )
    //The lazy loading in the modifications is necessary for the pipeline
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<ModificationLocation> modificationLocations;

    //Unidirectional relationship
    @ManyToMany(targetEntity = Assay.class, cascade = CascadeType.ALL)
    @JoinTable(
			name = "PEP_ASSAY", schema = "PRIDEPROT",
			joinColumns = {@JoinColumn(name = "PEPTIDE_ID")},
			inverseJoinColumns = {@JoinColumn(name = "ASSAY_ACCESSION")}
	)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Assay> assays;

    //Unidirectional relationship
	@ManyToMany(targetEntity = Cluster.class, cascade = CascadeType.ALL)
	@JoinTable(
			name = "PEP_CLUSTER", schema = "PRIDEPROT",
			joinColumns = {@JoinColumn(name = "PEPTIDE_ID")},
			inverseJoinColumns = {@JoinColumn(name = "CLUSTER_ID")}
	)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Cluster> clusters;

    //Unidirectional relationship
    @ManyToMany(targetEntity = CellType.class)
    @JoinTable(
			name = "PEP_CV", schema = "PRIDEPROT",
			joinColumns = {@JoinColumn(name = "PEPTIDE_ID")},
			inverseJoinColumns = {@JoinColumn(name = "CV_TERM")}
	)
	@LazyCollection(LazyCollectionOption.FALSE)
	@Where(clause = "CV_TYPE = 'CELL_TYPE'")  //This is necessary :(
	private Set<CellType> cellTypes;

    //Unidirectional relationship
    @ManyToMany(targetEntity = Disease.class)
    @JoinTable(
			name = "PEP_CV", schema = "PRIDEPROT",
			joinColumns = {@JoinColumn(name = "PEPTIDE_ID")},
			inverseJoinColumns = {@JoinColumn(name = "CV_TERM")}
	)
	@LazyCollection(LazyCollectionOption.FALSE)
	@Where(clause = "CV_TYPE = 'DISEASE'")  //This is necessary :(
	private Set<Disease> diseases;

    //Unidirectional relationship
    @ManyToMany(targetEntity = Tissue.class)
    @JoinTable(

			name = "PEP_CV", schema = "PRIDEPROT",
			joinColumns = {@JoinColumn(name = "PEPTIDE_ID")},
			inverseJoinColumns = {@JoinColumn(name = "CV_TERM")}
	)
	@LazyCollection(LazyCollectionOption.FALSE)
	@Where(clause = "CV_TYPE = 'TISSUE'") // This is necessary :(
	private Set<Tissue> tissues;

	@OneToMany(mappedBy = "peptide")
	@LazyCollection(LazyCollectionOption.TRUE)
	private Set<PeptideProtein> proteins;

    @OneToOne
    @JoinColumn(name = "SCORE_ID", referencedColumnName = "SCORE_ID")
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

    public Integer getMissedCleavages() {
        return missedCleavages;
    }

    public void setMissedCleavages(Integer missedCleavages) {
        this.missedCleavages = missedCleavages;
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

    public Set<ModificationLocation> getModificationLocations() {
        return modificationLocations;
    }

    public void setModificationLocations(SortedSet<ModificationLocation> modificationLocations) {
        this.modificationLocations = modificationLocations;
    }

    public Set<Assay> getAssays() {
		return assays;
	}

	public void setAssays(Set<Assay> assays) {
		this.assays = assays;
	}

	public Set<Cluster> getClusters() {
		return clusters;
	}

	public void setClusters(Set<Cluster> clusters) {
		this.clusters = clusters;
	}

	public Set<CellType> getCellTypes() {
		return cellTypes;
	}

	public void setCellTypes(Set<CellType> cellType) {
		this.cellTypes = cellType;
	}

	public Set<Tissue> getTissues() {
		return tissues;
	}

	public void setTissues(Set<Tissue> tissues) {
		this.tissues = tissues;
	}

	public Set<Disease> getDiseases() {
		return diseases;
	}

	public void setDiseases(Set<Disease> disease) {
		this.diseases = disease;
	}

	public Set<PeptideProtein> getProteins() {
		return proteins;
	}

	public void setProteins(Set<PeptideProtein> proteins) {
		this.proteins = proteins;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (o == null || getClass() != o.getClass()) return false;
//
//		Peptide peptide = (Peptide) o;
//
//		if (!peptideRepresentation.equals(peptide.peptideRepresentation)) return false;
//
//		return true;
//	}
//
//	@Override
//	public int hashCode() {
//		return peptideRepresentation.hashCode();
//	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Peptide)) return false;
		Peptide peptide = (Peptide) o;
		return Objects.equals(peptideRepresentation, peptide.peptideRepresentation);
	}

	@Override
	public int hashCode() {
		return Objects.hash(peptideRepresentation);
	}

	@Override
	public String toString() {
		return "Peptide{" +
				"peptideId=" + peptideId +
				", representation='" + peptideRepresentation + '\'' +
				", description='" + description + '\'' +
				", missedCleavages=" + missedCleavages +
				", modificationLocations=" + modificationLocations +
				", assays=" + assays +
				", clusters=" + clusters +
				", cellTypes=" + cellTypes +
				", diseases=" + diseases +
				", tissues=" + tissues +
				", proteins=" + proteins +
				", score=" + score +
				'}';
	}
}
