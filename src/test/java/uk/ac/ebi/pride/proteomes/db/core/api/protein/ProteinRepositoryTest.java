package uk.ac.ebi.pride.proteomes.db.core.api.protein;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ebi.pride.proteomes.db.core.api.RepositoryTest;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.protein.PeptideProtein;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.EntryGroup;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.GeneGroup;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.ProteinGroup;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * User: ntoro
 * Date: 13/09/2013
 * Time: 14:28
 */

public class ProteinRepositoryTest extends RepositoryTest {

	@Test
	@Transactional(readOnly = true)
	public void testFindByMethods() throws Exception {

		Protein protein = proteinRepository.findByProteinAccession(PROTEIN_ACCESSION);

		Collection<PeptideProtein> peptides = protein.getPeptides();
		assertNotNull(peptides);
		assertThat(peptides.size(), is(SYMBOLIC_PEP_IN_PROTEIN));

//		Collection<String> peptideIds = protein.getPeptideIds();
//		assertNotNull(peptideIds);
//		assertThat(peptideIds.size(), is(SYMBOLIC_PEP_IN_PROTEIN));

		Collection<GeneGroup> genes = protein.getGeneGroups();
		assertNotNull(genes);
		assertThat(genes.size(), is(NUM_GENES));

		Collection<EntryGroup> entryGroups = protein.getEntryGroups();
		assertNotNull(entryGroups);
		assertThat(entryGroups.size(), is(NUM_ENTRY_GROUPS));

		Collection<ProteinGroup> proteinGroups = protein.getProteinGroups();
		assertNotNull(proteinGroups);
		assertThat(proteinGroups.size(), is(NUM_PROT_GROUPS));

        List<Protein> results = proteinRepository.findByDescriptionContaining("kinase");
        assertNotNull(results);
        assertThat(results.size(), is(1));

        List<Protein> results2 = proteinRepository.findBySequenceContaining("DPYSQQPQTPRPS");
        assertNotNull(results2);
        assertThat(results2.size(), is(2));

        List<Protein> results3 = proteinRepository.findByTaxid(TAXID_HUMAN);
        assertNotNull(results3);
        assertThat(results3.size(), is(2));

        List<Protein> results4 = proteinRepository.findByTaxidAndDescriptionContaining(TAXID_HUMAN, "kinase");
        assertNotNull(results4);
        assertThat(results4.size(), is(1));

    }

	@Test
	@Transactional
	public void testSaveAndGetProtein() throws Exception {

		Protein protein = new Protein();
		protein.setTaxid(TAXID_HUMAN);
		protein.setDescription(NO_DESCRIPTION);

		protein.setProteinAccession(NEW_PROTEIN_ACCESSION);
		protein.setScore(scoreRepository.findOne(SCORE_ID));

		protein.setSequence(NEW_PROTEIN_SEQUENCE);
		protein.setCurationLevel(CurationLevel.PREDICTED);

		Set<EntryGroup> entryGroups = new HashSet<EntryGroup>();
		entryGroups.add((EntryGroup) proteinGroupRepository.findOne(ENTRY_GROUP_ID));

		Set<GeneGroup> geneGroups = new HashSet<GeneGroup>();
		geneGroups.add((GeneGroup) proteinGroupRepository.findOne(GENE_GROUP_ID));

		protein.setEntryGroups(entryGroups);
		protein.setGeneGroups(geneGroups);

		protein = proteinRepository.save(protein);

		//After we store the protein we add the mapping with the peptides
		PeptideProtein peptideProteinTwo = new PeptideProtein(PEPTIDE_TWO, NEW_PROTEIN_ACCESSION, 43);
		peptideProteinTwo.setProtein(protein);
		peptideProteinTwo.setPeptide(peptideRepository.findOne(PEPTIDE_TWO));
		peptideProteinTwo.setStartPosition(43);

		PeptideProtein peptideProteinSeven = new PeptideProtein(PEPTIDE_SEVEN, NEW_PROTEIN_ACCESSION, 1);
		peptideProteinSeven.setProtein(protein);
		peptideProteinSeven.setPeptide(peptideRepository.findOne(PEPTIDE_SEVEN));
		peptideProteinSeven.setStartPosition(1);

		PeptideProtein peptideProteinTen = new PeptideProtein(PEPTIDE_TEN, NEW_PROTEIN_ACCESSION, 13);
		peptideProteinTen.setProtein(protein);
		peptideProteinTen.setPeptide(peptideRepository.findOne(PEPTIDE_TEN));
		peptideProteinTen.setStartPosition(13);

		peptideProteinTwo = peptideProteinRepository.save(peptideProteinTwo);
		peptideProteinSeven = peptideProteinRepository.save(peptideProteinSeven);
		peptideProteinTen = peptideProteinRepository.save(peptideProteinTen);

		Set<PeptideProtein> symbolicPeptides = new HashSet<PeptideProtein>();
		symbolicPeptides.add(peptideProteinTwo);
		symbolicPeptides.add(peptideProteinSeven);
		symbolicPeptides.add(peptideProteinTen);
		protein.setPeptides(symbolicPeptides);


		String newId = protein.getProteinAccession();

		Protein other = proteinRepository.findByProteinAccession(newId);

		checkProteinInDB(other);

		proteinRepository.delete(other);
	}

	private void checkProteinInDB(Protein other) {
		assertThat(other.getDescription(), is(NO_DESCRIPTION));
		assertThat(other.getTaxid(), is(TAXID_HUMAN));
		assertThat(other.getSequence(), is(NEW_PROTEIN_SEQUENCE));
		assertThat(other.getCurationLevel(), is(CurationLevel.PREDICTED));
		checkSymbolicPeptides(other.getPeptides());
		checkEntryGroups(other.getEntryGroups());
		checkGeneGroups(other.getGeneGroups());
	}

	private void checkEntryGroups(Collection<EntryGroup> entryGroups) {
		assertNotNull(entryGroups);
		assertThat(entryGroups.size(), is(NUM_ENTRY_GROUPS));
		assertThat(entryGroups.iterator().next().getId(), is(ENTRY_GROUP_ID));
	}

	private void checkGeneGroups(Collection<GeneGroup> geneGroups) {
		assertNotNull(geneGroups);
		assertThat(geneGroups.size(), is(NUM_GENES));
		assertThat(geneGroups.iterator().next().getId(), is(GENE_GROUP_ID));
	}

	private void checkSymbolicPeptides(Collection<PeptideProtein> symbolicPeptides) {
		assertNotNull(symbolicPeptides);
		assertThat(symbolicPeptides.size(), is(SYMBOLIC_PEP_IN_PROTEIN));
	}
}
