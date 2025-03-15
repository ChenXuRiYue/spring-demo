package axelchen.spring.rabbitmq.quartz;
import axelchen.spring.rabbitmq.quartz.job.PullTaskToQuartzJob;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Quartz 相关的配置中心
@Configuration
@RequiredArgsConstructor
public class QuartzConfig {
    // 提交任务到消息队列过程配置
    // 1. 指定任务行为
    // 2，定位名称、组名等等。 二层划分管理任务需要
    // 3. 配置持久化

    @Bean
    public JobDetail taskToMqJobDetail() {
        return JobBuilder.newJob(PullTaskToQuartzJob.class)
                // 配置 JobDetail
                .withIdentity("taskToMq", "mqGroup")
                // 持久化任务
                .storeDurably()
                .build();
    }

    // 定义触发器。决定了调度时间，等等。
    // 1. 绑定到任务元信息。
    // 2. 指定定时逻辑
    @Bean
    public Trigger taskToMqTrigger() {
        // 配置触发器
        return TriggerBuilder.newTrigger()
                // 绑定任务。
                .forJob(taskToMqJobDetail())
                .withIdentity("taskToMqTrigger", "mqGroup")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
                .build();
     }
}