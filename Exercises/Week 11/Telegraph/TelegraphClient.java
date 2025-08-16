
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author alexa
 */
public class TelegraphClient implements Runnable {
    private static String clientMsg = "";
    private static String serverMsg = "";
    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int PORT = 14312;
        //final String HOST = "localhost";
        final String HOST = "192.168.2.98";
        
        ExecutorService exec = Executors.newCachedThreadPool();
        
        try{
            Socket conxn = new Socket(HOST, PORT);
            
            //Output stream connects to input of other end
            //alwasy flush after creation
            out = new ObjectOutputStream(conxn.getOutputStream());
            out.flush();    //causes empty message to other end
            
            //Input stream connects to ouput of other end
            in = new ObjectInputStream(conxn.getInputStream());
            
            exec.submit(new TelegraphClient());
            
            Scanner scanner = new Scanner(System.in);
            while(!(clientMsg.equals("!terminate") || serverMsg.equals("!terminate"))){
                clientMsg = scanner.nextLine();
                out.writeUTF(clientMsg);
                out.flush();
            }
            
        }catch(IOException ioe){
            Logger.getLogger(TelegraphServer.class.getName()).log(Level.SEVERE, null, ioe);
        }
        System.out.println("Exit Client Writer");
    }
    @Override
    public void run(){
        try{
            while(! (clientMsg.equals("!terminate") || serverMsg.equals("!terminate"))){
                serverMsg = in.readUTF();
                JOptionPane.showMessageDialog(null, serverMsg, "Client", JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(IOException ioe){
            Logger.getLogger(TelegraphServer.class.getName()).log(Level.SEVERE, null, ioe);
        }
        System.out.println("Exit Client Reader");
    }
}
