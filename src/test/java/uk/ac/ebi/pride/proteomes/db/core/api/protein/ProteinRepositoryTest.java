package uk.ac.ebi.pride.proteomes.db.core.api.protein;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ebi.pride.proteomes.db.core.api.RepositoryTest;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.Peptide;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.PeptideVariant;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.SymbolicPeptide;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.ProteinGroup;
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
 * Time: 14:28
 */

public class ProteinRepositoryTest extends RepositoryTest {

    @Test
    @Transactional(readOnly = true)
    public void testFindByMethods() throws Exception {

        Protein protein = proteinRepository.findByProteinAccession(PROTEIN_ACCESSION);

        Collection<PeptideVariant> peptideVariants = protein.getPeptideVariants();
        assertNotNull(peptideVariants);
        assertThat(peptideVariants.size(), is(PEP_VAR_IN_PROTEIN));

        Collection<SymbolicPeptide> symbolicPeptides = protein.getSymbolicPeptides();
        assertNotNull(symbolicPeptides);
        assertThat(symbolicPeptides.size(), is(SYMBOLIC_PEP_IN_PROTEIN));

        Collection<Peptide> peptides = protein.getPeptides();
        assertNotNull(peptides);
        assertThat(peptides.size(), is(PEPS_IN_PROTEIN));

    }

    @Test
    @Transactional
    public void testSaveAndGetProtein() throws Exception {

        Protein protein = new Protein();
        protein.setTaxid(TAXID);
        protein.setDescription(NO_DESCRIPTION);

        Set<Gene> genes = new HashSet<Gene>();
        genes.add(geneRepository.findByGeneAccession(GENE_GROUP_ID));
        protein.setGenes(genes);

        protein.setProteinAccession(NEW_PROTEIN_ACCESSION);
        protein.setScore(scoreRepository.findOne(SCORE_ID));

        protein.setSequence(NEW_PROTEIN_SEQUENCE);

        Set<SymbolicPeptide> symbolicPeptides = new HashSet<SymbolicPeptide>();
        symbolicPeptides.add((SymbolicPeptide) peptideRepository.findOne(7L));
        symbolicPeptides.add((SymbolicPeptide) peptideRepository.findOne(10L));
        symbolicPeptides.add((SymbolicPeptide) peptideRepository.findOne(2L));
        protein.setSymbolicPeptides(symbolicPeptides);

        Set<ProteinGroup> proteinGroups = new HashSet<ProteinGroup>();
        proteinGroups.add(proteinGroupRepository.findOne(PROT_GROUP_ID));
        protein.setProteinGroups(proteinGroups);

        protein = proteinRepository.save(protein);

        String newId = protein.getProteinAccession();

        Protein other = proteinRepository.findByProteinAccession(newId);

        checkProteinInDB(other);

        proteinRepository.delete(other);
    }

    private void checkProteinInDB(Protein other) {

        assertThat(other.getDescription(), is(NO_DESCRIPTION));
        assertThat(other.getTaxid(), is(TAXID));
        assertThat(other.getSequence(), is(NEW_PROTEIN_SEQUENCE));
        checkSymbolicPeptides(other.getSymbolicPeptides());
        checkGenes(other.getGenes());
        checkProteinGroups(other.getProteinGroups());
    }

    private void checkSymbolicPeptides(Collection<SymbolicPeptide> symbolicPeptides) {
        assertNotNull(symbolicPeptides);
        assertThat(symbolicPeptides.size(), is(3));
    }

    private void checkGenes(Collection<Gene> genes) {
        assertNotNull(genes);
        assertThat(genes.size(), is(1));
        assertThat(genes.iterator().next().getGeneAccession(), is(GENE_GROUP_ID));
    }

    private void checkProteinGroups(Collection<ProteinGroup> proteinGroups) {
        assertNotNull(proteinGroups);
        assertThat(proteinGroups.size(), is(1));
        assertThat(proteinGroups.iterator().next().getId(), is(PROT_GROUP_ID));
    }
}
