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

    PeptideGroup findByPeptidePeptideIdAndGeneGroupId(Long peptideId, String geneGroupId);

    List<PeptideGroup> findByGeneGroupId(String geneGroupId);

    List<PeptideGroup> findByPeptidePeptideId(Long peptideId);

    List<PeptideGroup> findByGeneGroupIdAndUniqueness(String geneGroupId, Integer uniqueness);


    //    Num genes with assigned protein
    //    SELECT COUNT(distinct PROT_PGRP.PROT_GROUP_ID) FROM PROT_PGRP,PROT_GROUP WHERE PROT_PGRP.PROT_GROUP_ID = PROT_GROUP.PROT_GROUP_ID and  PROT_GROUP_TYPE='GENE' and TAXID=9606; --22189
    @Query("select count (distinct pg.id.proteinGroupId) from PeptideGroup pg, GeneGroup gg where pg.taxid = :taxid AND pg.geneGroup.id = gg.id")
    Long countMappedGenesByTaxId(@Param("taxid") Integer taxid);
}
