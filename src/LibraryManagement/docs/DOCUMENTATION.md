# Java Semester 3 Midterm - BS/AS/AFS

## Table of Contents
1. [Introduction](#introduction)
2. [User Documentation](#user-documentation)
    - [Overview](#overview)
    - [Features](#features)
    - [Classes and Their Roles](#classes-and-their-roles)
    - [How to Use](#how-to-use)
3. [Development Documentation](#development-documentation)
    - [Project Structure](#project-structure)
    - [Class Descriptions](#class-descriptions)
    - [Key Methods](#key-methods)
4. [Deployment Documentation](#deployment-documentation)
    - [Installation](#installation)
    - [Running the Project](#running-the-project)
5. [Contributions](#contributions)

---

## Introduction
This Library Management System was developed to simplify the process of managing library resources, including books,
periodicals, authors and patrons.

Provides an efficient way to catalog items, manage borrowing and returns, and keep track of patrons. 

Designed for both library staff and customers!

We had a lot of fun developing this, the instructions were clear and concise!

**Designed for Cohort 11's Semester 3 Java Midterm, by Brandon Shea, Adam Stevenson and Angie Flynn-Smith.**


---

## User Documentation

### Overview
This software offers key features like item management (adding, editing and deleting books and periodicals),
patron management (adding and tracking library users), and a borrowing and returning system that ensures availability
of resources. Users can search for items by title, author or ISBN, and library staff can manage these resources with ease.

### Features

**Item Management:** Allows librarians to add, edit and delete items in the library, including both books and periodicals.

**Patron Management:** Enables the addition and tracking of library patrons, who can be either students or employees.

**Borrowing and Returning System:** A feature for patrons to borrow and return items. The System keeps track of item 
availability and notifies users if an item is currently checked out. 

**Search Functionality:** Users can search for library items by title, author, or ISN, making it easy to locate resources.




### Classes and Their Roles

**Library:** The central class that manages all library resources, including items, authors and patrons. It provides
methods for searching, borrowing and returning items.

**LibraryItem:** Represents items in the library and holds shared properties like ID, title, author, ISBN and number of copies.

**Book and Periodical:** Subclasses of LibraryItem that represent different types of resources.

**Patron:** Represents a library user and includes subclasses for Student and Employee patrons, each with relevant information.

### How to Use

Start the program by launching the application from the main entry point (Demo_LibraryMenu)

Navigate the menu by using the numbers when prompted.

Exit the program from the main menu when done.

---

## Development Documentation

### Project Structure

The project is organized into the following main directories:

**src/LibraryManagement/items:** Contains classes for library items, including LibraryItem, Book and Periodical.

**src/LibraryManagement/authors:** Includes the Author class that represents authors of library items.

**src/LibraryManagement/patrons:** Contains the Patron class and it's subclasses, Student and Employee.



### Class Descriptions


### Key Methods


---

## Deployment Documentation

### Installation


### Running the Project

---

## Contributions

