package uk.ac.ebi.pride.proteomes.db.core.api.peptide;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ntoro
 * Date: 13/09/2013
 * Time: 09:53
*/
@Entity
@DiscriminatorValue("TRUE")
public class SymbolicPeptide extends Peptide{

}
