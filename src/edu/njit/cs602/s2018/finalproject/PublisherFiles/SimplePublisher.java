package edu.njit.cs602.s2018.finalproject.PublisherFiles;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


/**
 * Created by Ravi Varadarajan on 4/8/2018.
 * Modified by Justin Bullock on 5/2/2018
 */
public class SimplePublisher {


    private final Socket cs;
    private final DataInputStream dis;
    private final DataOutputStream dos;

    public SimplePublisher(String serverHost, int serverPort) throws IOException {
        this.cs = new Socket(serverHost, serverPort);
        dis = new DataInputStream(cs.getInputStream());
        dos = new DataOutputStream(cs.getOutputStream());
        System.out.println("Publisher connected to server " + serverHost+":" + serverPort);
    }

    public void send(String topic, String message) throws IOException {

        PrintStream ps = new PrintStream(cs.getOutputStream());
        ps.println(topic);
        ps.println(message);
        ps.flush();
    }

    public static void main(String [] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            SimplePublisher publisher = new SimplePublisher(args[0], Integer.parseInt(args[1]));
            for (; ;) {
                System.out.print("Topic:");
                String topic = scanner.nextLine();
                System.out.print("Msg:");
                String msg = scanner.nextLine();
                publisher.send(topic, msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
