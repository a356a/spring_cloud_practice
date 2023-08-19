package cn.itcast.user.service;

import cn.itcast.user.mapper.UserMapper;
import cn.itcast.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Service
@RefreshScope
public class UserService {

    @Value("${user.suffix}")
    String userSuffix;

    @Autowired
    private UserMapper userMapper;

    public User queryById(Long id) {
        User byId = userMapper.findById(id);
        byId.setUsername(byId.getUsername()+"用户统一后缀为 "+userSuffix);
        return byId;
    }
}