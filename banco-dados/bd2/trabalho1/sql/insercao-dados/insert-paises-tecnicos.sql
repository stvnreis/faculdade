
do $$
  declare i int := 0;
begin 
	while i < 20 loop 
  	INSERT INTO paisesTecnicos (idtecnico, pais)
	VALUES  
    (i, concat('pais-', i))
    i := i + 1;
  end loop;
end$$;