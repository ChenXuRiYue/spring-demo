package axelchen.sprintboot.rabbitmq.quartz.job;

import axelchen.sprintboot.rabbitmq.model.TaskPo;
import axelchen.sprintboot.rabbitmq.model.TaskPoDemo;
import com.google.gson.Gson;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


// 从数据库拉取消息
@Service
public class PullTaskToQuartzJob implements Job {

    private Scheduler scheduler;

    @Autowired
    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }


    private final Gson gson = new Gson();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("任务执行中... 当前时间: " + new Date());
        // 获取定时任务信息：
        List<TaskPoDemo> taskPoDemos = new ArrayList<>(
                List.of(
                        new TaskPoDemo("1", "Hello world", "0/5 * * * * ?")
                )
        );

        submitTaskToQuartz(taskPoDemos);
    }

    private void submitTaskToQuartz(List<TaskPoDemo> tasks) {
        try {
            for (TaskPoDemo task : tasks) {
                submitCronTaskToQuartz(task);
            }
        } catch (SchedulerException e) {
            // 获取情形
        }
    }

    private void submitCronTaskToQuartz(TaskPoDemo taskPoDemo) throws SchedulerException {
        JobDetail job = JobBuilder.newJob(SubmitTaskToMqJob.class)
                .withIdentity("taskJobId" + taskPoDemo.getId(), "AutoTaskGroup")
                .storeDurably()
                .usingJobData("id", taskPoDemo.getId())
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("taskTriggerId" + taskPoDemo.getId(), "AutoTaskGroup")
                .withSchedule(CronScheduleBuilder.cronSchedule(taskPoDemo.getCron()))
                .build();
        scheduler.scheduleJob(job, trigger);
    }
}