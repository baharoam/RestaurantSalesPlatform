import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

class Restaurant {

    public Restaurant(String name, String menu) {
        this.name = name;
        this.menu = menu;
    }

    String name;
    String menu;

    public String toString() {
        return name;

    }

}

public class CustomerForm extends JFrame {
    DefaultListModel model = new DefaultListModel();
    public JPanel restnamepanel;
    public JList RestList;
    private JButton ShoppingList;
    private JButton restaurantsListButton;
    static ArrayList<Food> foodssnames = new ArrayList<Food>();
    static ArrayList<String> OrderList = new ArrayList<String>();
    boolean inrestmenu = true;
    int index = 0;
    String nameofres = null;

    public void call() {

        if (Requesthandler.restaurantsnames != null) {
            // System.out.println("null nabud");

            for (Restaurant res : Requesthandler.restaurantsnames) {
                // System.out.println(res);
                model.addElement(res);

            }

        }
    }

    public CustomerForm() {

        RestList.setModel(model);
        call();

        RestList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {


                if (e.getClickCount() == 2) {

                    if (inrestmenu == true) {
                        index = RestList.getSelectedIndex();
                        model.getElementAt(index);
                        System.out.println(model.getElementAt(index));


                        Restaurant rest = (Restaurant) model.getElementAt(index);
                        nameofres = rest.name;
                        System.out.println(rest.menu);

                        int Count = 0;
                        for (int r = 0; r < rest.menu.length(); r++) {

                            char a = ',';
                            if (rest.menu.charAt(r) == ',') {

                                Count++;

                            }


                        }
                        System.out.println(Count);


                        String[] firstSp = rest.menu.split(",", Count + 1);
                        for (int w = 0; w < Count + 1; w++) {

                            String secondSp[] = firstSp[w].split("/", 2);
                            String newprice = secondSp[1].replace('$', ' ');
                            double s = Double.parseDouble(newprice);
                            Food food = new Food(secondSp[0], s);
                            foodssnames.add(food);

                        }
                        System.out.println(foodssnames);

                        model.clear();

                        for (Food f : foodssnames) {

                            model.addElement(f);
                        }

                        inrestmenu = false;
                    }
                    else if (inrestmenu == false) {

                        int ind = RestList.getSelectedIndex();
                        if (ind >= 0) {
                            model.getElementAt(ind);
                            Food f = (Food) model.getElementAt(ind);
                            System.out.println("hello " + f);
                            SpinnerNumberModel smodel = new SpinnerNumberModel(1, 1, 50, 1);
                            JSpinner spinner = new JSpinner(smodel);
                            int option = JOptionPane.showOptionDialog(null, spinner, "Count", JOptionPane.OK_CANCEL_OPTION,
                                    JOptionPane.QUESTION_MESSAGE, null, null, null);

                            if (option == JOptionPane.OK_OPTION) {
                                int n = Integer.parseInt(spinner.getValue().toString());
                                System.out.println("option : " + n);
                                System.out.println("name " + model.getElementAt(index));
                                OrderList.add("Restaurant : " + nameofres + " Food : " +
                                        ((Food) model.getElementAt(ind)).name + "  Number : " + n
                                        + " Total Price : " + (n * ((Food) model.getElementAt(ind)).price) + "\n");

                                System.out.println(OrderList);
                            }


                        }


                    }


                }
                super.mouseClicked(e);
            }
        });


        ShoppingList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ShoppingListForm sh = new ShoppingListForm();
                sh.setSize(700, 700);
                sh.setVisible(true);
                sh.setLocationRelativeTo(null);
                sh.setContentPane(sh.panel1);


            }
        });
        restaurantsListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                model.clear();

                call();
            }
        });
    }


}
