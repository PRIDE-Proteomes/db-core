package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
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
public interface ProteinGroupRepository extends JpaRepository<ProteinGroup, String> {

    ProteinGroup findById(String proteinGroupId);

    List<ProteinGroup> findByTaxid(Integer taxid);
    List<ProteinGroup> findByTaxid(Integer taxid, Pageable pageable);

    long countByTaxid(int taxid);


}
