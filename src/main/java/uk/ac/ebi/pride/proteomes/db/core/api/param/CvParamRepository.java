package uk.ac.ebi.pride.proteomes.db.core.api.param;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */
public interface CvParamRepository extends CrudRepository<CvParam, String> {

    public CvParam findByCvTerm(String term);
    public CvParam findByCvName(String name);
    public List<CvParam> findAllByCvType(String cvType);

}
