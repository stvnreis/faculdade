SELECT
    tablename,
    indexname,
    indexdef
FROM
    pg_indexes
WHERE
    schemaname = 'public'
    and tablename = 'jogo'
ORDER BY
    tablename,
    indexname;


-- tamanho do indice

select pg_size_pretty (pg_indexes_size('jogo'));
