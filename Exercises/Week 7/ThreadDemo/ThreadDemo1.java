/**
 *
 * @author alexa
 */
public class ThreadDemo1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CountTask task1 = new CountTask("Task 1");
        CountTask task2 = new CountTask("Task 2");
        CountTask task3 = new CountTask("Task 3");
        
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);
        
        thread1.start();
        thread2.start();
        thread3.start();
        
        System.out.println("Main method ending");
    }
}
