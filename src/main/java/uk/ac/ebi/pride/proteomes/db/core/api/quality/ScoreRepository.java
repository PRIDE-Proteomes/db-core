package uk.ac.ebi.pride.proteomes.db.core.api.quality;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
public interface ScoreRepository extends CrudRepository<Score, Long> {

    public List<Score> findByValueGreaterThan(Integer value);
    public List<Score> findByValueLessThan(Integer value);
    public List<Score> findByStarCountGreaterThanAndStarType(Integer value, StarType starType);
    public List<Score> findByStarCountLessThanAndStarType(Integer value, StarType starType);
    public List<Score> findByStarType(StarType starType);
    public List<Score> findByStarCount(Integer value);
    public List<Score> findByValueGreaterThanAndStarCountAndStarType(Integer value, Integer startCount, StarType starType);
    public List<Score> findByValueLessThanAndStarCountAndStarType(Integer value, Integer startCount, StarType starType);
    public List<Score> findByValueBetweenAndStarCountAndStarType(Integer min, Integer max, Integer startCount, StarType starType);

}
