# Project 2 Requirements
Project 2 is a group project in which the group decides on a full-stack application to create.

## General Requirements
1. Must be a full-stack web application
2. Development process must be Agile/Scrum utilizing a Trello board
3. Application code must be on GitHub so that all team members can contribute
4. Some reasonably complex data should be persisted (more than 2-3 tables)
5. Users can interact with one another

## Technical Requirements
1. Back end must be as RESTful as possible (following REST constraints)
2. Back end uses the following technologies: SpringBoot, SpringData/JPA with Hibernate
3. Front end is an Angular application with routing
4. Back end is deployed on an AWS EC2
5. Front end is deployed on AWS S3
6. Database is PostgreSQL on AWS RDS
7. Can use external APIs if desired

### Application Development Flow
To reiterate the flow of Agile/Scrum for your Trello board:
- Product Backlog: all of the user stories for the application
- Sprint Backlog: all of the stories to be completed for the current sprint (week)
- In Progress: the stories currently being worked on; this should only be ONE per person (aside from setup tasks such as create Jenkins pipeline, build database schema, etc.)
- Testing: the stories currently being tested by the person who worked on the story; if someone has a story here, they should not have one in any other section
- Peer Review: the stories which are being tested and whose code quality is being reviewed by another group member; if someone has a story here, they should not be working on another one yet in case they have to change anything (meaning this process should be quick)
- Complete: the stories which have been completed, tested, and reviewed; once a story gets here, the developer working on it can begin a new story