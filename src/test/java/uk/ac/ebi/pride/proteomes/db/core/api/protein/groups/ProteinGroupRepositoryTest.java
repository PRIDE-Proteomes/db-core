package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ebi.pride.proteomes.db.core.api.RepositoryTest;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.Protein;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.gene.Gene;

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

public class ProteinGroupRepositoryTest extends RepositoryTest {

    @Test
    @Transactional(readOnly = true)
    public void testFindByMethods() throws Exception {

        ProteinGroup proteinGroup = proteinGroupRepository.findById(PROT_GROUP_ID);

        Collection<Protein> proteins = proteinGroup.getProteins();
        assertNotNull(proteins);
        assertThat(proteins.size(), is(PROTS_IN_GROUP));

        Collection<Gene> genes = proteinGroup.getGenes();
        assertNotNull(genes);
        assertThat(genes.size(), is(NUM_GENES));

    }

    @Test
    @Transactional
    public void testSaveAndGetProteinGroup() throws Exception {

        ProteinGroup proteinGroup = new ProteinGroup();
        proteinGroup.setDescription(NO_DESCRIPTION);
        proteinGroup.setType(ProteinGroupType.ISOFORM);

        Set<Gene> genes = new HashSet<Gene>();
        genes.add(geneRepository.findByGeneAccession(GENE_GROUP_ID));
        genes.add(geneRepository.findByGeneAccession(GENE_GROUP_ID));

        proteinGroup.setGenes(genes);

        Set<Protein> proteins = new HashSet<Protein>();
        proteins.add(proteinRepository.findByProteinAccession(PROTEIN_ACCESSION));
        proteins.add(proteinRepository.findByProteinAccession(ISOFORM_ACCESSION));
        proteinGroup.setProteins(proteins);

        proteinGroup = proteinGroupRepository.save(proteinGroup);

        Long newId = proteinGroup.getId();

        ProteinGroup other = proteinGroupRepository.findById(newId);

        checkProteinGroupInDB(other);

        proteinGroupRepository.delete(other);

    }

    private void checkProteinGroupInDB(ProteinGroup other) {

        assertThat(other.getDescription(), is(NO_DESCRIPTION));
        assertThat(other.getType(), is(ProteinGroupType.ISOFORM));
        assertThat(other.getGenes().size(), is(NUM_GENES));
        assertThat(other.getProteins().size(), is(NUM_PROTS));

    }
}
