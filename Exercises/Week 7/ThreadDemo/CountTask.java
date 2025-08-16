
import java.security.SecureRandom;

/**
 *
 * @author alexa
 */
public class CountTask implements Runnable{
    private SecureRandom generator = new SecureRandom();
    private int target = generator.nextInt(10_000);
    
    private String mesg;
    
    public CountTask(String mesg){
        this.mesg = mesg;
        System.out.printf("%s will count to %d%n", mesg, target);
    }

    @Override
    public void run() {
        for(int i = 0; i < target; i++){
            System.out.printf("%s::%d%n", mesg, i);
        }
    }
}
