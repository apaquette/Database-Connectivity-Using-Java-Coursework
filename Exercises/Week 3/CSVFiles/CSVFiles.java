package csvfiles;

import java.io.File;
import java.util.Formatter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author alexa
 */
public class CSVFiles {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //WRITE TO FILE
        Account account[] = new Account[100];
        File f = new File("C:\\Users\\alexa\\OneDrive - Canadore College\\Year 3\\Semester 5\\CIS355 - Dbase Using Java\\Week 3\\DemoJFileChooser\\accounts.csv");
        for (int i = 0; i < 100; i++) {
            account[i] = Account.generateRandomAccount();
        }
        try(Formatter out = new Formatter(f)){
            for(int x = 0; x != 100; x++){
                out.format("%d,%s,%s,%f\n", account[x].getAcctNumber(), account[x].getFirstName(), account[x].getLastName(), account[x].getBalance());
            }
        }catch(Exception e){
            //System.out.println("Error with formatter");
            e.printStackTrace();
        }
        
        Account accountList[] = new Account[100];
        
        //READ FROM FILE
        try(Scanner in = new Scanner(f)){
            for(int x = 0; in.hasNext(); x++){
                String acct = in.nextLine();
                StringTokenizer t = new StringTokenizer(acct,",");
                accountList[x] = new Account((Integer.parseInt(t.nextToken())), t.nextToken(), t.nextToken(), Double.parseDouble(t.nextToken()));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        for(int x = 0; x < 100; x++){
            System.out.printf("%d, %s, %s, %f\n", accountList[x].getAcctNumber(), accountList[x].getFirstName(), accountList[x].getLastName(), accountList[x].getBalance());
        }
    }
}
