package uk.ac.ebi.pride.proteomes.db.core.api.param;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ebi.pride.proteomes.db.core.api.RepositoryTest;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * User: ntoro
 * Date: 12/09/2013
 * Time: 11:35
 */

public class CvParamRepositoryTest extends RepositoryTest {

    @Test
    @Transactional(readOnly = true)
    public void testFindMethods() throws Exception {


        CvParam cvParam = cvParamRepository.findByCvName(CV_NAME);

        assertNotNull(cvParam);
        assertThat(cvParam.getCvName(), is(CV_NAME));

        cvParam = cvParamRepository.findByCvTerm(CV_TERM);

        assertNotNull(cvParam);
        assertTrue(cvParam instanceof Tissue);
        assertThat(cvParam.getCvTerm(), is(CV_TERM));

    }

}
