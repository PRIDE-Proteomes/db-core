package uk.ac.ebi.pride.proteomes.db.core.api.protein;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Where;
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
import java.util.Collection;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
@Entity
@Table(name = "PROTEIN", schema = "PRIDEPROT")
public class Protein {

    @Id
    @Column(name = "PROTEIN_ID", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
    private String proteinAccession;

    @Basic
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
    private Collection<ModificationLocation> modificationLocations;

    @ManyToMany(targetEntity = CellType.class)
    @JoinTable(
            name = "PROT_CV", schema = "PRIDEPROT",
            joinColumns = {@JoinColumn(name = "PROTEIN_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CV_TERM")}
    )
    @LazyCollection(LazyCollectionOption.TRUE)
    @Where(clause = "CV_TYPE = 'CELL_TYPE'")  //This is necessary :(
    private Collection<CellType> cellTypes;

    @ManyToMany(targetEntity = Disease.class)
    @JoinTable(
            name = "PROT_CV", schema = "PRIDEPROT",
            joinColumns = {@JoinColumn(name = "PROTEIN_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CV_TERM")}
    )
    @LazyCollection(LazyCollectionOption.TRUE)
    @Where(clause = "CV_TYPE = 'DISEASE'")  //This is necessary :(
    private Collection<Disease> diseases;

    @ManyToMany(targetEntity = Tissue.class)
    @JoinTable(

            name = "PROT_CV", schema = "PRIDEPROT",
            joinColumns = {@JoinColumn(name = "PROTEIN_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CV_TERM")}
    )
    @LazyCollection(LazyCollectionOption.TRUE)
    @Where(clause = "CV_TYPE = 'TISSUE'") // This is necessary :(
    private Collection<Tissue> tissues;

	@OneToMany(mappedBy = "protein")
	@LazyCollection(LazyCollectionOption.TRUE)
	private Collection<PeptideProtein> peptides;

    @ManyToMany(mappedBy = "proteins")
    @LazyCollection(LazyCollectionOption.TRUE)
    private Collection<ProteinGroup> proteinGroups;

    @ManyToMany(mappedBy = "entryProteins")
    @LazyCollection(LazyCollectionOption.TRUE)
    @Where(clause = "PROT_GROUP_TYPE = 'ENTRY'")  //This is necessary :(
    private Collection<EntryGroup> entryGroups;

    @ManyToMany(mappedBy = "geneProteins")
    @LazyCollection(LazyCollectionOption.TRUE)
    @Where(clause = "PROT_GROUP_TYPE = 'GENE'")  //This is necessary :(
    private Collection<GeneGroup> geneGroups;

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

    public Integer getTaxid() {
        return taxid;
    }

    public void setTaxid(Integer taxid) {
        this.taxid = taxid;
    }

    public Collection<ModificationLocation> getModificationLocations() {
        return modificationLocations;
    }

    public void setModificationLocations(Collection<ModificationLocation> modificationLocations) {
        this.modificationLocations = modificationLocations;
    }

    public Collection<CellType> getCellTypes() {
        return cellTypes;
    }

    public void setCellTypes(Collection<CellType> cellTypes) {
        this.cellTypes = cellTypes;
    }

    public Collection<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(Collection<Disease> diseases) {
        this.diseases = diseases;
    }

    public Collection<Tissue> getTissues() {
        return tissues;
    }

    public void setTissues(Collection<Tissue> tissues) {
        this.tissues = tissues;
    }

    public Collection<PeptideProtein> getPeptides() {
		return peptides;
	}

	public void setPeptides(Collection<PeptideProtein> peptideProteins) {
		this.peptides = peptideProteins;
	}

	public Collection<ProteinGroup> getProteinGroups() {
        return proteinGroups;
    }

    public void setProteinGroups(Collection<ProteinGroup> proteinGroups) {
        this.proteinGroups = proteinGroups;
    }

    public Collection<EntryGroup> getEntryGroups() {
        return entryGroups;
    }

    public void setEntryGroups(Collection<EntryGroup> entryGroups) {
        this.entryGroups = entryGroups;
    }

    public Collection<GeneGroup> getGeneGroups() {
        return geneGroups;
    }

    public void setGeneGroups(Collection<GeneGroup> geneGroups) {
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
        if (o == null || getClass() != o.getClass()) return false;

        Protein protein = (Protein) o;

        if (!proteinAccession.equals(protein.proteinAccession)) return false;
        if (!taxid.equals(protein.taxid)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = proteinAccession.hashCode();
        result = 31 * result + taxid.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Protein{" +
                "proteinAccession='" + proteinAccession + '\'' +
                ", sequence='" + sequence + '\'' +
                ", description='" + description + '\'' +
                ", taxid=" + taxid +
                ", modificationLocations=" + modificationLocations +
//                ", peptides=" + peptides +
//                ", entryGroups=" + entryGroups +
//                ", geneGroups=" + geneGroups +
                ", score=" + score +
                '}';
    }

}
