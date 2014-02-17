package uk.ac.ebi.pride.proteomes.db.core.api.utils;

import uk.ac.ebi.pride.proteomes.db.core.api.quality.QMethod;
import uk.ac.ebi.pride.proteomes.db.core.api.quality.Score;
import uk.ac.ebi.pride.proteomes.db.core.api.quality.Star;
import uk.ac.ebi.pride.proteomes.db.core.api.quality.StarType;

/**
 * User: ntoro
 * Date: 11/10/2013
 * Time: 16:21
 */
public class ScoreUtils {

    protected static final long DEFAULT_SCORE_ID = 1;
    protected static final int DEFAULT_QMETHOD_ID = 1;
    protected static final int DEAFULT_STAR_ID = 1;
    protected static final String DEFAULT_QMETHOD_DESC = "Default value to initialize the database";
    protected static final String DEFAULT_QMETHOD_NAME = "default";
    protected static final int DEAFULT_STAR_COUNT = 0;
    protected static final double DEFAULT_SCORE_VALUE = 0.0;

    /**
     * Creates a default Score to initialize peptides and proteins. It is the same score that the default one in
     * the Proteomes DB
     * @return a default Score
     */
    public static Score defaultScore() {

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

        return score;
    }
}
