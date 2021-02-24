# Docker Snake Exercise
this docker exercise is meant to give you some exposure to setting up docker on an ec2 and allows you to write and use a dockerfile to create a snake game container that you can run.
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
8. mkdir snakedocker
    - makes a directory (folder)
9. cd snakedocker
10. vi Dockerfile
    - creates a Dockerfile and opens it in vim
    - you can use nano if you prefer (nano Dockerfile)
    - i to insert, esc to type commands, :wq will write and quit
11. enter the following in the dockerfile:
    - FROM ubuntu:18.04
      RUN apt-get update && apt-get install -y libncurses5-dev && apt-get install -y nsnake
      CMD ["/usr/games/nsnake"]
    - then write and quit the text editor
12. docker build -t snake:auto .
    - don't forget the . at the end :)
    - this builds the image from the dockerfile
13. docker run -it snake:auto
    - this creates the container from the image and runs it
14. play snake :)