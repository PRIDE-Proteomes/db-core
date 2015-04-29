package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;

public class ProteinGroupPredicates {


    public static Predicate hasTaxid(final Integer taxid) {
        QProteinGroup proteinGroup = QProteinGroup.proteinGroup;
        return proteinGroup.taxid.eq(taxid);
    }

    public static Predicate isEntryGroup() {
        QProteinGroup proteinGroup = QProteinGroup.proteinGroup;
        QEntryGroup entryGroup = QEntryGroup.entryGroup;
        return proteinGroup.instanceOf(entryGroup.getType());
    }

    public static Predicate isGeneGroup() {
        QProteinGroup proteinGroup = QProteinGroup.proteinGroup;
        QGeneGroup geneGroup = QGeneGroup.geneGroup;
        return proteinGroup.instanceOf(geneGroup.getType());
    }

    public static Predicate isEntryGroupHasTaxid(final Integer taxid) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(isEntryGroup());
        booleanBuilder.and(hasTaxid(taxid));
        return booleanBuilder.getValue();
    }

    public static Predicate isGeneGroupHasTaxid(final Integer taxid) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(isGeneGroup());
        booleanBuilder.and(hasTaxid(taxid));
        return booleanBuilder.getValue();
    }


}
