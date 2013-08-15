package uk.ac.ebi.pride.proteomes.db.core.api.protein;

import org.springframework.data.repository.CrudRepository;

/**
 * Created with IntelliJ IDEA.
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */
public interface ProteinRepository extends CrudRepository<Protein, String> {

}
