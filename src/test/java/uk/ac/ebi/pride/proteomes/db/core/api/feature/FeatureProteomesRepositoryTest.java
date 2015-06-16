package uk.ac.ebi.pride.proteomes.db.core.api.feature;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ebi.pride.proteomes.db.core.api.RepositoryTest;
import uk.ac.ebi.pride.proteomes.db.core.api.param.CvParam;
import uk.ac.ebi.pride.proteomes.db.core.api.param.FeatureType;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author ntoro
 * @since 16/06/15 09:02
 */
public class FeatureProteomesRepositoryTest extends RepositoryTest {

    @Test
    @Transactional(readOnly = true)
    public void testFindMethods() throws Exception {
        Feature feature = featureProteomesRepository.findOne(FEATURE_ID);

        assertNotNull(feature);
        assertThat(feature.getFeatureId(), is(FEATURE_ID));

    }

    @Test
    @Transactional
    public void testSaveAndGetFeature() throws Exception {

        CvParam cvParam = cvParamProteomesRepository.findByCvTerm(FEATURE_TYPE_CV_TERM);

        //New assay in a existing project
        //We don't check it doesn't exist because we know that
        Feature feature = new Feature();
        feature.setProteinAccession(PROTEIN_ACCESSION);
        feature.setFeatureType((FeatureType) cvParam);
        feature.setStartPosition(FT_START_POS);
        feature.setEndPosition(FT_END_POS);

        featureProteomesRepository.save(feature);

        //id set after save
        Long newId = feature.getFeatureId();

        Feature other = featureProteomesRepository.findOne(newId);
        checkFeatureInDb(other);

        // delete the feature
        featureProteomesRepository.delete(other);

    }

    private void checkFeatureInDb(Feature feature) {

        assertNotNull(feature);
        assertThat(feature.getFeatureId(), is(FEATURE_ID_SAVE));
        assertThat(feature.getProteinAccession(), is(PROTEIN_ACCESSION));
        assertThat(feature.getStartPosition(), is(FT_START_POS));
        assertThat(feature.getEndPosition(), is(FT_END_POS));

        checkFeatureType(feature.getFeatureType());

    }


    private void checkFeatureType(FeatureType featureType) {
        assertNotNull(featureType);
        assertThat(featureType.getCvTerm(), is(FEATURE_TYPE_CV_TERM));
        assertThat(featureType.getCvName(), is(FEATURE_TYPE_CV_NAME));
    }

}
