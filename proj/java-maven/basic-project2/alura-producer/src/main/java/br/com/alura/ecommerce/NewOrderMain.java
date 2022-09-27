package br.com.alura.ecommerce;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    public static void main(String[] args) throws JsonProcessingException, ExecutionException, InterruptedException {

        var json = "{\n" +
                "   \"idOrder\":111,\n" +
                "   \"idUser\":\"user-01\",\n" +
                "   \"price\":\"10,5\"\n" +
                "}";


        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree(json);
        System.out.println("json:" + tree);

        Order order = new Order();


        order.setIdOrder(tree.get("idOrder").asText());
        order.setIdUser(tree.get("idUser").asText());
        order.setPrice(tree.get("idOrder").asDouble());

        System.out.println("id Order:" + order.getIdOrder());
        System.out.println("id User:" + order.getIdUser());
        System.out.println("Price:" + order.getPrice());

        var producer = new KafkaProducer<String, String>(properties());
        var record = new ProducerRecord<String, String>("ECOMMERCE_NEW_ORDER", order.getIdOrder(), tree.toString());

        producer.send(record, (data, exception) -> {
            if(exception != null){
                exception.printStackTrace();
            }

            System.out.println("Success! Submitted topic: "+ data.topic() // Observer
                    + " partition: " + data.partition()
                    + " offset: " + data.offset() // = message
                    + " timestamp: "+ data.timestamp());
         }).get(); // asynchronous, click with the CTRL + button on send and see. then the .get() waits for the method to complete and in that case success or exceptions may occur
    }

    private static Properties properties(){
        var properties = new Properties();
        // Server and location
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        // Both the value and the key will transform the message and the key, based on Strings
        // String-to-byte transformers, i.e. serializers
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        return properties;
    }

}

class Order{

    private String idOrder;
    private String idUser;
    private Double price;

    public Order(){}

    public Order(String idOrder, String idUser, Double price) {
        this.idOrder = idOrder;
        this.idUser = idUser;
        this.price = price;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", idUser=" + idUser +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return idOrder == order.idOrder && idUser == order.idUser && price == order.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, idUser, price);
    }
}


