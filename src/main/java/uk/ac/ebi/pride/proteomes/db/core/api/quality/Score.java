package uk.ac.ebi.pride.proteomes.db.core.api.quality;

import javax.persistence.*;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:07
 */
@Entity
@Table(name = "SCORE", schema = "PRIDEPROT")
@SequenceGenerator(name="SCORE_SEQ", schema = "PRIDEPROT", sequenceName="SCORE_SCORE_PK_SEQ")
public class Score {

    @Id
    @Column(name = "SCORE_PK", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    @GeneratedValue(generator = "SCORE_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Basic
    @Column(name = "VALUE", nullable = false, insertable = true, updatable = true)
    private Double value;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "QUALITY_METHOD_FK", referencedColumnName = "QUALITY_METHOD_PK", nullable = false)
    private QMethod qualityMethod;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "STAR_FK", referencedColumnName = "STAR_PK", nullable = false)
    private Star star;

//    private Collection<Peptide> peptidesByScorePk;
//    private Collection<Protein> proteinsByScorePk;

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

    public void setQualityMethod(QMethod qMethodByQualityMethodFk) {
        this.qualityMethod = qMethodByQualityMethodFk;
    }

    public Star getStar() {
        return star;
    }

    public void setStar(Star starByStarFk) {
        this.star = starByStarFk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Score score = (Score) o;

        if (id != null ? !id.equals(score.id) : score.id != null) return false;
        if (value != null ? !value.equals(score.value) : score.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

//    @OneToMany(mappedBy = "scoreByScoreFk")
//    public Collection<Peptide> getPeptidesByScorePk() {
//        return peptidesByScorePk;
//    }
//
//    public void setPeptidesByScorePk(Collection<Peptide> peptidesByScorePk) {
//        this.peptidesByScorePk = peptidesByScorePk;
//    }
//
//    @OneToMany(mappedBy = "scoreByScoreFk")
//    public Collection<Protein> getProteinsByScorePk() {
//        return proteinsByScorePk;
//    }
//
//    public void setProteinsByScorePk(Collection<Protein> proteinsByScorePk) {
//        this.proteinsByScorePk = proteinsByScorePk;
//    }


}
