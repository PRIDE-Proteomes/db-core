package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups;

import org.junit.Test;
import org.springframework.data.domain.PageRequest;
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
        Set<Protein> proteins = entryGroupGroup.getEntryProteins();
        assertNotNull(proteins);
        assertThat(proteins.size(), is(PROTS_IN_GROUP));


        GeneGroup geneGroup = (GeneGroup) proteinGroupRepository.findById(GENE_GROUP_ID);
        proteins = geneGroup.getGeneProteins();
        assertNotNull(proteins);
        assertThat(proteins.size(), is(PROTS_IN_GENE));

        Collection<ProteinGroup> proteinGroup = proteinGroupRepository.findAll();
        assertNotNull(proteinGroup);
        assertThat(proteinGroup.size(), is(3));

        Collection<ProteinGroup> proteinGroupPaged = proteinGroupRepository.findAll(new PageRequest(1, 1)).getContent();
        assertNotNull(proteinGroupPaged);
        assertThat(proteinGroupPaged.size(), is(1));

        Collection<ProteinGroup> humanList = proteinGroupRepository.findByTaxid(TAXID_HUMAN);
        assertNotNull(humanList);
        assertThat(humanList.size(), is(2));

        Collection<ProteinGroup> humanListPaged = proteinGroupRepository.findByTaxid(TAXID_HUMAN, new PageRequest(0, 1));
        assertNotNull(humanListPaged);
        assertThat(humanListPaged.size(), is(1));

        Collection<ProteinGroup> mouseList = proteinGroupRepository.findByTaxid(TAXID_MOUSE);
        assertNotNull(mouseList);
        assertThat(mouseList.size(), is(1));

    }

    @Test
    @Transactional
    public void testCountMethods() throws Exception {
        long total = proteinGroupRepository.count();
        long humanTotal = proteinGroupRepository.countByTaxid(TAXID_HUMAN);
        long mouseTotal = proteinGroupRepository.countByTaxid(TAXID_MOUSE);

        assertEquals(3, total);
        assertEquals(2, humanTotal);
        assertEquals(1, mouseTotal);
    }


    @Test
    @Transactional
    public void testSaveAndGetProteinGroup() throws Exception {

        EntryGroup entryGroup = new EntryGroup();
        entryGroup.setDescription(NO_DESCRIPTION);

        Set<Protein> proteins = new HashSet<Protein>();
        proteins.add(proteinRepository.findByProteinAccession(PROTEIN_ACCESSION));
        proteins.add(proteinRepository.findByProteinAccession(ISOFORM_ACCESSION));
        entryGroup.setEntryProteins(proteins);
        entryGroup.setProteins(proteins);
        entryGroup.setTaxid(TAXID_HUMAN);
        entryGroup.setId(ENTRY_GROUP_ID);

        entryGroup = proteinGroupRepository.save(entryGroup);

        String newId = entryGroup.getId();

        ProteinGroup other = proteinGroupRepository.findById(newId);

        checkProteinGroupInDB(other);

        proteinGroupRepository.delete(other);

    }

    private void checkProteinGroupInDB(ProteinGroup other) {

        assertThat(other.getTaxid(),is(TAXID_HUMAN));
        assertThat(other.getDescription(), is(NO_DESCRIPTION));
        assertEquals(other.getClass(), EntryGroup.class);
        assertThat(other.getProteins().size(), is(NUM_PROTS));

    }
}
