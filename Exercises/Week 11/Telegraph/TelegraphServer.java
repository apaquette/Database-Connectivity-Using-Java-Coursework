
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JOptionPane;

/**
 *
 * @author alexa
 */
public class TelegraphServer implements Runnable {
    private static Socket conxn;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private static String clientMsg = "";
    private static String serverMsg = "";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int PORT = 14312;
        
        ExecutorService exec = Executors.newCachedThreadPool();
        
        try{
            System.out.printf("Server running - waiting for connection%n");
            
            ServerSocket server = new ServerSocket(PORT);
            conxn = server.accept(); //waits for client to connect
            
            InetAddress addr = conxn.getInetAddress();  //get internet address object
            System.out.printf("%s connected from %s%n", addr.getHostName(), addr.getAddress());
            
            //Output stream connects to input of other end
            //alwasy flush after creation
            out = new ObjectOutputStream(conxn.getOutputStream());
            out.flush();    //causes empty message to other end
            
            //Input stream connects to ouput of other end
            in = new ObjectInputStream(conxn.getInputStream());
            
            exec.submit(new TelegraphServer());
            
            Scanner scanner = new Scanner(System.in);
            while(!(clientMsg.equals("!terminate") || serverMsg.equals("!terminate"))){
                serverMsg = scanner.nextLine();
                out.writeUTF(serverMsg);
                out.flush();
            }
        }catch(IOException ioe){
            Logger.getLogger(TelegraphServer.class.getName()).log(Level.SEVERE, null, ioe);
        }
        System.out.println("Exit Server Writer");
    }
    
    @Override
    public void run(){
        try{
            while(!(clientMsg.equals("!terminate") || serverMsg.equals("!terminate"))){
                clientMsg = in.readUTF();
                JOptionPane.showMessageDialog(null, clientMsg, "Server", JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(IOException ioe){
            Logger.getLogger(TelegraphServer.class.getName()).log(Level.SEVERE, null, ioe);
        }
        System.out.println("Exit Server Reader");
    }
}
