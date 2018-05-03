package edu.njit.cs602.s2018.finalproject.ServerFiles;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by Justin Bullock on 3/27/2018
 *
 */

public interface Server {
    /*
    * handles subscribe requests from client
    * @param id: client id
    * @param command: client request
    * @param sock: client socket
    * @param dos: output to client socket
    * @param dis: input to server socket
    * */
    void subscribe(int id, String command, Socket sock, DataOutputStream dos, DataInputStream dis);
    /*
     * handles unsubscribe requests from client
     * @param id: client id
     * @param command: client request
     * @param sock: client socket
     * @param dos: output to client socket
     * @param dis: input to server socket
     * */
    void unsubscribe(int id, String command, Socket sock, DataOutputStream dos, DataInputStream dis);
    /*
     * handles subscriptions requests from client
     * @param id: client id
     * @param command: client request
     * @param sock: client socket
     * @param dos: output to client socket
     * @param dis: input to server socket
     * */
    void getSubscriptions(int id, String command, Socket sock, DataOutputStream dos, DataInputStream dis);
    /*
     * handles getTopics requests from client
     * @param command: client request
     * @param sock: client socket
     * @param ds: output to client socket
     *
     * */
    void getAvailableTopics(String command, Socket sock, DataOutputStream ds);
    /*
     * controller for publisher requests
     * @param sock: client socket
     * @param sockType: indicates what type of socket, subscriber or publisher
     * @param dos: output to client socket
     * @param dis: input to server socket
     * */
    void processPublisherRequest(Socket sock, int sockType, DataInputStream dis, DataOutputStream dos);
    /*
     * controller for subscriber requests
     * @param sock: client socket
     * @param sockType: indicates what type of socket, subscriber or publisher
     * */
    void processSubscriberRequest(Socket sock, int sockType);



}
