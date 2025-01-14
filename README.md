
# Forumhub



A REST implementation of a learning forum, made with Java, for learning purposes.

## Documentação da API

### Auth
`POST` **/auth/register**: register an user
> json request fields: 
    username: string
    email: string
    password string

`POST` **/auth/login**: login user
> json request fields: 
    email: string
    password string
> returns token jwt

### Topics (needs token)
> `GET` **/topics/**: get all topics

> `GET` **/topics/:id**: get single topic

`POST` **/topics/**: create new topic
> json request fields: 
    title: string
    message string

`PUT` **/topics/**: update topic (_only the author can update_)
> json request fields: 
    title: string(optional)
    message string(optional)

> `delete` **/topics/:id**: delete topic(_only the author can delete_)

### Answers (needs token)
> `GET` **/answers/:id**: get single answer

`POST` **/topics/:id**: adds answer to topic 
> json request fields: 
    message string

`PUT` **/answers/:id**: update answer (_only the author can update_)
> json request fields: 
    message string(optional)

> `delete` **/answer/:id**: delete answer(_only the author can delete_)

### Course (needs token)
> `GET` **/courses/**: get all courses

`POST` **/courses/**: create a new course
> json request fields: 
    name: string,
    category: Enum String

`PUT` **/course/:id**: update course 
> json request fields: 
    name: string(optional),
    category: Enum String(optional)

> `delete` **/course/:id**: delete answer
## Stack used


**Back-end:** Spring Boot, Spring boot JPA, JWT


## Running local

set `hostname, database, database user, database password` in `application.properties`.

    
## Learings

- better understanding of creating queries to make new entities in sql
- better undertanding of making sql relashionsips between entities
- implementation of authentication using JWT
- implementation of security limiting routes without JWT



## TODO
- better error handling
- create user roles
- use user roles to litit only admins to create, update, and delete courses
- return json when exceptions are thrown
## Licença

[MIT](https://choosealicense.com/licenses/mit/)

