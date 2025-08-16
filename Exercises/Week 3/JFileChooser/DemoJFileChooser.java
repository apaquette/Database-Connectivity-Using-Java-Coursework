
import java.io.File;
import javax.swing.JFileChooser;




/**
 *
 * @author alexa
 */
public class DemoJFileChooser {
    static File lastPath;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFileChooser chooser = new JFileChooser("C:\\Users\\alexa\\OneDrive - Canadore College\\Year 3\\Semester 5\\CIS355 - Dbase Using Java");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = chooser.showOpenDialog(null);
        
        switch(result){
            case JFileChooser.APPROVE_OPTION -> System.out.println("Approve");
            case JFileChooser.CANCEL_OPTION -> System.out.println("Cancel");
            case JFileChooser.ERROR_OPTION -> System.out.println("Error");
        }
        
        lastPath = chooser.getCurrentDirectory();
        
        JFileChooser chooserTwo = new JFileChooser(lastPath);
        chooserTwo.showSaveDialog(null);
        
        System.out.println(chooserTwo.getSelectedFile());
    }
}
