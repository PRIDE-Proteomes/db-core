package uk.ac.ebi.pride.proteomes.db.core.api.peptide;

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
 * Date: 12/09/2013
 * Time: 16:43
 */

@ContextConfiguration(locations = {"/test-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class PeptideRepositoryTest {

    private static final String SEQUENCE = "MSAHVKR";
    private static final Integer PEPS_WITH_SEQUENCE = 3;
    private static final Integer PEPS_VAR_WITH_SEQUENCE = 2;
    private static final Integer PEPS_SYM_WITH_SEQUENCE = 1;

    @Autowired
    private PeptideRepository peptideRepository;

    @Test
    @Transactional(readOnly = true)
    public void testFindMethods() throws Exception {


        List<Peptide> peptide = peptideRepository.findBySequence(SEQUENCE);

        assertNotNull(peptide);
        assertThat(peptide.size(), is(PEPS_WITH_SEQUENCE));

        List<PeptideVariant> peptideVariants = peptideRepository.findPeptideVariantBySequence(SEQUENCE);

        assertNotNull(peptideVariants);
        assertThat(peptideVariants.size(), is(PEPS_VAR_WITH_SEQUENCE));

        List<PeptideSymbolic> peptideSymbolic = peptideRepository.findPeptideSymbolicBySequence(SEQUENCE);

        assertNotNull(peptideSymbolic);
        assertThat(peptideSymbolic.size(), is(PEPS_SYM_WITH_SEQUENCE));
    }
}
