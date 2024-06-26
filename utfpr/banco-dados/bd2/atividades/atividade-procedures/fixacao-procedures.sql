-- Exercício 2

create or replace function getQuantidadeFuncionariosCadastrados()
returns table (nome varchar(50), salario double precision) as
$$
begin 
	raise notice 'Quantidade total de funcionarios: %', (select count(*) from func);
	return query select f.nome, f.salario from func f;
end;
$$
language 'plpgsql';

-- Exercício 3 

create or replace function aumentaSalarioPercentual (idFuncionario int, percentualAumento int) 
returns void as
$$
declare 
	salariofunc decimal;
	novoSalario decimal;
begin
	select salario from func where cod_func = idFuncionario into salariofunc; 
	novoSalario := salarioFunc + (salarioFunc * (percentualAumento/100.0));

	update func set salario = (novoSalario) where cod_func = idFuncionario;
end;

$$ language 'plpgsql';

-- Exercício 4

create or replace function calculaQuantidadeHorasGerente (idDepartamento int) 
returns int as 
$$
declare idGerente int;
begin 
	select d.cod_gerce from depto d where d.nro_depto = idDepartamento into idGerente;
	return (
		select sum(pa.horas_trab)
		from participa pa
		join projeto p on pa.cod_projce = p.cod_proj
			and pa.cod_funcce = idGerente
	);
end;
$$
language 'plpgsql';

-- Exercício 5

create or replace function atualizaDuracaoProjeto()
returns void as 
$$
begin 
	update projeto set duracao = (
		select ceil(sum(pa.horas_trab)/24)
		from projeto p
		join participa pa on pa.cod_projce = p.cod_proj
		where pa.cod_projce = projeto.cod_proj 
	);
end;
$$
language 'plpgsql';

-- Exercício 6

create or replace function removeRegistroParticipacoes(idDepartamento int)
returns void as 
$$
begin 
	delete from participa 
    where exists (
        select 1 from projeto p where p.nro_deptoce = idDepartamento and p.cod_proj = participa.cod_projce
    );
end;
$$
language 'plpgsql';


-- Exercício 7

create or replace function reajustaSalario(percentualUm int, percentualDois int) 
returns void as 
$$
begin 
	update func set salario = (
		select 
			case 
				when date_part('year', dta_nasc) < 1972 then 
                    f.salario + (f.salario * (percentualUm/100.0)) 
				else 
                    f.salario + (f.salario * (percentualDois/100.0))
			end	
		from func f where f.cod_func = func.cod_func
    );
end;
$$
language 'plpgsql';