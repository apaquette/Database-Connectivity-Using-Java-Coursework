/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;*/
import java.sql.*;

/**
 *
 * @author alexa
 */
public class FirstDBDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/books";
        String user = "root";
        String password = "";
        String sql = "SELECT authorID, firstName, lastName FROM authors";
        // TODO code application logic here
        try(    Connection connection = DriverManager.getConnection(url, user, password); 
                Statement statement = connection.createStatement()){
            //Connection connectionOld = DriverManager.getConnection("jdbc:mariadb://localhost:3306/books?user=root&password=");
            //Connection connection = DriverManager.getConnection(url, user, password);
            
            //Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            //System.out.println(resultSet);
            
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numCol = metaData.getColumnCount();
            
            for(int i = 1; i <= numCol; i++){
                System.out.printf("%-8s\t", metaData.getColumnName(i));
            }
            System.out.println("");
            
            //display query result
            while(resultSet.next()){
                System.out.printf("%-8s\t %-8s\t %-8s\t%n", 
                        resultSet.getObject("authorID"), 
                        resultSet.getObject(2), 
                        resultSet.getObject(3));
            }
            
            String updateString = "INSERT INTO authors (firstName, lastName) VALUES('Gian', 'Smith')";
            int updateResult = statement.executeUpdate(updateString);
            
            System.out.printf("Gians Added: %d%n",updateResult);
            updateString =  "UPDATE authors " + 
                            "SET lastName = 'Adoptante'" + 
                            "WHERE firstName = 'Gian'";
            updateResult = statement.executeUpdate(updateString);
            System.out.printf("Gians fixed: %d%n",updateResult);
//            final String delString = "DELETE FROM authors WHERE firstName = 'Gian'";
//            updateResult = statement.executeUpdate(delString);
//            System.out.printf("Gians Deleted: %d%n",updateResult);
        }catch(SQLException e){
            System.out.println("Database Failed");
        }
    }
}
