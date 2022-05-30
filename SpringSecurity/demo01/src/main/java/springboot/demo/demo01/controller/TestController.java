package springboot.demo.demo01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/echo")
    public String echo() {
        return "返回测试";
    }

    @GetMapping("/home")
    public String home(){
        return "首页测试";
    }

    @GetMapping("/admin")
    public String admin() {
        return "管理员测试";
    }

    @GetMapping("/user")
    public String user(){
        return "用户测试";
    }

}
