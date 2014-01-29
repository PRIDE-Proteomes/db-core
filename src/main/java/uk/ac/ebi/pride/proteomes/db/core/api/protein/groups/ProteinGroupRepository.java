package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */

@Repository
public interface ProteinGroupRepository extends JpaRepository<ProteinGroup, String> {

    ProteinGroup findById(String proteinGroupId);

    List<ProteinGroup> findByTaxid(int taxid);
    List<ProteinGroup> findByTaxid(int taxid, Pageable pageable);

    long countByTaxid(int taxid);


}
