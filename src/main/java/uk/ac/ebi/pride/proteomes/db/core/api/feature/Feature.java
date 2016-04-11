package uk.ac.ebi.pride.proteomes.db.core.api.feature;

import org.hibernate.annotations.Where;
import uk.ac.ebi.pride.proteomes.db.core.api.param.FeatureType;

import javax.persistence.*;
import java.util.Objects;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */

@Entity
@Table(name = "FEATURE", schema = "PRIDEPROT")
//Allocation size must by same than the increment in the sequence by default is 50-> http://skay-dev.blogspot.co.uk/2013/09/hibernate-sequence-and-negative.html
@SequenceGenerator(name = "FEATURE_SEQ", schema = "PRIDEPROT", sequenceName = "PRIDEPROT.FEATURE_FEATURE_ID_SEQ")
public class Feature {

    @Id
    @Column(name = "FEATURE_ID", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
    @GeneratedValue(generator = "FEATURE_SEQ", strategy = GenerationType.SEQUENCE)
    private Long featureId;

    @Basic
    @Column(name = "PROTEIN_ID")
    private String proteinAccession;

    @ManyToOne
    @JoinColumn(name = "CV_TERM", referencedColumnName = "CV_TERM")
    @Where(clause = "CV_TYPE = 'FEATURE_TYPE'")
    private FeatureType featureType;

    @Basic
    @Column(name = "START_POSITION", nullable = false, insertable = true, updatable = true, length = 1000, precision = 0)
    private Integer startPosition;

    @Basic
    @Column(name = "END_POSITION", nullable = false, insertable = true, updatable = true, length = 1000, precision = 0)
    private Integer endPosition;

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 1000, precision = 0)
    private String description;

    public Feature() {
    }

    public Long getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Long featureId) {
        this.featureId = featureId;
    }

    public String getProteinAccession() {
        return proteinAccession;
    }

    public void setProteinAccession(String proteinAccession) {
        this.proteinAccession = proteinAccession;
    }

    public FeatureType getFeatureType() {
        return featureType;
    }

    public void setFeatureType(FeatureType featureType) {
        this.featureType = featureType;
    }

    public Integer getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Integer startPosition) {
        this.startPosition = startPosition;
    }

    public Integer getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(Integer end) {
        this.endPosition = end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Feature)) return false;
        Feature feature = (Feature) o;
        return Objects.equals(proteinAccession, feature.proteinAccession) &&
                Objects.equals(featureType, feature.featureType) &&
                Objects.equals(startPosition, feature.startPosition) &&
                Objects.equals(endPosition, feature.endPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proteinAccession, featureType, startPosition, endPosition);
    }

    @Override
    public String toString() {
        return "Feature{" +
                "featureId=" + featureId +
                ", proteinAccession='" + proteinAccession + '\'' +
                ", featureType=" + featureType +
                ", start=" + startPosition +
                ", end=" + endPosition +
                ", description='" + description + '\'' +
                '}';
    }
}
