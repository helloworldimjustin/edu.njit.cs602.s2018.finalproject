package edu.njit.cs602.s2018.finalproject;
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

public class JavaGUI extends JFrame{
	public String servername;
	public int portnumber;
	public String username;
	    public JavaGUI(String user, String serv, int port) {
	    	this.username=user;
	    	this.servername=serv;
	    	this.portnumber=port;
	      Start(user);
	    }
	    public JavaGUI(){}
	    public final void Start(String name) {
	    	JavaGUI newobj=new SubscriberClient();
	    	String serv1=this.servername;
	    	int portnum = this.portnumber;
	        JPanel panel = new JPanel();
	        getContentPane().add(panel);
	        panel.setLayout(null);

	        //Label to display user name
	        JLabel l1,l3,l4;  
	        l1=new JLabel("UserName");  
	        l1.setBounds(0,0,70,30);
	        panel.add(l1);  
	        panel.setSize(900,900);  
	        panel.setLayout(null);  
	        panel.setVisible(true);

	        //Label to enter user id
	        JTextField t1,t2,t3;  
	        t1=new JTextField(name);  
	        t1.setBounds(80,0,100,30);  
	        panel.add(t1);  
	        panel.setVisible(true); 
	        
	        //Button to connect to server
	        JLabel l2=new JLabel("Server Up");
	        JButton ConnectButton, SubscribeButton, UnsubscribeButton, ExitButton;
	        ConnectButton = new JButton("Connect");
	        ConnectButton.setBounds(200, 0, 100, 30);
	        /*ConnectButton.addActionListener(new ActionListener() 
	        {
	        	//To add connection method for server connection
	            public void actionPerformed(ActionEvent event) 
	            {
	            	try {
						String status =((SubscriberClient) newobj).getServerStatus(serv1,portnum);
						ConnectButton.setVisible(false);
						if(!ConnectButton.isEnabled() && status.contains("up"))
				        {l2.setText("Server up");
						l2.setVisible(true);}
						else l2.setText("Server Down");
				        l2.setBounds(350,0,100,30);
				        panel.add(l2);  
				        panel.setSize(700,700);  
				        panel.setLayout(null);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	           }
	        });*/
	        panel.add(ConnectButton);
	        
	        //Label to display server status
	        //l2=new JLabel("Server Up/Down");
	        try {
				if(((SubscriberClient) newobj).getServerStatus(serv1, portnum).contains("up"))
					{l2.setText("Server up");
			        panel.setVisible(true);
					}
				else l2.setText("Server Down");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			l2.setBounds(350,0,100,30);
	        panel.add(l2);  
	        panel.setSize(700,700);  
	        panel.setLayout(null);  	          
	        //Dropdown to select available topics
	        List newtopics = null;
	        String topics[]={"Select Topic","topic1","topic2","topic3","topic4","topic5"};        
	        JComboBox cb=new JComboBox(topics);    
	        cb.setBounds(80,50,100,20);    
	        panel.add(cb);        
	        panel.setSize(400,500);    
	        panel.setVisible(true);
	        cb.addActionListener(new ActionListener() 
		    {
		   	//To add connection method for server connection
		    public void actionPerformed(ActionEvent event) 
	        {
           	String item =(String) cb.getSelectedItem();
         	String AvailableTopics=((SubscriberClient) newobj).subScribeTopic(item);
		    }
		    });        
	        //Subscribe button
	        SubscribeButton =new JButton("Subscribe");
	        SubscribeButton.setBounds(200, 50, 100, 30);
	        SubscribeButton.addActionListener(new ActionListener() 
		    {
		        	//To add connection method for server connection
		            public void actionPerformed(ActionEvent event) 
		           {
		            	
		           }
		        });
	            
	            //Unsubscribe button
	            UnsubscribeButton =new JButton("Unsubscribe");
	            UnsubscribeButton.setBounds(320, 50, 150, 30);
	            UnsubscribeButton.addActionListener(new ActionListener() 
		        {
		        	//To add connection method for server connection
		            public void actionPerformed(ActionEvent event) 
		            {
		           }
		        });
	            
		        //Dropdown to view subscribed topics
	            String sublist[]={"Your Subscriptions","Sub1","Sub2","Sub3","Sub4","Sub5"};        
	            JComboBox cb1=new JComboBox(sublist);    
	            cb1.setBounds(500,50,150,20);    
	            panel.add(cb1);        
	            panel.setSize(400,500);    
	            panel.setVisible(true);
	            cb1.addActionListener(new ActionListener() 
		        {
		        	//To add connection method for server connection
		            public void actionPerformed(ActionEvent event) 
		           {
		            	
		           }
		        });
	            
	            //Label to display action message
	            l3=new JLabel("Action Message");  
		        l3.setBounds(0,85,100,30);
		        panel.add(l3);  
		        panel.setSize(700,700);  
		        panel.setLayout(null);  
		        panel.setVisible(true);
		        
		        //Text field to display action message
		        t2=new JTextField("");
		        t2.setBounds(0, 110, 400, 40);
		        panel.add(t2);
		        panel.setSize(700,700);  
		        panel.setLayout(null);  
		        panel.setVisible(true);
		        t2.addActionListener(new ActionListener() 
		        {
		        	//To add connection method for server connection
		            public void actionPerformed(ActionEvent event) 
		           {
		            	
		           }
		        });
		        
		        //label to display publisher message
		        l4=new JLabel("Publisher Message");  
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
		        
		        JFrame jf= new JFrame();
		        jf.add(l1);
		        jf.add(t1);
		        //jf.add(ConnectButton);
		        jf.add(l2);
		        jf.add(cb);
		        jf.add(SubscribeButton);
		        jf.add(UnsubscribeButton);
		        jf.add(cb1);
		        jf.add(l3);
		        jf.add(t2);
		        jf.add(l4);
		        //jf.add(t4);
		        jf.setTitle("Subscription Window");
		        jf.setSize(700,600);
		        jf.setVisible(true);
	     }
	    
	    class MyUIController extends SubscriberClient
	    {

			public MyUIController(String name, String server, int port) {
				super(name, server, port);
			}
	    }
	}

