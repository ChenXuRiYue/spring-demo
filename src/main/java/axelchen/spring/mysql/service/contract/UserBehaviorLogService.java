package axelchen.spring.mysql.service.contract;

import axelchen.spring.mysql.model.entity.UserBehaviorLog;
import java.util.List;

public interface UserBehaviorLogService {
    void save(UserBehaviorLog log);
    List<UserBehaviorLog> findByTargetType(String targetType);
}