package edu.njit.cs602.s2018.finalproject;

import java.util.List;
import java.util.Map;

public class ServerPublishServiceImpl<k> implements ServerPublishService {

    //Publisher Client
    Map<k,String> publishedMessages;
    List<String> availableTopics;


    /*
     * store message in publishedMessages;
     *
     * */
    public void storeMessage(){ }

    /*
     * send new message to all subscribers;
     *
     * */
    public void publishMessageToSubscribers(){}

    /*
     * return avaliableTopics;
     *
     * */
    public void getAvailableTopics(){}

}
