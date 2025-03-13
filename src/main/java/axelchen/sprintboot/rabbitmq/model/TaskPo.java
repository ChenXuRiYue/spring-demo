package axelchen.sprintboot.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * 定时任务实体类，对应数据库表 tasks。
 * 用于描述自动化测试脚本的调度信息。
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class TaskPo {

    /**
     * 任务唯一标识，主键，自增。
     */
    private Integer id;

    /**
     * 任务名称，唯一标识任务，便于用户识别。
     */
    private String name;

    /**
     * 任务描述，详细说明任务目的或功能。
     */
    private String description;

    /**
     * 关联的测试脚本 ID，外键指向 test_scripts 表。
     */
    private Integer scriptId;

    /**
     * Cron 表达式，定义任务的调度周期。
     * 示例: "0/1 * * * * ?" 表示每秒执行。
     */
    private String schedule;

    /**
     * 任务是否启用，1 表示启用，0 表示禁用。
     */
    private Boolean enabled;

    /**
     * 脚本执行参数，使用 JSON 格式存储动态参数。
     * 示例: {"env": "test", "timeout": 300}
     */
    private String parameters;

    /**
     * 关联的 Microsoft 管道 ID，外键指向 pipelines 表。
     * 可为空，表示任务不一定属于某个管道。
     */
    private Integer pipelineId;

    /**
     * 创建时间，记录任务创建的时间戳。
     */
    private Long createdAt;

    /**
     * 更新时间，记录任务最后更新的时间戳。
     */
    private Long updatedAt;

    /**
     * 预持久化钩子，自动设置创建时间。
     */
    protected void onCreate() {
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
    }

    /**
     * 预更新钩子，自动更新时间。
     */
    protected void onUpdate() {
        this.updatedAt = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "TaskPo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", scriptId=" + scriptId +
                ", schedule='" + schedule + '\'' +
                ", enabled=" + enabled +
                ", parameters='" + parameters + '\'' +
                ", pipelineId=" + pipelineId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}