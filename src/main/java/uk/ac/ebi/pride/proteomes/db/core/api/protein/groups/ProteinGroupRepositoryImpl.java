package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import static uk.ac.ebi.pride.proteomes.db.core.api.protein.groups.ProteinGroupPredicates.*;


@Repository
public class ProteinGroupRepositoryImpl implements ProteinGroupRepositoryCustom {

    private EntityManager entityManager;

    private QueryDslJpaRepository<? extends ProteinGroup, Long> proteinGroupRepository;


    /**
     * An initialization method which is run after the bean has been constructed.
     * This ensures that the entity manager is injected before we try to use it.
     */
    @PostConstruct
    public void init() {
        JpaEntityInformation<ProteinGroup, Long> proteinGroupEntityInfo = new JpaMetamodelEntityInformation<ProteinGroup, Long>(ProteinGroup.class, entityManager.getMetamodel());
        proteinGroupRepository = new QueryDslJpaRepository<ProteinGroup, Long>(proteinGroupEntityInfo, entityManager);
    }

    @PersistenceUnit(unitName = "pride-proteomes-db-core")
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public long countGeneGroups() {
        return proteinGroupRepository.count(isGeneGroup());
    }

    @Override
    public long countGeneGroupsByTaxid(Integer taxid) {
        return proteinGroupRepository.count(isGeneGroupAndHasTaxid(taxid));
    }

    @Override
    public long countGeneGroupsByTaxidAndHasPeptides(Integer taxid) {
        return proteinGroupRepository.count(isGeneGroupAndHasTaxidAndHasPeptides(taxid));
    }

    @Override
    public long countGeneGroupsByTaxidAndHasUniquePeptides(Integer taxid) {
        return proteinGroupRepository.count(isGeneGroupAndHasTaxidAndHasUniquePeptides(taxid));
    }
}
