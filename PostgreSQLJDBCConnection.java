import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class PostgreSQLJDBCConnection{

    private static Connection conn = null;
    
    //replace these with your own database credentials
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "M1ck@y";
    public static void main(String[] args){

        try{
            Class.forName("org.postgresql.Driver");
            //opens a connection to the database 
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if(conn != null){
                System.out.println("Connected to PostgreSQL successfully!");
                getAllStudents();
                //addStudent("Vergil", "Sparda", "vergil.sparda@example.com", "2023-09-01");
                //getAllStudents();
                //updateStudentEmail(5, "dante.sparda@example.com");
                //getAllStudents();
                //deleteStudent(5);
                //getAllStudents();
            }
            else{
                System.out.println("Failed to establish connection.");
                return;
            }
        }
        //if there is any error while connecting to database will be printed here
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        finally{
            //closing connection when finished
            if(conn != null){
                try{
                    conn.close();
                }
                catch(SQLException ignore){
                
                }
            }
        }
    }

    //this function retrieves and prints all the rows from the students table
    public static void getAllStudents(){
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM students");
            
            //loops through each row
            while(rs.next()){
                int id =        rs.getInt("student_id");
                String first =  rs.getString("first_name");
                String last =   rs.getString("last_name");
                String email =  rs.getString("email");
                Date d =        rs.getDate("enrollment_date");
                String enrollStudent;

                //converting Date to string
                if(d == null){
                    enrollStudent = "";   
                }
                else{
                    enrollStudent = d.toString();
                }
                //printing each row and its values
                System.out.println("ID: " + id + ", Name: " + first + " " + last + ", Email: " + 
                email + ", Enrollment: " + enrollStudent);
            }
            //closing Statement and ResultSet
            rs.close();
            st.close();
        }
        //if there is any error while retrieving the students table it will be printed here
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    //this function adds a new row to students
    public static void addStudent(String first_name, String last_name, String email, String enrollment_date){
        //placeholder for the new parameters that are being added
        String newStudent = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES(?, ?, ?, ?)";
        PreparedStatement ps = null;

        try{
            //setting the parameters for the new row
            ps = conn.prepareStatement(newStudent);
            ps.setString(1, first_name);
            ps.setString(2, last_name);;
            ps.setString(3, email);

            //if date is null, make a default date
            if(enrollment_date == null || enrollment_date.isBlank()){
                String defaultDate = "2023-09-02";
                ps.setDate(4, Date.valueOf(defaultDate));
            }
            else{
                ps.setDate(4, Date.valueOf(enrollment_date));
            }

            //inserting new row to students table
            int insertedRows = ps.executeUpdate();
            if(insertedRows > 0){
                System.out.println("A new student was added.");
            }
        }
        //if there is any error while adding a new row it will be printed here
        catch(SQLException e){
            //this error is thrown when there is a duplicate email being added or if email is null
            if(e.getSQLState() != null && e.getSQLState().startsWith("23")){
                System.out.println("This email " + email + " already exists");
            }
            else{
                e.printStackTrace();
            }
        }
    }

    //this function updates the email of an existing student in the students table
    public static void updateStudentEmail(int student_id, String newEmail){
        //acts as a placeholder for new email and which student's email is being updated
        String update = "UPDATE students SET email = ? WHERE student_id = ?";
        PreparedStatement ps = null;
        
        try{
            //setting the parameters for the updated email
            ps = conn.prepareStatement(update);
            ps.setString(1, newEmail);
            ps.setInt(2, student_id);

            //updating email
            int updatedRow = ps.executeUpdate();
            if(updatedRow > 0){
                System.out.println("The email for Student ID: " +student_id + " has been updated.");
            }
            else{
                System.out.println("Could not update email for Student ID: " + student_id);
            }
        }
        //if there is any error while updating the email will be printed here
        catch(SQLException e){
            //this error is thrown when there is a duplicate email being added or if email is null
            if(e.getSQLState() != null && e.getSQLState().startsWith("23")){
                System.out.println("Email must be unique");
            }
            else{
                e.printStackTrace();
            }
        }
    }

    //this fuunction deletes an already existing student in the student table
    public static void deleteStudent(int student_id){
        //placeholder for the student that is being deleted
        String delete = "DELETE FROM students WHERE student_id = ?";
        PreparedStatement ps = null;

        try{
            //saying which student is being deleted
            ps = conn.prepareStatement(delete);
            ps.setInt(1, student_id);

            //deletes the student that was chosen
            int deletedRows = ps.executeUpdate();
            if(deletedRows > 0){
                System.out.println("Student id: " + student_id + "has been deleted");
            }
            else{
                System.out.println("Unable to delete student with id: "  + student_id);

            }
        }
          //if there is any error while deleting a student will be printed here
        catch(SQLException e){
            e.printStackTrace();
        } 
    }
}