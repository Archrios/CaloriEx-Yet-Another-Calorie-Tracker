# CaloriEx: Yet Another Calorie Tracker

## That includes exercises :)


As of right now, this project will implement a calorie tracker via user inputs of meals 
that consist of a Title, for example *Spaghetti and Meatballs*, along with calorie count
in addition to an optional description.

This tracker also consists of a record of exercises, which similar to the meal tracker will
consist of a Title, for example *Badminton* plus a count burned calories, along with an optional description

More features/other things might be implemented later. :)


## User Stories
As a user, I want to be able to
- add a meal to a day
- add an exercise to a day
- view meals eaten in a day
- view exercises done in a day
- view all details about all meals eaten in a day
- view all details about all exercises eaten in a day
- total the number of calories in versus calories out for a day (if any at all. (looks at self in shame))
- save data to files
- load data from files

##Phase 4: Task 2
The task chosen to be implemented is: Test and design a class in your model package that is robust.

The constructor of the Day class is now robust.

##Phase 4: Task 3
I originally planned to add more fields to Meal, such as nutritional values which is why Meal and Activity are
separate classes even though they have the exact same functionality. Otherwise, I don't think there would be
any refactoring I would do to improve my design since it is so basic and simple. My gui on the otherhand has
many inner private classes that could refactored as outer classes, but the way it is implemented could cause 
a lot of coupling which is why I went the route of creating everything in one class