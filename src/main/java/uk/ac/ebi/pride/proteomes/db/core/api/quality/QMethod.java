package uk.ac.ebi.pride.proteomes.db.core.api.quality;


import javax.persistence.*;
import java.util.Objects;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
@Entity
@Table(name = "Q_METHOD", schema = "PRIDEPROT")
@SequenceGenerator(name="Q_METHOD_SEQ", schema = "PRIDEPROT", sequenceName="PRIDEPROT.Q_METHOD_QUALITY_METHOD_ID_SEQ")
public class QMethod {

    @Id
    @Column(name = "Q_METHOD_ID", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    @GeneratedValue(generator = "Q_METHOD_SEQ", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Basic
    @Column(name = "NAME", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
    private String name;

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 400, precision = 0)
    private String description;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QMethod)) return false;
        QMethod qMethod = (QMethod) o;
        return Objects.equals(name, qMethod.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "QMethod{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
