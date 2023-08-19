package cn.itcast.order.web;

import cn.itcast.order.config.GlobalConfig;
import cn.itcast.order.pojo.Order;
import cn.study.feignutil.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("restOrder")
public class RestOrderController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    GlobalConfig globalConfig;

    @GetMapping("{orderID}")
    public Order queryOrderAndUser(@PathVariable("orderID") Long orderID){
        String orderURL = "http://orderservice/order/"+orderID.toString();
        Order forObject = restTemplate.getForObject(orderURL, Order.class);

        String userURL = "http://userservice/user/"+forObject.getUserId().toString();
        User forObject1 = restTemplate.getForObject(userURL, User.class);


        forObject.setUser(forObject1);
        forObject.setName(forObject.getName()+ LocalDateTime.now().format(DateTimeFormatter.ofPattern(globalConfig.getTimefo())));
        return forObject;
    }

}
