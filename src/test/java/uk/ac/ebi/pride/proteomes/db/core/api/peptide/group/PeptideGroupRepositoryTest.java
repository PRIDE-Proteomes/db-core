package uk.ac.ebi.pride.proteomes.db.core.api.peptide.group;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ebi.pride.proteomes.db.core.api.RepositoryTest;

import java.util.Collection;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Florian Reisinger
 *         Date: 17/12/13
 * @since $version
 */
public class PeptideGroupRepositoryTest extends RepositoryTest {

    @Test
    @Transactional(readOnly = true)
    public void testFindMethods() throws Exception {

        Collection<PeptideGroup> proteinPeptideGroups = peptideGroupRepository.findByProteinGroupId(ENTRY_GROUP_ID);
        assertNotNull(proteinPeptideGroups);
        assertThat(proteinPeptideGroups.size(), is(3));
        PeptideGroup group = proteinPeptideGroups.iterator().next();
        assertThat(group.getProteinGroup().getId(), is(ENTRY_GROUP_ID));
        assertThat(group.getUniqueness(), is(1));

        long mappedUPEntries = peptideGroupRepository.countByMappedUPEntriesByTaxId(TAXID_HUMAN);
        assertThat(mappedUPEntries, is(1L));
        long mappedGeneEntries = peptideGroupRepository.countByMappedGenesByTaxId(TAXID_HUMAN);
        assertThat(mappedGeneEntries, is(1L));

    }


}
