package uk.ac.ebi.pride.proteomes.db.core.api.protein;

import uk.ac.ebi.pride.proteomes.db.core.api.peptide.Peptide;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "PROT_PEP", schema = "PRIDEPROT")
@Entity
public class ReferencedPeptide {

    @Id
    @Column(name = "PROTEIN_FK_PK", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
    private Integer peptideId;

    @Id
    @Column(name = "PEPTIDE_FK_PK", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    private String proteinAccession;

    @Basic
    @Column(name = "START_POSITION", nullable = true, insertable = true, updatable = true, length = 22, precision = 0)
    private Integer startPosition;

    @Basic
    @Column(name = "TRYPTIC_SCORE", nullable = true, insertable = true, updatable = true, length = 22, precision = 0)
    private Integer trypticScore;

    @ManyToOne
    @JoinColumn(name = "PEPTIDE_FK_PK", referencedColumnName = "PEPTIDE_PK", nullable = false)
    private Peptide peptide;

    @ManyToOne
    @JoinColumn(name = "PROTEIN_FK_PK", referencedColumnName = "PROTEIN_ACCESSION", nullable = false)
    private Protein protein;

    public String getProteinAccession() {
        return proteinAccession;
    }

    public void setProteinAccession(String proteinFkPk) {
        this.proteinAccession = proteinFkPk;
    }

    public Integer getPeptideId() {
        return peptideId;
    }

    public void setPeptideId(Integer peptideFkPk) {
        this.peptideId = peptideFkPk;
    }


    public Integer getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Integer startPosition) {
        this.startPosition = startPosition;
    }

     public Integer getTrypticScore() {
        return trypticScore;
    }

    public void setTrypticScore(Integer trypticScore) {
        this.trypticScore = trypticScore;
    }

    public Peptide getPeptide() {
        return peptide;
    }

    public void setPeptide(Peptide peptideByPeptideFkPk) {
        this.peptide = peptideByPeptideFkPk;
    }

     public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein proteinByProteinFkPk) {
        this.protein = proteinByProteinFkPk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReferencedPeptide protPep = (ReferencedPeptide) o;

        if (peptideId != null ? !peptideId.equals(protPep.peptideId) : protPep.peptideId != null) return false;
        if (proteinAccession != null ? !proteinAccession.equals(protPep.proteinAccession) : protPep.proteinAccession != null) return false;
        if (startPosition != null ? !startPosition.equals(protPep.startPosition) : protPep.startPosition != null)
            return false;
        if (trypticScore != null ? !trypticScore.equals(protPep.trypticScore) : protPep.trypticScore != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = proteinAccession != null ? proteinAccession.hashCode() : 0;
        result = 31 * result + (peptideId != null ? peptideId.hashCode() : 0);
        result = 31 * result + (startPosition != null ? startPosition.hashCode() : 0);
        result = 31 * result + (trypticScore != null ? trypticScore.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ReferencedPeptide{" +
                "peptide=" + peptide +
                ", protein=" + protein +
                ", proteinAccession='" + proteinAccession + '\'' +
                ", peptideId=" + peptideId +
                ", startPosition=" + startPosition +
                ", trypticScore=" + trypticScore +
                '}';
    }
}
