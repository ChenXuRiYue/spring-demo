package axelchen.sprintboot.rabbitmq.config;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue rabbitMQConnectionFactory() {
        // 配置一个简单的持久化消息队列
        return new Queue("simple-queue", true);
    }
}
