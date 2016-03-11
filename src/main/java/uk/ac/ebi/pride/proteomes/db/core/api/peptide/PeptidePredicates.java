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

    public static Predicate isUnique() {
        QPeptide peptide = QPeptide.peptide;
        return peptide.proteins.any().uniqueness.eq(1);
    }

    public static Predicate hasProteins() {
        QPeptide peptide = QPeptide.peptide;
        return peptide.proteins.isNotEmpty();
    }

    public static Predicate hasGenes() {
        QPeptide peptide = QPeptide.peptide;
        return peptide.peptideGroups.any().geneGroup.isNotNull();
    }

    public static Predicate hasProteinsWithoutContaminants() {
        QPeptide peptide = QPeptide.peptide;
        return peptide.proteins.any().protein.contaminant.eq(false);
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

    public static Predicate isSymbolicPeptide() {
        QPeptide peptide = QPeptide.peptide;
        QSymbolicPeptide symbolicPeptide = QSymbolicPeptide.symbolicPeptide;
        return peptide.instanceOf(symbolicPeptide.getType());
    }

    public static Predicate isSymbolicPeptideAndIsUnique() {
        QPeptide peptide = QPeptide.peptide;
        QSymbolicPeptide symbolicPeptide = QSymbolicPeptide.symbolicPeptide;
        return peptide.instanceOf(symbolicPeptide.getType()).and(isUnique());
    }

    public static Predicate isSymbolicPeptideAndHasTaxid(final Integer taxid) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(isSymbolicPeptide());
        booleanBuilder.and(hasTaxid(taxid));
        return booleanBuilder.getValue();
    }

    public static Predicate isSymbolicPeptideAndHasTaxidAndTissue(final Integer taxid, final String cvTerm) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(isSymbolicPeptide());
        booleanBuilder.and(hasTaxidAndTissue(taxid, cvTerm));
        return booleanBuilder.getValue();
    }

    public static Predicate isSymbolicPeptideAndHasTaxidAndModification(final Integer taxid, final String modId) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(isSymbolicPeptide());
        booleanBuilder.and(hasTaxidAndModification(taxid, modId));
        return booleanBuilder.getValue();
    }

    public static Predicate isSymbolicPeptideAndHasTaxidAndTissueAndModification(final Integer taxid, final String cvTerm, final String modId) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(isSymbolicPeptide());
        booleanBuilder.and(hasTaxidAndTissueAndModification(taxid, cvTerm, modId));
        return booleanBuilder.getValue();
    }

    public static Predicate isSymbolicPeptideAndHasTaxidAndHasProteins(final Integer taxid) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(isSymbolicPeptide());
        booleanBuilder.and(hasTaxid(taxid));
        booleanBuilder.and(hasProteins());
        return booleanBuilder.getValue();
    }

    public static Predicate isSymbolicPeptideAndHasTaxidAndHasProteinsWithoutContaminants(final Integer taxid) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(isSymbolicPeptide());
        booleanBuilder.and(hasTaxid(taxid));
        booleanBuilder.and(hasProteinsWithoutContaminants());
        return booleanBuilder.getValue();
    }

    public static Predicate isSymbolicPeptideAndIsUniqueAndHasTaxidAndHasProteins(final Integer taxid) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(isSymbolicPeptideAndIsUnique());
        booleanBuilder.and(hasTaxid(taxid));
        booleanBuilder.and(hasProteins());
        return booleanBuilder.getValue();
    }

    public static Predicate isSymbolicPeptideAndIsUniqueAndHasTaxidAndHasProteinsWithoutContaminants(final Integer taxid) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(isSymbolicPeptideAndIsUnique());
        booleanBuilder.and(hasTaxid(taxid));
        booleanBuilder.and(hasProteinsWithoutContaminants());
        return booleanBuilder.getValue();
    }

    public static Predicate isSymbolicPeptideAndHasTaxidAndHasGenes(final Integer taxid) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(isSymbolicPeptide());
        booleanBuilder.and(hasTaxid(taxid));
        booleanBuilder.and(hasGenes());
        return booleanBuilder.getValue();
    }

    public static Predicate isSymbolicPeptideAndIsUniqueAndHasTaxidAndHasGenes(final Integer taxid) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(isSymbolicPeptideAndIsUnique());
        booleanBuilder.and(hasTaxid(taxid));
        booleanBuilder.and(hasGenes());
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

    public static Predicate isPeptiformHasTaxidAndTissue(final Integer taxid, final String cvTerm) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(isPeptiform());
        booleanBuilder.and(hasTaxidAndTissue(taxid, cvTerm));
        return booleanBuilder.getValue();
    }

    public static Predicate isPeptiformHasTaxidAndModification(final Integer taxid, final String modId) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(isPeptiform());
        booleanBuilder.and(hasTaxidAndModification(taxid, modId));
        return booleanBuilder.getValue();
    }

    public static Predicate isPeptiformHasTaxidAndTissueAndModification(final Integer taxid, final String cvTerm, final String modId) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(isPeptiform());
        booleanBuilder.and(hasTaxidAndTissueAndModification(taxid, cvTerm, modId));
        return booleanBuilder.getValue();
    }

}
