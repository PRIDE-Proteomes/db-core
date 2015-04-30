package uk.ac.ebi.pride.proteomes.db.core.api.cluster;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * @author ntoro
 * @since 22/04/15 18:01
 */
@Entity
@Table(name = "CLUSTER_PSM", schema = "PRIDEPROT", uniqueConstraints = @UniqueConstraint(columnNames = {"SEQUENCE", "TAXID", "MODS", "ASSAY_ACCESSION", "CLUSTER_ID"}))
@SequenceGenerator(name = "PSM_SEQ", schema = "PRIDEPROT", sequenceName = "PRIDEPROT.CLUSTER_PSM_PSM_ID_SEQ")
public class ClusterPsm {

    @Id
    @Column(name = "PSM_ID", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    @GeneratedValue(generator = "PSM_SEQ", strategy = GenerationType.SEQUENCE)
    private Long psmId;

    @Basic
    @Column(name = "SEQUENCE", nullable = false, insertable = true, updatable = true, length = 1000, precision = 0)
    @NotNull     // validation constrain
    @Size(min = 6, max = 100)   // validation constrain
    @Pattern(regexp = "[GPAVLIMCFYWHKRQNEDST]{6,100}")  // validation constrain only valid amino acids
    private String sequence;

    @Basic
    @Column(name = "TAXID", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    private Integer taxid;

    @Basic
    @Column(name = "MODS", nullable = true, insertable = true, updatable = true, length = 22, precision = 0)
    private String modifications;

    @Basic
    @Column(name = "ASSAY_ACCESSION", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    private String assayAccession;

    @Basic
    @Column(name = "PROJECT_ACCESSION", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    private String projectAccession;

    @Basic
    @Column(name = "CLUSTER_ID", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    private String clusterId;

    public Long getPsmId() {
        return psmId;
    }

    public void setPsmId(Long psmId) {
        this.psmId = psmId;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public Integer getTaxid() {
        return taxid;
    }

    public void setTaxid(Integer taxid) {
        this.taxid = taxid;
    }

    public String getModifications() {
        return modifications;
    }

    public void setModifications(String modifications) {
        this.modifications = modifications;
    }

    public String getAssayAccession() {
        return assayAccession;
    }

    public void setAssayAccession(String assayAccession) {
        this.assayAccession = assayAccession;
    }

    public String getProjectAccession() {
        return projectAccession;
    }

    public void setProjectAccession(String projectAccession) {
        this.projectAccession = projectAccession;
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClusterPsm)) return false;
        ClusterPsm that = (ClusterPsm) o;
        return Objects.equals(sequence, that.sequence) &&
                Objects.equals(taxid, that.taxid) &&
                Objects.equals(modifications, that.modifications) &&
                Objects.equals(assayAccession, that.assayAccession) &&
                Objects.equals(clusterId, that.clusterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sequence, taxid, modifications, assayAccession, clusterId);
    }

    @Override
    public String toString() {
        return "ClusterPsm{" +
                "sequence='" + sequence + '\'' +
                ", taxid=" + taxid +
                ", modifications='" + modifications + '\'' +
                ", assayAccession='" + assayAccession + '\'' +
                ", projectAccession='" + projectAccession + '\'' +
                ", clusterId='" + clusterId + '\'' +
                '}';
    }
}
