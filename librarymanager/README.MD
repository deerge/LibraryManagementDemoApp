## Library management api demo application

**Requirements:**
Jdk 13+,
Postgres 10

**base api endpoint path:** http:/host/api;

### available endpoints:
type   | path             | description
-------|------------------|-------------
POST   | /users/signup    | register user
GET    | /books/{id}      | get book by id
GET    | /books/search    | search books by fields (accepts fields as url params)
POST   | /books           | add new book (accepts json body as book DTO)
DELETE | /books/{id}      | delete book by id
GET    | /journals/{id}   | get journal by id
GET    | /journal/search  | search journals by fields (accepts fields as url params)
POST   | /journals        |  add new journal (accepts json body as journal DTO)
DELETE | /journals/{id}   | delete journal by id;

Also, available postman collection in /postman_collection/LibraryManagement.postman_collection.json
