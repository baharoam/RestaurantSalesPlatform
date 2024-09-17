import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginForm extends JFrame {
    public JPanel panel1;
    private JTextField pleaseEnterTheNameTextField;
    private JTextField Restaunrantsname;
    private JButton nextButton;

    public LoginForm() {


        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Requesthandler.sendresname(Restaunrantsname.getText());
            }
        });
    }
}