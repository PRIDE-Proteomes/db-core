package uk.ac.ebi.pride.proteomes.db.core.api.release;

import org.junit.Test;
import uk.ac.ebi.pride.proteomes.db.core.api.RepositoryTest;

import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author ntoro
 * @since 08/04/2016 10:49
 */
public class ReleaseSummaryRepositoryTest extends RepositoryTest {

    @Test
    public void testFindByIdTaxid() throws Exception {
        List<ReleaseSummary> releaseSummaries  = releaseSummaryRepository.findByIdTaxid(TAXID_HUMAN);

        assertNotNull(releaseSummaries);
        assertThat(releaseSummaries.size(), is(NUM_RELEASE_PER_TAXID));
    }
}