package uk.ac.ebi.pride.proteomes.db.core.api.peptide;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.stereotype.Repository;
import uk.ac.ebi.pride.proteomes.db.core.api.ProteomesRepository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

import static uk.ac.ebi.pride.proteomes.db.core.api.peptide.PeptidePredicates.*;

/**
 * User: ntoro
 * Date: 21/02/2014
 * Time: 11:08
 */
@Repository
@SuppressWarnings("unchecked")
public class PeptideRepositoryImpl implements ProteomesRepository<Peptide>, PeptideRepositoryCustom {

    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    private QueryDslJpaRepository<? extends Peptide, Long> peptideRepository;

    //TODO: Remove the unchecked warnings for the lists using a better approach instead of down casting;

    public PeptideRepositoryImpl() {

    }

    @Override
    public List<Peptide> findAllByTaxid(Integer taxid) {
        return (List<Peptide>) peptideRepository.findAll(hasTaxid(taxid));
    }

    @Override
    public List<Peptide> findAllByTissue(String cvTerm) {
        return (List<Peptide>) peptideRepository.findAll(hasTissue(cvTerm));
    }

    @Override
    public List<Peptide> findAllByModification(String modId) {
        return (List<Peptide>) peptideRepository.findAll(hasModification(modId));
    }

    @Override
    public List<Peptide> findAllByTaxidAndTissue(Integer taxid, String cvTerm) {
        return (List<Peptide>) peptideRepository.findAll(hasTaxidAndTissue(taxid, cvTerm));
    }

    @Override
    public List<Peptide> findAllByTaxidAndModification(Integer taxid, String modId) {
        return (List<Peptide>) peptideRepository.findAll(hasTaxidAndTissue(taxid, modId));
    }

    @Override
    public List<Peptide> findAllByTissueAndModification(String cvTerm, String modId) {
        return (List<Peptide>) peptideRepository.findAll(hasTissueAndModification(cvTerm, modId));
    }

    @Override
    public List<Peptide> findAllByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId) {
        return (List<Peptide>) peptideRepository.findAll(hasTaxidAndTissueAndModification(taxid, cvTerm, modId));
    }

    @Override
    public List<Peptide> findAllByTaxid(Integer taxid, Pageable pageable) {
        return (List<Peptide>) peptideRepository.findAll(hasTaxid(taxid), pageable).getContent();
    }

    @Override
    public List<Peptide> findAllByTissue(String cvTerm, Pageable pageable) {
        return (List<Peptide>) peptideRepository.findAll(hasTissue(cvTerm), pageable).getContent();
    }

    @Override
    public List<Peptide> findAllByModification(String modId, Pageable pageable) {
        return (List<Peptide>) peptideRepository.findAll(hasModification(modId), pageable).getContent();
    }

    @Override
    public List<Peptide> findAllByTaxidAndTissue(Integer taxid, String cvTerm, Pageable pageable) {
        return (List<Peptide>) peptideRepository.findAll(hasTaxidAndTissue(taxid, cvTerm), pageable).getContent();
    }

    @Override
    public List<Peptide> findAllByTaxidAndModification(Integer taxid, String modId, Pageable pageable) {
        return (List<Peptide>) peptideRepository.findAll(hasTaxidAndTissue(taxid, modId), pageable).getContent();
    }

    @Override
    public List<Peptide> findAllByTissueAndModification(String cvTerm, String modId, Pageable pageable) {
        return (List<Peptide>) peptideRepository.findAll(hasTissueAndModification(cvTerm, modId), pageable).getContent();
    }

    @Override
    public List<Peptide> findAllByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId, Pageable pageable) {
        return (List<Peptide>) peptideRepository.findAll(hasTaxidAndTissueAndModification(taxid, cvTerm, modId), pageable).getContent();
    }

    @Override
    public List<SymbolicPeptide> findAllSymbolicPeptides() {
        return (List<SymbolicPeptide>) peptideRepository.findAll(isSymbolicPeptide());
    }

    @Override
    public List<SymbolicPeptide> findAllSymbolicPeptides(Pageable pageable) {
        return (List<SymbolicPeptide>) peptideRepository.findAll(isSymbolicPeptide(), pageable).getContent();
    }

    @Override
    public List<SymbolicPeptide> findAllSymbolicPeptideByTaxid(Integer taxid) {
        return (List<SymbolicPeptide>) peptideRepository.findAll(isSymbolicPeptideHasTaxid(taxid));
    }

    @Override
    public List<SymbolicPeptide> findAllSymbolicPeptideByTaxid(Integer taxid, Pageable pageable) {
        return (List<SymbolicPeptide>) peptideRepository.findAll(isSymbolicPeptideHasTaxid(taxid),pageable).getContent();
    }

    @Override
    public List<SymbolicPeptide> findAllSymbolicPeptideByTaxidAndTissue(Integer taxid, String cvTerm) {
        return (List<SymbolicPeptide>) peptideRepository.findAll(isSymbolicPeptideHasTaxidAndTissue(taxid, cvTerm));
    }

    @Override
    public List<SymbolicPeptide> findAllSymbolicPeptideByTaxidAndTissue(Integer taxid, String cvTerm, Pageable pageable) {
        return (List<SymbolicPeptide>) peptideRepository.findAll(isSymbolicPeptideHasTaxidAndTissue(taxid, cvTerm),pageable).getContent();
    }

    @Override
    public List<SymbolicPeptide> findAllSymbolicPeptideByTaxidAndModification(Integer taxid, String modId) {
        return (List<SymbolicPeptide>) peptideRepository.findAll(isSymbolicPeptideHasTaxidAndModification(taxid, modId));
    }

    @Override
    public List<SymbolicPeptide> findAllSymbolicPeptideByTaxidAndModification(Integer taxid, String modId, Pageable pageable) {
        return (List<SymbolicPeptide>) peptideRepository.findAll(isSymbolicPeptideHasTaxidAndModification(taxid, modId),pageable).getContent();
    }

    @Override
    public List<SymbolicPeptide> findAllSymbolicPeptideByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId) {
        return (List<SymbolicPeptide>) peptideRepository.findAll(isSymbolicPeptideHasTaxidAndTissueAndModification(taxid, cvTerm, modId));
    }

    @Override
    public List<SymbolicPeptide> findAllSymbolicPeptideByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId, Pageable pageable) {
        return (List<SymbolicPeptide>) peptideRepository.findAll(isSymbolicPeptideHasTaxidAndTissueAndModification(taxid, cvTerm, modId),pageable).getContent();
    }

    @Override
    public List<Peptiform> findAllPeptiforms() {
        return (List<Peptiform>) peptideRepository.findAll(isPeptiform());
    }

    @Override
    public List<Peptiform> findAllPeptiforms(Pageable pageable) {
        return (List<Peptiform>) peptideRepository.findAll(isPeptiform(),pageable).getContent();
    }

    @Override
    public List<Peptiform> findAllPeptiformsByTaxid(Integer taxid) {
        return (List<Peptiform>) peptideRepository.findAll(isPeptiformHasTaxid(taxid));
    }

    @Override
    public List<Peptiform> findAllPeptiformsByTaxid(Integer taxid, Pageable pageable) {
        return (List<Peptiform>) peptideRepository.findAll(isPeptiformHasTaxid(taxid),pageable).getContent();
    }

    @Override
    public List<Peptiform> findAllPeptiformsByTaxidAndTissue(Integer taxid, String cvTerm) {
        return (List<Peptiform>) peptideRepository.findAll(isPeptiformHasTaxidAndTissue(taxid, cvTerm));
    }

    @Override
    public List<Peptiform>  findAllPeptiformsByTaxidAndTissue(Integer taxid, String cvTerm, Pageable pageable) {
        return (List<Peptiform>) peptideRepository.findAll(isPeptiformHasTaxidAndTissue(taxid, cvTerm),pageable).getContent();
    }

    @Override
    public List<Peptiform> findAllPeptiformsByTaxidAndModification(Integer taxid, String modId) {
        return (List<Peptiform>) peptideRepository.findAll(isPeptiformHasTaxidAndModification(taxid, modId));
    }

    @Override
    public List<Peptiform>  findAllPeptiformsByTaxidAndModification(Integer taxid, String modId, Pageable pageable) {
        return (List<Peptiform>) peptideRepository.findAll(isPeptiformHasTaxidAndModification(taxid, modId),pageable).getContent();
    }

    @Override
    public List<Peptiform> findAllPeptiformsByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId) {
        return (List<Peptiform>) peptideRepository.findAll(isPeptiformHasTaxidAndTissueAndModification(taxid, cvTerm, modId));
    }

    @Override
    public List<Peptiform>  findAllPeptiformsByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId, Pageable pageable) {
        return (List<Peptiform>) peptideRepository.findAll(isPeptiformHasTaxidAndTissueAndModification(taxid, cvTerm, modId),pageable).getContent();
    }

    @Override
    public long countByTaxid(Integer taxid) {
        return peptideRepository.count(hasTaxid(taxid));
    }

    @Override
    public long countByTaxidAndTissue(Integer taxid, String cvTerm) {
        return peptideRepository.count(hasTaxidAndTissue(taxid, cvTerm));
    }

    @Override
    public long countByTaxidAndModification(Integer taxid, String modId) {
        return peptideRepository.count(hasTaxidAndModification(taxid, modId));
    }

    @Override
    public long countByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId) {
        return peptideRepository.count(hasTaxidAndTissueAndModification(taxid, cvTerm, modId));
    }

    @Override
    public long countSymbolicPeptide() {
        return peptideRepository.count(isSymbolicPeptide());
    }

    @Override
    public long countSymbolicPeptideByTaxid(Integer taxid) {
        return peptideRepository.count(isSymbolicPeptideHasTaxid(taxid));
    }

    @Override
    public long countSymbolicPeptideByTaxidAndTissue(Integer taxid, String cvTerm) {
        return peptideRepository.count(isSymbolicPeptideHasTaxidAndTissue(taxid, cvTerm));
    }

    @Override
    public long countSymbolicPeptideByTaxidAndModification(Integer taxid, String modId) {
        return peptideRepository.count(isSymbolicPeptideHasTaxidAndModification(taxid, modId));
    }

    @Override
    public long countSymbolicPeptideByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId) {
        return peptideRepository.count(isSymbolicPeptideHasTaxidAndTissueAndModification(taxid, cvTerm, modId));
    }

    @Override
    public long countPeptiforms() {
        return peptideRepository.count(isPeptiform());
    }

    @Override
    public long countPeptiformsByTaxid(Integer taxid) {
        return peptideRepository.count(isPeptiformHasTaxid(taxid));
    }

    @Override
    public long countPeptiformsByTaxidAndTissue(Integer taxid, String cvTerm) {
        return peptideRepository.count(isPeptiformHasTaxidAndTissue(taxid, cvTerm));
    }

    @Override
    public long countPeptiformsByTaxidAndModification(Integer taxid, String modId) {
        return peptideRepository.count(isPeptiformHasTaxidAndModification(taxid, modId));
    }

    @Override
    public long countPeptiformsByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId) {
        return peptideRepository.count(isSymbolicPeptideHasTaxidAndTissueAndModification(taxid,cvTerm,modId));
    }

    /**
     * An initialization method which is run after the bean has been constructed.
     * This ensures that the entity manager is injected before we try to use it.
     */
    @PostConstruct
    public void init() {
        JpaEntityInformation<Peptide, Long> peptideEntityInfo = new JpaMetamodelEntityInformation<Peptide, Long>(Peptide.class, entityManager.getMetamodel());
        peptideRepository = new QueryDslJpaRepository<Peptide, Long>(peptideEntityInfo, entityManager);
    }

    @PersistenceUnit(unitName = "pride-proteomes-db-core")
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManagerFactory.createEntityManager();
    }


    /**
     * This setter method should be used only by unit tests
     */
    protected void setPeptideRepository(QueryDslJpaRepository<Peptide, Long> peptideRepository) {
        this.peptideRepository = peptideRepository;
    }
}
