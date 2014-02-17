package uk.ac.ebi.pride.proteomes.db.core.api.param;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
@Repository
public interface CvParamProteomesRepository extends JpaRepository<CvParam, String> {

    public CvParam findByCvTerm(String term);

    //We are assuming that we don't have duplicated names
    public CvParam findByCvName(String name);

    @Query("select cv from Tissue cv")
    public List<Tissue> findAllTissue();

    @Query("select cv from Tissue cv")
    public List<Tissue> findAllTissue(Pageable pageable);

    @Query("select cv from CellType cv")
    public List<CellType> findAllCellType();

    @Query("select cv from CellType cv")
    public List<CellType> findAllCellType(Pageable pageable);

    @Query("select cv from Disease cv")
    public List<Disease> findAllDisease();

    @Query("select cv from Disease cv")
    public List<Disease> findAllDisease(Pageable pageable);


}
