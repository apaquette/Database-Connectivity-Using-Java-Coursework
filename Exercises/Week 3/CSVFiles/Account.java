package csvfiles;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author 114490
 */
public class Account implements Serializable{
    
    private int acctNumber;
    private String firstName;
    private String lastName;
    private double balance;

    public Account(int num, String fname, String lname, double bal) {
        acctNumber = num;
        firstName = fname;
        lastName = lname;
        balance = bal;
    }
    
    public int getAcctNumber() {
        return acctNumber;
    }

    public void setAcctNumber(int acctNumber) {
        this.acctNumber = acctNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    
    /**
     * a list of first names
     */
    public static final String FIRST_NAMES[] = { "Jevin", "Kallan", "Bob", "Joe",
        "James", "Steve", "Melinda", "Mary", "Kaitlyn", "Devin", "Callista",
        "Kali", "Abby", "Rachel", "Lauren", "Carli", "Isobelle", "Alex"
    };
    
    /**
     * a list of last names
     */
    public static final String LAST_NAMES[] = { "Johnstone","Jones","Hill",
        "Oullette", "Madden", "St. Onge", "Truchon", "McLaren", "Flynn",
        "Luckasavitch", "Mikyktowych", "Smith", "Coleman", "Milne", "Bennet"
    };
    
    /**
     * a random number generator
     */
    public static Random rand = new Random();
    
    /**
     * Generate a single random account
     * @return a new random values account
     */
    public static Account generateRandomAccount(){
        Account acct = new Account(
                rand.nextInt(1000),
                FIRST_NAMES[rand.nextInt(FIRST_NAMES.length)],
                LAST_NAMES[rand.nextInt(LAST_NAMES.length)],
                rand.nextDouble() * 5000
        );
        
        return acct;
    }
    
}
