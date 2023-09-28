-- Consulta 1
explain analyze select 
	j.datajogo,
	ec.id,
	ec.nome,
	ef.id,
	ef.nome,
	sum(j.golsequipecasa) as gols,
	sum(j.golsequipefora)
from jogo j
join equipe ec on ec.id = j.idequipecasa 
join equipe ef on ef.id = j.idequipefora 
join tecnico tc on tc.idequipe = ec.id 
join tecnico tf on tf.idequipe = ef.id
where j.idequipecasa = 1 
and j.datajogo between '1900-01-01' and now()
group by 
	j.datajogo,
	ec.id,
	ec.nome,
	ef.id,
	ef.nome


drop index jogo_equipecasa;
drop index jogo_datajogo;
create index jogo_equipecasa on jogo (idequipecasa);
create index jogo_datajogo on jogo (datajogo);


-- Consulta 2
explain analyse select 
	e.id,
	e.nome,
	sum(j.golsequipecasa) as gols_equipe_casa
from jogo j
join equipe e on e.id = j.idequipecasa
	and j.idequipecasa = 1
group by 
	e.id,
	e.nome
	
drop index jogo_equipecasa;
create index jogo_equipecasa on jogo using btree (idequipecasa);


-- Consulta 3
select 
    t.id,
    t.nome,
    t.cpf,
    e.id as idEquipe,
    e.nome as nomeEquipe
from tecnico t
join equipe e on e.id = t.idEquipe


explain analyse select 
    c.id,
    c.nome,
    count(distinct e.id) as quantidadeEquipes
from equipeCampeonato ec 
join equipe e on e.id = ec.idEquipe
join campeonato c on c.id = ec.idCampeonato
where ec.idcampeonato in (select id from campeonato limit 10)
group by 
    c.id,
    c.nome


create index equipecampeonato_campeonato on equipecampeonato using btree (idcampeonato);
drop index equipecampeonato_campeonato;

-- Consulta 4
explain analyse select 
    tmp.id,
    tmp.documento,
    tmp.nome,
    tmp.posicao,
    tmp.idEquipe,
    tmp.nomeEquipe,
    t.id,
    t.cpf,
    t.nome
    	
from (
    select 
        jb.id,
        jb.cpf as documento,
        jb.nome,
        jb.posicao,
        e.id as idEquipe,
        e.nome as nomeEquipe
    from jogadorBrasileiro jb 
    join equipe e on e.id = jb.idEquipe
    	and jb.idequipe in (select id from equipe limit 10)

    union all

    select
        je.id,
        je.passaporte as documento,
        je.nome,
        je.posicao,
        e.id as idEquipe,
        e.nome as nomeEquipe
    from jogadorEstrangeiro je 
    join equipe e on e.id = je.idEquipe
    	and je.idequipe in (select id from equipe limit 10)
) as tmp
join tecnico t on t.idEquipe = tmp.idEquipe

drop index jogadorbrasileiro_equipe;
drop index jogadorestrangeiro_equipe;
drop index tecnico_equipe;

create index jogadorbrasileiro_equipe on jogadorbrasileiro using hash (idequipe);
create index jogadorestrangeiro_equipe on jogadorestrangeiro using hash(idequipe);
create index tecnico_equipe on tecnico using hash (idEquipe);

-- Consulta 5 

