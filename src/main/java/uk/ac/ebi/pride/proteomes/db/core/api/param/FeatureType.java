package uk.ac.ebi.pride.proteomes.db.core.api.param;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author ntoro
 * @since 11/06/15 17:25
 */
@Entity
@DiscriminatorValue("FEATURE_TYPE")
public class FeatureType extends CvParam {
}
