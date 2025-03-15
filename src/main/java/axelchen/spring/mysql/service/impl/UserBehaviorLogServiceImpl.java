package axelchen.spring.mysql.service.impl;

import axelchen.spring.mysql.mapper.UserBehaviorLogMapper;
import axelchen.spring.mysql.model.entity.UserBehaviorLog;
import axelchen.spring.mysql.service.contract.UserBehaviorLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserBehaviorLogServiceImpl implements UserBehaviorLogService {
    @Resource
    private UserBehaviorLogMapper mapper;

    @Override
    public void save(UserBehaviorLog log) {
        mapper.insert(log);
    }

    @Override
    public List<UserBehaviorLog> findByTargetType(String targetType) {
        return mapper.findByTargetType(targetType);
    }
}