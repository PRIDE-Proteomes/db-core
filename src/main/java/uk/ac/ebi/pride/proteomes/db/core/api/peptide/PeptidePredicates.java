package uk.ac.ebi.pride.proteomes.db.core.api.peptide;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;


/**
 * User: ntoro
 * Date: 20/02/2014
 * Time: 10:11
 */
public class PeptidePredicates {

    public static Predicate hasTaxid(final Integer taxid) {
        QPeptide peptide = QPeptide.peptide;
        return peptide.taxid.eq(taxid);
    }

    public static Predicate hasTissue(final String cvTerm) {
        QPeptide peptide = QPeptide.peptide;
        return peptide.tissues.any().cvTerm.eq(cvTerm);
    }

    public static Predicate hasModification(final String modId) {
        QPeptide peptide = QPeptide.peptide;
        return peptide.modificationLocations.any().modId.eq(modId);
    }

    public static Predicate hasTaxidAndTissue (final Integer taxid, final String cvTerm){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(hasTaxid(taxid));
        booleanBuilder.and(hasTissue(cvTerm));
        return booleanBuilder.getValue();
    }

    public static Predicate hasTaxidAndModification (final Integer taxid, final String modId){
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

    public static Predicate hasTaxidAndTissueAndModification (final Integer taxid, final String cvTerm, final String modId){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(hasTaxid(taxid));
        booleanBuilder.and(hasTissue(cvTerm));
        booleanBuilder.and(hasModification(modId));
        return booleanBuilder.getValue();
    }

    public static Predicate isSymbolicPeptide() {
        QPeptide peptide = QPeptide.peptide;
        QSymbolicPeptide symbolicPeptide = QSymbolicPeptide.symbolicPeptide;
        return peptide.instanceOf(symbolicPeptide.getType());
    }

    public static Predicate isSymbolicPeptideHasTaxid (final Integer taxid){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(isSymbolicPeptide());
        booleanBuilder.and(hasTaxid(taxid));
        return booleanBuilder.getValue();
    }

    public static Predicate isSymbolicPeptideHasTaxidAndTissue (final Integer taxid, final String cvTerm){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(isSymbolicPeptide());
        booleanBuilder.and(hasTaxidAndTissue(taxid,cvTerm));
        return booleanBuilder.getValue();
    }

    public static Predicate isSymbolicPeptideHasTaxidAndModification (final Integer taxid, final String modId){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(isSymbolicPeptide());
        booleanBuilder.and(hasTaxidAndModification(taxid,modId));
        return booleanBuilder.getValue();
    }

    public static Predicate isSymbolicPeptideHasTaxidAndTissueAndModification (final Integer taxid, final String cvTerm, final String modId){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(isSymbolicPeptide());
        booleanBuilder.and(hasTaxidAndTissueAndModification(taxid,cvTerm,modId));
        return booleanBuilder.getValue();
    }

    public static Predicate isPeptiform() {
        QPeptide peptide = QPeptide.peptide;
        QPeptiform peptiform = QPeptiform.peptiform;
        return peptide.instanceOf(peptiform.getType());
    }

    public static Predicate isPeptiformHasTaxid(final Integer taxid) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(isPeptiform());
        booleanBuilder.and(hasTaxid(taxid));
        return booleanBuilder.getValue();
    }

    public static Predicate isPeptiformHasTaxidAndTissue (final Integer taxid, final String cvTerm){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(isPeptiform());
        booleanBuilder.and(hasTaxidAndTissue(taxid,cvTerm));
        return booleanBuilder.getValue();
    }

    public static Predicate isPeptiformHasTaxidAndModification (final Integer taxid, final String modId){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(isPeptiform());
        booleanBuilder.and(hasTaxidAndModification(taxid,modId));
        return booleanBuilder.getValue();
    }

    public static Predicate isPeptiformHasTaxidAndTissueAndModification (final Integer taxid, final String cvTerm, final String modId){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(isPeptiform());
        booleanBuilder.and(hasTaxidAndTissueAndModification(taxid,cvTerm,modId));
        return booleanBuilder.getValue();
    }
}
