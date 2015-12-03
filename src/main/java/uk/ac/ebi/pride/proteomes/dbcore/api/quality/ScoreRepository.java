package uk.ac.ebi.pride.proteomes.db.core.api.quality;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
@Repository
@Transactional(readOnly = true)
public interface ScoreRepository extends JpaRepository<Score, Long> {

    List<Score> findByValueGreaterThan(Double value);
    List<Score> findByValueLessThan(Double value);
    List<Score> findByStarCountGreaterThanAndStarType(Integer value, StarType starType);
    List<Score> findByStarCountLessThanAndStarType(Integer value, StarType starType);
    List<Score> findByStarType(StarType starType);
    List<Score> findByStarCount(Integer value);
    List<Score> findByValueGreaterThanAndStarCountAndStarType(Double value, Integer startCount, StarType starType);
    List<Score> findByValueLessThanAndStarCountAndStarType(Double value, Integer startCount, StarType starType);
    List<Score> findByValueBetweenAndStarCountAndStarType(Double min, Double max, Integer startCount, StarType starType);

}
