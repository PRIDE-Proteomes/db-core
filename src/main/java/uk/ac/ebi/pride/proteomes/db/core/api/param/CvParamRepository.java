package uk.ac.ebi.pride.proteomes.db.core.api.param;

import org.springframework.data.repository.CrudRepository;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
public interface CvParamRepository extends CrudRepository<CvParam, String> {

    public CvParam findByCvTerm(String term);

    //We are assuming that we don't have duplicated names
    public CvParam findByCvName(String name);

}
