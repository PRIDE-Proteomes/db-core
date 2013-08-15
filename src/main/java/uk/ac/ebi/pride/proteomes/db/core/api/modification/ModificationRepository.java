package uk.ac.ebi.pride.proteomes.db.core.api.modification;

import org.springframework.data.repository.CrudRepository;
import uk.ac.ebi.pride.proteomes.db.core.api.assay.Assay;

import java.util.List;

/**
 * @author Jose A. Dianes
 * @version $Id$
 */
public interface ModificationRepository extends CrudRepository<Assay, String> {

    public List<Assay> findAllAssays();
    public List<Assay> findAllAssaysByProjectAccession(String projectAccession);

    public Assay findByAssayAccession(String assayAccession);

}
