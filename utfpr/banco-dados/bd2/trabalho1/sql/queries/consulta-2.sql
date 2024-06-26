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