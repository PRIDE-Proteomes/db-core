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

    public List<T> findAllByTaxid(Integer taxid);

    public List<T> findAllByTissue(String cvTerm);

    public List<T> findAllByModification(String modId);

    public List<T> findAllByTaxidAndTissue(Integer taxid, String cvTerm);

    public List<T> findAllByTaxidAndModification(Integer taxid, String modId);

    public List<T> findAllByTissueAndModification(String cvTerm, String modId);

    public List<T> findAllByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId);

    public List<T> findAllByTaxid(Integer taxid, Pageable pageable);

    public List<T> findAllByTissue(String cvTerm, Pageable pageable);

    public List<T> findAllByModification(String modId, Pageable pageable);

    public List<T> findAllByTaxidAndTissue(Integer taxid, String cvTerm, Pageable pageable);

    public List<T> findAllByTaxidAndModification(Integer taxid, String modId, Pageable pageable);

    public List<T> findAllByTissueAndModification(String cvTerm, String modId, Pageable pageable);

    public List<T> findAllByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId, Pageable pageable);

    public long countByTaxid(Integer taxid);

    public long countByTaxidAndTissue(Integer taxid, String cvTerm);

    public long countByTaxidAndModification(Integer taxid, String modId);

    public long countByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId);
}
