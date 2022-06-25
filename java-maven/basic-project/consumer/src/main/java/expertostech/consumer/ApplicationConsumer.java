package expertostech.consumer;

import expertostech.consumer.events.EventConsumer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApplicationConsumer {

    public static void main(String[] args) {
        ApplicationConsumer applicationConsumer = new ApplicationConsumer();
        applicationConsumer.init();
    }

    private void init(){
        log.info("Starting the application!");
        EventConsumer eventConsumer = new EventConsumer();
        eventConsumer.execute();
    }
}
