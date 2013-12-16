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


		List<PeptideVariant> peptideVariants = peptideRepository.findPeptideVariantBySequence(SEQUENCE);
		assertNotNull(peptideVariants);
		assertThat(peptideVariants.size(), is(PEPS_VAR_WITH_SEQUENCE));


		List<SymbolicPeptide> symbolicPeptides = peptideRepository.findSymbolicPeptideBySequence(SEQUENCE);
		assertNotNull(symbolicPeptides);
		assertThat(symbolicPeptides.size(), is(PEPS_SYM_WITH_SEQUENCE));


		List<String> sequences = peptideRepository.findAllDistinctSequenceByTaxid(TAXID_HUMAN);
		assertNotNull(sequences);
		assertThat(sequences.size(), is(NUM_SYMBOLIC));

        List<String> sequencesPaged = peptideRepository.findAllDistinctSequenceByTaxid(TAXID_HUMAN, new PageRequest(2,12));
        assertNotNull(sequencesPaged);
        assertThat(sequencesPaged.size(), is(6)); // 30 total results that means on the third page with size 12 there have to be 6 results


		List<SymbolicPeptide> symbolicPeptideList = peptideRepository.findAllSymbolicPeptides();
		assertNotNull(symbolicPeptideList);
		assertThat(symbolicPeptideList.size(), is(NUM_SYMBOLIC));

        List<SymbolicPeptide> symbolicPeptideListPaged = peptideRepository.findAllSymbolicPeptides(new PageRequest(2,12));
        assertNotNull(symbolicPeptideListPaged);
        assertThat(symbolicPeptideListPaged.size(), is(6));


		SymbolicPeptide symbolicPeptide = peptideRepository.findSymbolicPeptideBySequenceAndTaxid(SEQUENCE, TAXID_HUMAN);
		assertNotNull(symbolicPeptide);
		assertThat(symbolicPeptide.getSequence(), is(SEQUENCE));
		assertThat(symbolicPeptide.getTaxid(), is(TAXID_HUMAN));


        SymbolicPeptide symbolicPeptideWithProteins = (SymbolicPeptide) peptideRepository.findOne(PEPTIDE_SEVEN);
		assertNotNull(symbolicPeptideWithProteins);
		assertThat(symbolicPeptideWithProteins.getProteins().size(), is(1));
		assertThat(symbolicPeptideWithProteins.getProteins().iterator().next().getId().getProteinAccession(), is(PROTEIN_ACCESSION));


        List<PeptideVariant> peptideVariantList = peptideRepository.findAllPeptideVariants();
		assertNotNull(peptideVariantList);
		assertThat(peptideVariantList.size(), is(NUM_VARIANTS));

        List<PeptideVariant> peptideVariantListPaged = peptideRepository.findAllPeptideVariants(new PageRequest(4,10));
        assertNotNull(peptideVariantListPaged);
        assertThat(peptideVariantListPaged.size(), is(1)); // 40 total, 5th page of 10 has to be 1 result


        symbolicPeptideList = peptideRepository.findAllSymbolicPeptidesByTaxidAndPeptideIdBetween(TAXID_HUMAN, 1L, 3L);
		assertNotNull(symbolicPeptideList);
		assertThat(symbolicPeptideList.size(), is(3));
		assertThat(symbolicPeptideList.get(0).getPeptideId(), is(1L));
		assertThat(symbolicPeptideList.get(1).getPeptideId(), is(2L));
		assertThat(symbolicPeptideList.get(2).getPeptideId(), is(3L));


        List<SymbolicPeptide> peptideVariantListByTaxid = peptideRepository.findSymbolicPeptideByTaxid(9606);
        assertNotNull(peptideVariantListByTaxid);
        assertThat(peptideVariantListByTaxid.size(), is(NUM_SYMBOLIC));

        List<SymbolicPeptide> peptideVariantListByTaxidPaged = peptideRepository.findSymbolicPeptideByTaxid(9606, new PageRequest(2,12));
        assertNotNull(peptideVariantListByTaxidPaged);
        assertThat(peptideVariantListByTaxidPaged.size(), is(6));


        List<PeptideVariant> variantList = peptideRepository.findPeptideVariantBySequenceAndTaxid(SEQUENCE, TAXID_HUMAN);
        assertNotNull(variantList);
        assertThat(variantList.size(), is(1));

	}

	@Test
	@Transactional
	public void testSaveAndGetPeptideVariant() throws Exception {

		//Peptides
		PeptideVariant peptideVariant = new PeptideVariant();
		peptideVariant.setTaxid(TAXID_HUMAN);
		peptideVariant.setDescription(NO_DESCRIPTION);
		peptideVariant.setSequence(SEQUENCE);

		//Score
		//We used the default score one, It should be always in the DB
		peptideVariant.setScore(scoreRepository.findOne(SCORE_ID));

//        They are not mandatory so we can postpone the insertion
//        peptideVariant.setAssays();
//        peptideVariant.setProteins();

		//PeptideModifications
		ModificationLocation pepMod = new ModificationLocation();

		//Modification
		Modification modification = modificationRepository.findByCvTerm(NEW_MOD_TERM);
		if (modification == null) {
			modification = new Modification();
			modification.setCvTerm(NEW_MOD_TERM);
			modification.setCvName(NEW_MOD_NAME);
			modification = modificationRepository.save(modification);
		}

		//We know the real modification was persisted before
		pepMod.setModCvTerm(NEW_MOD_TERM);
		pepMod.setPosition(NEW_MOD_POS);

		TreeSet<ModificationLocation> peptideMods = new TreeSet<ModificationLocation>();
		peptideMods.add(pepMod);
		peptideVariant.setModificationLocations(peptideMods);

		//CV Params

		//Tissue
		//We check first if that not exist in the DB and if not we will persist it
		Tissue tissue = (Tissue) cvParamRepository.findByCvTerm(TISSUE_TERM);
		if (tissue == null) {
			tissue = new Tissue();
			tissue.setCvTerm(TISSUE_TERM);
			tissue.setCvName(TISSUE_NAME);
			tissue.setDescription(NO_DESCRIPTION);
			tissue = cvParamRepository.save(tissue);
		}

		Set<Tissue> tissues = new HashSet<Tissue>();
		tissues.add(tissue);

		//Cell Type
		CellType cellType = (CellType) cvParamRepository.findByCvTerm(CELL_TYPE_TERM);
		if (cellType == null) {
			cellType = new CellType();
			cellType.setCvTerm(CELL_TYPE_TERM);
			cellType.setCvName(CELL_TYPE_NAME);
			cellType.setDescription(NO_DESCRIPTION);
			cellType = cvParamRepository.save(cellType);
		}
		Set<CellType> cellTypes = new HashSet<CellType>();
		cellTypes.add(cellType);

		//Disease
		Disease disease = (Disease) cvParamRepository.findByCvTerm(DISEASE_TERM);
		if (disease == null) {
			disease = new Disease();
			disease.setCvTerm(DISEASE_TERM);
			disease.setCvName(DISEASE_NAME);
			disease.setDescription(NO_DESCRIPTION);
			disease = cvParamRepository.save(disease);

		}
		Set<Disease> diseases = new HashSet<Disease>();
		diseases.add(disease);

		peptideVariant.setTissues(tissues);
		peptideVariant.setDiseases(diseases);
		peptideVariant.setCellTypes(cellTypes);

		peptideVariant =  peptideRepository.save(peptideVariant);

		//id set after save
		Long newId = peptideVariant.getPeptideId();

		PeptideVariant other = (PeptideVariant) peptideRepository.findOne(newId);
		checkPeptideInDb(other);

		// delete the assay
		peptideRepository.delete(other);

	}

	private void checkPeptideInDb(PeptideVariant peptideVariant) {
		assertNotNull(peptideVariant);
		assertThat(peptideVariant.getScore().getId(), is(SCORE_ID));
		assertThat(peptideVariant.getDescription(), is(NO_DESCRIPTION));
		assertThat(peptideVariant.getTaxid(), is(TAXID_HUMAN));

		checkPeptideModification(peptideVariant.getModificationLocations());
		checkCellType(peptideVariant.getCellTypes());
		checkDisease(peptideVariant.getDiseases());
		checkTissue(peptideVariant.getTissues());
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
		assertThat(pepMods.iterator().next().getModCvTerm(), is(NEW_MOD_TERM));
		assertThat(pepMods.iterator().next().getPosition(), is(NEW_MOD_POS));
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void testEqualPeptides() throws Exception {

		PeptideVariant peptideVariant = (PeptideVariant) savePeptide();

		Object id1 = entityManagerFactory.getPersistenceUnitUtil().getIdentifier(peptideVariant);
		assertEquals(id1, peptideVariant.getPeptideId());

		//Same Peptide
		PeptideVariant samePeptideVariant = new PeptideVariant();
		samePeptideVariant.setTaxid(TAXID_HUMAN);
		samePeptideVariant.setDescription(NO_DESCRIPTION);
		samePeptideVariant.setSequence(SEQUENCE);

		//We used the default score one, It should be always in the DB
		samePeptideVariant.setScore(scoreRepository.findOne(SCORE_ID));

		//PeptideModifications
		ModificationLocation pepMod = new ModificationLocation();

		pepMod.setModCvTerm(NEW_MOD_TERM);
		pepMod.setPosition(NEW_MOD_POS);

		TreeSet<ModificationLocation> modificationLocations = new TreeSet<ModificationLocation>();
		modificationLocations.add(pepMod);

		samePeptideVariant.setModificationLocations(modificationLocations);

		//This should be built always after inserting the sequence, taxid and mods;
		samePeptideVariant.setPeptideRepresentation(PeptideUtils.peptideRepresentationGenerator(samePeptideVariant));


		//CV Params
		//Tissue
		//We check first if that not exist in the DB and if not we will persist it
		Tissue tissue = (Tissue) cvParamRepository.findByCvTerm(TISSUE_TERM);
		if (tissue == null) {
			tissue = new Tissue();
			tissue.setCvTerm(TISSUE_TERM);
			tissue.setCvName(TISSUE_NAME);
			tissue.setDescription(NO_DESCRIPTION);
			tissue = (Tissue) cvParamRepository.save(tissue);
		}
		Set<Tissue> tissues = new HashSet<Tissue>();
		tissues.add(tissue);


		//Cell Type
		CellType cellType = (CellType) cvParamRepository.findByCvTerm(CELL_TYPE_TERM);
		if (cellType == null) {
			cellType = new CellType();
			cellType.setCvTerm(CELL_TYPE_TERM);
			cellType.setCvName(CELL_TYPE_NAME);
			cellType.setDescription(NO_DESCRIPTION);
			cellType = (CellType) cvParamRepository.save(cellType);
		}
		Set<CellType> cellTypes = new HashSet<CellType>();
		cellTypes.add(cellType);

		//Disease
		Disease disease = (Disease) cvParamRepository.findByCvTerm(DISEASE_TERM);
		if (disease == null) {
			disease = new Disease();
			disease.setCvTerm(DISEASE_TERM);
			disease.setCvName(DISEASE_NAME);
			disease.setDescription(NO_DESCRIPTION);
			disease = (Disease) cvParamRepository.save(disease);

		}

		Set<Disease> diseases = new HashSet<Disease>();
		diseases.add(disease);

		samePeptideVariant.setTissues(tissues);
		samePeptideVariant.setCellTypes(cellTypes);
		samePeptideVariant.setDiseases(diseases);

		assertEquals(true, peptideVariant.getModificationLocations().iterator().next().equals(samePeptideVariant.getModificationLocations().iterator().next()));
		assertEquals(true, samePeptideVariant.getModificationLocations().iterator().next().equals(peptideVariant.getModificationLocations().iterator().next()));

		//Now they are a collection, so we can not compare Collection, only List or Set that take into account the equals method

		// assertEquals(true, samePeptideVariant.getModificationLocations().equals(peptideVariant.getModificationLocations()));
		// assertEquals(true, peptideVariant.getModificationLocations().equals(samePeptideVariant.getModificationLocations()));

		assertEquals(true, samePeptideVariant.equals(peptideVariant));
		assertEquals(true, peptideVariant.equals(samePeptideVariant));

		Peptide auxPep = peptideRepository.findByPeptideRepresentation(samePeptideVariant.getPeptideRepresentation());

		try {
			//We shouldn't persisted because it was inserted before
			Peptide pep = peptideRepository.save(samePeptideVariant);
		} finally {
			assertNotNull(auxPep);
			assertEquals(id1, auxPep.getPeptideId());

			// delete the peptide
			peptideRepository.delete(auxPep);

			auxPep = peptideRepository.findByPeptideRepresentation(samePeptideVariant.getPeptideRepresentation());
			assertNull(auxPep);
		}
	}

	@Test
	public void testNotEqualPeptides() throws Exception {

		//The second peptide will have two modification instead of one
		PeptideVariant peptideVariant = (PeptideVariant) savePeptide();

		Object id1 = entityManagerFactory.getPersistenceUnitUtil().getIdentifier(peptideVariant);
		assertEquals(id1, peptideVariant.getPeptideId());

		//Same Peptide
		PeptideVariant differentPeptideVariant = new PeptideVariant();
		differentPeptideVariant.setTaxid(TAXID_HUMAN);
		differentPeptideVariant.setDescription(NO_DESCRIPTION);
		differentPeptideVariant.setSequence(SEQUENCE);

		//We used the default score one, It should be always in the DB
		differentPeptideVariant.setScore(scoreRepository.findOne(SCORE_ID));

		//PeptideModifications
		ModificationLocation pepModA = new ModificationLocation();
		pepModA.setModCvTerm(NEW_MOD_TERM);
		pepModA.setPosition(NEW_MOD_POS);

		ModificationLocation pepModB = new ModificationLocation();
		pepModB.setModCvTerm(NEW_MOD_TERM);
		pepModB.setPosition(SEC_MOD_POS);

		TreeSet<ModificationLocation> modificationLocations = new TreeSet<ModificationLocation>();
		modificationLocations.add(pepModB);
		modificationLocations.add(pepModA);

		differentPeptideVariant.setModificationLocations(modificationLocations);

		//This should be built always after inserting the sequence, taxid and mods;
		differentPeptideVariant.setPeptideRepresentation(PeptideUtils.peptideRepresentationGenerator(differentPeptideVariant));

		//CV Params
		//Tissue
		//We check first if that not exist in the DB and if not we will persist it
		Tissue tissue = (Tissue) cvParamRepository.findByCvTerm(TISSUE_TERM);
		if (tissue == null) {
			tissue = new Tissue();
			tissue.setCvTerm(TISSUE_TERM);
			tissue.setCvName(TISSUE_NAME);
			tissue.setDescription(NO_DESCRIPTION);
			tissue = cvParamRepository.save(tissue);
		}
		Set<Tissue> tissues = new HashSet<Tissue>();
		tissues.add(tissue);


		//Cell Type
		CellType cellType = (CellType) cvParamRepository.findByCvTerm(CELL_TYPE_TERM);
		if (cellType == null) {
			cellType = new CellType();
			cellType.setCvTerm(CELL_TYPE_TERM);
			cellType.setCvName(CELL_TYPE_NAME);
			cellType.setDescription(NO_DESCRIPTION);
			cellType = cvParamRepository.save(cellType);
		}
		Set<CellType> cellTypes = new HashSet<CellType>();
		cellTypes.add(cellType);

		//Disease
		Disease disease = (Disease) cvParamRepository.findByCvTerm(DISEASE_TERM);
		if (disease == null) {
			disease = new Disease();
			disease.setCvTerm(DISEASE_TERM);
			disease.setCvName(DISEASE_NAME);
			disease.setDescription(NO_DESCRIPTION);
			disease = cvParamRepository.save(disease);

		}

		Set<Disease> diseases = new HashSet<Disease>();
		diseases.add(disease);

		differentPeptideVariant.setTissues(tissues);
		differentPeptideVariant.setCellTypes(cellTypes);
		differentPeptideVariant.setDiseases(diseases);

		assertEquals(false, differentPeptideVariant.getModificationLocations().equals(peptideVariant.getModificationLocations()));
		assertEquals(false, peptideVariant.getModificationLocations().equals(differentPeptideVariant.getModificationLocations()));

		assertEquals(false, differentPeptideVariant.equals(peptideVariant));
		assertEquals(false, peptideVariant.equals(differentPeptideVariant));

		Peptide auxPep = peptideRepository.findByPeptideRepresentation(differentPeptideVariant.getPeptideRepresentation());
		assertNull(auxPep);

		Peptide pep = null;
		try {
			//We should persist it because it wasn't inserted before
			pep = peptideRepository.save(differentPeptideVariant);
			assertNotSame(id1, pep.getPeptideId());
		} finally {

			// delete the peptides
			peptideRepository.delete(peptideVariant);
			peptideRepository.delete(pep);

			auxPep = peptideRepository.findByPeptideRepresentation(differentPeptideVariant.getPeptideRepresentation());
			assertNull(auxPep);

			auxPep = peptideRepository.findByPeptideRepresentation(peptideVariant.getPeptideRepresentation());
			assertNull(auxPep);
		}

	}

	@Transactional
	private Peptide savePeptide() {

		//Peptide
		PeptideVariant peptideVariant = new PeptideVariant();

		peptideVariant.setTaxid(TAXID_HUMAN);
		peptideVariant.setDescription(NO_DESCRIPTION);
		peptideVariant.setSequence(SEQUENCE);

		//We used the default score one, It should be always in the DB
		peptideVariant.setScore(scoreRepository.findOne(SCORE_ID));

		//They are not mandatory so we can postpone the insertion
		//peptideVariant.setAssays();
		//peptideVariant.setProteins();

		//PeptideModifications
		ModificationLocation pepMod = new ModificationLocation();

		pepMod.setModCvTerm(NEW_MOD_TERM);
		pepMod.setPosition(NEW_MOD_POS);

		TreeSet<ModificationLocation> modificationLocations = new TreeSet<ModificationLocation>();
		modificationLocations.add(pepMod);
		peptideVariant.setModificationLocations(modificationLocations);

		//This should be built always after inserting the sequence, taxid and mods;
		peptideVariant.setPeptideRepresentation(PeptideUtils.peptideRepresentationGenerator(peptideVariant));

		//CV Params

		//Tissue
		//We check first if that not exist in the DB and if not we will persist it
		Tissue tissue = (Tissue) cvParamRepository.findByCvTerm(TISSUE_TERM);
		if (tissue == null) {
			tissue = new Tissue();
			tissue.setCvTerm(TISSUE_TERM);
			tissue.setCvName(TISSUE_NAME);
			tissue.setDescription(NO_DESCRIPTION);
			tissue = cvParamRepository.save(tissue);
		}
		Set<Tissue> tissues = new HashSet<Tissue>();
		tissues.add(tissue);


		//Cell Type
		CellType cellType = (CellType) cvParamRepository.findByCvTerm(CELL_TYPE_TERM);
		if (cellType == null) {
			cellType = new CellType();
			cellType.setCvTerm(CELL_TYPE_TERM);
			cellType.setCvName(CELL_TYPE_NAME);
			cellType.setDescription(NO_DESCRIPTION);
			cellType = cvParamRepository.save(cellType);
		}
		Set<CellType> cellTypes = new HashSet<CellType>();
		cellTypes.add(cellType);

		//Disease
		Disease disease = (Disease) cvParamRepository.findByCvTerm(DISEASE_TERM);
		if (disease == null) {
			disease = new Disease();
			disease.setCvTerm(DISEASE_TERM);
			disease.setCvName(DISEASE_NAME);
			disease.setDescription(NO_DESCRIPTION);
			disease = cvParamRepository.save(disease);

		}

		Set<Disease> diseases = new HashSet<Disease>();
		diseases.add(disease);


		peptideVariant.setTissues(tissues);
		peptideVariant.setCellTypes(cellTypes);
		peptideVariant.setDiseases(diseases);
		peptideVariant = (PeptideVariant) peptideRepository.saveAndFlush(peptideVariant);

		return peptideVariant;
	}


}
