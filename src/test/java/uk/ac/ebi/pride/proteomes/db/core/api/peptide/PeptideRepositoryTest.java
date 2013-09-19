package uk.ac.ebi.pride.proteomes.db.core.api.peptide;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ebi.pride.proteomes.db.core.api.modification.Modification;
import uk.ac.ebi.pride.proteomes.db.core.api.modification.ModificationRepository;
import uk.ac.ebi.pride.proteomes.db.core.api.param.CvParamCellType;
import uk.ac.ebi.pride.proteomes.db.core.api.param.CvParamDisease;
import uk.ac.ebi.pride.proteomes.db.core.api.param.CvParamRepository;
import uk.ac.ebi.pride.proteomes.db.core.api.param.CvParamTissue;
import uk.ac.ebi.pride.proteomes.db.core.api.quality.ScoreRepository;

import javax.persistence.EntityManagerFactory;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private static final String SEQUENCE = "HCGATSAGLR";
    private static final long PEPTIDE_ID = 56;

    private static final Integer PEPS_WITH_SEQUENCE = 2;
    private static final Integer PEPS_VAR_WITH_SEQUENCE = 1;
    private static final Integer PEPS_SYM_WITH_SEQUENCE = 1;

    private static final String MOD_TERM = "MOD:00696";
    private static final Integer MOD_POS = 7;
    private static final String MOD_NAME = "phosphorylated residue";

    private static final String TISSUE_TERM = "BTO:0000157";
    private static final String TISSUE_NAME = "aorta thoracica";
    private static final String CELL_TYPE_TERM = "CL:0000182";
    private static final String CELL_TYPE_NAME = "hepatocyte";
    private static final String DISEASE_TERM = "";
    private static final String DISEASE_NAME = "";

    private static final Integer TAXID = 9606;

    private static final String NO_DESCRIPTION = null;
    private static final long SCORE_ID = 1;


    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Autowired
    private PeptideRepository peptideRepository;

    @Autowired
    private CvParamRepository cvParamRepository;

    @Autowired
    private ModificationRepository modificationRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Test
    @Transactional(readOnly = true)
    public void testFindMethods() throws Exception {


        List<Peptide> peptides = peptideRepository.findPeptideBySequence(SEQUENCE);

        assertNotNull(peptides);
        assertThat(peptides.size(), is(PEPS_WITH_SEQUENCE));

        List<PeptideVariant> peptideVariants = peptideRepository.findPeptideVariantBySequence(SEQUENCE);

        assertNotNull(peptideVariants);
        assertThat(peptideVariants.size(), is(PEPS_VAR_WITH_SEQUENCE));

        List<SymbolicPeptide> peptideSymbolic = peptideRepository.findSymbolicPeptideBySequence(SEQUENCE);

        assertNotNull(peptideSymbolic);
        assertThat(peptideSymbolic.size(), is(PEPS_SYM_WITH_SEQUENCE));

    }

    @Test
    @Transactional
    public void testSaveAndGetPeptideVariant() throws Exception {


        //Peptides
        PeptideVariant peptideVariant = new PeptideVariant();
        peptideVariant.setTaxid(TAXID);
        peptideVariant.setDescription(NO_DESCRIPTION);
        peptideVariant.setSequence(SEQUENCE);

        //Score
        //We used the default score one, It should be always in the DB
        peptideVariant.setScore(scoreRepository.findOne(SCORE_ID));

//        They are not mandatory so we csn postpone the insertion
//        peptideVariant.setAssays();
//        peptideVariant.setProteins();

        //PeptideModifications
        PeptideModification pepMod = new PeptideModification();

        //Modification
        Modification modification = modificationRepository.findByCvTerm(MOD_TERM);
        if(modification == null){
            modification = new Modification();
            modification.setCvTerm(MOD_TERM);
            modification.setCvName(MOD_NAME);
            modification = modificationRepository.save(modification);
        }

        //We know the real modification was persisted before
        pepMod.setModCvTerm(MOD_TERM);
        pepMod.setPosition(MOD_POS);

        Set<PeptideModification> peptideMods = new HashSet<PeptideModification>();
        peptideMods.add(pepMod);
        peptideVariant.setPeptideModifications(peptideMods);

        //CV Params

        //Tissue
        //We check first if that not exist in the DB and if not we will persist it
        CvParamTissue tissue = (CvParamTissue) cvParamRepository.findByCvTerm(TISSUE_TERM);
        if(tissue == null){
            tissue = new CvParamTissue();
            tissue.setCvTerm(TISSUE_TERM);
            tissue.setCvName(TISSUE_NAME);
            tissue.setDescription(NO_DESCRIPTION);
            tissue = (CvParamTissue) cvParamRepository.save(tissue);
        }

        Set<CvParamTissue> tissues = new HashSet<CvParamTissue>();
        tissues.add(tissue);

        //Cell Type
        CvParamCellType cellType = (CvParamCellType) cvParamRepository.findByCvTerm(CELL_TYPE_TERM);
        if(cellType == null){
            cellType = new CvParamCellType();
            cellType.setCvTerm(CELL_TYPE_TERM);
            cellType.setCvName(CELL_TYPE_NAME);
            cellType.setDescription(NO_DESCRIPTION);
            cellType = (CvParamCellType) cvParamRepository.save(cellType);
        }
        Set<CvParamCellType> cellTypes = new HashSet<CvParamCellType>();
        cellTypes.add(cellType);

        //Disease
        CvParamDisease disease = (CvParamDisease) cvParamRepository.findByCvTerm(DISEASE_TERM);
        if(disease == null){
            disease = new CvParamDisease();
            disease.setCvTerm(DISEASE_TERM);
            disease.setCvName(DISEASE_NAME);
            disease.setDescription(NO_DESCRIPTION);
            disease = (CvParamDisease) cvParamRepository.save(disease);

        }
        Set<CvParamDisease> diseases = new HashSet<CvParamDisease>();
        diseases.add(disease);

        Object id = entityManagerFactory.getPersistenceUnitUtil().getIdentifier(peptideVariant);

        assert id==null;
//        if(id != null){
//            //If it exist we update here the metadata, but we know that this peptide doesn't exist.
//            //TODO create a specific test for updating
//            peptideVariant = (PeptideVariant) peptideRepository.findOne((Long) id);
//        }

        peptideVariant.setCvParamTissue(tissues);
        peptideVariant.setCvParamDisease(diseases);
        peptideVariant.setCvParamCellType(cellTypes);

        peptideVariant = (PeptideVariant) peptideRepository.save(peptideVariant);

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
        assertThat(peptideVariant.getTaxid(), is(TAXID));

        checkPeptideModification(peptideVariant.getPeptideModifications());
        checkCellType(peptideVariant.getCvParamCellType());
        checkDisease(peptideVariant.getCvParamDisease());
        checkTissue(peptideVariant.getCvParamTissue());
    }

    private void checkCellType(Set<CvParamCellType> cellTypes) {
        assertNotNull(cellTypes);
        assertThat(cellTypes.size(), is(1));
        assertThat(cellTypes.iterator().next().getCvTerm(),is(CELL_TYPE_TERM));
        assertThat(cellTypes.iterator().next().getCvName(),is(CELL_TYPE_NAME));
    }

    private void checkDisease(Set<CvParamDisease> diseases) {
        assertNotNull(diseases);
        assertThat(diseases.size(), is(1));
        assertThat(diseases.iterator().next().getCvTerm(),is(DISEASE_TERM));
        assertThat(diseases.iterator().next().getCvName(),is(DISEASE_NAME));
    }

    private void checkTissue(Set<CvParamTissue> tissues) {
        assertNotNull(tissues);
        assertThat(tissues.size(), is(1));
        assertThat(tissues.iterator().next().getCvTerm(),is(TISSUE_TERM));
        assertThat(tissues.iterator().next().getCvName(),is(TISSUE_NAME));

    }

    private void checkPeptideModification(Collection<PeptideModification> pepMods) {
        assertNotNull(pepMods);
        assertThat(pepMods.size(), is(1));
        assertThat(pepMods.iterator().next().getModCvTerm(),is(MOD_TERM));
        assertThat(pepMods.iterator().next().getPosition(),is(MOD_POS));
    }


}
