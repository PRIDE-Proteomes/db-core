package uk.ac.ebi.pride.proteomes.db.core.api.protein;

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

import static uk.ac.ebi.pride.proteomes.db.core.api.protein.ProteinPredicates.*;

/**
 * User: ntoro
 * Date: 21/02/2014
 * Time: 11:08
 */
@Repository
public class ProteinRepositoryImpl implements ProteomesRepository<Protein>, ProteinRepositoryCustom {

    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    private QueryDslJpaRepository<Protein, String> proteinRepository;

    public ProteinRepositoryImpl() {

    }

    @Override
    public List<Protein> findAllByTaxid(Integer taxid) {
        return proteinRepository.findAll(hasTaxid(taxid));
    }

    @Override
    public List<Protein> findAllByTissue(String cvTerm) {
        return proteinRepository.findAll(hasTissue(cvTerm));
    }

    @Override
    public List<Protein> findAllByModification(String modId) {
        return proteinRepository.findAll(hasModification(modId));
    }

    @Override
    public List<Protein> findAllByTaxidAndTissue(Integer taxid, String cvTerm) {
        return proteinRepository.findAll(hasTaxidAndTissue(taxid, cvTerm));
    }

    @Override
    public List<Protein> findAllByTaxidAndModification(Integer taxid, String modId) {
        return proteinRepository.findAll(hasTaxidAndTissue(taxid, modId));
    }

    @Override
    public List<Protein> findAllByTissueAndModification(String cvTerm, String modId) {
        return proteinRepository.findAll(hasTissueAndModification(cvTerm, modId));
    }

    @Override
    public List<Protein> findAllByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId) {
        return proteinRepository.findAll(hasTaxidAndTissueAndModification(taxid, cvTerm, modId));
    }

    @Override
    public List<Protein> findAllByTaxid(Integer taxid, Pageable pageable) {
        return proteinRepository.findAll(hasTaxid(taxid), pageable).getContent();
    }

    @Override
    public List<Protein> findAllByTissue(String cvTerm, Pageable pageable) {
        return proteinRepository.findAll(hasTissue(cvTerm), pageable).getContent();
    }

    @Override
    public List<Protein> findAllByModification(String modId, Pageable pageable) {
        return proteinRepository.findAll(hasModification(modId), pageable).getContent();
    }

    @Override
    public List<Protein> findAllByTaxidAndTissue(Integer taxid, String cvTerm, Pageable pageable) {
        return proteinRepository.findAll(hasTaxidAndTissue(taxid, cvTerm), pageable).getContent();
    }

    @Override
    public List<Protein> findAllByTaxidAndModification(Integer taxid, String modId, Pageable pageable) {
        return proteinRepository.findAll(hasTaxidAndModification(taxid, modId), pageable).getContent();
    }

    @Override
    public List<Protein> findAllByTissueAndModification(String cvTerm, String modId, Pageable pageable) {
        return proteinRepository.findAll(hasTissueAndModification(cvTerm, modId), pageable).getContent();
    }

    @Override
    public List<Protein> findAllByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId, Pageable pageable) {
        return proteinRepository.findAll(hasTaxidAndTissueAndModification(taxid, cvTerm, modId), pageable).getContent();
    }

    @Override
    public long countByTaxid(Integer taxid) {
        return proteinRepository.count(hasTaxid(taxid));
    }

    @Override
    public long countByTaxidAndTissue(Integer taxid, String cvTerm) {
        return proteinRepository.count(hasTaxidAndTissue(taxid,cvTerm));
    }

    @Override
    public long countByTaxidAndModification(Integer taxid, String modId) {
        return proteinRepository.count(hasTaxidAndModification(taxid,modId));
    }

    @Override
    public long countByTaxidAndTissueAndModification(Integer taxid, String cvTerm, String modId) {
        return proteinRepository.count(hasTaxidAndTissueAndModification(taxid,cvTerm,modId));
    }

    /* Specific for proteins. It is used in the stats */
    public long countByTaxidAndIsNotContaminant(Integer taxid) {
        return proteinRepository.count(hasTaxidAndIsNotContaminant(taxid));
    }

    public long countByTaxidAndIsNotContaminantAndIsCanonical(Integer taxid) {
        return proteinRepository.count(hasTaxidAndIsNotContaminantAndIsCanonical(taxid));
    }

    public long countByTaxidAndIsNotContaminantAndIsIsoform(Integer taxid) {
        return proteinRepository.count(hasTaxidAndIsNotContaminantAndIsNotCanonical(taxid));
    }

    /* Mapped proteins with peptides */
    // Mapped proteins
    public long countByTaxidAndIsNotContaminantAndHasPeptides(Integer taxid) {
        return proteinRepository.count(hasTaxidAndIsNotContaminantAndHasPeptides(taxid));
    }

    // Mapped canonical proteins
    public long countByTaxidAndIsNotContaminantAndIsCanonicalAndHasPeptides(Integer taxid) {
        return proteinRepository.count(hasTaxidAndIsNotContaminantAndIsCanonicalHasPeptides(taxid));
    }

    // Mapped isoform proteins
    public long countByTaxidAndIsNotContaminantAndIsIsoformAndHasPeptides(Integer taxid) {
        return proteinRepository.count(hasTaxidAndIsNotContaminantAndIsNotCanonicalHasPeptides(taxid));
    }

    /* Mapped proteins with unique peptides */
    // Mapped proteins with at least one unique peptide
    public long countByTaxidAndIsNotContaminantAndHasUniquePeptides(Integer taxid) {
        return proteinRepository.count(hasTaxidAndIsNotContaminantAndHasUniquePeptides(taxid));
    }

    // Mapped canonical proteins with at least one unique peptide
    public long countByTaxidAndIsNotContaminantAndIsCanonicalAndHasUniquePeptides(Integer taxid) {
        return proteinRepository.count(hasTaxidAndIsNotContaminantAndIsCanonicalHasUniquePeptides(taxid));
    }

    // Mapped isoform proteins with at least one unique peptide
    public long countByTaxidAndIsNotContaminantAndIsIsoformAndHasUniquePeptides(Integer taxid) {
        return proteinRepository.count(hasTaxidAndIsNotContaminantAndIsNotCanonicalHasUniquePeptides(taxid));
    }

    /**
     * An initialization method which is run after the bean has been constructed.
     * This ensures that the entity manager is injected before we try to use it.
     */
    @PostConstruct
    public void init() {
        JpaEntityInformation<Protein, String> proteinEntityInfo = new JpaMetamodelEntityInformation<Protein, String>(Protein.class, entityManager.getMetamodel());
        proteinRepository = new QueryDslJpaRepository<Protein, String>(proteinEntityInfo, entityManager);
    }

    @PersistenceUnit(unitName = "pride-proteomes-db-core")
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    /**
     * This setter method should be used only by unit tests
     *
     * @param proteinRepository
     */
    protected void setProteinRepository(QueryDslJpaRepository<Protein, String> proteinRepository) {
        this.proteinRepository = proteinRepository;
    }
}
