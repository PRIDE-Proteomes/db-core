package uk.ac.ebi.pride.proteomes.db.core.api.peptide;

import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ebi.pride.proteomes.db.core.api.RepositoryTest;
import uk.ac.ebi.pride.proteomes.db.core.api.modification.Modification;
import uk.ac.ebi.pride.proteomes.db.core.api.modification.ModificationLocation;
import uk.ac.ebi.pride.proteomes.db.core.api.param.CellType;
import uk.ac.ebi.pride.proteomes.db.core.api.param.Disease;
import uk.ac.ebi.pride.proteomes.db.core.api.param.Tissue;
import uk.ac.ebi.pride.proteomes.db.core.api.utils.PeptideUtils;

import java.util.*;

import static junit.framework.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


/**
 * User: ntoro
 * Date: 12/09/2013
 * Time: 16:43
 */

public class PeptideRepositoryTest extends RepositoryTest {

	@Test
	@Transactional(readOnly = true)
	public void testFindMethods() throws Exception {


		List<Peptide> peptides = peptideRepository.findPeptideBySequence(SEQUENCE);
		assertNotNull(peptides);
		assertThat(peptides.size(), is(PEPS_WITH_SEQUENCE));


		List<Peptiform> peptiforms = peptideRepository.findPeptiformBySequence(SEQUENCE);
		assertNotNull(peptiforms);
		assertThat(peptiforms.size(), is(PEPS_VAR_WITH_SEQUENCE));


		List<SymbolicPeptide> symbolicPeptides = peptideRepository.findSymbolicPeptideBySequence(SEQUENCE);
		assertNotNull(symbolicPeptides);
		assertThat(symbolicPeptides.size(), is(PEPS_SYM_WITH_SEQUENCE));


		List<String> sequences = peptideRepository.findAllDistinctSequenceByTaxid(TAXID_HUMAN);
		assertNotNull(sequences);
		assertThat(sequences.size(), is(DISTINCT_SEQUENCES_9606));

        List<String> sequencesPaged = peptideRepository.findAllDistinctSequenceByTaxid(TAXID_HUMAN, new PageRequest(2,12));
        assertNotNull(sequencesPaged);
        assertThat(sequencesPaged.size(), is(7)); // 31 total results that means on the third page with size 12 there have to be 6 results


		List<SymbolicPeptide> symbolicPeptideList = peptideRepository.findAllSymbolicPeptides();
		assertNotNull(symbolicPeptideList);
		assertThat(symbolicPeptideList.size(), is(NUM_SYMBOLIC));

        List<SymbolicPeptide> symbolicPeptideListPaged = peptideRepository.findAllSymbolicPeptides(new PageRequest(2,12));
        assertNotNull(symbolicPeptideListPaged);
        assertThat(symbolicPeptideListPaged.size(), is(8));


		SymbolicPeptide symbolicPeptide = peptideRepository.findSymbolicPeptideBySequenceAndTaxid(SEQUENCE, TAXID_HUMAN);
		assertNotNull(symbolicPeptide);
		assertThat(symbolicPeptide.getSequence(), is(SEQUENCE));
		assertThat(symbolicPeptide.getTaxid(), is(TAXID_HUMAN));


        SymbolicPeptide symbolicPeptideWithProteins = (SymbolicPeptide) peptideRepository.findOne(PEPTIDE_SEVEN);
		assertNotNull(symbolicPeptideWithProteins);
		assertThat(symbolicPeptideWithProteins.getProteins().size(), is(1));
		assertThat(symbolicPeptideWithProteins.getProteins().iterator().next().getId().getProteinAccession(), is(PROTEIN_ACCESSION));


        List<Peptiform> peptiformList = peptideRepository.findAllPeptiforms();
		assertNotNull(peptiformList);
		assertThat(peptiformList.size(), is(NUM_VARIANTS));

        List<Peptiform> peptiformListPaged = peptideRepository.findAllPeptiforms(new PageRequest(4, 10));
        assertNotNull(peptiformListPaged);
        assertThat(peptiformListPaged.size(), is(4)); // 40 total, 5th page of 10 has to be 1 result


        peptiformList = peptideRepository.findAllPeptiformsByTaxidAndPeptideIdBetween(TAXID_HUMAN, 31L, 45482310L);
        assertNotNull(symbolicPeptideList);
        assertThat(peptiformList .size(), is(2));
        assertThat(peptiformList .get(0).getPeptideId(), is(45482309L));
        assertThat(peptiformList .get(1).getPeptideId(), is(45482310L));


        symbolicPeptideList = peptideRepository.findAllSymbolicPeptidesByTaxidAndPeptideIdBetween(TAXID_HUMAN, 1L, 3L);
        assertNotNull(symbolicPeptideList);
        assertThat(symbolicPeptideList.size(), is(3));
        assertThat(symbolicPeptideList.get(0).getPeptideId(), is(1L));
        assertThat(symbolicPeptideList.get(1).getPeptideId(), is(2L));
        assertThat(symbolicPeptideList.get(2).getPeptideId(), is(3L));

        List<SymbolicPeptide> peptideVariantListByTaxid = peptideRepository.findSymbolicPeptideByTaxid(TAXID_HUMAN);
        assertNotNull(peptideVariantListByTaxid);
        assertThat(peptideVariantListByTaxid.size(), is(31));

        List<SymbolicPeptide> peptideVariantListByTaxidPaged = peptideRepository.findSymbolicPeptideByTaxid(TAXID_HUMAN, new PageRequest(2,12));
        assertNotNull(peptideVariantListByTaxidPaged);
        assertThat(peptideVariantListByTaxidPaged.size(), is(7));


        List<Peptiform> variantList = peptideRepository.findPeptiformBySequenceAndTaxid(SEQUENCE, TAXID_HUMAN);
        assertNotNull(variantList);
        assertThat(variantList.size(), is(1));

	}

    @Test
   	@Transactional(readOnly = true)
    public void testCountMethods() throws Exception {

        long allPeptideCnt = peptideRepository.count();
        assertThat(allPeptideCnt, is((long)NUM_VARIANTS+NUM_SYMBOLIC));

        long allSymPeptideCnt = peptideRepository.countSymbolicPeptide();
        assertThat(allSymPeptideCnt, is((long)NUM_SYMBOLIC));

        long allPeptiformsCnt = peptideRepository.countPeptiforms();
        assertThat(allPeptiformsCnt, is((long)NUM_VARIANTS));

        long humanPeptideCnt = peptideRepository.countByTaxid(9606);
        assertThat(humanPeptideCnt, is(74L));

        long mousePeptideCnt = peptideRepository.countByTaxid(10090);
        assertThat(mousePeptideCnt, is(2L));

        long humanSymPepCnt = peptideRepository.countSymbolicPeptideByTaxid(9606);
        assertThat(humanSymPepCnt, is((long)DISTINCT_SEQUENCES_9606));

        long mouseSymPepCnt = peptideRepository.countSymbolicPeptideByTaxid(10090);
        assertThat(mouseSymPepCnt, is(1L));

        long humanPeptiformCnt = peptideRepository.countPeptiformsByTaxid(9606);
        assertThat(humanPeptiformCnt, is((long) 43));

        long mousePeptiformCnt = peptideRepository.countPeptiformsByTaxid(10090);
        assertThat(mousePeptiformCnt, is((long)1));

    }

	@Test
	@Transactional
	public void testSaveAndGetPeptideVariant() throws Exception {

		//Peptides
		Peptiform peptiform = new Peptiform();
		peptiform.setTaxid(TAXID_HUMAN);
		peptiform.setDescription(NO_DESCRIPTION);
		peptiform.setSequence(SEQUENCE);

		//Score
		//We used the default score one, It should be always in the DB
		peptiform.setScore(scoreRepository.findOne(SCORE_ID));

//        They are not mandatory so we can postpone the insertion
//        peptiform.setAssays();
//        peptiform.setProteins();

		//PeptideModifications
		ModificationLocation pepMod = new ModificationLocation();

		//Modification
		Modification modification = modificationRepository.findByModId(NEW_MOD_TERM);
		if (modification == null) {
			modification = new Modification();
			modification.setModId(NEW_MOD_TERM);
			modification.setModName(NEW_MOD_NAME);
			modification = modificationRepository.save(modification);
		}

		//We know the real modification was persisted before
		pepMod.setModId(NEW_MOD_TERM);
		pepMod.setPosition(NEW_MOD_POS);

		TreeSet<ModificationLocation> peptideMods = new TreeSet<ModificationLocation>();
		peptideMods.add(pepMod);
		peptiform.setModificationLocations(peptideMods);

		//CV Params

		//Tissue
		//We check first if that not exist in the DB and if not we will persist it
		Tissue tissue = (Tissue) cvParamProteomesRepository.findByCvTerm(TISSUE_TERM);
		if (tissue == null) {
			tissue = new Tissue();
			tissue.setCvTerm(TISSUE_TERM);
			tissue.setCvName(TISSUE_NAME);
			tissue.setDescription(NO_DESCRIPTION);
			tissue = cvParamProteomesRepository.save(tissue);
		}

		Set<Tissue> tissues = new HashSet<Tissue>();
		tissues.add(tissue);

		//Cell Type
		CellType cellType = (CellType) cvParamProteomesRepository.findByCvTerm(CELL_TYPE_TERM);
		if (cellType == null) {
			cellType = new CellType();
			cellType.setCvTerm(CELL_TYPE_TERM);
			cellType.setCvName(CELL_TYPE_NAME);
			cellType.setDescription(NO_DESCRIPTION);
			cellType = cvParamProteomesRepository.save(cellType);
		}
		Set<CellType> cellTypes = new HashSet<CellType>();
		cellTypes.add(cellType);

		//Disease
		Disease disease = (Disease) cvParamProteomesRepository.findByCvTerm(DISEASE_TERM);
		if (disease == null) {
			disease = new Disease();
			disease.setCvTerm(DISEASE_TERM);
			disease.setCvName(DISEASE_NAME);
			disease.setDescription(NO_DESCRIPTION);
			disease = cvParamProteomesRepository.save(disease);

		}
		Set<Disease> diseases = new HashSet<Disease>();
		diseases.add(disease);

		peptiform.setTissues(tissues);
		peptiform.setDiseases(diseases);
		peptiform.setCellTypes(cellTypes);

		peptiform =  peptideRepository.save(peptiform);

		//id set after save
		Long newId = peptiform.getPeptideId();

		Peptiform other = (Peptiform) peptideRepository.findOne(newId);
		checkPeptideInDb(other);

		// delete the assay
		peptideRepository.delete(other);

        other = (Peptiform) peptideRepository.findOne(newId);
        assertNull(other);

    }

	private void checkPeptideInDb(Peptiform peptiform) {
		assertNotNull(peptiform);
		assertThat(peptiform.getScore().getId(), is(SCORE_ID));
		assertThat(peptiform.getDescription(), is(NO_DESCRIPTION));
		assertThat(peptiform.getTaxid(), is(TAXID_HUMAN));

		checkPeptideModification(peptiform.getModificationLocations());
		checkCellType(peptiform.getCellTypes());
		checkDisease(peptiform.getDiseases());
		checkTissue(peptiform.getTissues());
	}

	private void checkCellType(Collection<CellType> cellTypes) {
		assertNotNull(cellTypes);
		assertThat(cellTypes.size(), is(1));
		assertThat(cellTypes.iterator().next().getCvTerm(), is(CELL_TYPE_TERM));
		assertThat(cellTypes.iterator().next().getCvName(), is(CELL_TYPE_NAME));
	}

	private void checkDisease(Collection<Disease> diseases) {
		assertNotNull(diseases);
		assertThat(diseases.size(), is(1));
		assertThat(diseases.iterator().next().getCvTerm(), is(DISEASE_TERM));
		assertThat(diseases.iterator().next().getCvName(), is(DISEASE_NAME));
	}

	private void checkTissue(Collection<Tissue> tissues) {
		assertNotNull(tissues);
		assertThat(tissues.size(), is(1));
		assertThat(tissues.iterator().next().getCvTerm(), is(TISSUE_TERM));
		assertThat(tissues.iterator().next().getCvName(), is(TISSUE_NAME));

	}

	private void checkPeptideModification(Collection<ModificationLocation> pepMods) {
		assertNotNull(pepMods);
		assertThat(pepMods.size(), is(1));
		assertThat(pepMods.iterator().next().getModId(), is(NEW_MOD_TERM));
		assertThat(pepMods.iterator().next().getPosition(), is(NEW_MOD_POS));
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void testEqualPeptides() throws Exception {

		Peptiform peptiform = (Peptiform) savePeptide();

		Object id1 = entityManagerFactory.getPersistenceUnitUtil().getIdentifier(peptiform);
		assertEquals(id1, peptiform.getPeptideId());

		//Same Peptide
		Peptiform samePeptiform = new Peptiform();
		samePeptiform.setTaxid(TAXID_HUMAN);
		samePeptiform.setDescription(NO_DESCRIPTION);
		samePeptiform.setSequence(SEQUENCE);

		//We used the default score one, It should be always in the DB
		samePeptiform.setScore(scoreRepository.findOne(SCORE_ID));

		//PeptideModifications
		ModificationLocation pepMod = new ModificationLocation();

		pepMod.setModId(NEW_MOD_TERM);
		pepMod.setPosition(NEW_MOD_POS);

		TreeSet<ModificationLocation> modificationLocations = new TreeSet<ModificationLocation>();
		modificationLocations.add(pepMod);

		samePeptiform.setModificationLocations(modificationLocations);

		//This should be built always after inserting the sequence, taxid and mods;
		samePeptiform.setPeptideRepresentation(PeptideUtils.peptideRepresentationGenerator(samePeptiform));


		//CV Params
		//Tissue
		//We check first if that not exist in the DB and if not we will persist it
		Tissue tissue = (Tissue) cvParamProteomesRepository.findByCvTerm(TISSUE_TERM);
		if (tissue == null) {
			tissue = new Tissue();
			tissue.setCvTerm(TISSUE_TERM);
			tissue.setCvName(TISSUE_NAME);
			tissue.setDescription(NO_DESCRIPTION);
			tissue = (Tissue) cvParamProteomesRepository.save(tissue);
		}
		Set<Tissue> tissues = new HashSet<Tissue>();
		tissues.add(tissue);


		//Cell Type
		CellType cellType = (CellType) cvParamProteomesRepository.findByCvTerm(CELL_TYPE_TERM);
		if (cellType == null) {
			cellType = new CellType();
			cellType.setCvTerm(CELL_TYPE_TERM);
			cellType.setCvName(CELL_TYPE_NAME);
			cellType.setDescription(NO_DESCRIPTION);
			cellType = (CellType) cvParamProteomesRepository.save(cellType);
		}
		Set<CellType> cellTypes = new HashSet<CellType>();
		cellTypes.add(cellType);

		//Disease
		Disease disease = (Disease) cvParamProteomesRepository.findByCvTerm(DISEASE_TERM);
		if (disease == null) {
			disease = new Disease();
			disease.setCvTerm(DISEASE_TERM);
			disease.setCvName(DISEASE_NAME);
			disease.setDescription(NO_DESCRIPTION);
			disease = (Disease) cvParamProteomesRepository.save(disease);

		}

		Set<Disease> diseases = new HashSet<Disease>();
		diseases.add(disease);

		samePeptiform.setTissues(tissues);
		samePeptiform.setCellTypes(cellTypes);
		samePeptiform.setDiseases(diseases);

		assertEquals(true, peptiform.getModificationLocations().iterator().next().equals(samePeptiform.getModificationLocations().iterator().next()));
		assertEquals(true, samePeptiform.getModificationLocations().iterator().next().equals(peptiform.getModificationLocations().iterator().next()));

		//Now they are a collection, so we can not compare Collection, only List or Set that take into account the equals method

		// assertEquals(true, samePeptiform.getModificationLocations().equals(peptiform.getModificationLocations()));
		// assertEquals(true, peptiform.getModificationLocations().equals(samePeptiform.getModificationLocations()));

		assertEquals(true, samePeptiform.equals(peptiform));
		assertEquals(true, peptiform.equals(samePeptiform));

		Peptide auxPep = peptideRepository.findByPeptideRepresentation(samePeptiform.getPeptideRepresentation());

		try {
			//We shouldn't persisted because it was inserted before
			Peptide pep = peptideRepository.save(samePeptiform);
		} finally {
			assertNotNull(auxPep);
			assertEquals(id1, auxPep.getPeptideId());

			// delete the peptide
			peptideRepository.delete(auxPep);

			auxPep = peptideRepository.findByPeptideRepresentation(samePeptiform.getPeptideRepresentation());
			assertNull(auxPep);
		}
	}

	@Test
	public void testNotEqualPeptides() throws Exception {

		//The second peptide will have two modification instead of one
		Peptiform peptiform = (Peptiform) savePeptide();

		Object id1 = entityManagerFactory.getPersistenceUnitUtil().getIdentifier(peptiform);
		assertEquals(id1, peptiform.getPeptideId());

		//Same Peptide
		Peptiform differentPeptiform = new Peptiform();
		differentPeptiform.setTaxid(TAXID_HUMAN);
		differentPeptiform.setDescription(NO_DESCRIPTION);
		differentPeptiform.setSequence(SEQUENCE);

		//We used the default score one, It should be always in the DB
		differentPeptiform.setScore(scoreRepository.findOne(SCORE_ID));

		//PeptideModifications
		ModificationLocation pepModA = new ModificationLocation();
		pepModA.setModId(NEW_MOD_TERM);
		pepModA.setPosition(NEW_MOD_POS);

		ModificationLocation pepModB = new ModificationLocation();
		pepModB.setModId(NEW_MOD_TERM);
		pepModB.setPosition(SEC_MOD_POS);

		TreeSet<ModificationLocation> modificationLocations = new TreeSet<ModificationLocation>();
		modificationLocations.add(pepModB);
		modificationLocations.add(pepModA);

		differentPeptiform.setModificationLocations(modificationLocations);

		//This should be built always after inserting the sequence, taxid and mods;
		differentPeptiform.setPeptideRepresentation(PeptideUtils.peptideRepresentationGenerator(differentPeptiform));

		//CV Params
		//Tissue
		//We check first if that not exist in the DB and if not we will persist it
		Tissue tissue = (Tissue) cvParamProteomesRepository.findByCvTerm(TISSUE_TERM);
		if (tissue == null) {
			tissue = new Tissue();
			tissue.setCvTerm(TISSUE_TERM);
			tissue.setCvName(TISSUE_NAME);
			tissue.setDescription(NO_DESCRIPTION);
//			tissue = cvParamRepository.save(tissue);
		}
		Set<Tissue> tissues = new HashSet<Tissue>();
		tissues.add(tissue);


		//Cell Type
		CellType cellType = (CellType) cvParamProteomesRepository.findByCvTerm(CELL_TYPE_TERM);
		if (cellType == null) {
			cellType = new CellType();
			cellType.setCvTerm(CELL_TYPE_TERM);
			cellType.setCvName(CELL_TYPE_NAME);
			cellType.setDescription(NO_DESCRIPTION);
//			cellType = cvParamRepository.save(cellType);
		}
		Set<CellType> cellTypes = new HashSet<CellType>();
		cellTypes.add(cellType);

		//Disease
		Disease disease = (Disease) cvParamProteomesRepository.findByCvTerm(DISEASE_TERM);
		if (disease == null) {
			disease = new Disease();
			disease.setCvTerm(DISEASE_TERM);
			disease.setCvName(DISEASE_NAME);
			disease.setDescription(NO_DESCRIPTION);
//			disease = cvParamRepository.save(disease);

		}

		Set<Disease> diseases = new HashSet<Disease>();
		diseases.add(disease);

		differentPeptiform.setTissues(tissues);
		differentPeptiform.setCellTypes(cellTypes);
		differentPeptiform.setDiseases(diseases);

		assertEquals(false, differentPeptiform.getModificationLocations().equals(peptiform.getModificationLocations()));
		assertEquals(false, peptiform.getModificationLocations().equals(differentPeptiform.getModificationLocations()));

		assertEquals(false, differentPeptiform.equals(peptiform));
		assertEquals(false, peptiform.equals(differentPeptiform));

		Peptide auxPep = peptideRepository.findByPeptideRepresentation(differentPeptiform.getPeptideRepresentation());
		assertNull(auxPep);

		Peptide pep = null;
		try {
			//We should persist it because it wasn't inserted before
			pep = peptideRepository.save(differentPeptiform);
			assertNotSame(id1, pep.getPeptideId());
		} finally {

			// delete the peptides
			peptideRepository.delete(peptiform);
			peptideRepository.delete(pep);

			auxPep = peptideRepository.findByPeptideRepresentation(differentPeptiform.getPeptideRepresentation());
			assertNull(auxPep);

			auxPep = peptideRepository.findByPeptideRepresentation(peptiform.getPeptideRepresentation());
			assertNull(auxPep);
		}

	}

	@Transactional
	private Peptide savePeptide() {

		//Peptide
		Peptiform peptiform = new Peptiform();

		peptiform.setTaxid(TAXID_HUMAN);
		peptiform.setDescription(NO_DESCRIPTION);
		peptiform.setSequence(SEQUENCE);

		//We used the default score one, It should be always in the DB
		peptiform.setScore(scoreRepository.findOne(SCORE_ID));

		//They are not mandatory so we can postpone the insertion
		//peptiform.setAssays();
		//peptiform.setProteins();

		//PeptideModifications
		ModificationLocation pepMod = new ModificationLocation();

		pepMod.setModId(NEW_MOD_TERM);
		pepMod.setPosition(NEW_MOD_POS);

		TreeSet<ModificationLocation> modificationLocations = new TreeSet<ModificationLocation>();
		modificationLocations.add(pepMod);
		peptiform.setModificationLocations(modificationLocations);

		//This should be built always after inserting the sequence, taxid and mods;
		peptiform.setPeptideRepresentation(PeptideUtils.peptideRepresentationGenerator(peptiform));

		//CV Params

		//Tissue
		//We check first if that not exist in the DB and if not we will persist it
		Tissue tissue = (Tissue) cvParamProteomesRepository.findByCvTerm(TISSUE_TERM);
		if (tissue == null) {
			tissue = new Tissue();
			tissue.setCvTerm(TISSUE_TERM);
			tissue.setCvName(TISSUE_NAME);
			tissue.setDescription(NO_DESCRIPTION);
			tissue = cvParamProteomesRepository.save(tissue);
		}
		Set<Tissue> tissues = new HashSet<Tissue>();
		tissues.add(tissue);


		//Cell Type
		CellType cellType = (CellType) cvParamProteomesRepository.findByCvTerm(CELL_TYPE_TERM);
		if (cellType == null) {
			cellType = new CellType();
			cellType.setCvTerm(CELL_TYPE_TERM);
			cellType.setCvName(CELL_TYPE_NAME);
			cellType.setDescription(NO_DESCRIPTION);
			cellType = cvParamProteomesRepository.save(cellType);
		}
		Set<CellType> cellTypes = new HashSet<CellType>();
		cellTypes.add(cellType);

		//Disease
		Disease disease = (Disease) cvParamProteomesRepository.findByCvTerm(DISEASE_TERM);
		if (disease == null) {
			disease = new Disease();
			disease.setCvTerm(DISEASE_TERM);
			disease.setCvName(DISEASE_NAME);
			disease.setDescription(NO_DESCRIPTION);
			disease = cvParamProteomesRepository.save(disease);

		}

		Set<Disease> diseases = new HashSet<Disease>();
		diseases.add(disease);


		peptiform.setTissues(tissues);
		peptiform.setCellTypes(cellTypes);
		peptiform.setDiseases(diseases);
		peptiform = (Peptiform) peptideRepository.saveAndFlush(peptiform);

		return peptiform;
	}


}
