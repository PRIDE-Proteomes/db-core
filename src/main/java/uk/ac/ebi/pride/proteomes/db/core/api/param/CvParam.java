package uk.ac.ebi.pride.proteomes.db.core.api.param;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
@Entity
@Table(name = "CV_PARAM", schema = "PRIDEPROT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CV_TYPE", discriminatorType = DiscriminatorType.STRING, length = 90)
public abstract class CvParam {

    @Id
    @NotNull
    @Column(name = "CV_TERM", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
    private String cvTerm;

    @Basic
    @Column(name = "CV_NAME", nullable = false, insertable = true, updatable = true, length = 400, precision = 0)
    private String cvName;

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 1000, precision = 0)
    private String description;

    public String getCvTerm() {
        return cvTerm;
    }

    public void setCvTerm(String cvTerm) {
        this.cvTerm = cvTerm;
    }

    public String getCvName() {
        return cvName;
    }

    public void setCvName(String cvName) {
        this.cvName = cvName;
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
        if (!(o instanceof CvParam)) return false;

        CvParam cvParam = (CvParam) o;
        if (!cvTerm.equals(cvParam.cvTerm)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cvTerm.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CvParam{" +
                "cvTerm='" + cvTerm + '\'' +
                ", cvName='" + cvName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
