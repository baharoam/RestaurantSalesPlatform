import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingListForm extends JFrame {
    DefaultListModel Model = new DefaultListModel();

    public JPanel panel1;
    private JList list1;
    private JScrollBar scrollBar1;
    private JButton RESTAURANTSLISTButton;

    public ShoppingListForm() {
        list1.setModel(Model);
          Model.addElement(CustomerForm.OrderList);




    }
}