package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups;

import org.springframework.data.domain.Pageable;
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
public interface ProteinGroupRepository extends JpaRepository<ProteinGroup, String>, ProteinGroupRepositoryCustom {

    ProteinGroup findById(String proteinGroupId);

    List<ProteinGroup> findByTaxid(Integer taxid);
    List<ProteinGroup> findByTaxid(Integer taxid, Pageable pageable);

    long countByTaxid(int taxid);

    @Query("select count(distinct pg.geneGroup.id) from PeptideGroup pg where pg.taxid = :taxid and pg.geneGroup is not null")
    long countGeneGroupsByTaxidAndHasPeptides(@Param("taxid") Integer taxid);

    @Query("select count(distinct pg.geneGroup.id) from PeptideGroup pg where pg.taxid = :taxid and pg.uniqueness=1 and pg.geneGroup is not null")
    long countGeneGroupsByTaxidAndHasUniquePeptides(@Param("taxid") Integer taxid);

}
