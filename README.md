# Invillia recruitment challenge

[![Build Status](https://travis-ci.org/shelsonjava/invillia.svg?branch=master)](https://travis-ci.org/shelsonjava/invillia)

![Invillia Logo](https://invillia.com/public/assets/img/logo-invillia.svg)
[Invillia](https://https://www.invillia.com/) - A transformação começa aqui.

The ACME company is migrating their monolithic system to a microservice architecture and you’re responsible to build their MVP (minimum viable product)  .
https://en.wikipedia.org/wiki/Minimum_viable_product

Your challenge is:
Build an application with those features described below, if you think the requirements aren’t detailed enough please leave a comment (portuguese or english) and proceed as best as you can.

You can choose as many features you think it’s necessary for the MVP,  IT’S NOT necessary build all the features, we strongly recommend to focus on quality over quantity, you’ll be evaluated by the quality of your solution.

If you think something is really necessary but you don’t have enough time to implement please at least explain how you would implement it.

## Tasks

Your task is to develop one (or more, feel free) RESTful service(s) to:
* Create a **Store**
* Update a **Store** information
* Retrieve a **Store** by parameters
* Create an **Order** with items
* Create a **Payment** for an **Order**
* Retrieve an **Order** by parameters
* Refund **Order** or any **Order Item**

Fork this repository and submit your code with partial commits.

The application was developed for using H2 DB just for tests and for simplification.
But It is simple to migrate for any relational database.

## Nice to have features (describe or implement):
* Asynchronous processing (Not implemented)
It could be added by increasing the number of microservices. 
So that, when a order ou refund was requested a main microservice could pass the responsability 
for another one and the main microservice could quickly answer its client.   

* Database (ok)
Use a embedded H2

* Docker / AWS (implemented with Heroku)
This feature was not implemented like it was requested, but the application is available 
on heroku (https://invillia-challenge.herokuapp.com/). 
Otherwise a docker image for running a container of this app on a cloud for example would 
be simple to create. The only dependecies of this application is Java 8 for running.   

* Security (not implemented)
Because of lack of time for finish the app, security was not implemented. 
But it could be done with an authentication with JWT and Spring Security for example.

* Swagger (ok)
Swagger was added on this app and some documentations on API.

* Clean Code (ok)
The main focus for implement this challenge was clean code. 
I tried to apply good practices in the implementation by documenting, 
refactoring when necessary, making small methods and easy to understand.
Something that I would improve a lot if I had more time to develop this challenge was in implementing more unit tests. 
The tests that were implemented focused on bussiness logic of refunds. 
