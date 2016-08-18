package uk.ac.ebi.pride.proteomes.db.core.api.protein;

import org.springframework.data.repository.NoRepositoryBean;
import uk.ac.ebi.pride.proteomes.db.core.api.ProteomesRepository;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */

/**
 * Specific methods for release summary
 */
//TODO: Try to optimized and reduce the number of methods
@NoRepositoryBean
public interface ProteinRepositoryCustom extends ProteomesRepository<Protein> {

    /* Specific for proteins. It is used in the stats */

    long countByTaxidAndIsNotContaminant(Integer taxid);

    long countByTaxidAndIsNotContaminantAndIsCanonical(Integer taxid);

    long countByTaxidAndIsNotContaminantAndIsIsoform(Integer taxid);

    /* Mapped proteins with peptides */

    // Mapped proteins
    long countByTaxidAndIsNotContaminantAndHasPeptides(Integer taxid);

    // Mapped canonical proteins
    long countByTaxidAndIsNotContaminantAndIsCanonicalAndHasPeptides(Integer taxid);

    // Mapped isoform proteins
    long countByTaxidAndIsNotContaminantAndIsIsoformAndHasPeptides(Integer taxid);

    /* Mapped proteins with unique peptides */

    // Mapped proteins with at least one unique peptide
    long countByTaxidAndIsNotContaminantAndHasUniquePeptides(Integer taxid);

    // Mapped canonical proteins with at least one unique peptide
    long countByTaxidAndIsNotContaminantAndIsCanonicalAndHasUniquePeptides(Integer taxid);

    // Mapped isoform proteins with at least one unique peptide
    long countByTaxidAndIsNotContaminantAndIsIsoformAndHasUniquePeptides(Integer taxid);

    // Calculate protein evidence for the total number of proteins
    long countByTaxidAndEvidenceAndIsNotContaminant(Integer taxid, Integer evidence);

    // Calculate protein evidence for the mapped proteins only
    long countByTaxidAndEvidenceAndIsNotContaminantAndHasPeptides(Integer taxid, Integer evidence);


}
