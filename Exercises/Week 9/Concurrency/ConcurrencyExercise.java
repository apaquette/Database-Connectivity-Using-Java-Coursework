
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.util.Formatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author alexa
 */
public class ConcurrencyExercise implements Runnable {
    private static Formatter myFile;
    private static final SecureRandom generator = new SecureRandom();
    private static boolean done = false;
    private final String name;
    
    public ConcurrencyExercise(String name){
        this.name = name;
    }

    @Override
    public void run() {
        int msgcount = 0;
        while(!done){
            writeFile("Alex was here!", name, msgcount);
            msgcount++;
            try {
                Thread.sleep(generator.nextLong(500));
            } catch (InterruptedException ex) {
                Logger.getLogger(ConcurrencyExercise.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        writeFile("Finished!!", name, msgcount);
    }
    
    /**
     * Starts the GUI
     */
    public static void GUI(){
        JFrame frame = new JFrame("Concurrency");
        JButton stopButton = new JButton("Stop Threads");
        stopButton.addActionListener((ActionEvent e) -> {
            done = true;
            frame.setVisible(false);
        });
        frame.getContentPane().add(stopButton, BorderLayout.CENTER);
        frame.setSize(475, 150);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Write a message to a log file
     * @param msg the message
     * @param name the name of this thread
     * @param count how many messages have been sent
     */
    private static /*synchronized*/ void writeFile(String msg, String name, int count) {
        synchronized(myFile){
            myFile.format("%s::", name);
            myFile.format("#%d::", count);
            myFile.format("%s::%n", msg);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GUI();  //start GUI

        try {
            myFile = new Formatter("ConcurrentText.txt");
        } catch (FileNotFoundException ex) {}
        
        ExecutorService exec = Executors.newCachedThreadPool();
        
        for(int i =0; i < 10; i++){
            exec.execute(new ConcurrencyExercise("task " + i));
        }
        
        exec.shutdown();
        try{
        exec.awaitTermination(5, TimeUnit.SECONDS);
            
        }catch(InterruptedException ex){
           Logger.getLogger(ConcurrencyExercise.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        myFile.close();
        System.exit(0);
    }
}
