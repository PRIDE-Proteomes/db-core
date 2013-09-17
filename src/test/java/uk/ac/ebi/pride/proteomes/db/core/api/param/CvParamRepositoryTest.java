package uk.ac.ebi.pride.proteomes.db.core.api.param;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * User: ntoro
 * Date: 12/09/2013
 * Time: 11:35
 */
@ContextConfiguration(locations = {"/test-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CvParamRepositoryTest {

    private static final String CV_NAME = "aorta thoracica";
    private static final String CV_TERM = "BTO:0000157";

    @Autowired
    private CvParamRepository cvParamRepository;

    @Test
    @Transactional(readOnly = true)
    public void testFindMethods() throws Exception {


        CvParam cvParam = cvParamRepository.findByCvName(CV_NAME);

        assertNotNull(cvParam);
        assertThat(cvParam.getCvName(), is(CV_NAME));

        cvParam = cvParamRepository.findByCvTerm(CV_TERM);

        assertNotNull(cvParam);
        assertTrue(cvParam instanceof CvParamTissue);
        assertThat(cvParam.getCvTerm(), is(CV_TERM));

    }

}
