
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author alexa
 */
public class FirstBinaryFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try(DataOutputStream out = new DataOutputStream(new FileOutputStream(new File("AlexFile.dat")))){
            out.writeInt(42);
            out.writeDouble(101.1);
            out.writeUTF("Alex was here.");
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        try(DataInputStream in = new DataInputStream(new FileInputStream(new File("AlexFile.dat")))){
            System.out.printf("%d, %f, %s%n", in.readInt(), in.readDouble(), in.readUTF());
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
}
