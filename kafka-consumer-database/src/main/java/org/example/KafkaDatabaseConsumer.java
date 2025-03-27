package org.example;


import org.example.entity.WikimediaData;
import org.example.repository.WikimediaDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    private WikimediaDataRepository wikimediaDataRepository;

    //@Autowired // - no need to add for single
    public KafkaDatabaseConsumer(WikimediaDataRepository wikimediaDataRepository) {
        this.wikimediaDataRepository = wikimediaDataRepository;
    }

    @KafkaListener(
            topics = "wikimedia_recentchanges",
            groupId = "myGroup"
    )
    public void consume(String eventMessage) {
        System.out.println("Event message recieved -> " + eventMessage);


        //save the event msg to db
        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(eventMessage);

        //todo
        wikimediaDataRepository.save(wikimediaData);
    }

}
