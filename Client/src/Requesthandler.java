import java.io.IOException;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;
import java.util.ArrayList;
public class Requesthandler {
    static LoginForm lf = new LoginForm();

    static Socket socket;
    public static ArrayList<Food> foods = new ArrayList<Food>();
    static ArrayList<Restaurant> restaurantsnames = new ArrayList<Restaurant>();
    static Scanner input;
    static Formatter formatter;

    static void init(Socket sc) {
        socket = sc;
        try {
            input = new Scanner(socket.getInputStream());
            formatter = new Formatter(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void SignUp_check(String username, String password, boolean isManager) {

        String data = "SignUp-" + username + "-" + password + "-" + (isManager ? "M" : "C");
        formatter.format(data).format("\n").flush();

    }


    static void LogIn_check(String username, String password, boolean isManager) {


        String data = "LogIn-" + username + "-" + password + "-" + (isManager ? "M" : "C");
        formatter.format(data).format("\n").flush();
        if (input.hasNextLine()) {
            String stri = input.nextLine();
            System.out.println(stri);
            if (stri.contains("needs") && (stri.contains("new"))) {

                System.out.println("new form");

                Client.eForm.setVisible(false);
                Client.eForm.dispose();

                lf.setSize(500, 500);
                lf.setVisible(true);
                lf.setLocationRelativeTo(null);
                lf.setContentPane(lf.panel1);


            }
            if (stri.contains("List ")) {

                String[] first = stri.split("]", 2);
                String purename = first[0].substring(5);
                System.out.println(purename);
                int Count = 0;
                System.out.println("has form already");

                String foood = first[1];
                for (int i = 0; i < foood.length(); i++) {

                    char a = ',';
                    if (foood.charAt(i) == ',') {

                        Count++;

                    }

                }
                String[] h = foood.split(",", Count + 1);

                for (int i = 0; i < Count + 1; i++) {
                    String[] m = h[i].split("/", 2);

                    String newprice = m[1].replace('$', ' ');
                    String newnewprice=newprice.replace('>',' ');
                    // System.out.println(newprice);
                    double k = Double.parseDouble(newnewprice);
                    foods.add(new Food(m[0], k));
                    System.out.println("final " + m[0] + " price " + k);

                }


                Client.eForm.setVisible(false);
                Client.eForm.dispose();


                ManagerForm menu = new ManagerForm();
                menu.setSize(700, 700);
                menu.setVisible(true);
                menu.setLocationRelativeTo(null);
                menu.setContentPane(menu.rpanel);


            }

            if (stri.contains("Customer has logged in")) {
                int index = 0;
                System.out.println("log in az sever daryaft shod");
                String newAr = stri.replace("Customer has logged in", "");
                String newAr2 = newAr.substring(2, newAr.length() - 1);
                System.out.println("newnew " + newAr2);
                int Count = 0;
                for (int r = 0; r < newAr2.length(); r++) {

                    char a = '>';
                    if (newAr2.charAt(r) == '>') {

                        Count++;

                    }


                }
                System.out.println(Count);


                String[] firstSp = newAr2.split(">", Count + 1);
                for (String a : firstSp) {
                    System.out.println(a);
                }


                for (int d = 0; d < Count ; d++) {
                    String[] nm = firstSp[d].split("}", 2);
                    String newnm1=null;
                        for(int g=0;g<nm[0].length();g++){

                            newnm1 = nm[0].replace(",", "");

                        }
                    System.out.println("first "+newnm1);
                    System.out.println("second" +nm[1]);
                      Restaurant res = new Restaurant(newnm1,nm[1]);
                       restaurantsnames.add(res);


                    }
                    Client.eForm.setVisible(false);
                    Client.eForm.dispose();
                    CustomerForm cf = new CustomerForm();
                    cf.setSize(700, 700);
                    cf.setVisible(true);
                    cf.setLocationRelativeTo(null);
                    cf.setContentPane(cf.restnamepanel);

                }


            }



        }


    static void sendresname (String resname) {
        formatter.format("Restaurantsname-" + resname).format("\n").flush();
        lf.setVisible(false);
        lf.dispose();
        ManagerForm menu = new ManagerForm();
        menu.setSize(700, 700);
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
        menu.setContentPane(menu.rpanel);


    }


    }



