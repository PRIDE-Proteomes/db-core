package uk.ac.ebi.pride.proteomes.db.core.api.peptide.protein;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * User: ntoro
 * Date: 06/11/2013
 * Time: 16:37
 */
@Embeddable
public class PeptideProteinPK implements Serializable {

    @Basic
    @NotNull
    @Column(name = "PEPTIDE_ID", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
    private Long peptideId;

    @Basic
    @NotNull
    @Column(name = "PROTEIN_ID", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    private String proteinAccession;

    @Basic
    @NotNull
    @Column(name = "START_POSITION", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    private Integer startPosition;

    public PeptideProteinPK() {
    }

    public PeptideProteinPK(Long peptideId, String proteinAccession, Integer startPosition) {
        setPeptideId(peptideId);
        setProteinAccession(proteinAccession);
        setStartPosition(startPosition);
    }

    public Long getPeptideId() {
        return peptideId;
    }

    public void setPeptideId(Long peptideId) {
        this.peptideId = peptideId;
    }

    public String getProteinAccession() {
        return proteinAccession;
    }

    public void setProteinAccession(String proteinAccession) {
        this.proteinAccession = proteinAccession;
    }

    public Integer getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Integer startPosition) {
        this.startPosition = startPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeptideProteinPK)) return false;
        PeptideProteinPK that = (PeptideProteinPK) o;
        return Objects.equals(peptideId, that.peptideId) &&
                Objects.equals(proteinAccession, that.proteinAccession) &&
                Objects.equals(startPosition, that.startPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(peptideId, proteinAccession, startPosition);
    }

    @Override
    public String toString() {
        return "PeptideProteinPK{" +
                "peptideId=" + peptideId +
                ", proteinAccession='" + proteinAccession + '\'' +
                ", startPosition=" + startPosition +
                '}';
    }
}
