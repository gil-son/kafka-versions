package expertostech.producer;

import expertostech.producer.events.EventProducer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApplicationProducer {

    public static void main(String[] args) {
        ApplicationProducer applicationProducer = new ApplicationProducer();
        applicationProducer.init();
    }

    private void init(){
        log.info("Starting the application!");
        EventProducer eventProducer = new EventProducer();
        eventProducer.execute();
    }


}
