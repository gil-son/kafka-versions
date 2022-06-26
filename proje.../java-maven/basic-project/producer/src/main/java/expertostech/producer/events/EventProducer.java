package expertostech.producer.events;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

@Slf4j
public class EventProducer {

    // final, because will initialize in constructor
    private final Producer<String, String> producer;

    public EventProducer(){
        producer = createProducer();
    }

    private Producer<String, String>createProducer(){
        if(producer != null){
            return producer;
        }

        // Properties to connect
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("serializer.class", "kafka.serializer.DefaultEncoder");

        // initialize
        return new KafkaProducer<String, String>(properties);
    }

    public void execute(){

        String key = UUID.randomUUID().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String message = sdf.format(new Date());
        message += "|" + key + "| NEW MESSAGE WAS RECEIVED";

        log.info("Initiating message sending");
        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("EventRegister", key, message);
        producer.send(producerRecord);
        producer.flush();
        producer.close();

        log.info("Message sent successfully [{}]", message);
    }


}
