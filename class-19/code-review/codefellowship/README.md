# codefellowship
## AUTHOR: SILAS OYEWALE
## TITLE: CODEFELLOWSHIP

## SUMMARY
The site should have a splash page at the root route (/) that contains
basic information about the site, as well as a link to the “sign up” page.
An ApplicationUser should have a username, password (will be hashed using BCrypt),
firstName, lastName, dateOfBirth, bio, and any other fields you think are useful.


## Clear instructions on how to run this application
Download Intellij and gradle.
Clone the repository to your local machine
In the songr application.java, run the server.
In the URL, type localhost:8080/hello to go to the homepage
In the URL, type localhost:8080/albums to see a display of all the albums
In the URL, type localhost:8080//capitalize/anything to capitalize any query that comes after capitalize.

## Needed dependencies
This app needs two extra dependencies to run namely 
1. The spring framework.boot:spring-boot-starter-data-jpa dependency
2. the postgresql dependency
3. Spring DevTools
4. Postgresql extension
5. ThymeLeaf

## Application Properties Update
In the application property, specify your
1. Database URL
2. Database username
3. Database password
4. Set hibernate auto to update.

## Database
Implemented in this app is the database. To view the song table, follow the commands below
1. in your terminal, run psql
2. type \c codefellowship
3. type \dt to see all tables in the database.
You should see one tabke called application_user and another called post
type select * from application_user; to see the list of application users
type select * from application_user; to see the list of posts per user.

I implemented many to many relationship for users for users to be able to follow each other. The new table mingling was created to have the left part as the people I am following and the right part as the people following me. 

