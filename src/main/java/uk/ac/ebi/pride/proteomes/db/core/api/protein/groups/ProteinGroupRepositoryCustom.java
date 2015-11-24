package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups;

public interface ProteinGroupRepositoryCustom {

    public long countGeneGroups();

    public long countGeneGroupsByTaxid(Integer taxid);
}
