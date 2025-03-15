package axelchen.spring.mysql.mapper;

import axelchen.spring.mysql.model.entity.UserBehaviorLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserBehaviorLogMapper {
    void insert(UserBehaviorLog log);
    List<UserBehaviorLog> findByTargetType(String targetType);
}