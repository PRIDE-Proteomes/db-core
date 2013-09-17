# symbolyc peptides. we took only the first 30
# I can not reproduce the same result again
select distinct
	peptide.peptide_id as PEPTIDE_PK,
    peptide.sequence as SEQUENCE,
    '' as DESCRIPTION,
    1 as SCORE_FK,
    'true' as SYMBOLIC,
    protein.taxon_id as TAXID
from
    pride.pride_peptide as peptide,
    pride.pride_protein as protein,
    pride.pride_identification_protein_link as link
where
    protein.taxon_id = '9606'
        and peptide.identification_id = link.identification_id
        and link.protein_id = protein.protein_id
        and peptide.public_flag = 1
        and peptide.spectrum_ref is not null
        and LOCATE('*', peptide.sequence) = 0
        and LOCATE('#', peptide.sequence) = 0
        and length(peptide.sequence) > 5
        and length(peptide.sequence) < 100
limit 0,30;


# peptides variants
select distinct
    peptide.peptide_id as PEPTIDE_PK,
    peptide.sequence as SEQUENCE,
    '' as DESCRIPTION,
    'false' as CANONICAL,
    1 as SCORE_FK,
    protein.taxon_id as TAXID
from
    pride.pride_peptide as peptide,
    pride.pride_protein as protein,
    pride.pride_identification_protein_link as link
where
    protein.taxon_id = '9606'
        and peptide.identification_id = link.identification_id
        and link.protein_id = protein.protein_id
        and peptide.public_flag = 1
        and peptide.spectrum_ref is not null
        and peptide.sequence in (
        'PPCEDEIDEFLK',
        'PDELKVTVKLK',
        'LEETFPAYLQEAFFGK',
        'HCGATSAGLR',
        'SSSLLNQKPEGSICSEDDCTK',
        'LEKAETESCPGQEEPK',
        'DIDDNSNGTYEK',
        'SLGLSRSPVVSEQTAK',
        'WCVWCRHCGATSAGLR',
        'PTTVDPYSQQPQTPRPSTQTDLFVTPVTNQR',
        'VLDNAMNSNVTVVSR',
        'LPNSDFSQATPNQQTYANSEVDK',
        'SIKLSSETESSFSSSADISK',
        'ITPGFILPWRNQPSNKK',
        'QNPAEGLQTLGAQMQGGFGCGNQLPK',
        'PLSEEQLDGILSPELDKMVTDGAILGK',
        'APYVQKAR',
        'MANGFATTEELAGK',
        'INPGLEYRQHLLLRGPPPGSANPPR',
        'PVLMPNQDPFLQAAQNRGPALPGPLVR',
        'LSVDPYERPALTPR',
        'LKSGIGAVVLPGVSTADISSNK',
        'EIFKPRQLPGSAIWSIK',
        'AGREFPEEDAEQLK',
        'ILEPVACVR',
        'MSAHVKR',
        'DMLHCNMCK',
        'LDSDDPSVK',
        'PRISEGFTRSSMTR',
        'MKTEWKSNVYLAR');

# modification table
select distinct
    mod_param.accession as CV_TERM, mod_param.name as CV_NAME
from
    pride.pride_peptide as peptide,
    pride.pride_protein as protein,
    pride.pride_identification_protein_link as link,
    pride.pride_modification as modification,
    pride.pride_modification_param as mod_param
where
    protein.taxon_id = '9606'
        and peptide.identification_id = link.identification_id
        and link.protein_id = protein.protein_id
        and peptide.peptide_id = modification.peptide_id
        and modification.modification_id = mod_param.parent_element_fk
        and peptide.public_flag = 1
        and peptide.spectrum_ref is not null
        and peptide.sequence in ('PPCEDEIDEFLK' , 'PDELKVTVKLK',
        'LEETFPAYLQEAFFGK',
        'HCGATSAGLR',
        'SSSLLNQKPEGSICSEDDCTK',
        'LEKAETESCPGQEEPK',
        'DIDDNSNGTYEK',
        'SLGLSRSPVVSEQTAK',
        'WCVWCRHCGATSAGLR',
        'PTTVDPYSQQPQTPRPSTQTDLFVTPVTNQR',
        'VLDNAMNSNVTVVSR',
        'LPNSDFSQATPNQQTYANSEVDK',
        'SIKLSSETESSFSSSADISK',
        'ITPGFILPWRNQPSNKK',
        'QNPAEGLQTLGAQMQGGFGCGNQLPK',
        'PLSEEQLDGILSPELDKMVTDGAILGK',
        'APYVQKAR',
        'MANGFATTEELAGK',
        'INPGLEYRQHLLLRGPPPGSANPPR',
        'PVLMPNQDPFLQAAQNRGPALPGPLVR',
        'LSVDPYERPALTPR',
        'LKSGIGAVVLPGVSTADISSNK',
        'EIFKPRQLPGSAIWSIK',
        'AGREFPEEDAEQLK',
        'ILEPVACVR',
        'MSAHVKR',
        'DMLHCNMCK',
        'LDSDDPSVK',
        'PRISEGFTRSSMTR',
        'MKTEWKSNVYLAR');


# peptide_modification table
select distinct
    peptide.peptide_id as PEPTIDE_FK_PK,
    modification.accession as MOD_FK_PK,
    modification.location as POSITION
from
    pride.pride_peptide as peptide,
    pride.pride_protein as protein,
    pride.pride_identification_protein_link as link,
    pride.pride_modification as modification
where
    protein.taxon_id = '9606'
        and peptide.identification_id = link.identification_id
        and link.protein_id = protein.protein_id
        and peptide.peptide_id = modification.peptide_id
        and peptide.public_flag = 1
        and peptide.spectrum_ref is not null
        and peptide.sequence in ('PPCEDEIDEFLK' , 'PDELKVTVKLK',
        'LEETFPAYLQEAFFGK',
        'HCGATSAGLR',
        'SSSLLNQKPEGSICSEDDCTK',
        'LEKAETESCPGQEEPK',
        'DIDDNSNGTYEK',
        'SLGLSRSPVVSEQTAK',
        'WCVWCRHCGATSAGLR',
        'PTTVDPYSQQPQTPRPSTQTDLFVTPVTNQR',
        'VLDNAMNSNVTVVSR',
        'LPNSDFSQATPNQQTYANSEVDK',
        'SIKLSSETESSFSSSADISK',
        'ITPGFILPWRNQPSNKK',
        'QNPAEGLQTLGAQMQGGFGCGNQLPK',
        'PLSEEQLDGILSPELDKMVTDGAILGK',
        'APYVQKAR',
        'MANGFATTEELAGK',
        'INPGLEYRQHLLLRGPPPGSANPPR',
        'PVLMPNQDPFLQAAQNRGPALPGPLVR',
        'LSVDPYERPALTPR',
        'LKSGIGAVVLPGVSTADISSNK',
        'EIFKPRQLPGSAIWSIK',
        'AGREFPEEDAEQLK',
        'ILEPVACVR',
        'MSAHVKR',
        'DMLHCNMCK',
        'LDSDDPSVK',
        'PRISEGFTRSSMTR',
        'MKTEWKSNVYLAR');


# peptides variants plus modifications
select distinct
    peptide.peptide_id as PEPTIDE_PK,
    peptide.sequence as SEQUENCE,
    '' as DESCRIPTION,
    'false' as CANONICAL,
    1 as SCORE_FK,
    protein.taxon_id as TAXID,
    peptide.peptide_id as PEPTIDE_FK_PK,
    modification.modification_id as MOD_FK_PK,
    modification.location as POSITION,
    mod_param.accession as CV_TERM,
    mod_param.name as CV_NAME
from
    pride.pride_peptide as peptide,
    pride.pride_protein as protein,
    pride.pride_identification_protein_link as link,
    pride.pride_modification as modification,
    pride.pride_modification_param as mod_param
where
    protein.taxon_id = '9606'
        and peptide.identification_id = link.identification_id
        and link.protein_id = protein.protein_id
        and peptide.peptide_id = modification.peptide_id
        and modification.modification_id = mod_param.parent_element_fk
        and peptide.public_flag = 1
        and peptide.spectrum_ref is not null
        and peptide.sequence in ('PPCEDEIDEFLK' , 'PDELKVTVKLK',
        'LEETFPAYLQEAFFGK',
        'HCGATSAGLR',
        'SSSLLNQKPEGSICSEDDCTK',
        'LEKAETESCPGQEEPK',
        'DIDDNSNGTYEK',
        'SLGLSRSPVVSEQTAK',
        'WCVWCRHCGATSAGLR',
        'PTTVDPYSQQPQTPRPSTQTDLFVTPVTNQR',
        'VLDNAMNSNVTVVSR',
        'LPNSDFSQATPNQQTYANSEVDK',
        'SIKLSSETESSFSSSADISK',
        'ITPGFILPWRNQPSNKK',
        'QNPAEGLQTLGAQMQGGFGCGNQLPK',
        'PLSEEQLDGILSPELDKMVTDGAILGK',
        'APYVQKAR',
        'MANGFATTEELAGK',
        'INPGLEYRQHLLLRGPPPGSANPPR',
        'PVLMPNQDPFLQAAQNRGPALPGPLVR',
        'LSVDPYERPALTPR',
        'LKSGIGAVVLPGVSTADISSNK',
        'EIFKPRQLPGSAIWSIK',
        'AGREFPEEDAEQLK',
        'ILEPVACVR',
        'MSAHVKR',
        'DMLHCNMCK',
        'LDSDDPSVK',
        'PRISEGFTRSSMTR',
        'MKTEWKSNVYLAR');

# assay table
select distinct
    experiment.accession as ASSAY_PK,
    experiment.projectID as PROJECT_ACCESSION,
    protein.taxon_id as TAXID
from
    pride.pride_peptide as peptide,
    pride.pride_protein as protein,
    pride.pride_identification_protein_link as link,
    pride.pride_identification as identification,
    pride.pride_experiment as experiment
where
    protein.taxon_id = '9606'
        and peptide.identification_id = link.identification_id
        and peptide.identification_id = identification.identification_id
        and experiment.experiment_id = identification.experiment_id
        and link.protein_id = protein.protein_id
        and peptide.public_flag = 1
        and peptide.spectrum_ref is not null
        and peptide.sequence in ('PPCEDEIDEFLK' , 'PDELKVTVKLK',
        'LEETFPAYLQEAFFGK',
        'HCGATSAGLR',
        'SSSLLNQKPEGSICSEDDCTK',
        'LEKAETESCPGQEEPK',
        'DIDDNSNGTYEK',
        'SLGLSRSPVVSEQTAK',
        'WCVWCRHCGATSAGLR',
        'PTTVDPYSQQPQTPRPSTQTDLFVTPVTNQR',
        'VLDNAMNSNVTVVSR',
        'LPNSDFSQATPNQQTYANSEVDK',
        'SIKLSSETESSFSSSADISK',
        'ITPGFILPWRNQPSNKK',
        'QNPAEGLQTLGAQMQGGFGCGNQLPK',
        'PLSEEQLDGILSPELDKMVTDGAILGK',
        'APYVQKAR',
        'MANGFATTEELAGK',
        'INPGLEYRQHLLLRGPPPGSANPPR',
        'PVLMPNQDPFLQAAQNRGPALPGPLVR',
        'LSVDPYERPALTPR',
        'LKSGIGAVVLPGVSTADISSNK',
        'EIFKPRQLPGSAIWSIK',
        'AGREFPEEDAEQLK',
        'ILEPVACVR',
        'MSAHVKR',
        'DMLHCNMCK',
        'LDSDDPSVK',
        'PRISEGFTRSSMTR',
        'MKTEWKSNVYLAR');

# peptide assay table
select distinct
    peptide.peptide_id as PEPTIDE_FK_PK,
    experiment.accession as ASSAY_FK_PK
from
    pride.pride_peptide as peptide,
    pride.pride_protein as protein,
    pride.pride_identification_protein_link as link,
    pride.pride_identification as identification,
    pride.pride_experiment as experiment
where
    protein.taxon_id = '9606'
        and peptide.identification_id = link.identification_id
        and peptide.identification_id = identification.identification_id
        and experiment.experiment_id = identification.experiment_id
        and link.protein_id = protein.protein_id
        and peptide.public_flag = 1
        and peptide.spectrum_ref is not null
        and peptide.sequence in ('PPCEDEIDEFLK' , 'PDELKVTVKLK',
        'LEETFPAYLQEAFFGK',
        'HCGATSAGLR',
        'SSSLLNQKPEGSICSEDDCTK',
        'LEKAETESCPGQEEPK',
        'DIDDNSNGTYEK',
        'SLGLSRSPVVSEQTAK',
        'WCVWCRHCGATSAGLR',
        'PTTVDPYSQQPQTPRPSTQTDLFVTPVTNQR',
        'VLDNAMNSNVTVVSR',
        'LPNSDFSQATPNQQTYANSEVDK',
        'SIKLSSETESSFSSSADISK',
        'ITPGFILPWRNQPSNKK',
        'QNPAEGLQTLGAQMQGGFGCGNQLPK',
        'PLSEEQLDGILSPELDKMVTDGAILGK',
        'APYVQKAR',
        'MANGFATTEELAGK',
        'INPGLEYRQHLLLRGPPPGSANPPR',
        'PVLMPNQDPFLQAAQNRGPALPGPLVR',
        'LSVDPYERPALTPR',
        'LKSGIGAVVLPGVSTADISSNK',
        'EIFKPRQLPGSAIWSIK',
        'AGREFPEEDAEQLK',
        'ILEPVACVR',
        'MSAHVKR',
        'DMLHCNMCK',
        'LDSDDPSVK',
        'PRISEGFTRSSMTR',
        'MKTEWKSNVYLAR');


# simplified query with previous records
# assay table
select distinct
    experiment.accession as ASSAY_PK,
    experiment.projectID as PROJECT_ACCESSION,
    '9606' as TAXID
from
    pride.pride_peptide as peptide,
    pride.pride_identification as identification,
    pride.pride_experiment as experiment
where
    peptide.identification_id = identification.identification_id
        and experiment.experiment_id = identification.experiment_id
        and peptide.peptide_id in ('45482309' , '45482310',
        '45492524',
        '45499384',
        '45513223',
        '45520449',
        '45544650',
        '45544651',
        '45552497',
        '45560378',
        '45560379',
        '45560380',
        '45568184',
        '45575358',
        '45575359',
        '45575360',
        '45575361',
        '45582696',
        '45590402',
        '45597533',
        '45619484',
        '45642731',
        '45642732',
        '45642733',
        '45650591',
        '45650592',
        '45658280',
        '47681338',
        '47687212',
        '47687213',
        '47689691',
        '47699748',
        '47729850',
        '56753822',
        '57942747',
        '57948563',
        '57954250',
        '57991624',
        '57991625',
        '58110502',
        '58121447',
        '58189691',
        '58195748',
        '58210850',
        '58219338',
        '58221212',
        '58221213',
        '62565953',
        '62574179',
        '62594179',
        '62601995',
        '93861219',
        '109663404',
        '109663416',
        '109663425',
        '109663442',
        '109663447',
        '109663448',
        '109663449',
        '109663450',
        '109663465',
        '109663496',
        '109663504',
        '110281993',
        '110281994',
        '110281995',
        '110282032',
        '110282061',
        '110282088',
        '110282089',
        '110282090',
        '110282150',
        '110282151',
        '110282172',
        '110282174',
        '110282175');


# simplified query with previous records
# peptide_variant assay table
select distinct
    peptide.peptide_id as PEPTIDE_FK_PK,
    experiment.accession as ASSAY_PK
from
    pride.pride_peptide as peptide,
    pride.pride_identification as identification,
    pride.pride_experiment as experiment
where
    peptide.identification_id = identification.identification_id
        and experiment.experiment_id = identification.experiment_id
        and peptide.peptide_id in ('45482309' , '45482310',
        '45492524',
        '45499384',
        '45513223',
        '45520449',
        '45544650',
        '45544651',
        '45552497',
        '45560378',
        '45560379',
        '45560380',
        '45568184',
        '45575358',
        '45575359',
        '45575360',
        '45575361',
        '45582696',
        '45590402',
        '45597533',
        '45619484',
        '45642731',
        '45642732',
        '45642733',
        '45650591',
        '45650592',
        '45658280',
        '47681338',
        '47687212',
        '47687213',
        '47689691',
        '47699748',
        '47729850',
        '56753822',
        '57942747',
        '57948563',
        '57954250',
        '57991624',
        '57991625',
        '58110502',
        '58121447',
        '58189691',
        '58195748',
        '58210850',
        '58219338',
        '58221212',
        '58221213',
        '62565953',
        '62574179',
        '62594179',
        '62601995',
        '93861219',
        '109663404',
        '109663416',
        '109663425',
        '109663442',
        '109663447',
        '109663448',
        '109663449',
        '109663450',
        '109663465',
        '109663496',
        '109663504',
        '110281993',
        '110281994',
        '110281995',
        '110282032',
        '110282061',
        '110282088',
        '110282089',
        '110282090',
        '110282150',
        '110282151',
        '110282172',
        '110282174',
        '110282175');


# simplified query with previous records
# peptide_symbolic assay table
# the primary key needs to be mapped to the symbolic one
select distinct
    NULL as PEPTIDE_FK_PK,
    peptide.sequence as SEQUENCE,
    experiment.accession as ASSAY_FK_PK
from
    pride.pride_peptide as peptide,
    pride.pride_identification as identification,
    pride.pride_experiment as experiment
where
    peptide.identification_id = identification.identification_id
        and experiment.experiment_id = identification.experiment_id
        and peptide.peptide_id in ('45482309' , '45482310',
        '45492524',
        '45499384',
        '45513223',
        '45520449',
        '45544650',
        '45544651',
        '45552497',
        '45560378',
        '45560379',
        '45560380',
        '45568184',
        '45575358',
        '45575359',
        '45575360',
        '45575361',
        '45582696',
        '45590402',
        '45597533',
        '45619484',
        '45642731',
        '45642732',
        '45642733',
        '45650591',
        '45650592',
        '45658280',
        '47681338',
        '47687212',
        '47687213',
        '47689691',
        '47699748',
        '47729850',
        '56753822',
        '57942747',
        '57948563',
        '57954250',
        '57991624',
        '57991625',
        '58110502',
        '58121447',
        '58189691',
        '58195748',
        '58210850',
        '58219338',
        '58221212',
        '58221213',
        '62565953',
        '62574179',
        '62594179',
        '62601995',
        '93861219',
        '109663404',
        '109663416',
        '109663425',
        '109663442',
        '109663447',
        '109663448',
        '109663449',
        '109663450',
        '109663465',
        '109663496',
        '109663504',
        '110281993',
        '110281994',
        '110281995',
        '110282032',
        '110282061',
        '110282088',
        '110282089',
        '110282090',
        '110282150',
        '110282151',
        '110282172',
        '110282174',
        '110282175');
