package edu.njit.cs602.s2018.finalproject;
/**
 * Created by Prajwal Sankar
 */
import edu.njit.cs602.s2018.finalproject.JavaGUI;
import edu.njit.cs602.s2018.finalproject.SubUnsubTopics;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class SubscriberClient extends JavaGUI {
	private String username;
    private Socket socket;
    private OutputStream output;
    private InputStream input;
    private String server;
    private int port;
    private Thread t;
    
    
    //Constructor
    public SubscriberClient(String name, String server, int port)
    {
    	super(name,server,port);
    	this.username=name;
    	this.server=server;
    	this.port=port;
    }
    
	public SubscriberClient() {
		// TODO Auto-generated constructor stub
	}
	/*public void run(){
    	Start(username);
    }*/
	
	//Check whether server is up/down and display the status
    public String getServerStatus(String server, int port) throws IOException {
        socket = new Socket(server, port);
        output = socket.getOutputStream();
        if(socket.isConnected()) 
        {
        	return "up";
        }
        else return "down";
    }
	//Validate the user if already present, make connection
	public void validateUser(String name) {
				String passedname=name;
                try {
                	BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(output, name));
                	 PrintWriter out =
                		        new PrintWriter(socket.getOutputStream(), true);
                    String line;
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
                    while ((line = reader.readLine()) != null)
                    	if(line.contains("user1"))
                    	{
                    		System.out.println("User is valid");
                    	}
                } catch (IOException ex)
                {ex.printStackTrace();}
	}

	//If server is up, user is new, get id from user and connect
	public void connServer(String id) {		
		 try {
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             String line;
             while ((line = reader.readLine()) != null)
            	 if(line.contains("user1")&& socket.isConnected())
            	 {
            		 System.out.println("The user is not registered. Please sign up");
            	 }
         } catch (IOException ex) 
		 {ex.printStackTrace();}
	}
	
	//Get the list of available topics
	public String getAllTopics(String topic)
	{
		String receievedStream;
		String PassedTopic=topic;
		ArrayList<String> topicsAvailable = new ArrayList<String>();
		try {
			BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(output, topic));
			PrintWriter out =
    		        new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            while ((receievedStream = reader.readLine()) != null)
           	 if(receievedStream.contains("user1")&& socket.isConnected())
           	 {
           		topicsAvailable.add(receievedStream);
           	 }
        } catch (IOException ex) 
		 {ex.printStackTrace();}
		return topicsAvailable.toString();
	}
	
	//Get the list of already subscribed topics
	public void getSubTopics(String topics)
	{
		ArrayList<String> Subscriptions = new ArrayList<String>();

		try {
			PrintWriter out =
    		        new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null)
           	 if(line.contains("user1")&& socket.isConnected())
           	 {
           		 Subscriptions.add(line);
           	 }
        } catch (IOException ex) 
		 {ex.printStackTrace();}
	}
	
	//Subscribe to a topic
	public String subScribeTopic(String newtopic)
	{
		int b=0;
		String ntp= newtopic;
		try {
			PrintWriter out =
    		        new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null)
           	 if(line.contains("user1")&& socket.isConnected())
           	 {
           		 
           	 }
        } catch (IOException ex) 
		 {ex.printStackTrace();}
		return "Successfully Subscribed";
	}
	//Unsubscribe from a topic
	public String UnSubscribe()
	{
		int b=1;
		try {
			PrintWriter out =
    		        new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null)
           	 if(line.contains("user1")&& socket.isConnected())
           	 {
           		 output.write(b);
           	 }
        } catch (IOException ex) 
		 {ex.printStackTrace();}
		return "Successfully Unsubscribed";
	}
	
	//Display Help Menu
	public List DisplayHelp()
	{
		List menu=null;
		int b=2;
		try {
			PrintWriter out =
    		        new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null)
           	 if(line.contains("user1")&& socket.isConnected())
           	 {
           		 output.write(b);
           		 reader = new BufferedReader(
                         new InputStreamReader(socket.getInputStream()));
           		 menu.add(reader.toString());
           	 }
        } catch (IOException ex) 
		 {ex.printStackTrace();}
		return menu;
	}
	
	//Waiting for other command and displaying the publishers message
	public void PublisherMsgDisplay()
	{
		
	}

    /** Create socket, and receiving thread */
	public static void main(String[] args) {
         String server = "127.0.0.1";
         //int port =5354;
		int port = 3000;
         Scanner sc = new Scanner(System.in);
         System.out.println("Please enter the user name :");
         String user = sc.nextLine();
         SubscriberClient sclient= new SubscriberClient(user, server, port);
     }
	}
