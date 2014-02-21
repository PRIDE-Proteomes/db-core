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
@NoRepositoryBean
public interface PeptideRepositoryCustom extends ProteomesRepository<Peptide> {

    public List<SymbolicPeptide> findAllSymbolicPeptides();

    public List<SymbolicPeptide> findAllSymbolicPeptides(Pageable pageable);

    public List<SymbolicPeptide> findAllSymbolicPeptideByTaxid(Integer taxid);

    public List<SymbolicPeptide> findAllSymbolicPeptideByTaxid(Integer taxid, Pageable pageable);

    public List<SymbolicPeptide> findAllSymbolicPeptideByTaxidAndTissue(Integer taxid, String cvTerm);

    public List<SymbolicPeptide> findAllSymbolicPeptideByTaxidAndTissue(Integer taxid, String cvTerm, Pageable pageable);

    public List<SymbolicPeptide> findAllSymbolicPeptideByTaxidAndModification(Integer taxid, String modId);

    public List<SymbolicPeptide> findAllSymbolicPeptideByTaxidAndModification(Integer taxid, String modId, Pageable pageable);

    public List<SymbolicPeptide> findAllSymbolicPeptideByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId);

    public List<SymbolicPeptide> findAllSymbolicPeptideByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId, Pageable pageable);

    public List<Peptiform> findAllPeptiforms();

    public List<Peptiform> findAllPeptiforms(Pageable pageable);

    public List<Peptiform> findAllPeptiformsByTaxid(Integer taxid);

    public List<Peptiform> findAllPeptiformsByTaxid(Integer taxid, Pageable pageable);

    public List<Peptiform> findAllPeptiformsByTaxidAndTissue(Integer taxid, String cvTerm);

    public List<Peptiform> findAllPeptiformsByTaxidAndTissue(Integer taxid, String cvTerm, Pageable pageable);

    public List<Peptiform> findAllPeptiformsByTaxidAndModification(Integer taxid, String modId);

    public List<Peptiform> findAllPeptiformsByTaxidAndModification(Integer taxid, String modId, Pageable pageable);

    public List<Peptiform> findAllPeptiformsByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId);

    public List<Peptiform> findAllPeptiformsByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId, Pageable pageable);

    public long countSymbolicPeptide();

    public long countSymbolicPeptideByTaxid(Integer taxid);

    public long countSymbolicPeptideByTaxidAndTissue(Integer taxid, String cvTerm);

    public long countSymbolicPeptideByTaxidAndModification(Integer taxid, String modId);

    public long countSymbolicPeptideByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId);

    public long countPeptiforms();

    public long countPeptiformsByTaxid(Integer taxid);

    public long countPeptiformsByTaxidAndTissue(Integer taxid, String cvTerm);

    public long countPeptiformsByTaxidAndModification(Integer taxid, String modId);

    public long countPeptiformsByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId);


}
