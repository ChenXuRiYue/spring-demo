package axelchen.spring.rabbitmq.quartz.job;


import axelchen.spring.rabbitmq.model.TaskPoDemo;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SubmitTaskToMqJob implements Job {
    private final RabbitTemplate rabbitTemplate;
    private final Gson gson = new Gson();
    private final Logger logger = LoggerFactory.getLogger(SubmitTaskToMqJob.class);
    @Override
    public void execute(org.quartz.JobExecutionContext context) throws org.quartz.JobExecutionException {
        // 获取定时任务主键信息
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String id = jobDataMap.getString("id");

        // 根据Id 获得数据库对象
        TaskPoDemo task = new TaskPoDemo(id, "Hello world", "0/5 * * * * ?");

        logger.info("SubmitTaskToMqJob " + LocalDateTime.now());
        rabbitTemplate.convertAndSend("simple-queue", gson.toJson(task));
    }
}
