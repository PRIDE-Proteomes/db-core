package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups;

import com.querydsl.core.types.Predicate;
import junit.framework.TestCase;

/**
 * User: ntoro
 * Date: 20/02/2014
 * Time: 11:51
 */
public class ProteinGroupPredicatesTest extends TestCase {



    private static final int TAXID = 9606;
    private static final String TAXID_PREDICATE_STRING = "proteinGroup.taxid = 9606";

    private static final String GENE_GROUP_PREDICATE_STRING =
            "proteinGroup instanceof class uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.GeneGroup";
    private static final String PEPTIDES_PREDICATE_STRING =
            "!empty(any(proteinGroup.proteins).peptides)";
    private static final String UNIQUE_PEPTIDES_PREDICATE_STRING =
            "!empty(any(proteinGroup.proteins).peptides)";
    private static final String GENE_GROUP_TAXID_PREDICATE_STRING =
            "proteinGroup instanceof class uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.GeneGroup " +
                    "&& proteinGroup.taxid = 9606";
    private static final String GENE_GROUP_TAXID_PEPTIDES_PREDICATE_STRING =
            "proteinGroup instanceof class uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.GeneGroup " +
                    "&& proteinGroup.taxid = 9606 && !empty(any(proteinGroup.proteins).peptides)";
    private static final String GENE_GROUP_TAXID_UNIQUE_PEPTIDES_PREDICATE_STRING =
            "proteinGroup instanceof class uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.GeneGroup " +
                    "&& proteinGroup.taxid = 9606 && any(any(proteinGroup.proteins).peptides).uniqueness = 1";


    public void testIsGeneGroup() throws Exception {
        Predicate predicate = ProteinGroupPredicates.isGeneGroup();
        String predicateAsString = predicate.toString();
        assertEquals(GENE_GROUP_PREDICATE_STRING, predicateAsString);
    }

    public void testHasTaxid() throws Exception {
        Predicate predicate = ProteinGroupPredicates.hasTaxid(TAXID);
        String predicateAsString = predicate.toString();
        assertEquals(TAXID_PREDICATE_STRING, predicateAsString);
    }

    public void testHasPeptides() throws Exception {
        Predicate predicate = ProteinGroupPredicates.hasPeptides();
        String predicateAsString = predicate.toString();
        assertEquals(PEPTIDES_PREDICATE_STRING, predicateAsString);
    }

    public void testHasUniquePeptides() throws Exception {
        Predicate predicate = ProteinGroupPredicates.hasPeptides();
        String predicateAsString = predicate.toString();
        assertEquals(UNIQUE_PEPTIDES_PREDICATE_STRING, predicateAsString);
    }

    public void testIsGeneGroupAndHasTaxid() throws Exception {
        Predicate predicate = ProteinGroupPredicates.isGeneGroupAndHasTaxid(TAXID);
        String predicateAsString = predicate.toString();
        assertEquals(GENE_GROUP_TAXID_PREDICATE_STRING, predicateAsString);
    }

//    public void testIsGeneGroupAndHasTaxidAndHasPeptides() throws Exception {
//        Predicate predicate = ProteinGroupPredicates.isGeneGroupAndHasTaxidAndHasPeptides(TAXID);
//        String predicateAsString = predicate.toString();
//        assertEquals(GENE_GROUP_TAXID_PEPTIDES_PREDICATE_STRING, predicateAsString);
//    }
//
//
//    public void testIsGeneGroupAndHasTaxidAndHasUniquePeptides() throws Exception {
//        Predicate predicate = ProteinGroupPredicates.isGeneGroupAndHasTaxidAndHasUniquePeptides(TAXID);
//        String predicateAsString = predicate.toString();
//        assertEquals(GENE_GROUP_TAXID_UNIQUE_PEPTIDES_PREDICATE_STRING, predicateAsString);
//    }

}
