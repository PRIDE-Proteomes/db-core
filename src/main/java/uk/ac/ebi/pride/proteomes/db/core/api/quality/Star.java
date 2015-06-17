package uk.ac.ebi.pride.proteomes.db.core.api.quality;

import javax.persistence.*;
import java.util.Objects;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:07
 */
@Entity
@Table(name = "STAR", schema = "PRIDEPROT")
@SequenceGenerator(name="STAR_SEQ", schema = "PRIDEPROT", sequenceName="PRIDEPROT.STAR_STAR_ID_SEQ")
public class Star {

    @Id
    @Column(name = "STAR_ID", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    @GeneratedValue(generator = "STAR_SEQ", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Basic
    @Column(name = "STAR_COUNT", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    private Integer count;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "STAR_TYPE", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
    private StarType type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer starPk) {
        this.id = starPk;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public StarType getType() {
        return type;
    }

    public void setType(StarType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Star)) return false;
        Star star = (Star) o;
        return Objects.equals(count, star.count) &&
                Objects.equals(type, star.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, type);
    }

    @Override
    public String toString() {
        return "Star{" +
                "id=" + id +
                ", count=" + count +
                ", type=" + type +
                '}';
    }
}
