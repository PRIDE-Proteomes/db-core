package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */

@Repository
public interface ProteinGroupRepository extends JpaRepository<ProteinGroup, String> {

   public ProteinGroup findById(String proteinGroupId);

}
