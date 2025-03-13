package axelchen.sprintboot.rabbitmq.quartz.job;

import axelchen.sprintboot.rabbitmq.model.TaskPo;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


// 队列生产者。将任务派送到消息队列。

@Service
public class TaskToMqJob implements Job {

    @Autowired
    private  RabbitTemplate rabbitTemplate;
    private final Gson gson = new Gson();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("任务执行中... 当前时间: " + new Date());
        // 获取定时任务信息：
        List<TaskPo> taskPoList = new ArrayList<>(
                List.of(
                        new TaskPo(1, "task1", "description1", 1, "0/1 * * * * ?", true, "{\"key1\":\"value1\"}", 1, System.currentTimeMillis(),System.currentTimeMillis()),
                        new TaskPo(2, "task2", "description2", 2, "0/1 * * * * ?", true, "{\"key2\":\"value2\"}", 2, System.currentTimeMillis(), System.currentTimeMillis())
                )
        );

        // 提交到队列中。
        // json 实现序列化
        for(TaskPo taskPo : taskPoList) {
            rabbitTemplate.convertAndSend("simple-queue", gson.toJson(taskPo));
        }
    }
}