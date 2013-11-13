package uk.ac.ebi.pride.proteomes.db.core.api.peptide.protein;

import uk.ac.ebi.pride.proteomes.db.core.api.peptide.Peptide;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.Protein;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
@Entity
@Table(name = "PROT_PEP", schema = "PRIDEPROT")
public class PeptideProtein implements Serializable {

	@EmbeddedId
	private PeptideProteinPK id;

	@Basic
	@Column(name = "TRYPTIC_SCORE", nullable = true, insertable = true, updatable = true, length = 22, precision = 0)
	private Integer trypticScore;

	@Basic
	@Column(name = "UNIQUENESS", nullable = true, insertable = true, updatable = true, length = 22, precision = 0)
	private Integer uniqueness;

    @ManyToOne
    @JoinColumn(name = "PEPTIDE_FK_PK", referencedColumnName = "PEPTIDE_PK", nullable = false, insertable=false, updatable=false)
    private Peptide peptide;

    @ManyToOne
    @JoinColumn(name = "PROTEIN_FK_PK", referencedColumnName = "PROTEIN_ACCESSION", nullable = false, insertable=false, updatable=false)
    private Protein protein;


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

	public Integer getTrypticScore() {
		return trypticScore;
	}

	public void setTrypticScore(Integer trypticScore) {
		this.trypticScore = trypticScore;
	}

	public Integer getUniqueness() {
		return uniqueness;
	}

	public void setUniqueness(Integer uniqueness) {
		this.uniqueness = uniqueness;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PeptideProtein)) return false;

		PeptideProtein that = (PeptideProtein) o;

		if (!id.equals(that.id)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public String toString() {
		return "PeptideProtein{" +
				"id=" + id +
				", uniqueness=" + uniqueness +
				", trypticScore=" + trypticScore +
				'}';
	}
}
