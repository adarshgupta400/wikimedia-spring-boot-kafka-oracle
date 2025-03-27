package com.adarsh;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootProducerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootProducerApplication.class);
    }

    //todo: Start the kafka zookeper and broker first then run producer service.
    @Autowired
    private WikimeadiaChangesProducer wikimeadiaChangesProducer;
    @Override
    public void run(String... args) throws Exception {
        wikimeadiaChangesProducer.sendMessage();
    }
}
