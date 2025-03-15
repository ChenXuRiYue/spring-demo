package axelchen.spring.mysql.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@TableName("user_behavior_log")
public class UserBehaviorLog {
    // 主键字段，复合主键 (id, target_type_id)
    @TableId
    private String id;

    @TableId
    private Integer targetTypeId;

    // 非主键字段
    @TableField("target_type")
    private String targetType;

    @TableField("behavior_type")
    private String behaviorType;

    @TableField("target_id")
    private String targetId;

    @TableField("create_time")
    private Long createTime;

    @TableField("update_time")
    private Long updateTime;

    @TableField("user_id")
    private String userId;
}