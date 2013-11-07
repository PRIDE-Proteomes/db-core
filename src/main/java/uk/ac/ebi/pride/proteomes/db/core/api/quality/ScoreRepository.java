package uk.ac.ebi.pride.proteomes.db.core.api.quality;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
public interface ScoreRepository extends JpaRepository<Score, Long> {

    public List<Score> findByValueGreaterThan(Double value);
    public List<Score> findByValueLessThan(Double value);
    public List<Score> findByStarCountGreaterThanAndStarType(Integer value, StarType starType);
    public List<Score> findByStarCountLessThanAndStarType(Integer value, StarType starType);
    public List<Score> findByStarType(StarType starType);
    public List<Score> findByStarCount(Integer value);
    public List<Score> findByValueGreaterThanAndStarCountAndStarType(Double value, Integer startCount, StarType starType);
    public List<Score> findByValueLessThanAndStarCountAndStarType(Double value, Integer startCount, StarType starType);
    public List<Score> findByValueBetweenAndStarCountAndStarType(Double min, Double max, Integer startCount, StarType starType);

}
