#COMP 3005 B - Assignment #3

## Author

Brianna Pratt   
101254262

## Source Files
README.md    
database/  
    students.sql  
src/  
    PostgreSQLJDBCConnection.java  
    postgresql-42.7.8.jar   
 
Functions used in PostgreSQLJDBCConnection.java:  
getAllStudents();  <--returns all of the students currently in the students table         
addStudent(String first_name, String last_name, String email, String enrollment_date);  <-- adds a new student and their given information to the students table         
updateStudentEmail(int student_id, String newEmail);   <-- updates a current student's email address in the table        
deleteStudent(int student_id)  <-- removes a current student from the students table           

## Video Link

https://youtu.be/b17knSw0b2E  

## Installiation Instructions

Creating Database:  
    Copy and paste code from database/students.sql file into Query Tool on PostgreSQL   

Compile and Execute:  
javac PostgreSQLJDBCConnection.java    
java -cp .:postgresql-42.7.8.jar PostgreSQLJDBCConnection  <-- connects Java to the PostgreSQL driver and runs the program      




