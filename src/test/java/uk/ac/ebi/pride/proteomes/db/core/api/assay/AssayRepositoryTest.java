package uk.ac.ebi.pride.proteomes.db.core.api.assay;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ebi.pride.proteomes.db.core.api.param.CellType;
import uk.ac.ebi.pride.proteomes.db.core.api.param.Disease;
import uk.ac.ebi.pride.proteomes.db.core.api.param.CvParamRepository;
import uk.ac.ebi.pride.proteomes.db.core.api.param.Tissue;

import javax.persistence.EntityManagerFactory;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * User: ntoro
 * Date: 10/09/2013
 * Time: 13:34
 */

@ContextConfiguration(locations = {"/test-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AssayRepositoryTest {

    private static final String PROJECT_ACCESSION = "PRD000269";
    private static final String ASSAY_ACCESSION = "13565";

    private static final int NUM_ASSAY_PROJECT = 22;

    private static final Integer TAXID = 9606;

    private static final String NO_DESCRIPTION = null;

    private static final String TISSUE_TERM = "BTO:0000157";
    private static final String TISSUE_NAME = "aorta thoracica";
    private static final String CELL_TYPE_TERM = "CL:0000182";
    private static final String CELL_TYPE_NAME = "hepatocyte";
    private static final String DISEASE_TERM = "";
    private static final String DISEASE_NAME = "";

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Autowired
    private AssayRepository assayRepository;

    @Autowired
    private CvParamRepository cvParamRepository;


    @Test
    @Transactional(readOnly = true)
    public void testFindMethods() throws Exception {
        List<Assay> assays = assayRepository.findByProjectAccession(PROJECT_ACCESSION);

        assertNotNull(assays);
        assertThat(assays.size(), is(NUM_ASSAY_PROJECT));

    }

    @Test
    @Transactional
    public void testSaveAndGetAssay() throws Exception {

        //New assay in a existing project
        //We don't check it doesn't exist because we know that
        Assay assay = new Assay();
        assay.setAssayAccession(ASSAY_ACCESSION);
        assay.setProjectAccession(PROJECT_ACCESSION);
        assay.setTaxid(TAXID);

        //CV Params

        //Tissue
        //We check first if that not exist in the DB and if not we will persist it
        Tissue tissue = (Tissue) cvParamRepository.findByCvTerm(TISSUE_TERM);
        if(tissue == null){
            tissue = new Tissue();
            tissue.setCvTerm(TISSUE_TERM);
            tissue.setCvName(TISSUE_NAME);
            tissue.setDescription(NO_DESCRIPTION);
            tissue = (Tissue) cvParamRepository.save(tissue);
        }

        Set<Tissue> tissues = new HashSet<Tissue>();
        tissues.add(tissue);
        assay.setTissues(tissues);

        //Cell Type
        CellType cellType = (CellType) cvParamRepository.findByCvTerm(CELL_TYPE_TERM);
        if(cellType == null){
            cellType = new CellType();
            cellType.setCvTerm(CELL_TYPE_TERM);
            cellType.setCvName(CELL_TYPE_NAME);
            cellType.setDescription(NO_DESCRIPTION);
            cellType = (CellType) cvParamRepository.save(cellType);
        }

        Set<CellType> cellTypes = new HashSet<CellType>();
        cellTypes.add(cellType);
        assay.setCellTypes(cellTypes);

        //Disease
        Disease disease = (Disease) cvParamRepository.findByCvTerm(DISEASE_TERM);
        if(disease == null){
            disease = new Disease();
            disease.setCvTerm(DISEASE_TERM);
            disease.setCvName(DISEASE_NAME);
            disease.setDescription(NO_DESCRIPTION);
            disease = (Disease) cvParamRepository.save(disease);

        }
        Set<Disease> diseases = new HashSet<Disease>();
        diseases.add(disease);
        assay.setDiseases(diseases);

        assay = assayRepository.save(assay);

        //id set after save
        String newId = assay.getAssayAccession();

        Assay other = assayRepository.findOne(newId);
        checkAssayInDb(other);

        // delete the assay
        assayRepository.delete(other);


    }

    private void checkAssayInDb(Assay assay) {
        assertNotNull(assay);
        assertThat(assay.getAssayAccession(), is(ASSAY_ACCESSION));
        assertThat(assay.getProjectAccession(), is(PROJECT_ACCESSION));
        assertThat(assay.getTaxid(), is(TAXID));

        checkCellType(assay.getCellTypes());
        checkDisease(assay.getDiseases());
        checkTissue(assay.getTissues());
    }


    private void checkCellType(Set<CellType> cellTypes) {
        assertNotNull(cellTypes);
        assertThat(cellTypes.size(), is(1));
        assertThat(cellTypes.iterator().next().getCvTerm(),is(CELL_TYPE_TERM));
        assertThat(cellTypes.iterator().next().getCvName(),is(CELL_TYPE_NAME));
    }

    private void checkDisease(Set<Disease> diseases) {
        assertNotNull(diseases);
        assertThat(diseases.size(), is(1));
        assertThat(diseases.iterator().next().getCvTerm(),is(DISEASE_TERM));
        assertThat(diseases.iterator().next().getCvName(),is(DISEASE_NAME));
    }

    private void checkTissue(Set<Tissue> tissues) {
        assertNotNull(tissues);
        assertThat(tissues.size(), is(1));
        assertThat(tissues.iterator().next().getCvTerm(),is(TISSUE_TERM));
        assertThat(tissues.iterator().next().getCvName(),is(TISSUE_NAME));

    }

}
