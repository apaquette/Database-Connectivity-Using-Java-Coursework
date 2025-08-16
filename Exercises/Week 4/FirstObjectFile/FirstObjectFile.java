
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author alexa
 */
public class FirstObjectFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Person alex = new Person("Alex", 25, Color.BLUE);
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("people.dat"))){
            out.writeObject(alex);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        Person someone = null;
        
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("people.dat"))){
            someone = (Person)in.readObject();
            
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        
        System.out.println(someone);
    }
    
}

class Person implements Serializable{
    private String name;
    private int age;
    private Color favouriteColor;
    
    Person(String name, int age, Color fave){
        this.name = name;
        this.age = age;
        this.favouriteColor = fave;
    }
    
    @Override
    public String toString(){
        return String.format("%s%nage: %d%nColour %s%n", name, age, favouriteColor);
    }
}