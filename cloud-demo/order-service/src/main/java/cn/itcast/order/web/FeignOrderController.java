package cn.itcast.order.web;

import cn.itcast.order.client.UserClient;
import cn.itcast.order.config.GlobalConfig;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("feignOrder")
public class FeignOrderController {
    @Autowired
    GlobalConfig globalConfig;

    @Autowired
    UserClient userClient;

    @GetMapping("{userID}")
    public User queryOrderAndUser(@PathVariable("userID") Long userID){
        User byId = userClient.findById(userID);
        byId.setUsername(byId.getUsername()+  LocalDateTime.now().format(DateTimeFormatter.ofPattern(globalConfig.getTimefo()))  );
        return byId;
    }

}
