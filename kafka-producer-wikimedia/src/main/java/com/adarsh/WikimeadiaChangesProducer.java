package com.adarsh;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimeadiaChangesProducer {

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired //todo: no need to add if single armunet constricture in spring - auto autowired
    public WikimeadiaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessage() throws InterruptedException {

        String topic = "wikimedia_recentchanges";
        //to read real time stram data from wikimedia , WE USE EVENT SOURCE

        EventHandler eventHandler =
                new WikimediaChangerHandler(kafkaTemplate, topic);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange"; //todo:
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventSource = builder.build();

        eventSource.start();

        TimeUnit.MINUTES.sleep(10);

    }
}
