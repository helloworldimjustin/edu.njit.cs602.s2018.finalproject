package edu.njit.cs602.s2018.finalproject;
import java.awt.ItemSelectable;
import java.awt.List;
/**
 * Created by Prajwal Sankar
 * Note: Return types, Parameter types and Data Structures may not be final
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class JavaGUI extends JFrame implements Runnable{
	public String servername;
	public int portnumber;
	public String username;
	    public JavaGUI(String user, String serv, int port) {
	    	this.username=user;
	    	this.servername=serv;
	    	this.portnumber=port;
	    	//Start(user);
	    }
	    public JavaGUI(){}
	    public void run() 
	    {
		      Start(this.username);
	    }
	    
	    public String addItems(String items) 
	    {
    		StringBuilder ArrayOfItems = new StringBuilder();
    		ArrayOfItems.append(items);
	    	return ArrayOfItems.toString();
	    }
	   synchronized public final void Start(String name) {
	    	JavaGUI newobj=new SubscriberClient();
	    	String serv1=this.servername;
	    	int portnum = this.portnumber;
	        JLabel l1,l2,l3,l5,l4 = null;  
	        JTextField t1,t2,t3;  
	        JComboBox<String> comboBox = new JComboBox<>(new String[] {"Your Subscriptions"});
	        JFrame jf= new JFrame();
	        String PublishersMessage;
	        JPanel panel = new JPanel();
	        getContentPane().add(panel);
	        panel.setLayout(null);

	        //Label to display user name
	        l1=new JLabel("UserName");  
	        l1.setBounds(0,0,70,30);
	        panel.add(l1);  
	        panel.setSize(900,900);  
	        panel.setLayout(null);  
	        panel.setVisible(true);

	        //Label to enter user id
	        t1=new JTextField(name);  
	        t1.setBounds(80,0,100,30);  
	        panel.add(t1);  
	        panel.setVisible(true); 
	        
	        //Label to display to server status
	        l2=new JLabel("Server Up");
	        JButton ConnectButton, SubscribeButton, UnsubscribeButton, ExitButton;
	        
	        //Label to display server status
	        try {
				if(((SubscriberClient) newobj).getServerStatus(serv1, portnum).contains("up"))
					{l2.setText("Server Status: up");
			        panel.setVisible(true);
					}
				else l2.setText("Server Status: down");
			} catch (IOException e) {
				e.printStackTrace();
			}
			l2.setBounds(350,0,100,30);
	        panel.add(l2);  
	        panel.setSize(700,700);  
	        panel.setLayout(null);  
	        
	        l5=new JLabel("");  
	        l5.setBounds(500,0,85,30);
	        panel.add(l5);  
	        panel.setSize(900,900);  
	        panel.setLayout(null);  
	        panel.setVisible(true);
	        
	        //Dropdown to select available topics
	        List newtopics = null;
	        String topics[]={"Select Topic","Sports","Economics","Politics","Business","Social"};        
	        JComboBox cb=new JComboBox(topics);    
	        cb.setBounds(80,50,100,20);    
	        panel.add(cb);        
	        panel.setSize(400,500);    
	        panel.setVisible(true);
	        /*cb.addActionListener(new ActionListener() 
		    {
		   	//To get topics from the server and display them 
		    public void actionPerformed(ActionEvent event) 
	        {
		    	String allTopics=((SubscriberClient) newobj).getAllTopics();
		    	comboBox.addItem(allTopics);
	        }
		    });*/     
	        
	        //Subscribe button
	        SubscribeButton =new JButton("Subscribe");
	        SubscribeButton.setBounds(200, 50, 100, 30);
	        SubscribeButton.addActionListener(new ActionListener() 
		    {
	        	//To Subscribe from a selected topic and get the changed list
		            public void actionPerformed(ActionEvent event) 
		           {
		            	String item =(String) cb.getSelectedItem();
		             	((SubscriberClient) newobj).subScribeTopic(item);
		             	String allSubscribed = ((SubscriberClient) newobj).getSubscribedTopics();
		             	comboBox.addItem(allSubscribed);
		             	l5.setText("Subscribed");
		           }
		        });
	            
	            //Unsubscribe button
	            UnsubscribeButton =new JButton("Unsubscribe");
	            UnsubscribeButton.setBounds(320, 50, 150, 30);
	            UnsubscribeButton.addActionListener(new ActionListener() 
		        {
		        	//To unsubscribe from a selected topic and get the changed list
		            public void actionPerformed(ActionEvent event) 
		            {
		            	String item =(String) cb.getSelectedItem();
		             	((SubscriberClient) newobj).UnSubscribe(item);
		             	String AvailableTopics=((SubscriberClient) newobj).getAllTopics();
		             	comboBox.addItem(AvailableTopics);
		             	l5.setText("Unsubscribed");
		           }
		        });
	            
		        //Dropdown to view subscribed topics
	           
	            comboBox.setBounds(500,50,150,20);    
	            panel.add(comboBox);        
	            panel.setSize(400,500);    
	            panel.setVisible(true);
	            
	            //Label to display action message
	            l3=new JLabel("Publisher Message");  
		        l3.setBounds(0,85,300,30);
		        panel.add(l3);  
		        panel.setSize(700,700);  
		        panel.setLayout(null);  
		        panel.setVisible(true);
		        
		        //Text field to display action message
		        t2=new JTextField("");
		        t2.setBounds(0, 110, 400, 40);
		        t2.addActionListener(new ActionListener() 
		        {
		        	//To add connection method for server connection
		            public void actionPerformed(ActionEvent event) 
		           {
		             	String PublishersMessage=((SubscriberClient) newobj).PublisherMsgDisplay();
		             	t2.setText(PublishersMessage);
		           }
		        });
		        panel.add(t2);
		        panel.setSize(700,700);  
		        panel.setLayout(null);  
		        panel.setVisible(true);
		        
		        //label to display publisher message
		        l4=new JLabel("Action Message");  
		        l4.setBounds(0,120,80,30);
		        panel.add(l4);  
		        panel.setSize(100,100);  
		        panel.setLayout(null);  
		        panel.setVisible(true);

		        
		        /*//Text field to display Publisher message
		        JTextField t4=new JTextField("Publish");
		        t4.setBounds(5,130,200,30);
		        panel.add(t4);
		        panel.setSize(100,100);  
		        panel.setLayout(null);  
		        panel.setVisible(true);*/
		        
		        
		        jf.add(l1);
		        jf.add(t1);
		        //jf.add(ConnectButton);
		        jf.add(l2);
		        jf.add(l5);
		        jf.add(cb);
		        jf.add(SubscribeButton);
		        jf.add(UnsubscribeButton);
		        jf.add(comboBox);
		        jf.add(l3);
		        jf.add(t2);
		        jf.add(l4);
		        //jf.add(t4);
		        jf.setTitle("Subscription Window");
		        jf.setSize(700,600);
		        jf.setVisible(true);
	     }
	}

