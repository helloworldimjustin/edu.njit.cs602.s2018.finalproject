package edu.njit.cs602.s2018.finalproject;

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
