package edu.njit.cs602.s2018.finalproject;

/**
 * Created by Justin Bullock
 * Note: Return types, Parameter types and Data Structures may not be final
 */

public class ServerController {
    //Instance of ServerPublishService for performing publisher actions
    ServerPublishServiceImpl publisher;
    //Instance of ServerSubscriptionService for performing subscribtion actions
    ServerSubscriptionServiceImpl subscription;

    /*
     * use ServerPublishServiceImpl instance to store message in publishedMessages;
     * @param: String message to be stored
     * */
    public void storeMessage(String message){ }

    /*
     * use ServerPublishServiceImpl instance to send new message to all subscribers;
     * @param: String message to be stored
     * */
    public void publishMessageToSubscribers(String message){}

    /*
     * use ServerPublishServiceImpl instance to return avaliableTopics;
     *
     * */
    public void getAvailableTopics(){}

    /*
     * use ServerSubscriptionServiceImpl instance to add topic to clientSubscriptions
     * @param topicName: name of topic to be added
     * */
    public void subscribeToTopic(String topicName){}

    /*
     * use ServerSubscriptionServiceImpl instance to  remove topic to clientSubscriptions
     * @param topicName: name of topic to be removed
     * */
    public void unsubscribeFromTopic(String topicName){}
    /*
     * use ServerSubscriptionServiceImpl instance to return topics from clientSubscriptions
     * */
    public void getSubscriptions(){}





}
