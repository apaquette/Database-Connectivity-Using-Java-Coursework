
import java.util.HashMap;

/**
 *
 * @author alexa
 */
public class DemoHashMap {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HashMap<Integer, String> myMap = new HashMap();
        
        Integer one = 1;
        Integer two = 2;
        Integer three = 3;
        
        myMap.put(one, "One");
        myMap.put(two, "Two");
        myMap.put(three, "Three");
        
        System.out.printf("%s%n", myMap.get(one));
        
        one = 1 + 1;
        
        System.out.printf("%s%n", myMap.containsKey(one));
        System.out.printf("%s%n", myMap.get(one));
    }
    
}
