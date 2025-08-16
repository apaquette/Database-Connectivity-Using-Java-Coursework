import javax.swing.JFrame;

/**
 * Payment Calculator
 * @author Alexandre Paquette
 * Date: 09-06-2022
 * Last Edit: 09-06-2022
 * Description
 */

public class PayCalc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CalcFrame app = new CalcFrame("Pay Calculator");
        app.setSize(300,250);
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
