package br.com.alura.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class FraudDetectorService {

    public static void main(String[] args){
        var consumer = new KafkaConsumer<String, String>(new Properties());
        // List
        consumer.subscribe(Collections.singletonList("ECOMMERCE_NEW_ORDER"));

        // Listening by time
        var records = consumer.poll(Duration.ofMillis(100));

        if(records.isEmpty()){
            System.out.println(("Is empty"));
        }

        for(var record : records){
            System.out.println(("-----------------------------"));
            System.out.println(("Processing new order, checking for fraud"));
            System.out.println((record.key()));
            System.out.println((record.value()));
            System.out.println((record.partition()));
            System.out.println((record.offset()));

            // Simulation Fraud

            try{
                Thread.sleep(5000);
                System.out.println(("No fraud detected"));
            } catch (InterruptedException exception) {
                // ignoring
                exception.printStackTrace();
            }
        }

    }

    private static Properties properties(){
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");

        // byte-to-String
//        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // Creating a Group to receive all requests from the producer
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, FraudDetectorService.class.getSimpleName()); // getName

//        properties.setProperty(ConsumerConfig);
//        properties.setProperty(ConsumerConfig);
//        properties.setProperty(ConsumerConfig);
//        properties.setProperty(ConsumerConfig);

        return properties;
    }
}
