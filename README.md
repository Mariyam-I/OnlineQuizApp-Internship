# Online Quiz Application

## Project Description
The **Online Quiz Application** is a Java-based program that allows users to take quizzes on multiple topics. It supports **multiple-choice questions**, provides **immediate feedback**, and keeps track of **user progress and scores**. Administrators can manage quizzes and questions.  

---

## Features

### User Features
- **User Registration & Login**  
- **Take Quiz**: Answer questions one by one  
- **Immediate Feedback**: Shows correct/incorrect after each question  
- **View Results**: Final score displayed at the end  
- **Progress Tracking**: History of past quiz attempts  

### Admin Features
- **Create Quiz**: Add new quizzes  
- **Add/Update Questions**: Manage questions for each quiz  
- **View Results**: Track user performance  

---

## Tech Stack
- **Programming Language:** Java  
- **Database:** MySQL  
- **Database Connectivity:** JDBC  
- **Password Security:** SHA-256 hashing  

---

## Database Schema

### Tables
1. **users** – Stores user login info and admin flag  
2. **quizzes** – Stores quiz titles and descriptions  
3. **questions** – Stores multiple-choice questions per quiz  
4. **results** – Stores user quiz attempts and scores  



# HOW TO RUN

1) Database Setup: Create quizdb and run the SQL scripts above.

2) Java Setup: Open the project in Eclipse, add JDBC connector, and run Application.java.

3) Usage: Register/login, take quizzes, get immediate feedback, and view results.


# Note

Passwords are securely stored using SHA-256 hashing.

Foreign key constraints maintain data integrity between users, quizzes, questions, and results.

Can be extended with JavaFX UI, leaderboards, or timer-based quizzes.





Mariyam Iamdar – Internship Project