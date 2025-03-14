package axelchen.sprintboot.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class TaskPoDemo {
    private String id;
    private String code;
    private String cron;
}
