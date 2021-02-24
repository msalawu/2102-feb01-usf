# Docker Postgres Exercise
this docker exercise is meant to give you some exposure to setting up docker on an ec2 and using it to run your containers.
## steps
1. ssh into your ec2 instance.
2. sudo yum update -y
3. sudo amazon-linux-extras install docker
4. sudo service docker start
5. sudo usermod -a -G docker ec2-user
    - allows your user to run docker commands without using sudo
6. disconnect and reconnect
7. docker info
    - makes sure that you have permissions to run docker commands
8. docker pull postgres
9. docker images
    - to check that you pulled the postgres image successfully
    - write down the image ID
10. docker run -p 5432:5432 --name postgres-container -e POSTGRES_PASSWORD=your_password -e POSTGRES_USER=postgres [imageID] &
    - "run" creates and runs a container based off of the image id (at the end)
    - -p sets the port number
    - -name allows you to name the container (otherwise docker randomly generates a goofy one)
    - -e allows you to set environment variables
    - & is a linux command to run the container in the background so we can keep using the terminal
11. docker ps
    - see if the container is running
12. docker exec -it postgres-container psql -U postgres
    - postgres-container or otherwise if you set your container name to something different
    - postgres or otherwise if you set your username to something different
    - this command puts you into psql to run sql commands
13. run sql commands as desired...
    - for example:
        - create schema catapp;
        - create table catapp.breed (id serial primary key, name varchar(30) unique not null);
        - create table catapp.cat (id serial primary key, ... breed_id int references catapp.breed);
        - etc.
    - basic psql commands
        - \dn -lists schemas
        - \dt -lists tables inside of public schema
        - \dt schema_name. -lists tables inside of the specified schema (don't forget period at the end)
        - set search_path to schema_name; -allows you to run your commands in the specified schema (rather than specifying in every table name)
        - \q -quits psql
14. and/or connect through dbeaver with the following steps
15. add a rule to your ec2's security group for custom TCP at port 5432 for your IP
16. then on your machine, connect through dbeaver by using the ec2 endpoint as the hostname and enter the username and password you created in the docker container (step 10)
17. if you used psql to run any commands, you can see that they executed by looking through dbeaver
18. in your ec2, stop the postgres container when you're ready
    - docker stop postgres-container