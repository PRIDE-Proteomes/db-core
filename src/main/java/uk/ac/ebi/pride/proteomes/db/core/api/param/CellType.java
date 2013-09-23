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
public class CellType extends CvParam  {

//    @ManyToMany(mappedBy = "cvParamCellType")
//    private Collection<Assay> assaysWithCellType;
//
//    public Collection<Assay> getAssaysWithCellType() {
//        return assaysWithCellType;
//    }
//
//    public void setAssaysWithCellType(Collection<Assay> assaysWithCellType) {
//        this.assaysWithCellType = assaysWithCellType;
//    }

//    @ManyToMany(mappedBy = "cvParamCellType")
//    private Collection<Peptide> peptidesWithCellType;
//
//    public Collection<Peptide> getPeptidesWithCellType() {
//        return peptidesWithCellType;
//    }
//
//    public void setPeptidesWithCellType(Collection<Peptide> peptidesWithCellType) {
//        this.peptidesWithCellType = peptidesWithCellType;
//    }
}
