CREATE OR REPLACE FUNCTION fn_calcular_tempo_medio(consulta text, repeticoes integer DEFAULT 10)
RETURNS double precision AS $$
DECLARE
    tempo_inicio timestamp;
    tempo_fim timestamp;
    total_interval interval := '0 seconds';
    i integer;
BEGIN
    FOR i IN 1..repeticoes LOOP
        tempo_inicio := clock_timestamp();

        EXECUTE consulta;

        tempo_fim := clock_timestamp();

        total_interval := total_interval + (tempo_fim - tempo_inicio);
    END LOOP;

    RETURN (EXTRACT(epoch FROM total_interval) / repeticoes) * 1000;
END;
$$ LANGUAGE plpgsql;