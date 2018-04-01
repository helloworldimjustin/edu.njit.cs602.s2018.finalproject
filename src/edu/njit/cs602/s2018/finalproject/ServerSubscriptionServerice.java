package edu.njit.cs602.s2018.finalproject;

public interface ServerSubscriptionServerice {
    /* add topic to clientSubscriptions
    * @param topicName: name of topic to be added
    * */
    public void subscribeToTopic(String topicName);

    /*
     * remove topic to clientSubscriptions
     * @param topicName: name of topic to be removed
     * */
    public void unsubscribeFromTopic(String topicName);
    /*
     * return topics from clientSubscriptions
     * */
    public void getSubscriptions();
}
