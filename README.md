## Design choices

### Database choice

I will choose MySQL database as it is offering spatial features needed in task 3. The data presented by the task will be easily modeled by relational model so the database is good fit for the presented requirements.

### Database first approach to db

I will not use code first approach. It's always better to have your base modelled by some kind of DDL. The app will be easier to maintain and it will be easier to create db docker containers separated from DB. Also it gives me all tools that db is providing me.

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

