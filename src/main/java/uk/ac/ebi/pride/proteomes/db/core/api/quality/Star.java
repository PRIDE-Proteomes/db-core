package uk.ac.ebi.pride.proteomes.db.core.api.quality;

import javax.persistence.*;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:07
 */
@Entity
@Table(name = "STAR", schema = "PRIDEPROT")
@SequenceGenerator(name="STAR_SEQ", schema = "PRIDEPROT", sequenceName="STAR_STAR_PK_SEQ")
public class Star {

    @Id
    @Column(name = "STAR_PK", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    @GeneratedValue(generator = "STAR_SEQ", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Basic
    @Column(name = "STAR_COUNT", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    private Integer count;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "STAR_TYPE", nullable = false, insertable = true, updatable = true, length = 90, precision = 0)
    private StarType type;

//    private Collection<Score> scoresByStarPk;


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
        if (o == null || getClass() != o.getClass()) return false;

        Star star = (Star) o;

        if (count != null ? !count.equals(star.count) : star.count != null) return false;
        if (id != null ? !id.equals(star.id) : star.id != null) return false;
        if (type != null ? !type.equals(star.type) : star.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (count != null ? count.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

//    @OneToMany(mappedBy = "star")
//    public Collection<Score> getScoresByStarPk() {
//        return scoresByStarPk;
//    }
//
//    public void setScoresByStarPk(Collection<Score> scoresByStarPk) {
//        this.scoresByStarPk = scoresByStarPk;
//    }
}
