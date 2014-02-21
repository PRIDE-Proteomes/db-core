package uk.ac.ebi.pride.proteomes.db.core.api.protein;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;


/**
 * User: ntoro
 * Date: 20/02/2014
 * Time: 10:11
 */
public class ProteinPredicates {

    public static Predicate hasTaxid(final Integer taxid) {
        QProtein protein = QProtein.protein;
        return protein.taxid.eq(taxid);
    }

    public static Predicate hasTissue(final String cvTerm) {
        QProtein protein = QProtein.protein;
        return protein.tissues.any().cvTerm.eq(cvTerm);
    }

    public static Predicate hasModification(final String modId) {
        QProtein protein = QProtein.protein;
        return protein.modificationLocations.any().modId.eq(modId);
    }

    public static Predicate hasTaxidAndTissue(final Integer taxid, final String cvTerm) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(hasTaxid(taxid));
        booleanBuilder.and(hasTissue(cvTerm));
        return booleanBuilder.getValue();
    }

    public static Predicate hasTaxidAndModification(final Integer taxid, final String modId) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(hasTaxid(taxid));
        booleanBuilder.and(hasModification(modId));
        return booleanBuilder.getValue();

    }

    public static Predicate hasTissueAndModification(final String cvTerm, final String modId) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(hasTissue(cvTerm));
        booleanBuilder.and(hasModification(modId));
        return booleanBuilder.getValue();
    }

    public static Predicate hasTaxidAndTissueAndModification(final Integer taxid, final String cvTerm, final String modId) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(hasTaxid(taxid));
        booleanBuilder.and(hasTissue(cvTerm));
        booleanBuilder.and(hasModification(modId));
        return booleanBuilder.getValue();
    }
}
