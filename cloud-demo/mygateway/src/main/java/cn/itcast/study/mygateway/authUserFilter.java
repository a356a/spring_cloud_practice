package cn.itcast.study.mygateway;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.List;

@Order(1)
@Component
public class authUserFilter  implements GlobalFilter {
    String AUTH_USER = "authUser";
    String PASS_USER = "dengliang";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //首先获取请求
        ServerHttpRequest request = exchange.getRequest();
        //获取请求参数
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        //判断请求
        if(queryParams.containsKey(AUTH_USER)){
            List<String> strings = queryParams.get(AUTH_USER);
            if(strings.size()==1&&PASS_USER.equals(strings.get(0)))  return chain.filter(exchange);
        }
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
