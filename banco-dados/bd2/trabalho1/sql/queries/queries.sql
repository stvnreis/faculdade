-- consulta 1
select
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
where j.idequipefora = 1
group by
	j.datajogo,
	ec.id,
	ec.nome,
	ef.id,
	ef.nome


drop index jogo_equipecasa;
create index jogo_equipecasa on jogo using btree(idequipecasa);


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


select
    tmp.id,
    tmp.nome,
    count(distinct tmp.quantidadeEquipes) as quantidadeEquipes
from (
    select
        c.id,
        c.nome,
        e.id as quantidadeEquipes
    from equipeCampeonato ec
        join equipe e on e.id = ec.idEquipe
        join campeonato c on c.id = ec.idCampeonato
        where ec.idcampeonato = 1
) as tmp
    group by tmp.id, tmp.nome


create index equipecampeonato_campeonato on equipecampeonato using btree (idcampeonato);
drop index equipecampeonato_campeonato;

-- Consulta 4
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

-- Consulta 5 versão 1

WITH ClassificacaoLiga AS (
    SELECT
        e.id AS id_equipe,
        e.nome AS nome_equipe,
        SUM(
            CASE WHEN j.idequipecasa = e.id THEN
                CASE WHEN j.golsequipecasa > j.golsequipefora THEN 3
                     WHEN j.golsequipecasa = j.golsequipefora THEN 1
                     ELSE 0 END
             ELSE
                CASE WHEN j.golsequipefora > j.golsequipecasa THEN 3
                     WHEN j.golsequipefora = j.golsequipecasa THEN 1
                     ELSE 0 END
             END
        ) AS pontuacao
    FROM
        equipe e
    LEFT JOIN
        jogo j ON e.id = j.idequipecasa OR e.id = j.idequipefora
    GROUP BY
        e.id, e.nome
)

SELECT
    e.nome AS nome_equipe,
    CL.pontuacao AS pontuacao_total,
    RANK() OVER (ORDER BY CL.pontuacao DESC) AS posicao_na_classificacao
FROM
    ClassificacaoLiga CL
JOIN
    equipe e ON CL.id_equipe = e.id
ORDER BY
    posicao_na_classificacao;


-- Versão 2

WITH ClassificacaoLiga AS (
    select 
    	tmp.idcampeonato,
    	tmp.id_equipe,
    	tmp.nome_equipe,
    	sum(tmp.gols_totais) as gols_totais
    from (
	    select
	    	jcasa.idcampeonato,
	        e.id AS id_equipe,
	        e.nome AS nome_equipe,
	        SUM(jcasa.golsequipecasa) AS gols_totais
	    FROM
	        jogo jcasa
	    JOIN
	        equipe e ON e.id = jcasa.idequipecasa 
	    GROUP BY
	        e.id, e.nome, jcasa.idcampeonato 
	        
	    union all
	        
	    select
	    	jfora.idcampeonato,
	        e.id AS id_equipe,
	        e.nome AS nome_equipe,
	        SUM(jfora.golsequipefora) AS gols_totais
	    from
	    	jogo jfora 
	    JOIN
	        equipe e ON e.id = jfora.idequipecasa 
	    GROUP BY
	        e.id, e.nome, jfora.idcampeonato 
    ) as tmp 
    group by 
    	tmp.idcampeonato,
    	tmp.id_equipe,
    	tmp.nome_equipe
)

select
	c.id as id_campeonato,
	c.nome as nome_campeonato,
	CL.id_equipe,
    CL.nome_equipe,
    CL.gols_totais,
    ROW_NUMBER() OVER (partition by c.id ORDER BY CL.gols_totais DESC) AS posicao_na_classificacao
FROM
    ClassificacaoLiga CL
JOIN
    campeonato c on c.id = cl.idcampeonato
ORDER BY
    c.id, posicao_na_classificacao;