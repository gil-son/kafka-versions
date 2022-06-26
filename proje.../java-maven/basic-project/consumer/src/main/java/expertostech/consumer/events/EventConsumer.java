package expertostech.consumer.events;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
public class EventConsumer {

    private final KafkaConsumer<String, String > consumer;

    public EventConsumer(){
        consumer = createConsumer();
    }

    private KafkaConsumer<String, String> createConsumer(){
        if (consumer != null){
            return consumer;
        }

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "default"); // indicates how many groups will receive the same message, a group does not receive repeated messages

        return new KafkaConsumer<String, String>(properties);
    }

    public void execute(){
        List<String> topics = new ArrayList<>();
        topics.add("EventRegister");
        consumer.subscribe(topics); // Listen the RegisterEvent topic
        log.info("Starting consumer!");
        boolean validate = true;
        while(validate){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for(ConsumerRecord<String, String> record: records){
                messageRecord(record.topic(), record.partition(), record.value());
                if(record.value() == "close"){
                    validate = false;
                }
            }
        }
        consumer.close();
    }

    private void messageRecord(String topic, int partition, String message){
        log.info("Topic: {}, Partition: {}, Message: {}", topic, partition, message);
    }
}
