package axelchen.sprintboot.rabbitmq.consumer;

import axelchen.sprintboot.rabbitmq.model.TaskPo;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);
    private final Gson gson = new Gson();

    @RabbitListener(queues = "simple-queue")
    public void receive(String message) {
        logger.info("Received message: {}", message);
        logger.info("Parsed message: {}", gson.fromJson(message, TaskPo.class));
    }

}
