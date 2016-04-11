package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public class ProteinGroupPredicates {


    public static Predicate isGeneGroup() {
        QProteinGroup proteinGroup = QProteinGroup.proteinGroup;
        QGeneGroup geneGroup = QGeneGroup.geneGroup;
        return proteinGroup.instanceOf(geneGroup.getType());
    }

    public static Predicate hasTaxid(final Integer taxid) {
        QProteinGroup proteinGroup = QProteinGroup.proteinGroup;
        return proteinGroup.taxid.eq(taxid);
    }

    public static Predicate hasPeptides() {
        QProteinGroup proteinGroup = QProteinGroup.proteinGroup;
        return proteinGroup.proteins.any().peptides.isNotEmpty();
    }

    public static Predicate hasUniquePeptides() {
        QProteinGroup proteinGroup = QProteinGroup.proteinGroup;
        return proteinGroup.proteins.any().peptides.any().uniqueness.eq(1);
    }

    public static Predicate isGeneGroupAndHasTaxid(final Integer taxid) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(isGeneGroup());
        booleanBuilder.and(hasTaxid(taxid));
        return booleanBuilder.getValue();
    }

//    public static Predicate isGeneGroupAndHasTaxidAndHasPeptides(final Integer taxid) {
//        BooleanBuilder booleanBuilder = new BooleanBuilder();
//        booleanBuilder.and(isGeneGroup());
//        booleanBuilder.and(hasTaxid(taxid));
//        booleanBuilder.and(hasPeptides());
//        return booleanBuilder.getValue();
//    }

//    public static Predicate isGeneGroupAndHasTaxidAndHasUniquePeptides(final Integer taxid) {
//        BooleanBuilder booleanBuilder = new BooleanBuilder();
//        booleanBuilder.and(isGeneGroup());
//        booleanBuilder.and(hasTaxid(taxid));
//        booleanBuilder.and(hasUniquePeptides());
//        return booleanBuilder.getValue();
//    }

}
