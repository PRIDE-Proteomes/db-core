package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.gene;

import org.springframework.data.repository.CrudRepository;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
public interface GeneRepository extends CrudRepository<Gene, String> {

    Gene findByGeneAccession(String geneAccession);


}
