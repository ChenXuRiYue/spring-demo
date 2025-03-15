package axelchen.spring.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class TaskPoDemo {
    private String id;
    private String code;
    private String cron;
}
