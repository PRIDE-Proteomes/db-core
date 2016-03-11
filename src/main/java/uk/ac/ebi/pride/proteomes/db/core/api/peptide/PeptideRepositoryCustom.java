package uk.ac.ebi.pride.proteomes.db.core.api.peptide;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import uk.ac.ebi.pride.proteomes.db.core.api.ProteomesRepository;

import java.util.List;

/**
 * User: ntoro
 * Date: 21/02/2014
 * Time: 14:20
 */

/**
 * Specific methods for SymbolicPeptides and Peptiforms in the PeptideRepository
 */
//TODO: Try to optimized and reduce the number of methods
@NoRepositoryBean
public interface PeptideRepositoryCustom extends ProteomesRepository<Peptide> {

    List<SymbolicPeptide> findAllSymbolicPeptides();

    List<SymbolicPeptide> findAllSymbolicPeptides(Pageable pageable);

    List<SymbolicPeptide> findAllSymbolicPeptideByTaxid(Integer taxid);

    List<SymbolicPeptide> findAllSymbolicPeptideByTaxid(Integer taxid, Pageable pageable);

    List<SymbolicPeptide> findAllSymbolicPeptideByTaxidAndTissue(Integer taxid, String cvTerm);

    List<SymbolicPeptide> findAllSymbolicPeptideByTaxidAndTissue(Integer taxid, String cvTerm, Pageable pageable);

    List<SymbolicPeptide> findAllSymbolicPeptideByTaxidAndModification(Integer taxid, String modId);

    List<SymbolicPeptide> findAllSymbolicPeptideByTaxidAndModification(Integer taxid, String modId, Pageable pageable);

    List<SymbolicPeptide> findAllSymbolicPeptideByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId);

    List<SymbolicPeptide> findAllSymbolicPeptideByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId, Pageable pageable);

    List<Peptiform> findAllPeptiforms();

    List<Peptiform> findAllPeptiforms(Pageable pageable);

    List<Peptiform> findAllPeptiformsByTaxid(Integer taxid);

    List<Peptiform> findAllPeptiformsByTaxid(Integer taxid, Pageable pageable);

    List<Peptiform> findAllPeptiformsByTaxidAndTissue(Integer taxid, String cvTerm);

    List<Peptiform> findAllPeptiformsByTaxidAndTissue(Integer taxid, String cvTerm, Pageable pageable);

    List<Peptiform> findAllPeptiformsByTaxidAndModification(Integer taxid, String modId);

    List<Peptiform> findAllPeptiformsByTaxidAndModification(Integer taxid, String modId, Pageable pageable);

    List<Peptiform> findAllPeptiformsByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId);

    List<Peptiform> findAllPeptiformsByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId, Pageable pageable);

    long countSymbolicPeptide();

    long countSymbolicPeptideByTaxid(Integer taxid);

    long countSymbolicPeptideByTaxidAndTissue(Integer taxid, String cvTerm);

    long countSymbolicPeptideByTaxidAndModification(Integer taxid, String modId);

    long countSymbolicPeptideByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId);

    long countSymbolicPeptideByTaxidAndHasProteins(Integer taxid);

    long countSymbolicPeptideByTaxidAndHasProteinsWithoutContaminants(Integer taxid);

    long countSymbolicPeptideByIsUniqueAndTaxidAndHasProteins(Integer taxid);

    long countSymbolicPeptideByIsUniqueAndTaxidAndHasProteinsWithoutContaminants(Integer taxid);

    long countSymbolicPeptideByTaxidAndHasGenes(Integer taxid);

    long countSymbolicPeptideByIsUniqueAndTaxidAndHasGenes(Integer taxid);

    long countPeptiforms();

    long countPeptiformsByTaxid(Integer taxid);

    long countPeptiformsByTaxidAndTissue(Integer taxid, String cvTerm);

    long countPeptiformsByTaxidAndModification(Integer taxid, String modId);

    long countPeptiformsByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId);


}
