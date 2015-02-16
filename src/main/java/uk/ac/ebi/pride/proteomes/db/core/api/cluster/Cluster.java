package uk.ac.ebi.pride.proteomes.db.core.api.cluster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
@Table(name = "PRIDE_CLUSTER", schema = "PRIDEPROT")
@Entity
public class Cluster {

    @Id
    @Column(name = "CLUSTER_ID", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
    private Long clusterId;

    public Long getClusterId() {
        return clusterId;
    }

    public void setClusterId(Long clusterId) {
        this.clusterId = clusterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cluster cluster = (Cluster) o;

        if (!clusterId.equals(cluster.clusterId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clusterId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Cluster{" +
                "clusterId='" + clusterId +
                '}';
    }
}
