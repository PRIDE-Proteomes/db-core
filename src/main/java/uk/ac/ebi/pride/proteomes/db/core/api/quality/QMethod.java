package uk.ac.ebi.pride.proteomes.db.core.api.quality;


import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "Q_METHOD", schema = "PRIDEPROT")
@SequenceGenerator(name="Q_METHOD_SEQ", schema = "PRIDEPROT", sequenceName="Q_METHOD_QUALITY_METHOD_PK_SEQ")
public class QMethod {

    @Id
    @Column(name = "QUALITY_METHOD_PK", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    @GeneratedValue(generator = "Q_METHOD_SEQ", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Basic
    @Column(name = "NAME", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
    private String name;

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 400, precision = 0)
    private String description;

//    private Collection<Score> scoresByQualityMethodPk;

    public Integer getId() {
        return id;
    }

    public void setId(Integer qualityMethodPk) {
        this.id = qualityMethodPk;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

     public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    @OneToMany(mappedBy = "qualityMethod")
//    public Collection<Score> getScoresByQualityMethodPk() {
//        return scoresByQualityMethodPk;
//    }
//
//    public void setScoresByQualityMethodPk(Collection<Score> scoresByQualityMethodPk) {
//        this.scoresByQualityMethodPk = scoresByQualityMethodPk;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QMethod qMethod = (QMethod) o;

        if (description != null ? !description.equals(qMethod.description) : qMethod.description != null) return false;
        if (name != null ? !name.equals(qMethod.name) : qMethod.name != null) return false;
        if (id != null ? !id.equals(qMethod.id) : qMethod.id != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
