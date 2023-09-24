package cn.itcast.order.web;

import cn.study.feignutil.client.UserClient;
import cn.itcast.order.config.GlobalConfig;
import cn.itcast.order.pojo.Order;
import cn.study.feignutil.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("feignOrder")
@CrossOrigin
public class FeignOrderController {
    @Autowired
    GlobalConfig globalConfig;

    @Autowired
    UserClient userClient;

    @GetMapping("{userID}")
    public User queryOrderAndUser(@PathVariable("userID") Long userID,HttpServletRequest servletRequest){
        User byId = userClient.findById(userID);
        String realPath = servletRequest.getServletContext().getRealPath("");
        byId.setUsername(byId.getUsername()+  LocalDateTime.now().format(DateTimeFormatter.ofPattern(globalConfig.getTimefo()))  );
        return byId;
    }

}
