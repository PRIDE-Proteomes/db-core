package uk.ac.ebi.pride.proteomes.db.core.api.quality;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ebi.pride.proteomes.db.core.api.RepositoryTest;

import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * User: ntoro
 * Date: 11/09/2013
 * Time: 14:16
 */

public class ScoreRepositoryTest extends RepositoryTest {

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
