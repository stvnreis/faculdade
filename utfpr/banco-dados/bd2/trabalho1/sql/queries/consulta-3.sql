select
  je.nome,
  e.nome,
  count(distinct c.id) as quantidade_campeonatos
from jogadorestrangeiro je
join equipe e on e.id = je.idequipe
join equipecampeonato ec on ec.idequipe = e.id
join campeonato c on c.id = ec.idcampeonato
where passaporte = '269324055648332607246655355547058252596073843'
group by je.nome, e.nome

create index jogador_passaporte on jogadorestrangeiro using btree(passaporte)
drop index jogador_passaporte