import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class RequestHandeling extends Thread {
    static String result;

    Scanner input;
    Formatter output;
    Socket socket;
    static ArrayList<String> resnames = new ArrayList<String>();
    static ArrayList<String> appname = new ArrayList<String>();
    static String restauransname;
    static String managername;

    public RequestHandeling(Socket socket) {

        this.socket = socket;
        try {
            input = new Scanner(socket.getInputStream());
            output = new Formatter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                if (input.hasNextLine()) {
                    // String restauransname= null;
                    String str;
                    String message = input.nextLine();
                    System.out.println(message);
                    if (message.contains("SignUp")) {
                        System.out.println("hi");
                        result = message.substring(7);
                        System.out.println(result);
                        Main.WriteInFile();
                    } else if (message.contains("LogIn") && (message.contains("-M"))) {
                        str = message.substring(6, message.length());
                        System.out.println(str);
                        File Input = new File("E:\\lessons\\java\\Server\\Information.txt.txt");
                        Scanner sr = new Scanner(Input);
                        boolean find1 = false;
                        while (sr.hasNextLine()) {
                            String m = sr.nextLine().trim();
                            if (m.equalsIgnoreCase(str)) {
                                System.out.println("tu file bud");
                                find1 = true;
                                String[] arrOfStr = str.split("-");
                                managername = arrOfStr[0];
                                System.out.println(arrOfStr[0]);
                                System.out.println("manager name " + managername);
                                File DataFile = new File("E:\\lessons\\java\\Server\\ManagerData");
                                Scanner scr = new Scanner(DataFile);
                                boolean find = false;
                                while (scr.hasNextLine()) {
                                    String s = scr.nextLine().trim();

                                    String[] partdatalist = s.split("-", 2);
                                    restauransname = partdatalist[1];
                                    System.out.println("esme resturan :" + restauransname);
                                    System.out.println(partdatalist[0] + arrOfStr[0]);
                                    if (partdatalist[0].equalsIgnoreCase(arrOfStr[0])) {
                                        find = true;
                                        System.out.println("true");
                                        File file = new File("E:\\lessons\\java\\Server\\ApplyFile");
                                        Scanner scannerr = new Scanner(file);
                                        while (scannerr.hasNextLine()) {
                                            String line = scannerr.nextLine();
                                            System.out.println(line);
                                            String[] a = line.split("#", 3);
                                            if (a[0].equalsIgnoreCase(managername)) {
                                                System.out.println("a1:" + a[1]);
                                                System.out.println("a2:" + a[2]);
                                                String p = "List " + a[1] + "]" + a[2];
                                                output.format(p).format("\n").flush();


                                                BufferedWriter writer = null;
                                                try {
                                                    writer = new BufferedWriter(new FileWriter("RestaurantsName", true));
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                                try {
                                                    writer.append(' ');
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                                try {
                                                    writer.append("\r\n" + a[1]);
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }

                                                try {
                                                    writer.close();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }


                                            }

                                        }

                                    }


                                }
                                if (!find) {
                                    System.out.println("tu liste rest sakhte shodeha nabud");
                                    output.format("Manager needs new restaurant form").format("\n").flush();

                                }

                            }

                        }
                        if (!find1) {

                            System.out.println("your name was not in the list");

                        }
                    }
                    if (message.contains("Restaurantsname-")) {
                        restauransname = message.substring(16);
                        System.out.println(restauransname);
                        BufferedWriter writer = null;
                        try {
                            writer = new BufferedWriter(new FileWriter("ManagerData", true));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            writer.append(' ');
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            writer.append("\r\n" + managername + "-" + restauransname);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        try {
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    } else if (message.contains("LogIn") && (message.contains("-C"))) {

                        str = message.substring(6);
                        System.out.println(str);

                        File Inputfile = new File("E:\\lessons\\java\\Server\\Information.txt.txt");
                        Scanner scanner = new Scanner(Inputfile);
                        boolean check = false;
                        while (scanner.hasNextLine()) {
                            String data = scanner.nextLine().trim();

                            if (data.equalsIgnoreCase(str)) {

                                check = true;

                                System.out.println("logged in");


                                System.out.println(String.valueOf(resnames));



                                File f= new File("E:\\lessons\\java\\Server\\ApplyFile");
                                Scanner sca = new Scanner(f);
                                while (sca.hasNextLine()) {
                                    String q = sca.nextLine();
                                    String[] splitapply = q.split("#",3);


                                    System.out.println(splitapply[0] + " G " + splitapply[1] + " G" + splitapply[2]);

                                   appname.add(splitapply[1] +"}"+splitapply[2]);

                                }
                                System.out.println(appname);
                                output.format("Customer has logged in " + appname).format("\n").flush();


                                break;
                            }

                        }
                        if (!check) {
                            System.out.println("you are not logged in");
                        }
                    }

                    if (message.contains("Food:")) {

                        String newstr = message.substring(4);
                        String sub = newstr.substring(2, newstr.length() - 1);
                        String newnew = managername + "#" + restauransname + "#" + sub;
                        System.out.println(newnew);

                        BufferedWriter writer = null;
                        try {
                            writer = new BufferedWriter(new FileWriter("ApplyFile", true));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            writer.append(' ');
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            writer.append("\r\n" + newnew+">");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        try {
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }


}
