select 
	e.nome,
	c.nome,
	sum(j.golsequipecasa) as gols_feitos_em_casa,
	sum(j.golsequipefora) as gols_sofridos_em_casa
from equipe e
join equipecampeonato e2 on e2.idequipe = e.id 
join campeonato c on c.id = e2.idcampeonato 
join jogo j on j.idcampeonato = c.id and j.idequipecasa = e.id 
group by 
	e.nome,
	c.nome