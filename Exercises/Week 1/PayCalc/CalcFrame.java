import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Payment Calculator JFrame
 * @author Alexandre Paquette
 * Date: 09-06-2022
 * Last Edit: 09-06-2022
 * Description
 */
public class CalcFrame extends JFrame {
    //hours
    private JLabel label_hours = new JLabel("Hours: ");
    private JTextField textField_hours = new JTextField(10);
    private JPanel panel_hours = new JPanel(new FlowLayout(FlowLayout.LEFT));
    
    //wage
    private JLabel label_wage = new JLabel("Wage: ");
    private JTextField textField_wage = new JTextField(10);
    private JPanel panel_wage = new JPanel(new FlowLayout(FlowLayout.LEFT));
    
    //buttons
    private JButton button_calculate = new JButton("Calculate");
    private JButton button_clear = new JButton("Clear");
    private JPanel panel_buttons = new JPanel(new GridLayout(1,0));
    
    //pay
    private JLabel label_pay = new JLabel("Pay: ");
    private JTextField textField_pay = new JTextField(10);
    private JPanel panel_pay = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    
    //taxes
    private JLabel label_taxes = new JLabel("Taxes: ");
    private JTextField textField_taxes = new JTextField(10);
    private JPanel panel_taxes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    
    //total
    private JLabel label_total = new JLabel("Total: ");
    private JTextField textField_total = new JTextField(10);
    private JPanel panel_total = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    
    public CalcFrame(String title){
        super(title);
        
        JPanel panel_inputs = new JPanel(new GridLayout(0, 1));
        JPanel panel_results = new JPanel(new GridLayout(0, 1));
        
        panel_hours.add(label_hours);
        panel_hours.add(textField_hours);
        
        panel_wage.add(label_wage);
        panel_wage.add(textField_wage);
        
        panel_buttons.add(button_calculate);
        button_calculate.setEnabled(false);
        panel_buttons.add(button_clear);
        
        panel_pay.add(label_pay);
        panel_pay.add(textField_pay);
        textField_pay.setEditable(false);
        textField_pay.setText("$0.00");
        
        panel_taxes.add(label_taxes);
        panel_taxes.add(textField_taxes);
        textField_taxes.setEditable(false);
        textField_taxes.setText("$0.00");
        
        panel_total.add(label_total);
        panel_total.add(textField_total);
        textField_total.setEditable(false);
        textField_total.setText("$0.00");
        
        //Inputs Panel
        panel_inputs.add(panel_hours);
        panel_inputs.add(panel_wage);
        panel_inputs.add(panel_buttons);
        
        //Results Panel
        panel_results.add(panel_pay);
        panel_results.add(panel_taxes);
        panel_results.add(panel_total);
        
        this.getContentPane().add(panel_inputs, BorderLayout.CENTER);
        this.getContentPane().add(panel_results, BorderLayout.SOUTH);
        
        button_clear.addActionListener((ActionEvent e) -> {
            textField_hours.setText("");
            textField_wage.setText("");
            textField_pay.setText("$0.00");
            textField_taxes.setText("$0.00");
            textField_total.setText("$0.00");
            
            textField_hours.requestFocus();
            checkButton();
        });
        
        button_calculate.addActionListener(new Calculate());
        
        KeyAdapter inputListener = new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e){
                checkButton();
            }
        };
        
        textField_hours.addKeyListener(inputListener);
        textField_wage.addKeyListener(inputListener);
    }//constructor end

    private void checkButton() {
        if(! textField_hours.getText().isBlank() && ! textField_wage.getText().isBlank()){
            button_calculate.setEnabled(true);
        }else{
            button_calculate.setEnabled(false);
        }
    }
    private class Calculate implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            double hours = Double.parseDouble(textField_hours.getText());
            double wage = Double.parseDouble(textField_wage.getText());
            double pay;
            double taxes;
            double total;
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
            
            if(hours > 40){
                pay = 40 * wage + (hours - 40) * wage * 1.5;
            }else{
                pay = hours * wage;
            }
            
            taxes = 0.15 * pay;
            total = pay - taxes;
            
            textField_pay.setText(currencyFormatter.format(pay));
            textField_taxes.setText(currencyFormatter.format(taxes));
            textField_total.setText(currencyFormatter.format(total));
        }
    }
}
