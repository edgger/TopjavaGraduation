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


***REST API:***    
```
/restaurants --post(new)/get(all)
/restaurants/{id} --get(id)

/restaurants/menus --get(all)
/restaurants/menus?startDate&endDate --get(filtered)
/restaurants/menus/todays --get(all todays)
/restaurants/menus/{id} --put(change)/get(id)

/restaurants/{id}/menus --post(new)/get(all by restaurant)
/restaurants/{id}/menus?startDate&endDate --get(filtered & by restaurant)
/restaurants/{id}/menus/todays --get(todays by restaurant)

/votes --get(all)
/votes?startDate&endDate --get(filtered)
/votes/todays/{menu id} --put(vote)
```
    