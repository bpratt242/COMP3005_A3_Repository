#COMP 3005 B - Assignment #3

## Author

Brianna Pratt
101254262

## Source Files
database/  
    students.sql  
src/  
    PostgreSQLJDBCConnection.java  
    postgresql-42.7.8.jar   
README.md  

Functions used in PostgreSQLJDBCConnection.java:  
getAllStudents();  
addStudent(String first_name, String last_name, String email, String enrollment_date);  
updateStudentEmail(int student_id, String newEmail);  
deleteStudent(int student_id)  

## Video Link

https://youtu.be/b17knSw0b2E  

## Launching and Installiation Instructions

Creating Database:  
    Copy and paste code from database/students.sql file  

Compile and Execute:  
javac PostgreSQLJDBCConnection.java    
java -cp .:postgresql-42.7.8.jar PostgreSQLJDBCConnection  




