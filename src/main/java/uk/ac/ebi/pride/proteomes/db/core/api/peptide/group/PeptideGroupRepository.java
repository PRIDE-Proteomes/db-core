package uk.ac.ebi.pride.proteomes.db.core.api.peptide.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
@Repository
public interface PeptideGroupRepository extends JpaRepository<PeptideGroup, PeptideGroupPK> {

    public List<PeptideGroup> findByPeptidePeptideIdAndProteinGroupId(Long peptideId, String proteinGroupId);

    public List<PeptideGroup> findByProteinGroupId(String proteinGroupId);
    // Note: the view generating the peptide - group uniqueness has been limited to only take EBTRY groups into account!


}
