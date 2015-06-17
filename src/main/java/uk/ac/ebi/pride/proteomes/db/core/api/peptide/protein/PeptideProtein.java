package uk.ac.ebi.pride.proteomes.db.core.api.peptide.protein;

import uk.ac.ebi.pride.proteomes.db.core.api.peptide.Peptide;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.Protein;
import uk.ac.ebi.pride.proteomes.db.core.api.quality.Score;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
@Entity
@Table(name = "PEP_PROT", schema = "PRIDEPROT")
public class PeptideProtein implements Serializable {

    @EmbeddedId
    private PeptideProteinPK id;

    @Basic
    @Column(name = "NTERM_DEGRADATION", nullable = true, insertable = true, updatable = true, length = 22, precision = 0)
    private Integer nTermDegradation;

    @Basic
    @Column(name = "CTERM_DEGRADATION", nullable = true, insertable = true, updatable = true, length = 22, precision = 0)
    private Integer cTermDegradation;

    @Basic
    @Column(name = "UNIQUENESS", nullable = true, insertable = true, updatable = true, length = 22, precision = 0)
    private Integer uniqueness;

    @ManyToOne
    @JoinColumn(name = "PEPTIDE_ID", referencedColumnName = "PEPTIDE_ID", nullable = false, insertable = false, updatable = false)
    private Peptide peptide;

    @ManyToOne
    @JoinColumn(name = "PROTEIN_ID", referencedColumnName = "PROTEIN_ID", nullable = false, insertable = false, updatable = false)
    private Protein protein;

    @OneToOne
    @JoinColumn(name = "SCORE_ID", referencedColumnName = "SCORE_ID")
    private Score score;

    public PeptideProtein() {
        setId(new PeptideProteinPK());
    }

    public PeptideProtein(Long peptideId, String proteinAccession, Integer startPosition) {
        setId(new PeptideProteinPK(peptideId, proteinAccession, startPosition));
    }

    public PeptideProteinPK getId() {
        return id;
    }

    public void setId(PeptideProteinPK id) {
        this.id = id;
    }

    public Peptide getPeptide() {
        return peptide;
    }

    public void setPeptide(Peptide peptide) {
        this.peptide = peptide;
    }

    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein proteinByProteinFkPk) {
        this.protein = proteinByProteinFkPk;
    }

    public Long getPeptideId() {
        return id.getPeptideId();
    }

    public void setPeptideId(Long peptideId) {
        id.setPeptideId(peptideId);
    }

    public String getProteinAccession() {
        return id.getProteinAccession();
    }

    public void setProteinAccession(String proteinAccession) {
        id.setProteinAccession(proteinAccession);
    }

    public Integer getStartPosition() {
        return id.getStartPosition();
    }

    public void setStartPosition(Integer startPosition) {
        id.setStartPosition(startPosition);
    }

    public Integer getnTermDegradation() {
        return nTermDegradation;
    }

    public void setnTermDegradation(Integer nTermDegradation) {
        this.nTermDegradation = nTermDegradation;
    }

    public Integer getcTermDegradation() {
        return cTermDegradation;
    }

    public void setcTermDegradation(Integer cTermDegradation) {
        this.cTermDegradation = cTermDegradation;
    }

    public Integer getUniqueness() {
        return uniqueness;
    }

    public void setUniqueness(Integer uniqueness) {
        this.uniqueness = uniqueness;
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
        if (!(o instanceof PeptideProtein)) return false;
        PeptideProtein that = (PeptideProtein) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(uniqueness, that.uniqueness);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uniqueness);
    }

    @Override
    public String toString() {
        return "PeptideProtein{" +
                "id=" + id +
                ", nTermDegradation=" + nTermDegradation +
                ", cTermDegradation=" + cTermDegradation +
                ", uniqueness=" + uniqueness +
                '}';
    }
}
