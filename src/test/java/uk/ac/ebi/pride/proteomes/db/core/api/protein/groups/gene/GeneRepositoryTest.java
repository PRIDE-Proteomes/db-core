package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.gene;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ebi.pride.proteomes.db.core.api.RepositoryTest;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.Protein;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.ProteinGroup;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * User: ntoro
 * Date: 13/09/2013
 * Time: 14:30
 */

public class GeneRepositoryTest extends RepositoryTest {

    @Test
    @Transactional(readOnly = true)
    public void testFindByMethods() throws Exception {

        Gene gene = geneRepository.findByGeneAccession(GENE_GROUP_ID);

        Collection<Protein> proteins = gene.getProteins();
        assertNotNull(proteins);
        assertThat(proteins.size(), is(PROTS_IN_GROUP));

        Collection<ProteinGroup> proteinGroups = gene.getProteinGroups();
        assertNotNull(proteinGroups);
        assertThat(proteinGroups.size(), is(NUM_PRO_GROUPS));

    }

    @Test
    @Transactional
    public void testSaveAndGetGene() throws Exception {

        Gene gene = new Gene();
        gene.setDescription(NO_DESCRIPTION);
        gene.setGeneAccession(SECOND_GENE);

        Set<ProteinGroup> proteinGroups = new HashSet<ProteinGroup>();
        proteinGroups.add(proteinGroupRepository.findById(PROT_GROUP_ID));
        proteinGroups.add(proteinGroupRepository.findById(PROT_GROUP_ID));

        gene.setProteinGroups(proteinGroups);

        Set<Protein> proteins = new HashSet<Protein>();
        proteins.add(proteinRepository.findByProteinAccession(PROTEIN_ACCESSION));
        proteins.add(proteinRepository.findByProteinAccession(ISOFORM_ACCESSION));
        gene.setProteins(proteins);
        gene.setTaxid(TAXID);

        gene = geneRepository.save(gene);

        String newId = gene.getGeneAccession();

        Gene other = geneRepository.findByGeneAccession(newId);

        checkGeneInDB(other);

    }

    private void checkGeneInDB(Gene other) {

        assertThat(other.getTaxid(),is(TAXID));
        assertThat(other.getDescription(), is(NO_DESCRIPTION));
        assertThat(other.getProteinGroups().size(), is(NUM_PRO_GROUPS));
        assertThat(other.getProteins().size(), is(NUM_PROTS));

    }

}
