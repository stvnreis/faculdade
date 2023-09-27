explain analyze select 
	j.datajogo,
	ec.nome,
	tc.nome,
	ef.nome,
	tf.nome 
from jogo j
join equipe ec on ec.id = j.idequipecasa 
join equipe ef on ef.id = j.idequipefora 
join tecnico tc on tc.idequipe = ec.id 
join tecnico tf on tf.idequipe = ef.id
where j.idequipecasa = 1 
and j.datajogo between '1900-01-01' and now()


drop index jogo_equipecasa
drop index jogo_datajogo
create index jogo_equipecasa on jogo (idequipecasa);
create index jogo_datajogo on jogo (datajogo);
















select 
    t.id,
    t.nome,
    t.cpf,
    e.id as idEquipe,
    e.nome as nomeEquipe
from tecnico t
join equipe e on e.id = t.idEquipe


select 
    c.id,
    c.nome,
    count(distinct e.id) as quantidadeEquipes
from equipeCampeonato ec 
join equipe e on e.id = ec.idEquipe
join campeonato c on c.id = ec.idCampeonato
group by 
    c.id,
    c.nome

select 
    t.id,
    t.cpf,
    t.nome,
    tmp.id,
    tmp.nome,
    tmp.posicao
from (
    select 
        e.id,
        e.nome,
        ec.posicao
    from equipeCampeonato ec 
    join equipe e on e.id = ec.idEquipe

    intersect 

    select
        e.id,
        e.nome,
        ec.posicao
    from equipeCampeonato ec 
    join equipe e on e.id = ec.idEquipe
) as tmp
join tecnico t on t.idEquipe = tmp.id


select 
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

    union 

    select
        je.id,
        je.passaporte as documento,
        je.nome,
        je.posicao,
        e.id as idEquipe,
        e.nome as nomeEquipe
    from jogadorEstrangeiro je 
    join equipe e on e.id = je.idEquipe
) as tmp
join tecnico t on t.idEquipe = tmp.idEquipe


select 
    t.id,
    t.cpf,
    t.nome,
    tmp.id,
    tmp.nome,
    tmp.posicao
from (
    select 
        e.id,
        e.nome,
        ec.posicao
    from equipeCampeonato ec 
    join equipe e on e.id = ec.idEquipe

    except 

    select
        e.id,
        e.nome,
        ec.posicao
    from equipeCampeonato ec 
    join equipe e on e.id = ec.idEquipe
    where ec.posicao = 5
) as tmp
join tecnico t on t.idEquipe = tmp.id


create or replace function inserir_equipe()
returns void as $$
declare i int := 1
begin
    while i < 10001
    begin 
        insert into equipe(nome) values(concat('cidade ', random()));
        set i += 1
    end 
end

