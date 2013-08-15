package uk.ac.ebi.pride.proteomes.db.core.api.param;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:59
 * To change this template use File | Settings | File Templates.
 */
@Entity
@DiscriminatorValue( value = "sample")
public class CvParamSample extends CvParam {

}
