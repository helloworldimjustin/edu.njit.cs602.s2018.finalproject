package edu.njit.cs602.s2018.finalproject.ServerFiles;

import advancedJava.CS602.testClasses.ServerController;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Justin Bullock
 * Note: Return types, Parameter types and Data Structures may not be final
 *
 */

public class ServerImpl implements Server {
    //For storing and managing subscriber connections
    ConcurrentHashMap<Integer,Socket> connectionsList;
    //For storing and managing subscriber subscriptions
    ConcurrentHashMap<Socket,List<String>> subscriptions;
    //For storing and managing published topics
    ConcurrentHashMap<String,String> publishedTopics;



    ServerController serverController;



    public ServerImpl(){
        this.serverController = new ServerController();
        this.connectionsList = new ConcurrentHashMap<>();
        this.subscriptions = new ConcurrentHashMap<>();
        this.publishedTopics = new ConcurrentHashMap<>();

    }

    /*
     * Store connections in connectionsList
     * @param Socket is the socket to be stored
     * */
     public void storeId(Socket socket, int id){
        //System.out.println("Store Id");


        Socket sock = socket;
        try{
            //DataInputStream dis = new DataInputStream(sock.getInputStream());
            DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
            if(connectionsList.get(id) == null){
                connectionsList.put(id,sock);
            }else{
                if(sock.isClosed()){
                    if(connectionsList.get(id) != null){
                        connectionsList.remove(id);
                    }
                }else{
                    dos.writeUTF("USER ALREADY CONNECTED");
                    sock.close();
                }
            }



        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /*
    *
    * takes a request: UNSUBSRCIBE, SUBRSCRIBE, GETTOPICS, etc
    * and passes it to the ServerController serverController to handle the request
    * */
    public void subscribe(int id, String command, Socket sock, DataOutputStream dos, DataInputStream dis){
        try{


            synchronized (subscriptions){

                dos.writeUTF("Enter topic: ");
                String topic = dis.readUTF();
                System.out.println(topic);
                synchronized (publishedTopics){
                    if(subscriptions.isEmpty()){

                        if(publishedTopics.contains(topic)){
                            List<String> topics = new ArrayList<>();
                            topics.add(topic);
                            subscriptions.put(sock,topics);
                            dos.writeUTF("Subscribed to topic: "+topic);
                            System.out.println("contents of Subscribtions : "+subscriptions.get(sock).toString());
                            dis.readUTF();
                        }else{
                            System.out.println("topic "+topic+" does not exist");
                        }


                    }else if(subscriptions.get(sock) != null){
                            if(publishedTopics.contains(topic)){
                                List<String> temp = subscriptions.get(sock);
                                temp.add(topic);
                                subscriptions.put(sock,temp);
                                dos.writeUTF("Subscribed to topic: "+topic);
                                System.out.println("contents of Subscribtions : "+subscriptions.get(sock).toString());
                            }else{
                                System.out.println("topic "+topic+" does not exist");
                            }
                            notifyAll();


                    }else{
                        System.out.println("something wrong with subscriptions method");
                    }
                    notifyAll();
                }

            }



        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void unsubscribe(int id, String command, Socket sock, DataOutputStream dos, DataInputStream dis){
        try{

            synchronized (subscriptions){
                //send response to client
                dos.writeUTF("Choose a topic to Unsubscribe from");
                //get response from client
                String topic = dis.readUTF();


                List<String> temp = subscriptions.get(sock);
                if(temp != null){
                    if(temp.contains(topic)){
                        temp.remove(topic);
                        subscriptions.put(sock, temp);
                    }
                }else{
                    dos.writeUTF("Unsubscribe successful");
                    System.out.println(topic+"does not exist");
                }
                notifyAll();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void getSubscriptions(int id, String command, Socket sock, DataOutputStream dos, DataInputStream dis){
        try{

            synchronized (subscriptions){
                while(subscriptions.isEmpty()){
                    wait();
                }
                String subs = "";
                List<String> subscriptionsTemp = subscriptions.get(sock);
                if(subscriptionsTemp != null && !subscriptionsTemp.isEmpty()){
                    for(String topic : subscriptionsTemp){
                        subs+=topic+"\n";
                    }
                    dos.writeUTF(subs);
                }else{
                    System.out.println("no subscriptions");
                    dos.writeUTF("you have no subscriptions");
                }
                notifyAll();

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void getAvailableTopics(String command, Socket sock, DataOutputStream ds){
        try{

            synchronized (publishedTopics){
                while(publishedTopics.isEmpty()){
                    wait();
                }
                System.out.println("getting topics");

                ArrayList<String> temparr = new ArrayList<>();
                for(String topic : publishedTopics.keySet()){
                    System.out.println(topic);
                    temparr.add(topic);

                }
                ds.writeUTF(temparr.toString());
                notifyAll();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void processPublisherRequest(Socket sock, int sockType, DataInputStream dis, DataOutputStream dos){
        try{
            System.out.println(Thread.currentThread().getName()+" is of type : "+sockType);

            InputStreamReader isr = new InputStreamReader(sock.getInputStream());
            BufferedReader br = new BufferedReader(isr);

            String topic = "";
            String message = "";
            SimpleDateFormat timeStamp;


            while(true){

                try{
                        synchronized (publishedTopics){
                            System.out.println("inserting a topic");
                            timeStamp = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");

                            if((topic = br.readLine()) != null && (message = br.readLine()) != null ){
                                System.out.println(topic+" "+message+" "+timeStamp.toString());
                                publishedTopics.put(topic,message);
                                notifyAll();

                            }
                            synchronized (subscriptions){
                                if(!subscriptions.isEmpty()){
                                    List<String> topics = subscriptions.get(sock);
                                    if((!topics.isEmpty()) && topics.contains(topic)){
                                        dos.writeUTF(topic+" "+message+" "+timeStamp.toString());
                                    }
                                }
                                notifyAll();
                            }
                        }

                    if(sock.isClosed()){
                        br.close();
                        isr.close();
                        dis.close();
                        dos.close();
                        break;
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void processSubscriberRequest(Socket sock, int sockType){
        try{

            DataInputStream dis = new DataInputStream(sock.getInputStream());
            DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
            String receiveMessage = "";
            int id = 0;

            while(true){
                try{

                    if(id == 0 ){
                        dos.writeUTF("Enter UserId");
                        if(!(receiveMessage = dis.readUTF()).isEmpty()){
                            id = Integer.parseInt(receiveMessage);
                            System.out.println("id = "+id);
                            storeId(sock, id);
                            dos.writeUTF("Verified");
                        }
                    }else if(sock.isConnected() && !(receiveMessage = dis.readUTF()).isEmpty()){
                        if(receiveMessage.equals("SUBSCRIBE")){

                            System.out.println("you clicked SUBSCRIBE");
                            subscribe(id,receiveMessage, sock, dos, dis);

                        }else if(receiveMessage.equals("UNSUBSCRIBE")){

                            System.out.println("UNSUBSCRIBE");
                            unsubscribe(id, receiveMessage, sock, dos, dis);

                        }else if(receiveMessage.equals("SUBSCRIPTIONS")){

                            System.out.println("SUBSCRIPTIONS");
                            getSubscriptions(id, receiveMessage, sock, dos, dis);

                        }else if(receiveMessage.equals("TOPICS")){

                            System.out.println("TOPICS");
                            getAvailableTopics(receiveMessage, sock, dos);

                        }else if(receiveMessage.equals("COMMANDS")){
                            String commands = "SUBSCRIBE\nUNSUBSCRIBE\nSUBSCRIPTIONS\nTOPICS";
                            dos.writeUTF(commands);
                        }
                        else{
                            if(sock.isConnected()){
                                System.out.println("Client did not choose option : "+receiveMessage);
                            }else if(sock.isClosed()){
                                System.out.println(sock+"has disconnected");
                                connectionsList.remove(sockType);
                                break;
                            }

                        }
                    }else{
                        if(sock.isClosed()){
                            dis.close();
                            dos.close();
                            break;
                        }

                    }
                }catch (Exception e){
                    e.printStackTrace();
                    if(sock.isClosed()){
                        System.out.println(sock+"has disconnected");
                        connectionsList.remove(sockType);
                        break;
                    }
                    break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static class ServerThread extends Thread{
        Socket sock;
        ServerImpl serverImpl;
        int sockType;

        public ServerThread(Socket sock, ServerImpl serverImpl, int sockType){
            this.sock = sock;
            this.serverImpl = serverImpl;
            this.sockType = sockType;
        }
        public int getSockType(){
            return sockType;
        }

        public void run(){
            if(getSockType()==0){
                try{
                    serverImpl.processPublisherRequest(sock,sockType, new DataInputStream(sock.getInputStream()), new DataOutputStream(sock.getOutputStream()));
                }catch (Exception e){
                    e.printStackTrace();
                }

            }else{
                serverImpl.processSubscriberRequest(sock,sockType);

            }
        }
    }

    public static void main(String[] args){
        try{

            ServerImpl serverImpl = new ServerImpl();
            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));

            Socket pubSock = serverSocket.accept();
            new ServerThread(pubSock,serverImpl, 0).start();
            int id = 1;

            while(true){

                Socket clientSock = serverSocket.accept();
                new ServerThread(clientSock,serverImpl, id).start();

                id++;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}