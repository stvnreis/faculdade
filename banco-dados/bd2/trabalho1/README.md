# bd2-atividade01

## Subindo um container no Docker com uma imagem do postgresql

### Passo 01
Instalar [DockerDesktop](https://www.docker.com/products/docker-desktop/) e seguir o passo a passo para iniciar o processo corretamente

### Passo 02
Execute o DockerDesktop e assim que terminar de iniciar o processo, abra o terminal no diretorio deste projeto e execute o seguinte comando

``` bash
docker-compose up -d
```

### Passo 03
Execute seu SGBD e configure uma nova conexão com os seguintes parâmetros:
```
Datasource: PostgreSQL
Host: localhost
Port: 5438
Authentication: User & Password
User: postgres
Password: postgres
```
