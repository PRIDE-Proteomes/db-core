package uk.ac.ebi.pride.proteomes.db.core.api.reprocessed;

import uk.ac.ebi.pride.proteomes.db.core.api.assay.Assay;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "REPRO", schema = "PRIDEPROT")
@Entity
public class Reprocessed {

    private String reprocessedAccession;
//    private String assayAccession;
    private Assay assay;

    @Column(name = "REPROCESSED_ACCESSION", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
    @Id
    public String getReprocessedAccession() {
        return reprocessedAccession;
    }

    public void setReprocessedAccession(String reprocessedAccession) {
        this.reprocessedAccession = reprocessedAccession;
    }

//    @Column(name = "ASSAY_FK_PK", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
//    @Id
//    public String getAssayAccession() {
//        return assayAccession;
//    }
//
//    public void setAssayAccession(String assayFkPk) {
//        this.assayAccession = assayFkPk;
//    }

    @ManyToOne
    @JoinColumn(name = "ASSAY_FK_PK", referencedColumnName = "ASSAY_ACCESSION", nullable = false)
    public Assay getAssay() {
        return assay;
    }

    public void setAssay(Assay assayByAssayFkPk) {
        this.assay = assayByAssayFkPk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reprocessed)) return false;

        Reprocessed that = (Reprocessed) o;

        if (!assay.equals(that.assay)) return false;
        if (!reprocessedAccession.equals(that.reprocessedAccession)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reprocessedAccession.hashCode();
        result = 31 * result + assay.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Reprocessed{" +
                "reprocessedAccession='" + reprocessedAccession + '\'' +
                ", assay=" + assay +
                '}';
    }
}
