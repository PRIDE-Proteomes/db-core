package uk.ac.ebi.pride.proteomes.db.core.api.assay;

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
public interface AssayProteomesRepository extends JpaRepository<Assay, String> {

//  public  List<Assay> findAllAssays();
    public List<Assay> findByProjectAccession(String projectAccession);

//   public Assay findByAssayAccession(String assayAccession);

}
