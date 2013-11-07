package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ntoro
 * Date: 06/11/2013
 * Time: 13:18
 */
//This group identified all the proteins and isoform under the same Uniprot Entry
@Entity
@DiscriminatorValue(value = "ENTRY")
public class EntryGroup extends ProteinGroup {
}
