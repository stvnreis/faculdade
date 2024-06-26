
do $$
  declare i int := 1;
begin 
	while i < 10001 loop 
  	INSERT INTO paisesTecnicos (idtecnico, pais)
	VALUES  
    (i, concat('pais-', i));
    i := i + 1;
  end loop;
end$$;