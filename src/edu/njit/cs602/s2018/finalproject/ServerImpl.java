package edu.njit.cs602.s2018.finalproject;

import java.net.Socket;
import java.util.List;

public class ServerImpl implements Server {
    //For storing and managing connections
    List<Socket> connectionsList;
    //For storing and managing userNames
    List<String> userNameList;
    //For handling data passed into ServerSocket
    ServerController controller;


    /*
    * Start server socket and listen for connections
    * takse no parameters
    * */
    public void startServer(){}

    /*
     * Store connections in connectionsList
     * @param Socket is the socket to be stored
     * */
    public void storeConnection(Socket sock){}

    //Will instantiate ServerImpl and run startServer() method
    public static void main(String[] args){}


}
