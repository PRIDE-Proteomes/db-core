package uk.ac.ebi.pride.proteomes.db.core.api.feature;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
@Repository
@Transactional(readOnly = true)
public interface FeatureProteomesRepository extends JpaRepository<Feature, Long> {

}
