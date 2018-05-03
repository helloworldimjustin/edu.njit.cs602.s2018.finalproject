package advancedJava.CS602.testClasses;

import java.util.List;
import java.util.Map;

/**
 * Created by Justin Bullock
 * Note: Return types, Parameter types and Data Structures may not be final
 */

public class ServerPublishServiceImpl<k> implements advancedJava.CS602.testClasses.ServerPublishService.ServerPublishService {

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
