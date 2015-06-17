package uk.ac.ebi.pride.proteomes.db.core.api.reprocessed;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */

@Embeddable
public class Reprocessed {

    @Column(name = "REPRO_ACCESSION")
    private String reprocessedAccession;

    public String getReprocessedAccession() {
        return reprocessedAccession;
    }

    public void setReprocessedAccession(String reprocessedAccession) {
        this.reprocessedAccession = reprocessedAccession;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reprocessed)) return false;
        Reprocessed that = (Reprocessed) o;
        return Objects.equals(reprocessedAccession, that.reprocessedAccession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reprocessedAccession);
    }

    @Override
    public String toString() {
        return "Reprocessed{" +
                "reprocessedAccession='" + reprocessedAccession + '\'' +
                '}';
    }
}
