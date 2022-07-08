package br.com.alura.ecommerce;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class NewOrderMain {

    public static void main(String[] args) {
        var producer = new KafkaProducer<String, String>(properties());
    }

    private static Properties properties(){
        var properties = new Properties();
        // Servidor e local
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        // Tanto o valor quanto a chave, vao transformar a mensagem e a chave, baseadas em Strings
        //Transformadores de String para bytes, ou seja, serializadores
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }

}
