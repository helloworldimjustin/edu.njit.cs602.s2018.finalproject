package edu.njit.cs602.s2018.finalproject;
/**
 * Created by Justin Bullock
 * Note: Return types, Parameter types and Data Structures may not be final
 */

public interface ServerPublishService {
    /*
     * store message in publishedMessages;
     *
     * */
    public void storeMessage();

    /*
     * send new message to all subscribers;
     *
     * */
    public void publishMessageToSubscribers();

    /*
     * return avaliableTopics;
     *
     * */
    public void getAvailableTopics();
}
