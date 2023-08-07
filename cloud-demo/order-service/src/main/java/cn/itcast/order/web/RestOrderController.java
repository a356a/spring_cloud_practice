package cn.itcast.order.web;

import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("restOrder")
public class RestOrderController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("{orderID}")
    public Order queryOrderAndUser(@PathVariable("orderID") Long orderID){
        String orderURL = "http://localhost:8088/order/"+orderID.toString();
        Order forObject = restTemplate.getForObject(orderURL, Order.class);

        String userURL = "http://localhost:8081/user/"+forObject.getUserId().toString();
        User forObject1 = restTemplate.getForObject(userURL, User.class);


        forObject.setUser(forObject1);
        return forObject;
    }

}
