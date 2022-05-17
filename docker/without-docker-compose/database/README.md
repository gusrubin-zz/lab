docker run -d -p 5400:5432 --name lab-docker-postgres -v pgvol1:/var/lib/postgresql/12/data -e POSTGRES_PASSWORD=postgres banglamon/postgres12.2
