package uk.ac.ebi.pride.proteomes.db.core.api.quality;

import javax.persistence.*;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:07
 */
@Entity
@Table(name = "SCORE", schema = "PRIDEPROT")
@SequenceGenerator(name="SCORE_SEQ", schema = "PRIDEPROT", sequenceName="PRIDEPROT.SCORE_SCORE_ID_SEQ")
public class Score {

    @Id
    @Column(name = "SCORE_ID", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    @GeneratedValue(generator = "SCORE_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Basic
    @Column(name = "VALUE", nullable = false, insertable = true, updatable = true)
    private Double value;

    @ManyToOne
    @JoinColumn(name = "Q_METHOD_ID", referencedColumnName = "Q_METHOD_ID", nullable = false)
    private QMethod qualityMethod;

    @ManyToOne
    @JoinColumn(name = "STAR_ID", referencedColumnName = "STAR_ID", nullable = false)
    private Star star;

    public Long getId() {
        return id;
    }

    public void setId(Long scorePk) {
        this.id = scorePk;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public QMethod getQualityMethod() {
        return qualityMethod;
    }

    public void setQualityMethod(QMethod qualityMethod) {
        this.qualityMethod = qualityMethod;
    }

    public Star getStar() {
        return star;
    }

    public void setStar(Star star) {
        this.star = star;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Score score = (Score) o;

        if (!qualityMethod.equals(score.qualityMethod)) return false;
        if (!star.equals(score.star)) return false;
        if (!value.equals(score.value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = value.hashCode();
        result = 31 * result + qualityMethod.hashCode();
        result = 31 * result + star.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", value=" + value +
                ", qualityMethod=" + qualityMethod +
                ", star=" + star +
                '}';
    }
}
