package edu.njit.cs602.s2018.finalproject;
/**
 * Created by Prajwal Sankar
 * Note: Return types, Parameter types and Data Structures may not be final
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class JavaGUI extends JFrame  {
	

	    public JavaGUI() {
	      Start();
	    }
	    public final void Start() {

	        JPanel panel = new JPanel();
	        getContentPane().add(panel);

	        panel.setLayout(null);

	        //Label to display user name
	        JLabel l1,l2;  
	        l1=new JLabel("Username");  
	        l1.setBounds(0,0,70,30);
	        panel.add(l1);  
	        panel.setSize(700,700);  
	        panel.setLayout(null);  
	        panel.setVisible(true);

	        //Label to enter user id
	        JTextField t1;  
	        t1=new JTextField("Enter UserID");  
	        t1.setBounds(80,0,100,30);  
	        panel.add(t1);  
	        panel.setVisible(true); 
	        
	        //Button to connect to server
	        JButton conButton = new JButton("Connect");
	        conButton.setBounds(200, 0, 100, 30);
	        conButton.addActionListener(new ActionListener() 
	        {
	        	//To add connection method for server connection
	            public void actionPerformed(ActionEvent event) 
	            {
	           }
	        });

	        panel.add(conButton);
	        setTitle("Subscriber");
	        setSize(500, 200);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        
	        //Label to display server status
	        l1=new JLabel("Server Up/Down");  
	        l1.setBounds(350,0,100,30);
	        panel.add(l1);  
	        panel.setSize(700,700);  
	        panel.setLayout(null);  
	        panel.setVisible(true);
	          
	        //Dropdown to select available topics
	            String topics[]={"Select Topic","topic1","topic2","topic3","topic4","topic5"};        
	            JComboBox cb=new JComboBox(topics);    
	            cb.setBounds(80,50,100,20);    
	            panel.add(cb);        
	            panel.setSize(400,500);    
	            panel.setVisible(true); 
	            
		        //Dropdown to view subscribed topics
	            String sublist[]={"Your SubList","Sub1","Sub2","Sub3","Sub4","Sub5"};        
	            JComboBox cb1=new JComboBox(sublist);    
	            cb1.setBounds(200,50,100,20);    
	            panel.add(cb1);        
	            panel.setSize(400,500);    
	            panel.setVisible(true);
	            
	            //Label to display action message
	            l1=new JLabel("Action Message");  
		        l1.setBounds(80,70,100,30);
		        panel.add(l1);  
		        panel.setSize(700,700);  
		        panel.setLayout(null);  
		        panel.setVisible(true);
		        
		        //label to display publisher message
		        l1=new JLabel("Publisher Message");  
		        l1.setBounds(80,110,100,30);
		        panel.add(l1);  
		        panel.setSize(700,700);  
		        panel.setLayout(null);  
		        panel.setVisible(true);
	        
	        
	     }
	    public static void main(String[] args) {
	    	JavaGUI jg = new JavaGUI();
	       jg.setVisible(true);
	    }
	}