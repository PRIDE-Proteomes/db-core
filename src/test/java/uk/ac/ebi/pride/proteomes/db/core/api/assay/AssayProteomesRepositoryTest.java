package uk.ac.ebi.pride.proteomes.db.core.api.assay;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ebi.pride.proteomes.db.core.api.RepositoryTest;
import uk.ac.ebi.pride.proteomes.db.core.api.param.CellType;
import uk.ac.ebi.pride.proteomes.db.core.api.param.Disease;
import uk.ac.ebi.pride.proteomes.db.core.api.param.Tissue;

import java.util.HashSet;
import java.util.List;
import java.util.Collection;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * User: ntoro
 * Date: 10/09/2013
 * Time: 13:34
 */

public class AssayProteomesRepositoryTest extends RepositoryTest {

    @Test
    @Transactional(readOnly = true)
    public void testFindMethods() throws Exception {
        List<Assay> assays = assayProteomesRepository.findByProjectAccession(PROJECT_ACCESSION);

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
        assay.setTaxid(TAXID_HUMAN);

        //CV Params

        //Tissue
        //We check first if that not exist in the DB and if not we will persist it
        Tissue tissue = (Tissue) cvParamProteomesRepository.findByCvTerm(TISSUE_TERM);
        if (tissue == null) {
            tissue = new Tissue();
            tissue.setCvTerm(TISSUE_TERM);
            tissue.setCvName(TISSUE_NAME);
            tissue.setDescription(NO_DESCRIPTION);
            tissue = (Tissue) cvParamProteomesRepository.save(tissue);  //Should be persisted before we persist the parent
        }

        Collection<Tissue> tissues = new HashSet<Tissue>();
        tissues.add(tissue);
        assay.setTissues(tissues);

        //Cell Type
        CellType cellType = (CellType) cvParamProteomesRepository.findByCvTerm(CELL_TYPE_TERM);
        if (cellType == null) {
            cellType = new CellType();
            cellType.setCvTerm(CELL_TYPE_TERM);
            cellType.setCvName(CELL_TYPE_NAME);
            cellType.setDescription(NO_DESCRIPTION);
            cellType = (CellType) cvParamProteomesRepository.save(cellType);   //Should be persisted before we persist the parent
        }

        Collection<CellType> cellTypes = new HashSet<CellType>();
        cellTypes.add(cellType);
        assay.setCellTypes(cellTypes);

        //Disease
        Disease disease = (Disease) cvParamProteomesRepository.findByCvTerm(DISEASE_TERM);
        if (disease == null) {
            disease = new Disease();
            disease.setCvTerm(DISEASE_TERM);
            disease.setCvName(DISEASE_NAME);
            disease.setDescription(NO_DESCRIPTION);
            disease = (Disease) cvParamProteomesRepository.save(disease);  //Should be persisted before we persist the parent

        }
        Collection<Disease> diseases = new HashSet<Disease>();
        diseases.add(disease);
        assay.setDiseases(diseases);

        assay = assayProteomesRepository.save(assay);

        //id set after save
        String newId = assay.getAssayAccession();

        Assay other = assayProteomesRepository.findOne(newId);
        checkAssayInDb(other);

        // delete the assay
        assayProteomesRepository.delete(other);


    }

    private void checkAssayInDb(Assay assay) {
        assertNotNull(assay);
        assertThat(assay.getAssayAccession(), is(ASSAY_ACCESSION));
        assertThat(assay.getProjectAccession(), is(PROJECT_ACCESSION));
        assertThat(assay.getTaxid(), is(TAXID_HUMAN));

        checkCellType(assay.getCellTypes());
        checkDisease(assay.getDiseases());
        checkTissue(assay.getTissues());
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

}
