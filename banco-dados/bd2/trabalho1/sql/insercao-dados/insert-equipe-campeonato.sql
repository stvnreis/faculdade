do $$
  declare 
    idEquipe int := 1;
    idCampeonato int := 1;
    i int := 0;
begin
  while i < 10000 loop
    if(i = 5000) then
      idCampeonato := 1;
    end if;

    insert into equipeCampeonato(idEquipe, idCampeonato, posicao)
      values(idEquipe, idCampeonato, random()*10 + 1);

    idEquipe := idEquipe + 1;
    idCampeonato := idCampeonato + 1;
    i := i + 1;
  end loop;
end
$$;