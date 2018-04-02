package edu.njit.cs602.s2018.finalproject;

import java.net.Socket;
import java.util.List;

/**
 * Created by Justin Bullock
 * Note: Return types, Parameter types and Data Structures may not be final
 */

public class ServerImpl implements Server {
    //For storing and managing connections
    List<Socket> connectionsList;
    //For storing and managing userNames
    List<String> userNameList;
    //For persisting users
    List<String> userNamePersister;
    //For persisting connections
    List<String> connectionPersister;



    /*
    * Start server socket and listen for connections
    * takse no parameters
    * */
    public void startServer(){}

    /*
     * store connections in connectionsList
     * @param Socket is the socket to be stored
     * */
    public void storeConnection(Socket sock){}

    //Will instantiate ServerImpl and run startServer() method
    public static void main(String[] args){}


}
