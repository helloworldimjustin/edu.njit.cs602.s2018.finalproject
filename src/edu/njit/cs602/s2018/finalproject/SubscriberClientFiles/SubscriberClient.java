package edu.njit.cs602.s2018.finalproject.SubscriberClientFiles;
/**
 * Created by Prajwal Sankar
 **/

import edu.njit.cs602.s2018.finalproject.SubscriberClientFiles.JavaGUI;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
        
    /*
     * Validate the user if already present, make connection
     * use getServerStatus method to check the server status and display on GUI
     * @param: String name of user to be validated
     * */
	public void validateUser(String name) {
				String passedname=name;
                try {
                	DataOutputStream OutStream= new DataOutputStream(socket.getOutputStream());
                	OutStream.writeUTF(passedname);
        			DataInputStream InStream =new DataInputStream(socket.getInputStream());
        			String line;
                    while ((line = InStream.readUTF()) != null)
                    	if(line.contains("user1")&& socket.isConnected())
                    	{
                    		System.out.println("User is already connected");
                       	}
                 	 }
                catch (IOException ex)
                {ex.printStackTrace();}
	}
	
	
     /*
     * Use getAllTopics method to get all topics subscribed and call in GUI
     * 
     * */
	public String getAllTopics()
	{
		ArrayList<String> topicsAvailable = new ArrayList<String>();
		try {
			DataOutputStream OutStream= new DataOutputStream(socket.getOutputStream());
			OutStream.writeUTF("TOPICS"+username);
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
			OutStream.writeUTF("SUBSCRIPTIONS");
			DataInputStream InStream =new DataInputStream(socket.getInputStream());
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
		String ntp= newtopic;
		String result = null;
		try {
			DataOutputStream OutStream= new DataOutputStream(socket.getOutputStream());
			DataInputStream InStream =new DataInputStream(socket.getInputStream());
            while (socket.isConnected())
           	 {
            	OutStream.writeUTF("SUBSCRIBE"+ntp);
            	result= InStream.readUTF();           	 }
        } catch (IOException ex) 
		 {ex.printStackTrace();}
		return result;
	}
	
	
	/*
     * use UnsubScribe method to subscribe the topic and call in GUI
     * @param: String topic to be Unsubscribed
     * */	
	public String UnSubscribe(String unsubTopic)
	{
		String UnsubscribedTopic =unsubTopic;
		String result = null;		
		try {
			DataOutputStream OutStream= new DataOutputStream(socket.getOutputStream());
			DataInputStream InStream =new DataInputStream(socket.getInputStream());
            while (socket.isConnected())
           	 {
            	OutStream.writeUTF("UNSUBSCRIBE"+UnsubscribedTopic);
            	result= InStream.readUTF();
           	 }
        } catch (IOException ex) 
		 {ex.printStackTrace();}
		return result;
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
         String server =args[0];
         int port =5354;
         Scanner sc = new Scanner(System.in);
         System.out.println("Please enter the user name :");
         String user = sc.nextLine();
         Thread t1 =new Thread(new JavaGUI(user, server, port));
         t1.start();
         }
	}
