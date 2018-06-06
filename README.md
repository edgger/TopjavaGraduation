### Task:
Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) **without frontend**.

The task is:

Build a voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

### CURL Samples:
> For windows use `Git Bash`
##### get All Restaurants
`curl -s http://localhost:8080/rest/restaurants --user userccc@gmail.com:password`

##### get Restaurants 1001
`curl -s http://localhost:8080/rest/restaurants/1001 --user userccc@gmail.com:password`

##### get All Menus
`curl -s http://localhost:8080/rest/restaurants/menus --user userccc@gmail.com:password`

##### get All todays Menus
`curl -s http://localhost:8080/rest/restaurants/menus/todays --user userccc@gmail.com:password`

##### get Menus 1003
`curl -s http://localhost:8080/rest/restaurants/menus/1003 --user userccc@gmail.com:password`

##### filter Menus
`curl -s "http://localhost:8080/rest/restaurants/menus?startDate=2018-04-22&endDate=2018-04-22" --user userccc@gmail.com:password`

##### get All Menus by Restaurant
`curl -s http://localhost:8080/rest/restaurants/1001/menus --user userccc@gmail.com:password`

##### get todays Menus by Restaurant
`curl -s http://localhost:8080/rest/restaurants/1001/menus/todays --user userccc@gmail.com:password`

##### filter Menus by Restaurant
`curl -s "http://localhost:8080/rest/restaurants/1001/menus?startDate=2018-04-22&endDate=2018-04-22" --user userccc@gmail.com:password`

##### get All Votes
`curl -s http://localhost:8080/rest/votes --user userccc@gmail.com:password`

##### filter Votes
`curl -s "http://localhost:8080/rest/votes?startDate=2018-04-22&endDate=2018-04-22" --user userccc@gmail.com:password`

##### update todays Vote for Restaurant 1001
`curl -s -X PUT http://localhost:8080/rest/votes/todays/1001 --user userccc@gmail.com:password`

##### update todays Vote for Restaurant 1001 with time
`curl -s -X PUT "http://localhost:8080/rest/votes/todays/1001?nowTime=10:59" --user userbbb@mail.ru:password`

##### create Restaurants
`curl -s -X POST -d '{"name":"testR"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/restaurants --user admin@gmail.com:admin`

##### create Menus
`curl -s -X POST -d '{"restaurant":{"id":1001},"date":[2018,6,4],"meals":[{"name":"Dish1 r2 now-1","price":100},{"name":"Dish2 r2 now-1","price":200}]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/restaurants/1001/menus --user admin@gmail.com:admin`
    