package uk.ac.ebi.pride.proteomes.db.core.api;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * User: ntoro
 * Date: 21/02/2014
 * Time: 10:25
 */

/**
 * General methods that need to be implemented for the repositories od entities that contains taxonomy identifier,
 * tissue and modification information (e.g. Protein, Peptide, SymbolicPeptide, Peptiform)
 * @param <T>
 */
@NoRepositoryBean
public interface ProteomesRepository<T> {

    List<T> findAllByTaxid(Integer taxid);

    List<T> findAllByTissue(String cvTerm);

    List<T> findAllByModification(String modId);

    List<T> findAllByTaxidAndTissue(Integer taxid, String cvTerm);

    List<T> findAllByTaxidAndModification(Integer taxid, String modId);

    List<T> findAllByTissueAndModification(String cvTerm, String modId);

    List<T> findAllByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId);

    List<T> findAllByTaxid(Integer taxid, Pageable pageable);

    List<T> findAllByTissue(String cvTerm, Pageable pageable);

    List<T> findAllByModification(String modId, Pageable pageable);

    List<T> findAllByTaxidAndTissue(Integer taxid, String cvTerm, Pageable pageable);

    List<T> findAllByTaxidAndModification(Integer taxid, String modId, Pageable pageable);

    List<T> findAllByTissueAndModification(String cvTerm, String modId, Pageable pageable);

    List<T> findAllByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId, Pageable pageable);

    long countByTaxid(Integer taxid);

    long countByTaxidAndTissue(Integer taxid, String cvTerm);

    long countByTaxidAndModification(Integer taxid, String modId);

    long countByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId);
}
