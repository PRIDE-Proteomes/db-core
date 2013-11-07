package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ebi.pride.proteomes.db.core.api.RepositoryTest;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.Protein;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * User: ntoro
 * Date: 13/09/2013
 * Time: 14:30
 */

public class ProteinGroupRepositoryTest extends RepositoryTest {

    @Test
    @Transactional(readOnly = true)
    public void testFindByMethods() throws Exception {

        EntryGroup entryGroupGroup = (EntryGroup) proteinGroupRepository.findById(ENTRY_GROUP_ID);
        Collection<Protein> proteins = entryGroupGroup.getProteins();
        assertNotNull(proteins);
        assertThat(proteins.size(), is(PROTS_IN_GROUP));


        GeneGroup geneGroup = (GeneGroup) proteinGroupRepository.findById(GENE_GROUP_ID);
        proteins = geneGroup.getProteins();
        assertNotNull(proteins);
        assertThat(proteins.size(), is(PROTS_IN_GENE));

        Collection<ProteinGroup> proteinGroup = proteinGroupRepository.findAll();
        assertNotNull(proteinGroup);
        assertThat(proteinGroup.size(), is(NUM_PROT_GROUPS));


    }

    @Test
    @Transactional
    public void testSaveAndGetProteinGroup() throws Exception {

        EntryGroup entryGroupGroup = new EntryGroup();
        entryGroupGroup.setDescription(NO_DESCRIPTION);

        Set<Protein> proteins = new HashSet<Protein>();
        proteins.add(proteinRepository.findByProteinAccession(PROTEIN_ACCESSION));
        proteins.add(proteinRepository.findByProteinAccession(ISOFORM_ACCESSION));
        entryGroupGroup.setProteins(proteins);
        entryGroupGroup.setTaxid(TAXID);
        entryGroupGroup.setId(ENTRY_GROUP_ID);

        entryGroupGroup = proteinGroupRepository.save(entryGroupGroup);

        String newId = entryGroupGroup.getId();

        ProteinGroup other = proteinGroupRepository.findById(newId);

        checkProteinGroupInDB(other);

        proteinGroupRepository.delete(other);

    }

    private void checkProteinGroupInDB(ProteinGroup other) {

        assertThat(other.getTaxid(),is(TAXID));
        assertThat(other.getDescription(), is(NO_DESCRIPTION));
        assertEquals(other.getClass(), EntryGroup.class);
        assertThat(other.getProteins().size(), is(NUM_PROTS));

    }
}
