package uk.ac.ebi.pride.proteomes.db.core.api.peptide.protein;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ebi.pride.proteomes.db.core.api.RepositoryTest;

import java.util.Collection;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by IntelliJ IDEA.
 * User: noedelta
 * Date: 12/11/2013
 * Time: 11:49
 */
public class PeptideProteinRepositoryTest extends RepositoryTest {

	@Test
	@Transactional(readOnly = true)
	public void testFindMethods() throws Exception {

		PeptideProtein peptideProtein = peptideProteinRepository.findByPeptidePeptideIdAndProteinProteinAccession(PEPTIDE_TWO, PROTEIN_ACCESSION);
		assertNotNull(peptideProtein);
		assertThat(peptideProtein.getStartPosition(), is(1));

        Collection<PeptideProtein> peptideProteins = peptideProteinRepository.findByProteinProteinAccession(PROTEIN_ACCESSION);
        assertNotNull(peptideProteins);
        assertThat(peptideProteins.size(), is(3));

	}

	@Test
	@Transactional
	public void testSaveAndGetPeptideProtein() throws Exception {

		PeptideProtein peptideProteinTwo = new PeptideProtein(PEPTIDE_TWO, PROTEIN_ACCESSION, 4);
		peptideProteinTwo.setPeptide(peptideRepository.findOne(PEPTIDE_TWO));
		peptideProteinTwo.setProtein(proteinRepository.findOne(PROTEIN_ACCESSION));
		peptideProteinTwo.setStartPosition(4);

		peptideProteinRepository.save(peptideProteinTwo);

		PeptideProtein otherTwo = peptideProteinRepository.findOne(new PeptideProteinPK(PEPTIDE_TWO, PROTEIN_ACCESSION, 4));

		checkPeptideProteinInDb(otherTwo);

		peptideProteinRepository.delete(otherTwo);
	}

	private void checkPeptideProteinInDb(PeptideProtein other) {
		assertNotNull(other);
		assertThat(other.getPeptide().getPeptideId(), is(PEPTIDE_TWO));
		assertThat(other.getProtein().getProteinAccession(), is(PROTEIN_ACCESSION));
		assertThat(other.getStartPosition(), is(4));
	}

}
