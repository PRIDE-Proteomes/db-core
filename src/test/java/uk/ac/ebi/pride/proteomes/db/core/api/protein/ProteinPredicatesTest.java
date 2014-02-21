package uk.ac.ebi.pride.proteomes.db.core.api.protein;

import com.mysema.query.types.Predicate;
import junit.framework.TestCase;

/**
 * User: ntoro
 * Date: 20/02/2014
 * Time: 11:51
 */
public class ProteinPredicatesTest extends TestCase {

    private static final String CV_TERM = "BTO:0000142";
    private static final String TISSUE_PREDICATE_STRING = "any(protein.tissues).cvTerm = BTO:0000142";

    private static final String MOD_ID = "MOD:01214";
    private static final String MOD_PREDICATE_STRING = "any(protein.modificationLocations).modId = MOD:01214";

    public void testHasTissue() throws Exception {
        Predicate predicate = ProteinPredicates.hasTissue(CV_TERM);
        String predicateAsString = predicate.toString();
        assertEquals(TISSUE_PREDICATE_STRING, predicateAsString);
    }

    public void testHasModification() throws Exception {
        Predicate predicate = ProteinPredicates.hasModification(MOD_ID);
        String predicateAsString = predicate.toString();
        assertEquals(MOD_PREDICATE_STRING, predicateAsString);
    }
}
