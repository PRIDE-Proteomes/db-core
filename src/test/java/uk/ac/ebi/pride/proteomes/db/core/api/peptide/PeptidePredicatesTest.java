package uk.ac.ebi.pride.proteomes.db.core.api.peptide;

import com.querydsl.core.types.Predicate;
import junit.framework.TestCase;

/**
 * User: ntoro
 * Date: 20/02/2014
 * Time: 11:51
 */
public class PeptidePredicatesTest extends TestCase {

    private static final String CV_TERM = "BTO:0000142";
    private static final String TISSUE_PREDICATE_STRING = "any(peptide.tissues).cvTerm = BTO:0000142";

    private static final String MOD_ID = "MOD:01214";
    private static final String MOD_PREDICATE_STRING = "any(peptide.modificationLocations).modId = MOD:01214";

    private static final String UNIQUE_PREDICATE_STRING = "any(peptide.proteins).uniqueness = 1";
    private static final String PROTEINS_PREDICATE_STRING = "!empty(peptide.proteins)";
    private static final String PROTEINS_NO_CONTAMINANTS_PREDICATE_STRING = "any(peptide.proteins).protein.contaminant = false";
    private static final String GENES_PREDICATE_STRING = "any(peptide.peptideGroups).geneGroup is not null";


    public void testHasTissue() throws Exception {
        Predicate predicate = PeptidePredicates.hasTissue(CV_TERM);
        String predicateAsString = predicate.toString();
        assertEquals(TISSUE_PREDICATE_STRING, predicateAsString);
    }

    public void testHasModification() throws Exception {
        Predicate predicate = PeptidePredicates.hasModification(MOD_ID);
        String predicateAsString = predicate.toString();
        assertEquals(MOD_PREDICATE_STRING, predicateAsString);
    }

    public void testIsUnique() throws Exception {
        Predicate predicate = PeptidePredicates.isUnique();
        String predicateAsString = predicate.toString();
        assertEquals(UNIQUE_PREDICATE_STRING, predicateAsString);
    }

    public void testHasProteins() throws Exception {
        Predicate predicate = PeptidePredicates.hasProteins();
        String predicateAsString = predicate.toString();
        assertEquals(PROTEINS_PREDICATE_STRING, predicateAsString);
    }

    public void testHasProteinsNoContaminants() throws Exception {
        Predicate predicate = PeptidePredicates.hasProteinsWithoutContaminants();
        String predicateAsString = predicate.toString();
        assertEquals(PROTEINS_NO_CONTAMINANTS_PREDICATE_STRING, predicateAsString);
    }

    public void testHasGenes() throws Exception {
        Predicate predicate = PeptidePredicates.hasGenes();
        String predicateAsString = predicate.toString();
        assertEquals(GENES_PREDICATE_STRING, predicateAsString);
    }

}
