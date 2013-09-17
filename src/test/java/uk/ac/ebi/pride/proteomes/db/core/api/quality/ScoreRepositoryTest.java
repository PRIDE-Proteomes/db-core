package uk.ac.ebi.pride.proteomes.db.core.api.quality;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * User: ntoro
 * Date: 11/09/2013
 * Time: 14:16
 */
@ContextConfiguration(locations = {"/test-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ScoreRepositoryTest {

    private static final Double SCORE_VALUE = 2.0;
    private static final Integer STAR_COUNT = 2;
    private static final Integer NUM_SCORES_GREATER_THAN = 5;
    private static final Integer NUM_SCORES_LESS_THAN = 3;
    private static final Integer SCORE_WITH_TWO_STARS = 3;
    private static final Integer TWO_STARS = 2;
    private static final Integer SCORES_WITH_GOLD = 3;
    private static final Integer SCORES_WITH_SILVER_AND_VALUE_GREATER_THAT = 1;
    private static final Integer SCORES_WITH_SILVER_AND_VALUE_LESS_THAT = 1;
    private static final Integer NUM_SCORES_GREATER_THAN_TWO_STARS_GOLD = 1;
    private static final Integer NUM_SCORES_LESS_THAN_TWO_STARS_GOLD = 1;
    private static final Integer NUM_SCORES_BETWEEN_TWO_STARS_GOLD = 2;
    private static final Double MIN = 1.0;
    private static final Double MAX = 3.0;

    @Autowired
    private ScoreRepository scoreRepository;

    @Test
    @Transactional(readOnly = true)
    public void testFindByMethods() throws Exception {

        List<Score> scores = scoreRepository.findByValueGreaterThan(SCORE_VALUE);

        assertNotNull(scores);
        assertThat(scores.size(), is(NUM_SCORES_GREATER_THAN));

        scores = scoreRepository.findByValueLessThan(SCORE_VALUE);

        assertNotNull(scores);
        assertThat(scores.size(), is(NUM_SCORES_LESS_THAN));

        scores = scoreRepository.findByStarCountGreaterThanAndStarType(STAR_COUNT, StarType.SILVER);

        assertNotNull(scores);
        assertThat(scores.size(), is(SCORES_WITH_SILVER_AND_VALUE_GREATER_THAT));

        scores = scoreRepository.findByStarCountLessThanAndStarType(STAR_COUNT, StarType.SILVER);

        assertNotNull(scores);
        assertThat(scores.size(), is(SCORES_WITH_SILVER_AND_VALUE_LESS_THAT));

        scores = scoreRepository.findByStarType(StarType.GOLD);

        assertNotNull(scores);
        assertThat(scores.size(), is(SCORES_WITH_GOLD));

        scores = scoreRepository.findByStarCount(TWO_STARS);

        assertNotNull(scores);
        assertThat(scores.size(), is(SCORE_WITH_TWO_STARS));

        scores = scoreRepository.findByValueGreaterThanAndStarCountAndStarType(SCORE_VALUE, TWO_STARS, StarType.GOLD);

        assertNotNull(scores);
        assertThat(scores.size(), is(NUM_SCORES_GREATER_THAN_TWO_STARS_GOLD));

        scores = scoreRepository.findByValueLessThanAndStarCountAndStarType(SCORE_VALUE, TWO_STARS, StarType.GOLD);

        assertNotNull(scores);
        assertThat(scores.size(), is(NUM_SCORES_LESS_THAN_TWO_STARS_GOLD));

        scores = scoreRepository.findByValueBetweenAndStarCountAndStarType(MIN, MAX, TWO_STARS, StarType.GOLD);

        assertNotNull(scores);
        assertThat(scores.size(), is(NUM_SCORES_BETWEEN_TWO_STARS_GOLD));
    }

}
