package uk.ac.ebi.pride.proteomes.db.core.api.param;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
public interface CvParamProteomesRepository extends JpaRepository<CvParam, String> {

    public CvParam findByCvTerm(String term);

    @Query("select cv from Tissue cv where cv.cvTerm = :term")
    public Tissue findTissueByCvTerm(@Param("term") String term);

    @Query("select cv from CellType cv where cv.cvTerm = :term")
    public CellType findCellTypeByCvTerm(@Param("term") String term);

    @Query("select cv from Disease cv where cv.cvTerm = :term")
    public Disease findDiseaseByCvTerm(@Param("term") String term);

    @Query("select cv from FeatureType cv where cv.cvTerm = :term")
    public FeatureType findFeatureTypeByCvTerm(@Param("term") String term);

    //We are assuming that we don't have duplicated names
    public CvParam findByCvName(String name);

    @Query("select cv from Tissue cv where cv.cvName = :name")
    public Tissue findTissueByCvName(@Param("name") String name);

    @Query("select cv from CellType cv where cv.cvName = :name")
    public CellType findCellTypeByCvName(@Param("name") String name);

    @Query("select cv from Disease cv where cv.cvName = :name")
    public Disease findDiseaseByCvName(@Param("name") String name);

    @Query("select cv from FeatureType cv where cv.cvName = :name")
    public FeatureType findFeatureTypeByCvName(@Param("name") String name);


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

    @Query("select cv from FeatureType cv")
    public List<FeatureType> findAllFeatureType();

    @Query("select cv from Disease cv")
    public List<FeatureType> findAllFeatureType(Pageable pageable);


}
