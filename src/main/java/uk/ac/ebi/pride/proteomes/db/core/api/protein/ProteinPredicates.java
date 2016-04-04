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

    public static Predicate isContaminant() {
        QProtein protein = QProtein.protein;
        return protein.contaminant.eq(true);
    }

    public static Predicate isCanonical() {
        QProtein protein = QProtein.protein;
        return protein.name.notLike("%soform");
    }

    public static Predicate hasPeptides() {
        QProtein protein = QProtein.protein;
        return protein.peptides.isNotEmpty();
    }

    public static Predicate hasUniquePeptides() {
        QProtein protein = QProtein.protein;
        return protein.peptides.any().uniqueness.eq(1);
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

    public static Predicate hasTaxidAndIsNotContaminant(final Integer taxid) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(hasTaxid(taxid));
        booleanBuilder.andNot(isContaminant());
        return booleanBuilder.getValue();
    }

    public static Predicate hasTaxidAndIsNotContaminantAndIsCanonical(final Integer taxid) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(hasTaxid(taxid));
        booleanBuilder.andNot(isContaminant());
        booleanBuilder.and(isCanonical());
        return booleanBuilder.getValue();
    }

    public static Predicate hasTaxidAndIsNotContaminantAndIsNotCanonical(final Integer taxid) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(hasTaxid(taxid));
        booleanBuilder.andNot(isContaminant());
        booleanBuilder.andNot(isCanonical());
        return booleanBuilder.getValue();
    }

    public static Predicate hasTaxidAndIsNotContaminantAndHasPeptides(final Integer taxid) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(hasTaxid(taxid));
        booleanBuilder.andNot(isContaminant());
        booleanBuilder.and(hasPeptides());
        return booleanBuilder.getValue();
    }

    public static Predicate hasTaxidAndIsNotContaminantAndIsCanonicalHasPeptides(final Integer taxid) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(hasTaxidAndIsNotContaminantAndHasPeptides(taxid));
        booleanBuilder.and(isCanonical());
        return booleanBuilder.getValue();
    }

    public static Predicate hasTaxidAndIsNotContaminantAndIsNotCanonicalHasPeptides(final Integer taxid) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(hasTaxidAndIsNotContaminantAndHasPeptides(taxid));
        booleanBuilder.andNot(isCanonical());
        return booleanBuilder.getValue();
    }

    public static Predicate hasTaxidAndIsNotContaminantAndHasUniquePeptides(final Integer taxid) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(hasTaxid(taxid));
        booleanBuilder.andNot(isContaminant());
        booleanBuilder.and(hasUniquePeptides());
        return booleanBuilder.getValue();
    }

    public static Predicate hasTaxidAndIsNotContaminantAndIsCanonicalHasUniquePeptides(final Integer taxid) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(hasTaxidAndIsNotContaminantAndHasUniquePeptides(taxid));
        booleanBuilder.and(isCanonical());
        return booleanBuilder.getValue();
    }

    public static Predicate hasTaxidAndIsNotContaminantAndIsNotCanonicalHasUniquePeptides(final Integer taxid) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(hasTaxidAndIsNotContaminantAndHasUniquePeptides(taxid));
        booleanBuilder.andNot(isCanonical());
        return booleanBuilder.getValue();
    }
}
