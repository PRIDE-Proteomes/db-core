package uk.ac.ebi.pride.proteomes.db.core.api.peptide.group;

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
public class PeptideGroupPK implements Serializable {

    @NotNull
    @Column(name = "PEPTIDE_ID", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
    private Long peptideId;

    @NotNull
    @Column(name = "PROT_GROUP_ID", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    private String proteinGroupId;

    public Long getPeptideId() {
        return peptideId;
    }

    public void setPeptideId(Long peptideId) {
        this.peptideId = peptideId;
    }

    public String getProteinGroupId() {
        return proteinGroupId;
    }

    public void setProteinGroupId(String proteinGroupId) {
        this.proteinGroupId = proteinGroupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeptideGroupPK)) return false;
        PeptideGroupPK that = (PeptideGroupPK) o;
        return Objects.equals(peptideId, that.peptideId) &&
                Objects.equals(proteinGroupId, that.proteinGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(peptideId, proteinGroupId);
    }

    @Override
    public String toString() {
        return "PeptideGroupPK{" +
                "peptideId=" + peptideId +
                ", proteinAccession='" + proteinGroupId + '\'' +
                '}';
    }
}
