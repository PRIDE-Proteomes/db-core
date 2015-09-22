package uk.ac.ebi.pride.proteomes.db.core.api.protein;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;
import uk.ac.ebi.pride.proteomes.db.core.api.feature.Feature;
import uk.ac.ebi.pride.proteomes.db.core.api.modification.ModificationLocation;
import uk.ac.ebi.pride.proteomes.db.core.api.param.CellType;
import uk.ac.ebi.pride.proteomes.db.core.api.param.Disease;
import uk.ac.ebi.pride.proteomes.db.core.api.param.Tissue;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.protein.PeptideProtein;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.EntryGroup;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.GeneGroup;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.ProteinGroup;
import uk.ac.ebi.pride.proteomes.db.core.api.quality.Score;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
@Entity
@Table(name = "PROTEIN", schema = "PRIDEPROT")
public class Protein {

    @Id
    @NotNull
    @Column(name = "PROTEIN_ID", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
    private String proteinAccession;

    @Basic
    @NotNull
    @Lob
    @Column(name = "SEQUENCE", nullable = false, insertable = true, updatable = true)
    private String sequence;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "CURATION_LEVEL", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    private CurationLevel curationLevel;

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 500, precision = 0)
    private String description;

    @Basic
    @NotNull
    @Column(name = "CONTAMINANT", nullable = false, insertable = true, updatable = true, length = 1, precision = 0)
    @Type(type="true_false")
    private Boolean contaminant;

    @Basic
    @NotNull
    @Column(name = "TAXID", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    private Integer taxid;

    @OrderColumn
    @ElementCollection(targetClass=ModificationLocation.class)
    @CollectionTable(
            name = "PROT_MOD", schema = "PRIDEPROT",
            joinColumns = @JoinColumn(name = "PROTEIN_ID", referencedColumnName = "PROTEIN_ID")
    )
    //The lazy loading in the modifications is necessary for the pipeline
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<ModificationLocation> modificationLocations;

    //Unidirectional relationship this side is the owner of the relationship
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name= "PROTEIN_ID" )
    private Set<Feature> features;

    //Unidirectional relationship
    @ManyToMany(targetEntity = CellType.class)
    @JoinTable(
            name = "PROT_CV", schema = "PRIDEPROT",
            joinColumns = {@JoinColumn(name = "PROTEIN_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CV_TERM")}
    )
    @LazyCollection(LazyCollectionOption.TRUE)
    @Where(clause = "CV_TYPE = 'CELL_TYPE'")  //This is necessary :(
    private Set<CellType> cellTypes;

    //Unidirectional relationship
    @ManyToMany(targetEntity = Disease.class)
    @JoinTable(
            name = "PROT_CV", schema = "PRIDEPROT",
            joinColumns = {@JoinColumn(name = "PROTEIN_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CV_TERM")}
    )
    @LazyCollection(LazyCollectionOption.TRUE)
    @Where(clause = "CV_TYPE = 'DISEASE'")  //This is necessary :(
    private Set<Disease> diseases;

    //Unidirectional relationship
    @ManyToMany(targetEntity = Tissue.class)
    @JoinTable(
            name = "PROT_CV", schema = "PRIDEPROT",
            joinColumns = {@JoinColumn(name = "PROTEIN_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CV_TERM")}
    )
    @LazyCollection(LazyCollectionOption.TRUE)
    @Where(clause = "CV_TYPE = 'TISSUE'") // This is necessary :(
    private Set<Tissue> tissues;

    @OneToMany(mappedBy = "protein")
	@LazyCollection(LazyCollectionOption.TRUE)
	private Set<PeptideProtein> peptides;

    @ManyToMany(mappedBy = "proteins")
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<ProteinGroup> proteinGroups;

    @ManyToMany(mappedBy = "entryProteins")
    @LazyCollection(LazyCollectionOption.TRUE)
    @Where(clause = "PROT_GROUP_TYPE = 'ENTRY'")  //This is necessary :(
    private Set<EntryGroup> entryGroups;

    @ManyToMany(mappedBy = "geneProteins")
    @LazyCollection(LazyCollectionOption.TRUE)
    @Where(clause = "PROT_GROUP_TYPE = 'GENE'")  //This is necessary :(
    private Set<GeneGroup> geneGroups;

    @OneToOne
    @JoinColumn(name = "SCORE_ID", referencedColumnName = "SCORE_ID")
    private Score score;

    public String getProteinAccession() {
        return proteinAccession;
    }

    public void setProteinAccession(String proteinAccession) {
        this.proteinAccession = proteinAccession;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public CurationLevel getCurationLevel() {
        return curationLevel;
    }

    public void setCurationLevel(CurationLevel level) {
        this.curationLevel = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isContaminant() {
        return contaminant;
    }

    public void setContaminant(Boolean contaminant) {
        this.contaminant = contaminant;
    }

    public Integer getTaxid() {
        return taxid;
    }

    public void setTaxid(Integer taxid) {
        this.taxid = taxid;
    }

    public Set<ModificationLocation> getModificationLocations() {
        return modificationLocations;
    }

    public void setModificationLocations(Set<ModificationLocation> modificationLocations) {
        this.modificationLocations = modificationLocations;
    }

    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }

    public Set<CellType> getCellTypes() {
        return cellTypes;
    }

    public void setCellTypes(Set<CellType> cellTypes) {
        this.cellTypes = cellTypes;
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

    public Set<PeptideProtein> getPeptides() {
		return peptides;
	}

	public void setPeptides(Set<PeptideProtein> peptideProteins) {
		this.peptides = peptideProteins;
	}

	public Set<ProteinGroup> getProteinGroups() {
        return proteinGroups;
    }

    public void setProteinGroups(Set<ProteinGroup> proteinGroups) {
        this.proteinGroups = proteinGroups;
    }

    public Set<EntryGroup> getEntryGroups() {
        return entryGroups;
    }

    public void setEntryGroups(Set<EntryGroup> entryGroups) {
        this.entryGroups = entryGroups;
    }

    public Set<GeneGroup> getGeneGroups() {
        return geneGroups;
    }

    public void setGeneGroups(Set<GeneGroup> geneGroups) {
        this.geneGroups = geneGroups;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score scoreByScoreFk) {
        this.score = scoreByScoreFk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Protein)) return false;
        Protein protein = (Protein) o;
        return Objects.equals(proteinAccession, protein.proteinAccession) &&
                Objects.equals(taxid, protein.taxid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proteinAccession, taxid);
    }

    @Override
    public String toString() {
        return "Protein{" +
                "proteinAccession='" + proteinAccession + '\'' +
                ", sequence='" + sequence + '\'' +
                ", description='" + description + '\'' +
                ", taxid=" + taxid +
                ", modificationLocations=" + modificationLocations +
                ", features=" + features +
//                ", peptides=" + peptides +
//                ", entryGroups=" + entryGroups +
//                ", geneGroups=" + geneGroups +
                ", score=" + score +
                '}';
    }

}
