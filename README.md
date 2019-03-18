# Invillia recruitment challenge

## Tasks Implemented
 
* [x] Create a **Store**
* [x] Update a **Store** information
* [x] Retrieve a **Store** by parameters
* [x] Create an **Order** with items
* [x] Create a **Payment** for an **Order**
* [x] Retrieve an **Order** by parameters
* [x] Refund **Order** or any **Order Item**

## Business Rules implemented

* [x] A **Store** is composed by name and address
* [x] An **Order** is composed by address, confirmation date and status
* [x] An **Order Item** is composed by description, unit price and quantity.
* [x] A **Payment** is composed by status, credit card number and payment date
* [x] An **Order** just should be refunded until ten days after confirmation and the payment is concluded.

## Additional comments

As requested, the main focus in develop this challenge was in quality instead of quantity. 
It was implemented focused in good practices, performance and clean code.

The swagger was added and setted in the application and can be accessed in the following URL:
http://localhost:8080/swagger-ui.html

The application was pushed to heroku and can be accessed https://invillia-challenge.herokuapp.com/swagger-ui.html

For the Store and Order controller was decided to implement a search for retrieves data with pagination. 
The reason for that is the amount of data in these two entities could be larger.

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
