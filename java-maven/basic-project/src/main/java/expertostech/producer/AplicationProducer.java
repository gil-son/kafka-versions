package expertostech.producer;

import expertostech.producer.events.EventProducer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AplicationProducer {

    public static void main(String[] args) {

    }

    private void init(){
        log.info("starting the application");
        EventProducer eventProducer = new EventProducer();
        eventProducer.execute();
    }


}
