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
	        	and jcasa.idcampeonato = 1
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
	        	and jfora.idcampeonato = 1
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

drop index jogo_campeonato
create index jogo_campeonato on jogo using btree(idcampeonato)