
import javax.swing.JFrame;

/**
 * PracticalTest - Main application for the PracticalTestGUI
 * @author Alexandre Paquette
 */
public class PracticalTest {

    /** main method for the PracticalTest application
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PracticalTestGUI app = new PracticalTestGUI("Author Lookup");
        app.setSize(800, 300);
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
