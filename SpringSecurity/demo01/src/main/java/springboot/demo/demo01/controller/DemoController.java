package springboot.demo.demo01.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("demo")
public class DemoController {

    @PermitAll
    @GetMapping("/echo")
    public String echo() {
        return "返回测试";
    }

    @GetMapping("/home")
    public String home() {
        return "首页测试";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String admin(){
        return "管理员测试";
    }

    @PreAuthorize("hasRole('ROLE_UESER')")
    @GetMapping("/user")
    public String user() {
        return "用户测试";
    }
}
