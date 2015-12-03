package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups;

public interface ProteinGroupRepositoryCustom {

    long countGeneGroups();

    long countGeneGroupsByTaxid(Integer taxid);
}
