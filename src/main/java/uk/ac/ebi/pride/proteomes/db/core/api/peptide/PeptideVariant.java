package uk.ac.ebi.pride.proteomes.db.core.api.peptide;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ntoro
 * Date: 15/08/2013
 * Time: 14:06
 */
@Entity
@DiscriminatorValue("FALSE")
public class PeptideVariant extends Peptide {

}
