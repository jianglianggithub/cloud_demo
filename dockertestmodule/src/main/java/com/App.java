package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.SocketException;

@SpringBootApplication
@RestController
public class App extends SpringBootServletInitializer {

//    @Autowired
//    StringRedisTemplate redisTemplate;

    private AddressUtils addressUtils=new AddressUtils();

    @RequestMapping("/**")
    public String a(HttpServletRequest request) throws SocketException {
        System.out.println(request.getRequestURL());
        return "docker - 内网ip 为"+addressUtils.getInnetIp();
    }


//    @RequestMapping("/b")
//    public String b(){
//        String aa = redisTemplate.opsForValue().get("aa");
//        Boolean aa1 = redisTemplate.hasKey("aa");
//        return aa1+"";
//    }
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(getClass());
    }
}
