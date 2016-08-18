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
    private Long numPeptiforms;

    @Basic
    @Column(name = "NUM_PEPTIDES")
    private Long numPeptides;

    @Basic
    @Column(name = "NUM_PROTEINS")
    private Long numProteins;

    @Basic
    @Column(name = "NUM_ISOFORMS_PROTEINS")
    private Long numIsoformProteins;

    @Basic
    @Column(name = "NUM_CANONICAL_PROTEINS")
    private Long numCanonicalProteins;

    @Basic
    @Column(name = "NUM_GENES")
    private Long numGenes;

    @Basic
    @Column(name = "NUM_MAPPED_PROTEINS")
    private Long numMappedProteins;

    @Basic
    @Column(name = "NUM_MAPPED_CANONICAL_PROTEINS")
    private Long numMappedCanonicalProteins;

    @Basic
    @Column(name = "NUM_MAPPED_ISOFORM_PROTEINS")
    private Long numMappedIsoformProteins;

    @Basic
    @Column(name = "NUM_MAPPED_GENES")
    private Long numMappedGenes;

    @Basic
    @Column(name = "NUM_MAPPED_PEP_TO_PROT")
    private Long numMappedPeptidesToProteins;

    @Basic
    @Column(name = "NUM_MAPPED_UNIQ_PEP_TO_PROT")
    private Long numMappedUniquePeptidesToProteins;

    @Basic
    @Column(name = "NUM_MAPPED_UNIQ_PEP_TO_I_PROT")
    private Long numMappedUniquePeptidesToIsoformProteins;

    @Basic
    @Column(name = "NUM_MAPPED_UNIQ_PEP_TO_C_PROT")
    private Long numMappedUniquePeptidesToCanonicalProteins;

    @Basic
    @Column(name = "NUM_MAPPED_UNIQ_PEP_TO_GENES")
    private Long numMappedUniquePeptidesToGenes;

    @Basic
    @Column(name = "NUM_MAPPED_PROTS_W_UNIQ_PEPS")
    private Long numMappedProteinsWithUniquePeptides;

    @Basic
    @Column(name = "NUM_MAPPED_C_PROT_W_UNIQ_PEPS")
    private Long numMappedCanonicalProteinsWithUniquePeptides;

    @Basic
    @Column(name = "NUM_MAPPED_I_PROT_W_UNIQ_PEPS")
    private Long numMappedIsoformProteinsWithUniquePeptides;

    @Basic
    @Column(name = "NUM_MAPPED_GENES_W_UNIQ_PEPS")
    private Long numMappedGenesWithUniquePeptides;

    @Basic
    @Column(name = "NUM_MAPPED_PROTS_W_PE1")
    private Long numMappedProteinsWithExpEvidence;

    @Basic
    @Column(name = "NUM_MAPPED_PROTS_W_PE2")
    private Long numMappedProteinsWithExpEvidenceAtTranscript;

    @Basic
    @Column(name = "NUM_MAPPED_PROTS_W_PE3")
    private Long numMappedProteinsWithEvidenceInferredByHomology;

    @Basic
    @Column(name = "NUM_MAPPED_PROTS_W_PE4")
    private Long numMappedProteinWithEvidencePredicted;

    @Basic
    @Column(name = "NUM_MAPPED_PROTS_W_PE5")
    private Long numMappedProteinWithEvidenceUncertain;

    @Basic
    @Column(name = "NUM_MAPPED_PROTS_W_PE_NA")
    private Long numMappedProteinsWithEvidenceNotReported;

    @Basic
    @Column(name = "NUM_PROTS_W_PE1")
    private Long numProteinsWithExpEvidence;

    @Basic
    @Column(name = "NUM_PROTS_W_PE2")
    private Long numProteinsWithExpEvidenceAtTranscript;

    @Basic
    @Column(name = "NUM_PROTS_W_PE3")
    private Long numProteinsWithEvidenceInferredByHomology;

    @Basic
    @Column(name = "NUM_PROTS_W_PE4")
    private Long numProteinsWithEvidencePredicted;

    @Basic
    @Column(name = "NUM_PROTS_W_PE5")
    private Long numProteinsWithEvidenceUncertain;

    @Basic
    @Column(name = "NUM_PROTS_W_PE_NA")
    private Long numProteinsWithEvidenceNotReported;


    public ReleaseSummary() {
        setId(new ReleaseSummaryPK());
    }

    public ReleaseSummary(Integer taxid, String releaseDate) {
        setId(new ReleaseSummaryPK(taxid, releaseDate));
    }

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

    public Long getNumPeptiforms() {
        return numPeptiforms;
    }

    public void setNumPeptiforms(Long numPeptiforms) {
        this.numPeptiforms = numPeptiforms;
    }

    public Long getNumPeptides() {
        return numPeptides;
    }

    public void setNumPeptides(Long numPeptides) {
        this.numPeptides = numPeptides;
    }

    public Long getNumProteins() {
        return numProteins;
    }

    public void setNumProteins(Long numProteins) {
        this.numProteins = numProteins;
    }

    public Long getNumIsoformProteins() {
        return numIsoformProteins;
    }

    public void setNumIsoformProteins(Long numIsoformProteins) {
        this.numIsoformProteins = numIsoformProteins;
    }

    public Long getNumCanonicalProteins() {
        return numCanonicalProteins;
    }

    public void setNumCanonicalProteins(Long numCanonicalProteins) {
        this.numCanonicalProteins = numCanonicalProteins;
    }

    public Long getNumGenes() {
        return numGenes;
    }

    public void setNumGenes(Long numGenes) {
        this.numGenes = numGenes;
    }

    public Long getNumMappedProteins() {
        return numMappedProteins;
    }

    public void setNumMappedProteins(Long numMappedProteins) {
        this.numMappedProteins = numMappedProteins;
    }

    public Long getNumMappedCanonicalProteins() {
        return numMappedCanonicalProteins;
    }

    public void setNumMappedCanonicalProteins(Long numMappedCanonicalProteins) {
        this.numMappedCanonicalProteins = numMappedCanonicalProteins;
    }

    public Long getNumMappedIsoformProteins() {
        return numMappedIsoformProteins;
    }

    public void setNumMappedIsoformProteins(Long numMappedIsoformProteins) {
        this.numMappedIsoformProteins = numMappedIsoformProteins;
    }

    public Long getNumMappedGenes() {
        return numMappedGenes;
    }

    public void setNumMappedGenes(Long numMappedGenes) {
        this.numMappedGenes = numMappedGenes;
    }

    public Long getNumMappedPeptidesToProteins() {
        return numMappedPeptidesToProteins;
    }

    public void setNumMappedPeptidesToProteins(Long numMappedPeptidesToProteins) {
        this.numMappedPeptidesToProteins = numMappedPeptidesToProteins;
    }

    public Long getNumMappedUniquePeptidesToProteins() {
        return numMappedUniquePeptidesToProteins;
    }

    public void setNumMappedUniquePeptidesToProteins(Long numMappedUniquePeptidesToProteins) {
        this.numMappedUniquePeptidesToProteins = numMappedUniquePeptidesToProteins;
    }

    public Long getNumMappedUniquePeptidesToIsoformProteins() {
        return numMappedUniquePeptidesToIsoformProteins;
    }

    public void setNumMappedUniquePeptidesToIsoformProteins(Long numMappedUniquePeptidesToIsoformProteins) {
        this.numMappedUniquePeptidesToIsoformProteins = numMappedUniquePeptidesToIsoformProteins;
    }

    public Long getNumMappedUniquePeptidesToCanonicalProteins() {
        return numMappedUniquePeptidesToCanonicalProteins;
    }

    public void setNumMappedUniquePeptidesToCanonicalProteins(Long numMappedUniquePeptidesToCanonicalProteins) {
        this.numMappedUniquePeptidesToCanonicalProteins = numMappedUniquePeptidesToCanonicalProteins;
    }

    public Long getNumMappedUniquePeptidesToGenes() {
        return numMappedUniquePeptidesToGenes;
    }

    public void setNumMappedUniquePeptidesToGenes(Long numMappedUniquePeptidesToGenes) {
        this.numMappedUniquePeptidesToGenes = numMappedUniquePeptidesToGenes;
    }

    public Long getNumMappedProteinsWithUniquePeptides() {
        return numMappedProteinsWithUniquePeptides;
    }

    public void setNumMappedProteinsWithUniquePeptides(Long numMappedProteinsWithUniquePeptides) {
        this.numMappedProteinsWithUniquePeptides = numMappedProteinsWithUniquePeptides;
    }

    public Long getNumMappedCanonicalProteinsWithUniquePeptides() {
        return numMappedCanonicalProteinsWithUniquePeptides;
    }

    public void setNumMappedCanonicalProteinsWithUniquePeptides(Long numMappedCanonicalProteinsWithUniquePeptides) {
        this.numMappedCanonicalProteinsWithUniquePeptides = numMappedCanonicalProteinsWithUniquePeptides;
    }

    public Long getNumMappedIsoformProteinsWithUniquePeptides() {
        return numMappedIsoformProteinsWithUniquePeptides;
    }

    public void setNumMappedIsoformProteinsWithUniquePeptides(Long numMappedIsoformProteinsWithUniquePeptides) {
        this.numMappedIsoformProteinsWithUniquePeptides = numMappedIsoformProteinsWithUniquePeptides;
    }

    public Long getNumMappedGenesWithUniquePeptides() {
        return numMappedGenesWithUniquePeptides;
    }

    public void setNumMappedGenesWithUniquePeptides(Long numMappedGenesWithUniquePeptides) {
        this.numMappedGenesWithUniquePeptides = numMappedGenesWithUniquePeptides;
    }

    public Long getNumMappedProteinsWithExpEvidenceAtTranscript() {
        return numMappedProteinsWithExpEvidenceAtTranscript;
    }

    public void setNumMappedProteinsWithExpEvidenceAtTranscript(Long numMappedProteinsWithExpEvidenceAtTranscript) {
        this.numMappedProteinsWithExpEvidenceAtTranscript = numMappedProteinsWithExpEvidenceAtTranscript;
    }

    public Long getNumMappedProteinsWithEvidenceInferredByHomology() {
        return numMappedProteinsWithEvidenceInferredByHomology;
    }

    public void setNumMappedProteinsWithEvidenceInferredByHomology(Long numMappedProteinsWithEvidenceInferredByHomology) {
        this.numMappedProteinsWithEvidenceInferredByHomology = numMappedProteinsWithEvidenceInferredByHomology;
    }

    public Long getNumMappedProteinWithEvidencePredicted() {
        return numMappedProteinWithEvidencePredicted;
    }

    public void setNumMappedProteinWithEvidencePredicted(Long numMappedProteinWithEvidencePredicted) {
        this.numMappedProteinWithEvidencePredicted = numMappedProteinWithEvidencePredicted;
    }

    public Long getNumMappedProteinWithEvidenceUncertain() {
        return numMappedProteinWithEvidenceUncertain;
    }

    public void setNumMappedProteinWithEvidenceUncertain(Long numMappedProteinWithEvidenceUncertain) {
        this.numMappedProteinWithEvidenceUncertain = numMappedProteinWithEvidenceUncertain;
    }

    public Long getNumMappedProteinsWithExpEvidence() {
        return numMappedProteinsWithExpEvidence;
    }

    public void setNumMappedProteinsWithExpEvidence(Long numMappedProteinsWithExpEvidence) {
        this.numMappedProteinsWithExpEvidence = numMappedProteinsWithExpEvidence;
    }

    public Long getNumMappedProteinsWithEvidenceNotReported() {
        return numMappedProteinsWithEvidenceNotReported;
    }

    public void setNumMappedProteinsWithEvidenceNotReported(Long numMappedProteinsWithEvidenceNotReported) {
        this.numMappedProteinsWithEvidenceNotReported = numMappedProteinsWithEvidenceNotReported;
    }

    public Long getNumProteinsWithExpEvidence() {
        return numProteinsWithExpEvidence;
    }

    public void setNumProteinsWithExpEvidence(Long numProteinsWithExpEvidence) {
        this.numProteinsWithExpEvidence = numProteinsWithExpEvidence;
    }

    public Long getNumProteinsWithExpEvidenceAtTranscript() {
        return numProteinsWithExpEvidenceAtTranscript;
    }

    public void setNumProteinsWithExpEvidenceAtTranscript(Long numProteinsWithExpEvidenceAtTranscript) {
        this.numProteinsWithExpEvidenceAtTranscript = numProteinsWithExpEvidenceAtTranscript;
    }

    public Long getNumProteinsWithEvidenceInferredByHomology() {
        return numProteinsWithEvidenceInferredByHomology;
    }

    public void setNumProteinsWithEvidenceInferredByHomology(Long numProteinsWithEvidenceInferredByHomology) {
        this.numProteinsWithEvidenceInferredByHomology = numProteinsWithEvidenceInferredByHomology;
    }

    public Long getNumProteinsWithEvidencePredicted() {
        return numProteinsWithEvidencePredicted;
    }

    public void setNumProteinsWithEvidencePredicted(Long numProteinsWithEvidencePredicted) {
        this.numProteinsWithEvidencePredicted = numProteinsWithEvidencePredicted;
    }

    public Long getNumProteinsWithEvidenceUncertain() {
        return numProteinsWithEvidenceUncertain;
    }

    public void setNumProteinsWithEvidenceUncertain(Long numProteinsWithEvidenceUncertain) {
        this.numProteinsWithEvidenceUncertain = numProteinsWithEvidenceUncertain;
    }

    public Long getNumProteinsWithEvidenceNotReported() {
        return numProteinsWithEvidenceNotReported;
    }

    public void setNumProteinsWithEvidenceNotReported(Long numProteinsWithEvidenceNotReported) {
        this.numProteinsWithEvidenceNotReported = numProteinsWithEvidenceNotReported;
    }
}
