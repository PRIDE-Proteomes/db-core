package uk.ac.ebi.pride.proteomes.db.core.api.cluster;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ebi.pride.proteomes.db.core.api.RepositoryTest;

import static junit.framework.Assert.assertNotNull;

public class ClusterProteomesRepositoryTest extends RepositoryTest {

    private static final Long CLUSTER_ID = 1L;

    @Test
    @Transactional(readOnly = true)
    public void testFindMethods() throws Exception {

        Cluster cluster = clusterProteomesRepository.findOne(CLUSTER_ID);
        assertNotNull(cluster);

    }
}
