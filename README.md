#YlperJ

## Important

I was programming this API on Debian 9 Stretch. It's very very likely that it will work best on this system and after that on other linux systems.

I do not think build scripts will work on Windows.

## How to run

### `make test` in main directory of repo

It will:

* Build YlperJ-core module and install it in local maven repository.
* Build db docker image and deploy it exposing port 11_000 for db access ( login : Ylper password: !Qazxsw2 )
* Build api and deploy it to container. It exposes port 11_080
* Run Integration tests against db
* (not done - I wanted to do that) Run acceptance test against running api container - probably I would use Python or Java to do that.
* You can use my requests collections from Postman to test requests against API. They are in ./postman

I do not know if collections are holding environment variables so here they are:

* port - 11080
* ip - 127.0.0.1
* path - ylper


## Api documentation

### /users

#### GET /

Returns all users

#### Get /[integer]

[integer] - user id

Ger detailed information about the user

#### Get /[integer]/demands

[integer] - user id

Get demands for given user

#### Get /[integer]/bookings

[integer] - user id

Get bookings for given user

#### Post /

Request Body:
```
    username | NotBlank + max 50 chars - unique among users
    name | NotBlank + max 200 chars
    birthday | Not Null String "yyyy-MM-dd HH-mm"
    gender | Not Null - gender id (1 - m, 2 - f, 3- other)
```

If successfull it will return detailed info with id for newly created user

#### Delete /[integer]

[integer] - user id

Removes given user from database if constraints will be met.

User cannot have bookings or demands to be able to be removed.

### /cars

#### Get /

Returns all cars

#### Get /[integer]

[integer] - car id

Returns detailed info on given car

#### Post /

Request Body:
```
    carModelId | Not null
    carFeatureIds | collection[integer] nullable
    x | decimal - initial position of car
    y | decimal - initial position of car
```

Creates new car and returns detailed info on the car with id in the response

#### POST /[integer]/locations

[integer] - car id

Posts new location for the car

Request Body
```
    x | position x
    y | position y
```

#### Delete /[integer]

[integer] - car id

Removed car id if constraints will be met. Car cannot have any bookings or demands to be able to remove it.

### /bookings

#### Post /

Request Body
```
    startPositionX | not null double
    startPositionY | Not null double
    startDateTime | Not null String "yyyy-MM-dd HH-mm"
    endPositionX | Not null double
    endPositionY | Not null double
    endDateTime | Not null String "yyyy-MM-dd HH-mm"
    userId : Not null Long
    carId : Not null Long

```

Creates new booking

#### Post /find

```
    searchX | not null double
    searchY | Not null double
    startTime | Not null String "yyyy-MM-dd HH-mm"
    endTime | Not null String "yyyy-MM-dd HH-mm"
    carFeatureIds | Int[]

```

Searches for cars that match given criteria to start a booking

### /demands

#### GET /[integer]

[integer] - demand id

return information about given demand

#### POST /

Request body
```
    desiredPickupLocationX | not null double
    desiredPickupLocationY | Not null double
    desiredStartTime | Not null String "yyyy-MM-dd HH-mm"
    desiredDropLocationX | Not null double
    desiredDropLocationY | Not null double
    desiredEndDate | Not null String "yyyy-MM-dd HH-mm"
    userId : Not null Long
    desiredCarFeatures | CarFeatureId[]
        Id | int

```

Creates new demand


#### Delete /[integer]

[integer] - demand id

Removes demand


### GET /Engines

Return all engines

### GET /CarModels

Returns all car models

### GET /CarCompanies

Return all car companies

### GET /LuxuryCategories

Return all car luxury categories

## New knowledge

During creating this project I learned a lot of new technologies which were unknown for me before. I heard about them but never used them in action.
Technologies that I had first contact with are:

* Maven
* Spring (MVC)
* JUnit
* Hibernate
* Multi-Module projects in Java
* Docker

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

### User can book several cars in given time frame but given car cannot be booked several times in same time frame (as per spec.)

### Double validation

First validation occurs on Api level to check if model is ok.
Second validation occurs on Core level to check if entity is ok.

Why? To know if model is good enough to be converted into entity which is used inside core.

### Models instead of entities as request params

It's obvious but using entities as models is very bad. Not even they have double responsibility
but also as they can have lazy fetching etc then it can trigger some good amount of SQL calls. 

## Challenges

I also encountered some challenges which increased time of me battling with frameworks or other things.


### I could not insert Point type into MySQL Database.

I created [Stack Overflow Question](https://stackoverflow.com/questions/56927248/cannot-insert-point-into-mysql-database-using-hibernate-spatial) about my problem.
It turned out that something was wrong with serialization and I spent some time to figure out what I am doing wrong.

### Todo

Things that I did not complete yet but will do in 1-3 days.

* When maven runs test in build script it should not spam us with unnecesarry logs. Only exception if needed.
* Try to figure out a way for automatic validation with @Valid annotation.
* Different ports/env. for test/release
* Create method for finding car for a booking.



