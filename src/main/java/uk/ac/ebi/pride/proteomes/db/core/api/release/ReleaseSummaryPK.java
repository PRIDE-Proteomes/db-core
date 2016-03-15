package uk.ac.ebi.pride.proteomes.db.core.api.release;

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
public class ReleaseSummaryPK implements Serializable {

    @NotNull
    @Column(name = "TAXID", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
    private Integer taxid;

    @NotNull
    @Column(name = "RELEASE_DATE", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    private String releaseDate;

    public Integer getTaxid() {
        return taxid;
    }

    public void setTaxid(Integer taxid) {
        this.taxid = taxid;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public ReleaseSummaryPK() {
    }

    public ReleaseSummaryPK(Integer taxid, String releaseDate) {
        setTaxid(taxid);
        setReleaseDate(releaseDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReleaseSummaryPK)) return false;
        ReleaseSummaryPK that = (ReleaseSummaryPK) o;
        return Objects.equals(taxid, that.taxid) &&
                Objects.equals(releaseDate, that.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taxid, releaseDate);
    }

    @Override
    public String toString() {
        return "ReleaseSummaryPK{" +
                "taxid=" + taxid +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
