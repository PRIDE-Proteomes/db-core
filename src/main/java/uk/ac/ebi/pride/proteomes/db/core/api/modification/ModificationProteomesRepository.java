package uk.ac.ebi.pride.proteomes.db.core.api.modification;

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
public interface ModificationProteomesRepository extends JpaRepository<Modification, String> {

    Modification findByModId(String id);
    Modification findByModName(String name);
    List<Modification> findByBiologicalSignificant(Boolean biologicalSignificant);
    List<Modification> findByMonoDeltaBetween(Double min, Double max);
}
