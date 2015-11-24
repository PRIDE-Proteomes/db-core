package uk.ac.ebi.pride.proteomes.db.core.api.peptide.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
@Repository
@Transactional(readOnly = true)
public interface PeptideGroupRepository extends JpaRepository<PeptideGroup, PeptideGroupPK> {

    public PeptideGroup findByPeptidePeptideIdAndProteinGroupId(Long peptideId, String proteinGroupId);

    public List<PeptideGroup> findByProteinGroupId(String proteinGroupId);

    public List<PeptideGroup> findByPeptidePeptideId(Long peptideId);

    public List<PeptideGroup> findByProteinGroupIdAndUniqueness(String proteinGroupId, Integer uniqueness);

    @Query("select count (distinct pg.id.proteinGroupId) from PeptideGroup pg, GeneGroup gg where pg.proteinGroup.taxid = :taxid AND pg.proteinGroup.id = gg.id" )
    public long countMappedGenesByTaxId(@Param("taxid") Integer taxid);
}
