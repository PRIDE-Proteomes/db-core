package uk.ac.ebi.pride.proteomes.db.core.api.assay;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
public interface AssayRepository extends CrudRepository<Assay, String> {

//  public  List<Assay> findAllAssays();
    public List<Assay> findByProjectAccession(String projectAccession);

//   public Assay findByAssayAccession(String assayAccession);

}
