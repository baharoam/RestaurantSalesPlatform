import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Formatter;

public class Client {
     public static EntranceForm eForm = new EntranceForm();
    public static void main(String[] args) {

        Socket socket = null;
        try
        {
            socket = new Socket("localhost", 8888);
            Requesthandler.init(socket);
        }
        catch (IOException e)
        {
            System.out.println("Server not found");
            System.exit(0);
        }



        try
        {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        eForm.setSize(700,700);
        eForm.setVisible(true);
        eForm.setLocationRelativeTo(null);
        eForm.setContentPane(eForm.panel);



    }
}
