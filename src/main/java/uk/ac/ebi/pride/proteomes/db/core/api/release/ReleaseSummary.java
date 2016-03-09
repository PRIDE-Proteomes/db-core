package uk.ac.ebi.pride.proteomes.db.core.api.release;

import javax.persistence.*;

/**
 * @author ntoro
 * @since 07/03/2016 16:55
 */
@Entity
@Table(name = "RELEASE_SUM", schema = "PRIDEPROT")
public class ReleaseSummary {

    @EmbeddedId
    private ReleaseSummaryPK id;

    @Basic
    @Column(name = "REFERENCE_DATABASE")
    private String referenceDatabase;

    @Basic
    @Column(name = "REFERENCE_DATABASE_VERSION")
    private String referenceDatabaseVersion;

    @Basic
    @Column(name = "NUM_PEPTIFORMS")
    private Integer numPeptiforms;

    @Basic
    @Column(name = "NUM_PEPTIDES")
    private Integer numPeptides;

    @Basic
    @Column(name = "NUM_PROTEINS")
    private Integer numProteins;

    @Basic
    @Column(name = "NUM_ISOFORMS_PROTEINS")
    private Integer numIsoformProteins;

    @Basic
    @Column(name = "NUM_CANONICAL_PROTEINS")
    private Integer numCanonicalProteins;

    @Basic
    @Column(name = "NUM_GENES")
    private Integer numGenes;

    @Basic
    @Column(name = "NUM_MAPPED_PROTEINS")
    private Integer numMappedProteins;

    @Basic
    @Column(name = "NUM_MAPPED_CANONICAL_PROTEINS")
    private Integer numMappedCanonicalProteins;

    @Basic
    @Column(name = "NUM_MAPPED_ISOFORM_PROTEINS")
    private Integer numMappedIsoformProteins;

    @Basic
    @Column(name = "NUM_MAPPED_GENES")
    private Integer numMappedGenes;

    @Basic
    @Column(name = "NUM_MAPPED_PEP_TO_PROT")
    private Integer numMappedPeptidesToProteins;

    @Basic
    @Column(name = "NUM_MAPPED_UNIQ_PEP_TO_PROT")
    private Integer numMappedUniquePeptidesToProteins;

    @Basic
    @Column(name = "NUM_MAPPED_UNIQ_PEP_TO_I_PROT")
    private Integer numMappedUniquePeptidesToIsoformProteins;

    @Basic
    @Column(name = "NUM_MAPPED_UNIQ_PEP_TO_C_PROT")
    private Integer numMappedUniquePeptidesToCanonicalProteins;

    @Basic
    @Column(name = "NUM_MAPPED_UNIQ_PEP_TO_GENES")
    private Integer numMappedUniquePeptidesToGenes;

    @Basic
    @Column(name = "NUM_MAPPED_C_PROT_W_UNIQ_PEPS")
    private Integer numMappedCanonicalProteinsWithUniquePeptides;

    @Basic
    @Column(name = "NUM_MAPPED_I_PROT_W_UNIQ_PEPS")
    private Integer numMappedIsoformProteinsWithUniquePeptides;

    @Basic
    @Column(name = "NUM_MAPPED_GENES_W_UNIQ_PEPS")
    private Integer numMappedGenesWithUniquePeptides;

    @Basic
    @Column(name = "NUM_MAPPED_PROTS_W_PE2")
    private Integer numMappedProteinsWithExpEvidenceAtTranscript;

    @Basic
    @Column(name = "NUM_MAPPED_PROTS_W_PE3")
    private Integer numMappedProteinsWithEvidenceInferredByHomology;

    @Basic
    @Column(name = "NUM_MAPPED_PROTS_W_PE4")
    private Integer numMappedProteinWithEvidencePredicted;

    @Basic
    @Column(name = "NUM_MAPPED_PROTS_W_PE5")
    private Integer numMappedProteinWithEvidenceUncertain;

    public ReleaseSummaryPK getId() {
        return id;
    }

    public void setId(ReleaseSummaryPK id) {
        this.id = id;
    }

    public String getReferenceDatabase() {
        return referenceDatabase;
    }

    public void setReferenceDatabase(String referenceDatabase) {
        this.referenceDatabase = referenceDatabase;
    }

    public String getReferenceDatabaseVersion() {
        return referenceDatabaseVersion;
    }

    public void setReferenceDatabaseVersion(String referenceDatabaseVersion) {
        this.referenceDatabaseVersion = referenceDatabaseVersion;
    }

    public Integer getNumPeptiforms() {
        return numPeptiforms;
    }

    public void setNumPeptiforms(Integer numPeptiforms) {
        this.numPeptiforms = numPeptiforms;
    }

    public Integer getNumPeptides() {
        return numPeptides;
    }

    public void setNumPeptides(Integer numPeptides) {
        this.numPeptides = numPeptides;
    }

    public Integer getNumProteins() {
        return numProteins;
    }

    public void setNumProteins(Integer numProteins) {
        this.numProteins = numProteins;
    }

    public Integer getNumIsoformProteins() {
        return numIsoformProteins;
    }

    public void setNumIsoformProteins(Integer numIsoformProteins) {
        this.numIsoformProteins = numIsoformProteins;
    }

    public Integer getNumCanonicalProteins() {
        return numCanonicalProteins;
    }

    public void setNumCanonicalProteins(Integer numCanonicalProteins) {
        this.numCanonicalProteins = numCanonicalProteins;
    }

    public Integer getNumGenes() {
        return numGenes;
    }

    public void setNumGenes(Integer numGenes) {
        this.numGenes = numGenes;
    }

    public Integer getNumMappedProteins() {
        return numMappedProteins;
    }

    public void setNumMappedProteins(Integer numMappedProteins) {
        this.numMappedProteins = numMappedProteins;
    }

    public Integer getNumMappedCanonicalProteins() {
        return numMappedCanonicalProteins;
    }

    public void setNumMappedCanonicalProteins(Integer numMappedCanonicalProteins) {
        this.numMappedCanonicalProteins = numMappedCanonicalProteins;
    }

    public Integer getNumMappedIsoformProteins() {
        return numMappedIsoformProteins;
    }

    public void setNumMappedIsoformProteins(Integer numMappedIsoformProteins) {
        this.numMappedIsoformProteins = numMappedIsoformProteins;
    }

    public Integer getNumMappedGenes() {
        return numMappedGenes;
    }

    public void setNumMappedGenes(Integer numMappedGenes) {
        this.numMappedGenes = numMappedGenes;
    }

    public Integer getNumMappedPeptidesToProteins() {
        return numMappedPeptidesToProteins;
    }

    public void setNumMappedPeptidesToProteins(Integer numMappedPeptidesToProteins) {
        this.numMappedPeptidesToProteins = numMappedPeptidesToProteins;
    }

    public Integer getNumMappedUniquePeptidesToProteins() {
        return numMappedUniquePeptidesToProteins;
    }

    public void setNumMappedUniquePeptidesToProteins(Integer numMappedUniquePeptidesToProteins) {
        this.numMappedUniquePeptidesToProteins = numMappedUniquePeptidesToProteins;
    }

    public Integer getNumMappedUniquePeptidesToIsoformProteins() {
        return numMappedUniquePeptidesToIsoformProteins;
    }

    public void setNumMappedUniquePeptidesToIsoformProteins(Integer numMappedUniquePeptidesToIsoformProteins) {
        this.numMappedUniquePeptidesToIsoformProteins = numMappedUniquePeptidesToIsoformProteins;
    }

    public Integer getNumMappedUniquePeptidesToCanonicalProteins() {
        return numMappedUniquePeptidesToCanonicalProteins;
    }

    public void setNumMappedUniquePeptidesToCanonicalProteins(Integer numMappedUniquePeptidesToCanonicalProteins) {
        this.numMappedUniquePeptidesToCanonicalProteins = numMappedUniquePeptidesToCanonicalProteins;
    }

    public Integer getNumMappedUniquePeptidesToGenes() {
        return numMappedUniquePeptidesToGenes;
    }

    public void setNumMappedUniquePeptidesToGenes(Integer numMappedUniquePeptidesToGenes) {
        this.numMappedUniquePeptidesToGenes = numMappedUniquePeptidesToGenes;
    }

    public Integer getNumMappedCanonicalProteinsWithUniquePeptides() {
        return numMappedCanonicalProteinsWithUniquePeptides;
    }

    public void setNumMappedCanonicalProteinsWithUniquePeptides(Integer numMappedCanonicalProteinsWithUniquePeptides) {
        this.numMappedCanonicalProteinsWithUniquePeptides = numMappedCanonicalProteinsWithUniquePeptides;
    }

    public Integer getNumMappedIsoformProteinsWithUniquePeptides() {
        return numMappedIsoformProteinsWithUniquePeptides;
    }

    public void setNumMappedIsoformProteinsWithUniquePeptides(Integer numMappedIsoformProteinsWithUniquePeptides) {
        this.numMappedIsoformProteinsWithUniquePeptides = numMappedIsoformProteinsWithUniquePeptides;
    }

    public Integer getNumMappedGenesWithUniquePeptides() {
        return numMappedGenesWithUniquePeptides;
    }

    public void setNumMappedGenesWithUniquePeptides(Integer numMappedGenesWithUniquePeptides) {
        this.numMappedGenesWithUniquePeptides = numMappedGenesWithUniquePeptides;
    }

    public Integer getNumMappedProteinsWithExpEvidenceAtTranscript() {
        return numMappedProteinsWithExpEvidenceAtTranscript;
    }

    public void setNumMappedProteinsWithExpEvidenceAtTranscript(Integer numMappedProteinsWithExpEvidenceAtTranscript) {
        this.numMappedProteinsWithExpEvidenceAtTranscript = numMappedProteinsWithExpEvidenceAtTranscript;
    }

    public Integer getNumMappedProteinsWithEvidenceInferredByHomology() {
        return numMappedProteinsWithEvidenceInferredByHomology;
    }

    public void setNumMappedProteinsWithEvidenceInferredByHomology(Integer numMappedProteinsWithEvidenceInferredByHomology) {
        this.numMappedProteinsWithEvidenceInferredByHomology = numMappedProteinsWithEvidenceInferredByHomology;
    }

    public Integer getNumMappedProteinWithEvidencePredicted() {
        return numMappedProteinWithEvidencePredicted;
    }

    public void setNumMappedProteinWithEvidencePredicted(Integer numMappedProteinWithEvidencePredicted) {
        this.numMappedProteinWithEvidencePredicted = numMappedProteinWithEvidencePredicted;
    }

    public Integer getNumMappedProteinWithEvidenceUncertain() {
        return numMappedProteinWithEvidenceUncertain;
    }

    public void setNumMappedProteinWithEvidenceUncertain(Integer numMappedProteinWithEvidenceUncertain) {
        this.numMappedProteinWithEvidenceUncertain = numMappedProteinWithEvidenceUncertain;
    }
}
