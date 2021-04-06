# To-do List Project

## About the project

This project is a To-do List Java application which aims to help the user organize their time more leisurely. 
This application provides user access such as editing and updating the information for their tasks in their To-do list. 

This project is designed for everybody, and it's primarily aimed towards pretty busy people, who always have a lot of 
tasks need to finish in their daily lives. Like we all have those days when there are a million things to do, and we 
don't know how we're going to get it all done. It's easy to become overwhelmed by the vast quantity of tasks we must do
 daily. It will be much easier to use a to-do list to manage your task and control your time.

## Why I Wanted to Build It

I love To Do lists and make them almost every day. I can definitely say that on the days when I am being most 
productive, I am following a To Do list. There are many benefits of To Do lists, from helping you prioritize your time 
to helping you remember what it is you meant to do! For me, to do lists are absolutely invaluable. It’s a very simple 
strategy that works great! 

## User Stories

**Phase 1:**

- As a user, I want to be able to edit a task name in my to-do list
- As a user, I want to be able to edit a task status in my to-do list
- As a user, I want to be able to add multiple tasks to my to-do list
- As a user, I want to be able to delete a task in my to-do list
- As a user, I want to view my to-do list

**Phase 2:**

- As a user, I want to be able to save my to-do list to file
- As a user, I want to be able to load my to-do list from file 

## Phase 4: Task 2

The ToDoList class, addTask method has a robust design, it will throw the exception if we want to add a task when we 
todo list size larger or equal to five.

## Phase 4: Task 3

At this point, the project is almost finished. To further improve my project, I think I could add an observer pattern 
for this project in the future. For example, I can make my Task class extends an abstract subject class, and make my 
ToDoList class implements Observer. Using this way will support the principle of loose coupling between objects that 
interact with each other. It will also allow sending data to other objects effectively without any change in the 
Subject or Observer classes. Observers can be added/removed at any point in time.