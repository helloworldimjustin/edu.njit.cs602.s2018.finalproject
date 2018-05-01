package edu.njit.cs602.s2018.finalproject;
/**
 * Created by Prajwal Sankar
 */
import edu.njit.cs602.s2018.finalproject.JavaGUI;
import edu.njit.cs602.s2018.finalproject.SubUnsubTopics;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
    	//Super Constructor
    	super(name,server,port);
    	this.username=name;
    	this.server=server;
    	this.port=port;
    }
    
	public SubscriberClient() {
	}
	
	/*
     * use getServerStatus method to check the server status and display on GUI
     * @param: String server to be connected
     *  @param: Int port to be connected
     * */
    public String getServerStatus(String server, int port) throws IOException {
        socket = new Socket(server, port);
        output = socket.getOutputStream();
        if(socket.isConnected()) 
        {
        	return "up";
        }
        else return "down";
    }

    
    /*/----------------------Leave this part as of now-----------------------------------------------------------------------------------------------------
	/*Validate the user if already present, make connection
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
                    	if(line.contains("user1")&& socket.isConnected())
                    	{
                    		System.out.println("User is valid");
                    	}
                } catch (IOException ex)
                {ex.printStackTrace();}
	}

	/*If server is up, user is new, get id from user and connect
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
	}*/
//----------------------------------------------------------------------------------------------------------*/
    
    
    /*
     * Use getAllTopics method to get all topics subscribed and call in GUI
     * 
     * */
	public String getAllTopics()
	{
		String receievedStream;
		ArrayList<String> topicsAvailable = new ArrayList<String>();
		try {
			DataOutputStream OutStream= new DataOutputStream(socket.getOutputStream());
			DataInputStream InStream =new DataInputStream(socket.getInputStream());
			
            while (socket.isConnected())
           	 {
            	topicsAvailable.add(InStream.readUTF());
           	 }
        } catch (IOException ex) 
		 {ex.printStackTrace();}
		return topicsAvailable.toString();
	}
	
	
	/*
     * Use getSubscribedTopics method to get the subscribed topics and call in GUI
     * 
     * */
	public String getSubscribedTopics()
	{
			ArrayList<String> Subscriptions = new ArrayList<String>();

		try {
			DataOutputStream OutStream= new DataOutputStream(socket.getOutputStream());
			DataInputStream InStream =new DataInputStream(socket.getInputStream());
            String line;
            while (socket.isConnected())
           	 {
           		 Subscriptions.add(InStream.readUTF());
           	 }
        } catch (IOException ex) 
		 {ex.printStackTrace();}
		return Subscriptions.toString();
	}
	
	
	/*
     * use subScribeTopic method to subscribe the topic and display on GUI
     * @param: String topic to be subscribed
     * */
	public String subScribeTopic(String newtopic)
	{
		int b=0;//Use if server needs
		String ntp= newtopic;
		try {
			DataOutputStream OutStream= new DataOutputStream(socket.getOutputStream());
			DataInputStream InStream =new DataInputStream(socket.getInputStream());
            while (socket.isConnected())
           	 {
            	OutStream.writeUTF(ntp);
           	 }
        } catch (IOException ex) 
		 {ex.printStackTrace();}
		return "Successfully Subscribed";
	}
	
	
	/*
     * use UnsubScribe method to subscribe the topic and call in GUI
     * @param: String topic to be Unsubscribed
     * */	
	public String UnSubscribe(String unsubTopic)
	{
		String UnsubscribedTopic =unsubTopic;
		int b=1;
		try {
			DataOutputStream OutStream= new DataOutputStream(socket.getOutputStream());
			DataInputStream InStream =new DataInputStream(socket.getInputStream());
            while (socket.isConnected())
           	 {
            	OutStream.writeUTF(UnsubscribedTopic);
           	 }
        } catch (IOException ex) 
		 {ex.printStackTrace();}
		return "Successfully Unsubscribed";
	}
	
	
	/*
     * use DisplayHelp method to get the Help menu and call in GUI
     */
	public ArrayList<String> DisplayHelp()
	{
		ArrayList<String> HelpMenu = new ArrayList<String>();
		int b=2;
		try {
			DataOutputStream OutStream= new DataOutputStream(socket.getOutputStream());
			DataInputStream InStream =new DataInputStream(socket.getInputStream());
            String line;
            while (socket.isConnected())
           	 {
            	HelpMenu.add(InStream.readUTF());
           	 }
        } catch (IOException ex) 
		 {ex.printStackTrace();}
		return HelpMenu;
	}
	
	
	/*
     * use PublisherMsgDisplay method to get the Publishers Message and call in GUI
     */
	public String PublisherMsgDisplay()
	{
		byte b=0;
		String PublisherMsg = null;
		 try {
			 DataOutputStream OutStream= new DataOutputStream(socket.getOutputStream());
				DataInputStream InStream =new DataInputStream(socket.getInputStream());
             while(socket.isConnected())
             	{
             		 PublisherMsg= InStream.readUTF();
             	}
         } catch (IOException ex)
         {ex.printStackTrace();
	}
		 return PublisherMsg;
	}

	public static void main(String[] args) {
         String server = "127.0.0.1";
         int port =5354;
         Scanner sc = new Scanner(System.in);
         System.out.println("Please enter the user name :");
         String user = sc.nextLine();
         //SubscriberClient sclient= new SubscriberClient();
         Thread t1 =new Thread(new JavaGUI(user, server, port));
         t1.start();
         System.out.println("Please enter the user name :");
         String user2 = sc.nextLine();
         Thread t2=new Thread(new JavaGUI(user2, server, port));
         t2.start();
     }
	}
