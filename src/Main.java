import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
//Server

public class Main {
public static void WriteInFile() {
    BufferedWriter writer = null;
    try {
        writer = new BufferedWriter(new FileWriter("Information.txt.txt", true));
    } catch (IOException e) {
        e.printStackTrace();
    }
    try {
        writer.append(' ');
    } catch (IOException e) {
        e.printStackTrace();
    }
    try {
        writer.append("\r\n" + RequestHandeling.result);
    } catch (IOException e) {
        e.printStackTrace();
    }

    try {
        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
    }


}


    public static void main(String[] args)  {
        System.out.println("server started");
        ServerSocket serversocket = null;
        try {
            serversocket = new ServerSocket(8888);
            while (true) {
                Socket socket = serversocket.accept();
              RequestHandeling rq = new RequestHandeling(socket);
              rq.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
     finally {
            try {
                serversocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


