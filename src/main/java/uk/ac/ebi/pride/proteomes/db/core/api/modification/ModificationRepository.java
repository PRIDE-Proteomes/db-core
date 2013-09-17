package uk.ac.ebi.pride.proteomes.db.core.api.modification;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Jose A. Dianes
 * @version $Id$
 */
public interface ModificationRepository extends CrudRepository<Modification, String> {

    public Modification findByCvTerm(String term);
    public Modification findByCvName(String name);
    public List<Modification> findByBiologicalSignificant(Boolean biologicalSignificant);
    public List<Modification> findByMonoDeltaBetween(Double min, Double max);
}
