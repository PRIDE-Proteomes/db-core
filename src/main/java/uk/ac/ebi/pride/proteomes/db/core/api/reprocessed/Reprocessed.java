package uk.ac.ebi.pride.proteomes.db.core.api.reprocessed;

import javax.persistence.Column;
import javax.persistence.Embeddable;

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

        if (!reprocessedAccession.equals(that.reprocessedAccession)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return reprocessedAccession.hashCode();
    }

    @Override
    public String toString() {
        return "Reprocessed{" +
                "reprocessedAccession='" + reprocessedAccession + '\'' +
                '}';
    }
}
