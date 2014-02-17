package uk.ac.ebi.pride.proteomes.db.core.api.param;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import uk.ac.ebi.pride.proteomes.db.core.api.assay.Assay;
import uk.ac.ebi.pride.proteomes.db.core.api.peptide.Peptide;
import uk.ac.ebi.pride.proteomes.db.core.api.protein.Protein;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collection;

/**
 * User: ntoro
 * Date: 14/08/2013
 * Time: 10:59
 */
@Entity
@DiscriminatorValue("CELL_TYPE")
public class CellType extends CvParam {

    @ManyToMany(mappedBy = "cellTypes")
    @LazyCollection(LazyCollectionOption.TRUE)
    private Collection<Assay> assays;

    @ManyToMany(mappedBy = "cellTypes")
    @LazyCollection(LazyCollectionOption.TRUE)
    private Collection<Peptide> peptides;

    @ManyToMany(mappedBy = "cellTypes")
    @LazyCollection(LazyCollectionOption.TRUE)
    private Collection<Protein> proteins;

    public Collection<Assay> getAssays() {
        return assays;
    }

    public void setAssays(Collection<Assay> assays) {
        this.assays = assays;
    }

    public Collection<Peptide> getPeptides() {
        return peptides;
    }

    public void setPeptides(Collection<Peptide> peptides) {
        this.peptides = peptides;
    }

    public Collection<Protein> getProteins() {
        return proteins;
    }

    public void setProteins(Collection<Protein> proteins) {
        this.proteins = proteins;
    }
}
