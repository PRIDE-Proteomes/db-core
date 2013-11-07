package uk.ac.ebi.pride.proteomes.db.core.api.peptide.group;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * User: ntoro
 * Date: 06/11/2013
 * Time: 16:37
 */
@Embeddable
public class PeptideGroupPK implements Serializable {

    @NotNull
    @Column(name = "PEPTIDE_FK_PK", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
    private Long peptideId;

    @NotNull
    @Column(name = "P_GROUP_FK_PK", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
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

        if (!peptideId.equals(that.peptideId)) return false;
        if (!proteinGroupId.equals(that.proteinGroupId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = peptideId.hashCode();
        result = 31 * result + proteinGroupId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PeptideGroupPK{" +
                "peptideId=" + peptideId +
                ", proteinAccession='" + proteinGroupId + '\'' +
                '}';
    }
}
