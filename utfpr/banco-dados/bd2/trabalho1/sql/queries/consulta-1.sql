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