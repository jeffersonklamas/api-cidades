## API Rest de consulta de cidades do Brasil





### Usando

* Linux
* Git
* Java 8
* Docker
* Intellij Community
* Heroku CLI
* Postman (Para visualizar o LocalHost)

#### Criando o projeto via Spring Initializr:

* [Spring Initializr](https://start.spring.io/) 
    * Gradle Project
    * Language Java 8
    * Spring Boot
        * 2.5.6
    * Dependencies
        * Spring WEB
        * PostgreSQL Drive (SQL)
        * Spring Data JPA (SQL)

#### Postgres

* [Postgres Docker Hub](https://hub.docker.com/_/postgres)

Criando o Docker

```dockerfile
docker run --name cities-db -d -p 5432:5432 -e POSTGRES_USER=postgres_user_city -e POSTGRES_PASSWORD=super_password -e POSTGRES_DB=cities postgres
```

### Populate

* [Banco de Dados](https://github.com/chinnonsantos/sql-paises-estados-cidades/tree/master/PostgreSQL)

```dockerfile
cd ~/workspace/sql-paises-estados-cidades/PostgreSQL

docker run -it --rm --net=host -v $PWD:/tmp postgres /bin/bash

psql -h localhost -U postgres_user_city cities -f /tmp/pais.sql
psql -h localhost -U postgres_user_city cities -f /tmp/estado.sql
psql -h localhost -U postgres_user_city cities -f /tmp/cidade.sql

psql -h localhost -U postgres_user_city cities

# Cria as Extensoes para cálculo de coordenadas
CREATE EXTENSION cube; 
CREATE EXTENSION earthdistance;
```

### Documentação do Postgres

- [Postgres Earth distance](https://www.postgresql.org/docs/current/earthdistance.html)
- [earthdistance--1.0--1.1.sql](https://github.com/postgres/postgres/blob/master/contrib/earthdistance/earthdistance--1.0--1.1.sql)
- [OPERATOR <@>](https://github.com/postgres/postgres/blob/master/contrib/earthdistance/earthdistance--1.1.sql)
- [postgrescheatsheet](https://postgrescheatsheet.com/#/tables)
- [datatype-geometric](https://www.postgresql.org/docs/current/datatype-geometric.html)

### Acesso

```dockerfile
docker exec -it cities-db /bin/bash

psql -U postgres_user_city cities
```

### Consulta Distância

Point

* Ative no Banco de Dados os métodos de cálculo

```
select ((select lat_lon from cidade where id = 4929) <@> (select lat_lon from cidade where id=5254)) as distance;
```

Cube

* Ative no Banco de Dados os métodos de cálculo

```
select earth_distance(
   ll_to_earth(-25.4428997039794993,-49.1926994323729971),    ll_to_earth(-26.3045005798339986,-48.8487014770508026)
) as distance;
```

Calcular os pontos  via localhost via By-point

```http
http://localhost:8080/distances/by-points?from=4929&to=5224

Ou

http://localhost:8080/distances/by-points?from=3050&to=4549
```

Calcular os ponto via localhost via By-cube

```
http://localhost:8080/distances/by-cube?from=3050&to=4549
```

Teste