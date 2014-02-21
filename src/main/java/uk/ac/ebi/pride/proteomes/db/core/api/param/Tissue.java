package uk.ac.ebi.pride.proteomes.db.core.api.param;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 11:00
 */
@Entity
@DiscriminatorValue(value = "TISSUE")
public class Tissue extends CvParam {

}
