package edu.njit.cs602.s2018.finalproject;

public interface SubscriberClient {

    /*
    * checkConncetionStatus()
    * checkCurrentSubscriber()
    *
    * */
    public void establishConnection();

    /*
    * return available topics
    * return topic (String)
    * */
    public String getTopicList();

    /*
     * return subsribed topics
     * return topic (String)
     * */
    public String getSubscribedList();

    /*
    * update (add) topic into list on Server
    * */
    public void subscribeToTopic();

    /*
     * update (remove) topic into list on Server
     * */
    public void unsubscribeFromTopic();
    /*
    * display help menu containing options
    * */
    public void helpMethod();

}
