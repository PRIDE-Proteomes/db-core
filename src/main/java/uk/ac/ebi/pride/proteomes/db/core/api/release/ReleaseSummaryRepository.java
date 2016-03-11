package uk.ac.ebi.pride.proteomes.db.core.api.release;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.Protein;

/**
 * @author ntoro
 * @since 07/03/2016 16:54
 */
@Repository
@Transactional(readOnly = true)
public interface ReleaseSummaryRepository extends JpaRepository<Protein, String>, QueryDslPredicateExecutor<Protein> {
}
