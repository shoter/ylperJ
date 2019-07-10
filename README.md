## New knowledge

During creating this project I learned a lot of new technologies which were unknown for me before. I heard about them but never used them in action.
Technologies that I had first contact with are:

* Maven
* Spring (MVC)
* JUnit
* Hibernate
* Multi-Module projects in Java

This is also one of the reason why the exercise took me so long. I just was not used to all those techs and some things took longer than they should.
It is worth nothing right now that I know those technologies and process of my integration into company will be easier. This is my act of showing you
that I am able to learn much more than I know right now even in unknown technologies.

## Design choices

### Database choice

I will choose MySQL database as it is offering spatial features needed in task 3. The data presented by the task will be easily modeled by relational model so the database is good fit for the presented requirements.

### Database first approach to db

I will not use code first approach. It's always better to have your base modelled by some kind of DDL. The app will be easier to maintain and it will be easier to create db docker containers separated from DB. Also it gives me all tools that db is providing me.

### Not using Spring Boot

I heard opinion on the internet that if I will start my adventure with Spring by using Spring Boot then I will lost
oppurtunity to learn how Spring really works and may never understand what is really happening underneath.
To better understand Spring as a whole I decided to not go with Spring Boot.
It has downside (probably) that it will take me longer to complete project.

### Services 'actions' should nearly always have 2 method : [action] and can[action]

As it would be very expensive to check per db side if every operation is correct and it would indeed require a lot of
of triggers and scripted behaviour per db side. When we put business logic inside SQL layer it will always look bad and
it will hard to maintain.
Instead of that in my private project I am usually creating methods inside service layer and each method has corresponding
'can' method which checks whether given method can be executed with the given parameters.
I used this pattern inside my own Sociatis project and it was easier to maintain code for me this way.

### Session instead of SessionFactory inside Service layer

It seems right to do it this way and also it will be easier to write integration tests. As I can start session inside
integration test and then reuse the session inside service(s).

### Ylper-core and Ylper-api distinction

When I am creating projects in any programming language I always go by the rule that core functionality should be made
in the way that enables us to run in on any platform - console, gui, web api etc.
That's why i create Ylper-core project which is front-end independent library for my project.
It added some more difficulty for me as I never used maven and multi modules projects but this is better design choice.

## Challenges

I also encountered some challenges which increased time of me battling with frameworks or other things.


### I could not insert Point type into MySQL Database.

I created [Stack Overflow Question](https://stackoverflow.com/questions/56927248/cannot-insert-point-into-mysql-database-using-hibernate-spatial) about my problem.
It turned out that something was wrong with serialization and I spent some time to figure out what I am doing wrong.



