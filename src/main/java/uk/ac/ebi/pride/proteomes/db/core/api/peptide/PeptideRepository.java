package uk.ac.ebi.pride.proteomes.db.core.api.peptide;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
public interface PeptideRepository extends CrudRepository<Peptide, Long> {

    public List<Peptide> findBySequence(String sequence);
    public List<PeptideVariant> findPeptideVariantBySequence(String sequence);
    public List<PeptideSymbolic> findPeptideSymbolicBySequence(String sequence);

}
