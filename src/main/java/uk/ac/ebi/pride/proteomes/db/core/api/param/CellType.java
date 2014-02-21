package uk.ac.ebi.pride.proteomes.db.core.api.param;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:59
 */
@Entity
@DiscriminatorValue("CELL_TYPE")
public class CellType extends CvParam {

}
