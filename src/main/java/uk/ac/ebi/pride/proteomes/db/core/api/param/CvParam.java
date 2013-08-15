package uk.ac.ebi.pride.proteomes.db.core.api.param;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "CV_PARAM", schema = "PRIDEPROT")
@DiscriminatorColumn(name = "CV_TYPE", discriminatorType = DiscriminatorType.STRING, length = 90)
@Entity
public abstract class CvParam {

    @Id
    @Column(name = "CV_TERM", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
    private String cvTerm;

    @Basic
    @Column(name = "CV_NAME", nullable = false, insertable = true, updatable = true, length = 400, precision = 0)
    private String cvName;

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 1000, precision = 0)
    private String description;

    @Basic
    @Column(name = "CV_TYPE", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
    private String cvType;

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

    public final String getCvType() {
        return cvType;
    }

    public final void setCvType(String type) {
        this.cvType = type;
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
        if (o == null || getClass() != o.getClass()) return false;

        CvParam cvParam = (CvParam) o;

        if (cvName != null ? !cvName.equals(cvParam.cvName) : cvParam.cvName != null) return false;
        if (cvTerm != null ? !cvTerm.equals(cvParam.cvTerm) : cvParam.cvTerm != null) return false;
        if (description != null ? !description.equals(cvParam.description) : cvParam.description != null) return false;
        if (cvType != null ? !cvType.equals(cvParam.cvType) : cvParam.cvType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cvTerm != null ? cvTerm.hashCode() : 0;
        result = 31 * result + (cvName != null ? cvName.hashCode() : 0);
        result = 31 * result + (cvType != null ? cvType.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CvParam{" +
                "cvTerm='" + cvTerm + '\'' +
                ", cvName='" + cvName + '\'' +
                ", type='" + cvType + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
