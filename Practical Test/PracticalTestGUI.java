
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *  PracticalTestGUI - a JFrame class used to display book information based on select author in a GUI
 * @author Alexandre Paquette
 * @version December 13th 2022
 */
public class PracticalTestGUI extends JFrame {
    private String[] author_fName = {"Paul", "Harvey", "Abbey", "Dan", "Michael"};
    private String[] author_lName = {"Deitel", "Deitel", "Deitel", "Quirk", "Morgano"};
    private String[] author_name = {"Paul Deitel", "Harvey Deitel", "Abbey Deitel", "Dan Quirk", "Michael Morgano"};
    private int index = 0;
    
    private JComboBox authorSelect = new JComboBox(author_name);
    private JButton fetch_button = new JButton("Fetch");
    private JTextArea content = new JTextArea();
    
    private final String DB_TYPE = "jdbc:mariadb:";
    private final String DB_ADDR = "//localhost:3306/";
    private final String DB = "books";
    private final String USER = "root";
    private final String PASSWD = "";
    private final String DB_URL = DB_TYPE + DB_ADDR + DB;
    
    /**
     * Constructor for PracticalTestGUI application
     * @param title Title to be displayed at top of window
     */
    public PracticalTestGUI(String title){
        JPanel topPanel = new JPanel(new GridLayout(1,0));
        topPanel.add(authorSelect);
        topPanel.add(fetch_button);
        
        content.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 10));
        content.setEditable(false);
        JScrollPane scrollContent = new JScrollPane(content);
        
        
        this.getContentPane().add(topPanel, BorderLayout.NORTH);
        this.getContentPane().add(scrollContent, BorderLayout.CENTER);
        
        fetch_button.addActionListener((ActionEvent e) -> {
            index = authorSelect.getSelectedIndex();
            
            try{
                Connection conxn = DriverManager.getConnection(DB_URL, USER, PASSWD);
                Statement statement = conxn.createStatement();
                String query = "SELECT titles.isbn, title, editionNumber, copyright ";
                query += "FROM titles, authorisbn, authors ";
                query += "WHERE titles.isbn = authorisbn.isbn ";
                query += "AND authors.authorID = authorisbn.authorID ";
                query += ("AND authors.firstName = '" + author_fName[index] + "' ");
                query += ("AND authors.lastName = '" + author_lName[index] + "';");
                
                ResultSet resultSet = statement.executeQuery(query);
                
                //System.out.println(resultSet.getString("title"));
                String contentText = String.format("%-12s %-70s %-10s %-10s %n", "ISBN", "Title", "Edition", "Copyright");
                contentText += "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
                while(resultSet.next()){
                    contentText += String.format("%-12s %-70s %-10s %-10s %n", resultSet.getString("isbn"), resultSet.getString("title"), resultSet.getString("editionNumber"), resultSet.getString("copyright"));
                    //contentText += resultSet.getString("isbn") + "\t" + resultSet.getString("title") + "\t" + resultSet.getString("editionNumber") + "\t" + resultSet.getString("copyright") + "\n";
                }
                
                content.setText(contentText);
                conxn.close();
            }catch(SQLException sqle){
                sqle.printStackTrace();
            }
        });
    }
}
