package uk.ac.ebi.pride.proteomes.db.core.api.protein;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:06
 */
public interface ProteinRepository extends JpaRepository<Protein, String> {

    Protein findByProteinAccession(String proteinAccession);

}
