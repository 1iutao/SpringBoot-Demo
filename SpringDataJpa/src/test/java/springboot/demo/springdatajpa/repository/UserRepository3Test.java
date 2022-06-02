package springboot.demo.springdatajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import springboot.demo.springdatajpa.entity.UserDo;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepository3Test {

    @Autowired
    private UserRepository3 userRepository3;

    @Test
    public void testFindByUsername() {
        UserDo user = userRepository3.findByUsername("lt");
        System.out.println(user);
    }

    @Test
    public void testFindByCreateTimeAfter() {
        // 创建分页条件
        Pageable pageable = PageRequest.of(1, 10);
        // 执行分页操作
        Date createTime = new Date(2018 - 1990, Calendar.FEBRUARY, 24); // 临时 Demo ，实际不建议这么写
        Page<UserDo> page = userRepository3.findByCreateTimeAfter(createTime, pageable);
        // 打印
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
    }

}