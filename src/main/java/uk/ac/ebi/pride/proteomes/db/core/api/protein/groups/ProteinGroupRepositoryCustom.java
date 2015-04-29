package uk.ac.ebi.pride.proteomes.db.core.api.protein.groups;

import uk.ac.ebi.pride.proteomes.db.core.api.ProteomesRepository;

public interface ProteinGroupRepositoryCustom {

    public long countEntryGroups();

    public long countGeneGroups();

    public long countEntryGroupsByTaxid(Integer taxid);

    public long countGeneGroupsByTaxid(Integer taxid);
}
