package uk.ac.ebi.pride.proteomes.db.core.api.modification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
@Repository
public interface ModificationRepository extends JpaRepository<Modification, String> {

    public Modification findByModId(String id);
    public Modification findByModName(String name);
    public List<Modification> findByBiologicalSignificant(Boolean biologicalSignificant);
    public List<Modification> findByMonoDeltaBetween(Double min, Double max);
}
