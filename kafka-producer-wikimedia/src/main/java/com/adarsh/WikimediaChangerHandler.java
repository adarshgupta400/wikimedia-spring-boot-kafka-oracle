package com.adarsh;


import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.springframework.kafka.core.KafkaTemplate;

public class WikimediaChangerHandler implements EventHandler {

    /*
    wheneerver there is new event triggered , this handler will  take event and
     call onMessage method which will then send it to the kafkaTopic
     */
    private KafkaTemplate<String, String> kafkaTemplate;
    private  String topic;

    public WikimediaChangerHandler(KafkaTemplate<String, String> kafkaTemplate, String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }


    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {

    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        System.out.println("Event data -> "+ messageEvent.getData());
        this.kafkaTemplate.send(topic, messageEvent.getData());

    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
