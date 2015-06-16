package uk.ac.ebi.pride.proteomes.db.core.api.modification;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ebi.pride.proteomes.db.core.api.RepositoryTest;

import java.util.List;

import static junit.framework.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * User: ntoro
 * Date: 13/09/2013
 * Time: 14:28
 */

public class ModificationProteomesRepositoryTest extends RepositoryTest {

    @Test
    @Transactional(readOnly = true)
    public void testFindMethods() throws Exception {

        Modification modification = modificationProteomesRepository.findByModId(MOD_TERM_RO);

        assertNotNull(modification);
        assertThat(modification.getModId(), is(MOD_TERM_RO));

        modification = modificationProteomesRepository.findByModName(MOD_NAME_RO);

        assertNotNull(modification);
        assertThat(modification.getModName(), is(MOD_NAME_RO));

        List<Modification> modifications = modificationProteomesRepository.findByBiologicalSignificant(BIO_SIGN);

        assertNotNull(modifications);
        assertThat(modifications.size(), is(MODS_BIO_SIGNIFICANT));

        modifications = modificationProteomesRepository.findByBiologicalSignificant(!BIO_SIGN);

        assertNotNull(modifications);
        assertThat(modifications.size(), is(MODS_NO_BIO_SIGNIFICANT));

        modifications = modificationProteomesRepository.findByMonoDeltaBetween(MIN_DELTA, MAX_DELTA);

        assertNotNull(modifications);
        assertThat(modifications.size(), is(MODS_BETWEEN_DELTA));


    }

    @Test
    @Transactional
    public void testSaveAndGetModification() throws Exception {


        //Modification
        Modification modification = modificationProteomesRepository.findByModId(MOD_TERM);

        assertNull(modification);

        modification = new Modification();
        modification.setModId(MOD_TERM);
        modification.setModName(MOD_NAME);
        modification.setBiologicalSignificant(BIO_SIGN);
        modification.setMonoDelta(MONO_DELTA);
        modification.setDescription(NO_DESCRIPTION);
        modification = modificationProteomesRepository.save(modification);


        //id set after save
        String newId = modification.getModId();

        Modification other = modificationProteomesRepository.findOne(newId);
        checkModInDb(other);

        // delete the mod
        modificationProteomesRepository.delete(other);

    }

    private void checkModInDb(Modification other) {

        assertThat(other.getModId(), is(MOD_TERM));
        assertThat(other.getModName(), is(MOD_NAME));
        assertThat(other.getMonoDelta(), is(MONO_DELTA));
        assertThat(other.getBiologicalSignificant(), is(BIO_SIGN));
        assertThat(other.getDescription(), is(NO_DESCRIPTION));

    }

    @Test
    @Transactional
    public void testEqualsModification() throws Exception {


        //This modification exists in the test dataset.
        Modification modification = new Modification();
        modification.setModId(NEW_MOD_TERM);
        modification.setModName(NEW_MOD_NAME);

        String cvTerm = (String) entityManagerFactory.getPersistenceUnitUtil().getIdentifier(modification);

        assertNotNull(cvTerm);

        Modification sameModification = new Modification();
        sameModification.setModId(NEW_MOD_TERM);
        sameModification.setModName(NEW_MOD_NAME);

        String sameCvTerm = (String) entityManagerFactory.getPersistenceUnitUtil().getIdentifier(sameModification);

        assertNotNull(sameCvTerm);

        assertEquals(modification, sameModification);


    }

}
