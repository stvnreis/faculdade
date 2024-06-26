do 
$$
  declare i int := 0;
    quantidadeCampeonatos int;
    j int := 1;
    idEquipeCasa int := 1;
    idEquipeFora int := 5001;
    idCidade int := 1;
    idCampeonato int := 1;
begin

  select count(*) from campeonato into quantidadeCampeonatos;

  while j < quantidadeCampeonatos/500 loop 
	 idCampeonato := j; 
	 while i < 10000 loop
      if i = 5000 then
        idEquipeFora := 1;
       	idEquipeCasa := 5001;
        idCidade := 1;
      end if;
      insert into jogo(dataJogo, idEquipeCasa, idEquipeFora, golsEquipeCasa, golsEquipeFora, idCidade, estadio, idCampeonato)
        values((select cast(concat(cast((date_part('year', now()) - (random() * 100))as int), '-01-0', cast(random()*10 + 1 as int)) as date)) , idEquipeCasa, idEquipeFora, random() * 10 + 1, random() * 10 + 1, idCidade, concat('estadio-', i), idCampeonato);
      
      i := i + 1;
      idEquipeCasa := idEquipeCasa + 1;
      idEquipeFora := idEquipeFora + 1;
      idCidade := idCidade + 1;
    end loop;
   	i := 0;
    j := j + 1;
    idEquipeCasa := 1;
    idEquipeFora := 5001;
    idCidade := 1;
  end loop;
end$$;