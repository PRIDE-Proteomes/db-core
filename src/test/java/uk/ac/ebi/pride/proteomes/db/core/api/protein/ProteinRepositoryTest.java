package uk.ac.ebi.pride.proteomes.db.core.api.protein;

import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ebi.pride.proteomes.db.core.api.RepositoryTest;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.protein.PeptideProtein;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.GeneGroup;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.ProteinGroup;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
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
        Set<PeptideProtein> peptides = protein.getPeptides();
        assertNotNull(peptides);
        assertThat(peptides.size(), is(SYMBOLIC_PEP_IN_PROTEIN));

//		Set<String> peptideIds = protein.getPeptideIds();
//		assertNotNull(peptideIds);
//		assertThat(peptideIds.size(), is(SYMBOLIC_PEP_IN_PROTEIN));

        Set<GeneGroup> genes = protein.getGeneGroups();
        assertNotNull(genes);
        assertThat(genes.size(), is(NUM_GENES));

        Set<ProteinGroup> proteinGroups = protein.getProteinGroups();
        assertNotNull(proteinGroups);
        assertThat(proteinGroups.size(), is(NUM_PROT_GROUPS_FOR_PROTEIN));

        List<Protein> resultsSeqContains = proteinRepository.findBySequenceContaining("DPYSQQPQTPRPS");
        assertNotNull(resultsSeqContains);
        assertThat(resultsSeqContains.size(), is(3));


        List<Protein> resultsTaxid = proteinRepository.findAllByTaxid(TAXID_HUMAN);
        assertNotNull(resultsTaxid);
        assertThat(resultsTaxid.size(), is(3));

        // same request with limitation of page size to one
        Sort sort = new Sort(Sort.Direction.ASC, "proteinAccession");
        List<Protein> resultsTaxidPaged = proteinRepository.findAllByTaxid(TAXID_HUMAN, new PageRequest(0, 1, sort));
        assertNotNull(resultsTaxidPaged);
        assertThat(resultsTaxidPaged.size(), is(1));
        assertThat(resultsTaxidPaged.get(0).getProteinAccession(), is("X12345"));
        resultsTaxidPaged = proteinRepository.findAllByTaxid(TAXID_HUMAN, new PageRequest(2, 1, sort));
        assertNotNull(resultsTaxidPaged);
        assertThat(resultsTaxidPaged.size(), is(1));
        assertThat(resultsTaxidPaged.get(0).getProteinAccession(), is("X12345-2"));
        resultsTaxidPaged = proteinRepository.findAllByTaxid(TAXID_HUMAN, new PageRequest(1, 2, sort));
        assertNotNull(resultsTaxidPaged);
        assertThat(resultsTaxidPaged.size(), is(1));
        assertThat(resultsTaxidPaged.get(0).getProteinAccession(), is("X12345-2"));
        resultsTaxidPaged = proteinRepository.findAllByTaxid(TAXID_HUMAN, new PageRequest(1, 2, new Sort(Sort.Direction.DESC, "proteinAccession")));
        assertNotNull(resultsTaxidPaged);
        assertThat(resultsTaxidPaged.size(), is(1));
        assertThat(resultsTaxidPaged.get(0).getProteinAccession(), is("X12345"));


        List<Protein> resultsDescContains = proteinRepository.findByNameContaining("kinase");
        assertNotNull(resultsDescContains);
        assertThat(resultsDescContains.size(), is(3));

        List<Protein> resultsDescContainsPaged = proteinRepository.findByNameContaining("kinase", new PageRequest(1, 2));
        assertNotNull(resultsDescContainsPaged);
        assertThat(resultsDescContainsPaged.size(), is(1)); // total 3 proteins, second page has to have 1 result


        List<Protein> resultsTaxidDescContains = proteinRepository.findByTaxidAndNameContaining(TAXID_HUMAN, "kinase");
        assertNotNull(resultsTaxidDescContains);
        assertThat(resultsTaxidDescContains.size(), is(2));

        List<Protein> resultsTaxidDescContainsPaged = proteinRepository.findByTaxidAndNameContaining(TAXID_HUMAN, "kinase", new PageRequest(1, 1));
        assertNotNull(resultsTaxidDescContainsPaged);
        assertThat(resultsTaxidDescContainsPaged.size(), is(1)); // total 2 proteins, second page has to have 1 rsult

        List<Protein> tissueProteinList = proteinRepository.findAllByTissue(TISSUE_TERM);
        assertNotNull(tissueProteinList);
        assertThat(tissueProteinList.size(), is(2));

        List<Protein> modProteinList = proteinRepository.findAllByModification(MOD_ID);
        assertNotNull(modProteinList);
        assertThat(modProteinList.size(), is(4));

        List<Protein> modTissueProteinList = proteinRepository.findAllByTissueAndModification(TISSUE_TERM, MOD_ID);
        assertNotNull(modTissueProteinList);
        assertThat(modTissueProteinList.size(), is(1));
    }

    @Test
    @Transactional
    public void testCountMethods() throws Exception {
        long total = proteinRepository.count();
        long humanTotal = proteinRepository.countByTaxid(TAXID_HUMAN);
        long mouseTotal = proteinRepository.countByTaxid(TAXID_MOUSE);
        long searchTerm1Count = proteinRepository.countByNameContaining("kinase");
        long searchTerm2Count = proteinRepository.countByNameContaining("random");
        long searchTerm1HumanCount = proteinRepository.countByTaxidAndNameContaining(TAXID_HUMAN, "kinase");
        long searchTerm2HumanCount = proteinRepository.countByTaxidAndNameContaining(TAXID_HUMAN, "random");

        assertEquals(5, total);
        assertEquals(3, humanTotal);
        assertEquals(2, mouseTotal);
        assertEquals(3, searchTerm1Count);
        assertEquals(2, searchTerm2Count);
        assertEquals(2, searchTerm1HumanCount);
        assertEquals(1, searchTerm2HumanCount);

        humanTotal = proteinRepository.countByTaxidAndIsNotContaminant(TAXID_HUMAN);
        assertEquals(3, humanTotal);

        humanTotal = proteinRepository.countByTaxidAndIsNotContaminantAndIsCanonical(TAXID_HUMAN);
        assertEquals(3, humanTotal);

        humanTotal = proteinRepository.countByTaxidAndIsNotContaminantAndIsIsoform(TAXID_HUMAN);
        assertEquals(0, humanTotal);

        /* Mapped proteins with peptides */

        // Mapped proteins
        humanTotal = proteinRepository.countByTaxidAndIsNotContaminantAndHasPeptides(TAXID_HUMAN);
        assertEquals(1, humanTotal);

        // Mapped canonical proteins
        humanTotal = proteinRepository.countByTaxidAndIsNotContaminantAndIsCanonicalAndHasPeptides(TAXID_HUMAN);
        assertEquals(1, humanTotal);

        // Mapped isoform proteins
        humanTotal = proteinRepository.countByTaxidAndIsNotContaminantAndIsIsoformAndHasPeptides(TAXID_HUMAN);
        assertEquals(0, humanTotal);

        /* Mapped proteins with unique peptides */

        // Mapped proteins with at least one unique peptide
        humanTotal = proteinRepository.countByTaxidAndIsNotContaminantAndHasUniquePeptides(TAXID_HUMAN);
        assertEquals(0, humanTotal);

        // Mapped canonical proteins with at least one unique peptide
        humanTotal = proteinRepository.countByTaxidAndIsNotContaminantAndIsCanonicalAndHasUniquePeptides(TAXID_HUMAN);
        assertEquals(0, humanTotal);

        // Mapped isoform proteins with at least one unique peptide
        humanTotal = proteinRepository.countByTaxidAndIsNotContaminantAndIsIsoformAndHasUniquePeptides(TAXID_HUMAN);
        assertEquals(0, humanTotal);

    }

    @Test
    @Transactional
    public void testSaveAndGetProtein() throws Exception {

        Protein protein = new Protein();
        protein.setTaxid(TAXID_HUMAN);
        protein.setDescription(NO_DESCRIPTION);
        protein.setContaminant(Boolean.FALSE);
        protein.setIsoform(Boolean.TRUE);
        protein.setProteinAccession(NEW_PROTEIN_ACCESSION);
        protein.setScore(scoreRepository.findOne(SCORE_ID));

        protein.setSequence(NEW_PROTEIN_SEQUENCE);
        protein.setCurationLevel(CurationLevel.PREDICTED);

        Set<GeneGroup> geneGroups = new HashSet<>();
        GeneGroup geneGroup = (GeneGroup) proteinGroupRepository.findOne(GENE_GROUP_ID);
        geneGroup.getProteins().addAll(Collections.singletonList(protein));
        geneGroups.add(geneGroup);

        protein.setGeneGroups(geneGroups);

        protein = proteinRepository.save(protein);

        //After we store the protein we add the mapping with the peptides
        PeptideProtein peptideProteinTwo = new PeptideProtein(PEPTIDE_TWO, NEW_PROTEIN_ACCESSION, 43);
        peptideProteinTwo.setProtein(protein);
        peptideProteinTwo.setPeptide(peptideRepository.findOne(PEPTIDE_TWO));
        peptideProteinTwo.setStartPosition(43);
        peptideProteinTwo.setScore(scoreRepository.findOne(SCORE_ID));

        PeptideProtein peptideProteinSeven = new PeptideProtein(PEPTIDE_SEVEN, NEW_PROTEIN_ACCESSION, 1);
        peptideProteinSeven.setProtein(protein);
        peptideProteinSeven.setPeptide(peptideRepository.findOne(PEPTIDE_SEVEN));
        peptideProteinSeven.setStartPosition(1);
        peptideProteinSeven.setScore(scoreRepository.findOne(SCORE_ID));

        PeptideProtein peptideProteinTen = new PeptideProtein(PEPTIDE_TEN, NEW_PROTEIN_ACCESSION, 13);
        peptideProteinTen.setProtein(protein);
        peptideProteinTen.setPeptide(peptideRepository.findOne(PEPTIDE_TEN));
        peptideProteinTen.setStartPosition(13);
        peptideProteinTen.setScore(scoreRepository.findOne(SCORE_ID));

        peptideProteinTwo = peptideProteinRepository.save(peptideProteinTwo);
        peptideProteinSeven = peptideProteinRepository.save(peptideProteinSeven);
        peptideProteinTen = peptideProteinRepository.save(peptideProteinTen);

        Set<PeptideProtein> symbolicPeptides = new HashSet<>();
        symbolicPeptides.add(peptideProteinTwo);
        symbolicPeptides.add(peptideProteinSeven);
        symbolicPeptides.add(peptideProteinTen);
        protein.setPeptides(symbolicPeptides);


        String newId = protein.getProteinAccession();

        Protein other = proteinRepository.findByProteinAccession(newId);

        checkProteinInDB(other);

        proteinRepository.delete(other);
    }

    private void checkProteinInDB(Protein other) {
        assertThat(other.getDescription(), is(NO_DESCRIPTION));
        assertThat(other.getTaxid(), is(TAXID_HUMAN));
        assertThat(other.getSequence(), is(NEW_PROTEIN_SEQUENCE));
        assertThat(other.getCurationLevel(), is(CurationLevel.PREDICTED));
        checkSymbolicPeptides(other.getPeptides());
        checkGeneGroups(other.getGeneGroups());
    }


    private void checkGeneGroups(Set<GeneGroup> geneGroups) {
        assertNotNull(geneGroups);
        assertThat(geneGroups.size(), is(NUM_GENES));
        assertThat(geneGroups.iterator().next().getId(), is(GENE_GROUP_ID));
    }

    private void checkSymbolicPeptides(Set<PeptideProtein> symbolicPeptides) {
        assertNotNull(symbolicPeptides);
        assertThat(symbolicPeptides.size(), is(SYMBOLIC_PEP_IN_PROTEIN));
    }
}
