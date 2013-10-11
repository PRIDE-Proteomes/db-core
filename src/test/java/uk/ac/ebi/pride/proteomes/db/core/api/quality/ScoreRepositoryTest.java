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

    @Test
    @Transactional
    public void testSaveAndGetScore() throws Exception {

        QMethod qMethod = new QMethod();
        qMethod.setId(DEFAULT_QMETHOD_ID);
        qMethod.setDescription(DEFAULT_QMETHOD_DESC);
        qMethod.setName(DEFAULT_QMETHOD_NAME);

        Star star = new Star();
        star.setId(DEAFULT_STAR_ID);
        star.setType(StarType.NONE);
        star.setCount(DEAFULT_STAR_COUNT);

        Score score = new Score();
        score.setId(DEFAULT_SCORE_ID);
        score.setQualityMethod(qMethod);
        score.setStar(star);
        score.setValue(DEFAULT_SCORE_VALUE);

        //This should do a merge
        score = scoreRepository.save(score);

        Long id = score.getId();

        Score other = scoreRepository.findOne(id);

        checkScoreDB(other);

        //Because the default value should exits in the DB we don't want delete it
//        scoreRepository.delete(other);

    }

    private void checkScoreDB(Score other) {
        assertNotNull(other);
        assertThat(other.getId(), is(DEFAULT_SCORE_ID));
        assertThat(other.getValue(), is(DEFAULT_SCORE_VALUE));
        assertThat(other.getStar().getId(), is(DEAFULT_STAR_ID));
        assertThat(other.getStar().getType(), is(StarType.NONE));
        assertThat(other.getStar().getCount(), is(DEAFULT_STAR_COUNT));
        assertThat(other.getQualityMethod().getId(), is(DEFAULT_QMETHOD_ID));
        assertThat(other.getQualityMethod().getName(), is(DEFAULT_QMETHOD_NAME));
        assertThat(other.getQualityMethod().getDescription(), is(DEFAULT_QMETHOD_DESC));

    }

}
