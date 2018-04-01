package edu.njit.cs602.s2018.finalproject;
import jdk.internal.util.xml.impl.Input;
import jdk.nashorn.internal.objects.annotations.Constructor;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public interface ServerClient extends Runnable {

    //listenForClientConnection()
    //ListenFor/PushPublishedData()
    //interpretData()
    /*
    * listen for connections
    * listen for incoming data (From Publisher):
        * sendMessageToSubscriber
    * listen for subscriberAction (Subscriber)
        * subcribe/unsubscribe to topic
        * return list of topics
        * return list of subscriptions
    * maintain list of connections
    * maintain list of client IDs
    * push data to clients
    * sendResponseToSubscriber
    *
    *
    * */

    /*
    * Calls ServerSocket .accept() and waits for an incoming client socket
    * Will instantiate ServerSocket to establish communication with a client
    *
    * */
    void listenForConnection();

    /*
    * listens for client action and client data
    *
    * */
    void listenForIncomingData(InputStream input);

    void interpretData(InputStream input);

    /*
    * will send data to client socket
    *
    * */
    void sendMessageToClient(OutputStream output);

    void maintainConnections(Socket sock);

    void maintainClientIDs(Socket sock);





}
