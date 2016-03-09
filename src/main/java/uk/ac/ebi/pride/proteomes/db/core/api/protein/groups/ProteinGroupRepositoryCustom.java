package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups;

public interface ProteinGroupRepositoryCustom {

    long countGeneGroups();

    long countGeneGroupsByTaxid(Integer taxid);

    long countGeneGroupsByTaxidAndHasPeptides(Integer taxid);

    long countGeneGroupsByTaxidAndHasUniquePeptides(Integer taxid);
}
