package uk.ac.ebi.pride.proteomes.db.core.api.quality;

import org.springframework.data.repository.CrudRepository;

/**
 * Created with IntelliJ IDEA.
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */
public interface ScoreRepository extends CrudRepository<Score, Long> {

    public Score findAllScoreGreaterThan(Integer value);
    public Score findAllScoreLessThan(Integer value);

}
