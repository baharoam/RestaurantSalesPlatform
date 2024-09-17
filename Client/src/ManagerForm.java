import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

class Food{

    public Food(String name, double price) {
        this.name = name;
        this.price = price;
    }

    String name;
     double price;
public String toString(){
 return name + "/"  + price + "$";

}


}

public class ManagerForm extends JFrame {
    DefaultListModel listModel = new DefaultListModel();
    SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(1,1,100,1);
    public JPanel rpanel;
    private JList Foodslist;
    private JButton ADDButton;
    private JButton REMOVEButton;
    private JButton UPDATEButton;
    private JButton APPLYSENDButton;
    private JSpinner spinner1;
    private JTextField textField1;
    static ArrayList<String> SendingList = new ArrayList<>();

    public ManagerForm(){
 Foodslist.setModel(listModel);
        if(Requesthandler.foods!= null)
        {

            for(Food food : Requesthandler.foods) {
                listModel.addElement(food);
            }
        }


        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


         String nameoffood = textField1.getText();
            double priceiffodd= Double.parseDouble(spinner1.getValue().toString());

                for(int i=0;i<listModel.size();i++){


                    Food food = (Food) listModel.getElementAt(i);
                    if(food != null ) {

                        if (food.name.equalsIgnoreCase(nameoffood)) {
                            return;
                        }
                    }



                }

                Food food = new Food(nameoffood,priceiffodd);
        if(food!=null) {
    listModel.addElement(food);
}
            }
        });





        REMOVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              int i = Foodslist.getSelectedIndex();
              listModel.removeElementAt(i);
            }
        });
Foodslist.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {

        int i = Foodslist.getSelectedIndex();
        Food food = (Food)listModel.getElementAt(i);

        textField1.setText(food.name);
        spinner1.setValue(food.price);


        super.mouseClicked(e);
    }
});


        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int i = Foodslist.getSelectedIndex();
                String nameoffood = textField1.getText();
                double priceiffodd= Double.parseDouble(spinner1.getValue().toString());
                Food food = new Food(nameoffood,priceiffodd);
                listModel.setElementAt(food, i);

            }
        });


        APPLYSENDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i<Foodslist.getModel().getSize();i++){
                    System.out.println(Foodslist.getModel().getElementAt(i));
                    String f = String.valueOf(Foodslist.getModel().getElementAt(i));
                    String list = f.substring(7);
                    if(list!=null) {
                        SendingList.add(list);
                        System.out.println(SendingList);
                    }
                }
                System.out.println("List : "+SendingList );
                Requesthandler.formatter.format(String.valueOf("Food:"+SendingList)).format("\n").flush();


            }
        });
    }
}
