import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;

public class EntranceForm extends JFrame {
    private JRadioButton Manager;
    private JRadioButton Customer;
    private JTextField UsernameField;
    private JPasswordField PasswordField;
    private JButton signUpButton;
    private JButton logInButton;
    public JPanel panel;


    public EntranceForm() {
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((UsernameField.getText().isEmpty()) || (PasswordField.getPassword().length == 0)) {
                    JOptionPane.showMessageDialog(null, " Please fill empty blanks",
                            "WARNING", JOptionPane.WARNING_MESSAGE);


                } else {
                    boolean isManager = true;

                    if (Manager.isSelected()) isManager = true;
                    if (Customer.isSelected()) isManager = false;


                    Requesthandler.SignUp_check(UsernameField.getText(), String.valueOf(PasswordField.getPassword()), isManager);
                    JOptionPane.showMessageDialog(null, "Register is done successfully",
                            "WARNING", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((UsernameField.getText().isEmpty()) || (PasswordField.getPassword().length == 0)) {
                    JOptionPane.showMessageDialog(null, " Please fill empty blanks",
                            "WARNING", JOptionPane.WARNING_MESSAGE);
                } else {
                    boolean isManager = true;

                    if (Manager.isSelected()) isManager = true;
                    if (Customer.isSelected()) isManager = false;

                    Requesthandler.LogIn_check(UsernameField.getText(), String.valueOf(PasswordField.getPassword()), isManager);
                    JOptionPane.showMessageDialog(null, "You are logged in",
                            "WARNING", JOptionPane.WARNING_MESSAGE);
                }

            }
        });
    }
}



