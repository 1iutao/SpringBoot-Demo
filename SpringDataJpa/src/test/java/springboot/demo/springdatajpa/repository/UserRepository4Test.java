package springboot.demo.springdatajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import springboot.demo.springdatajpa.entity.UserDo;

import javax.transaction.Transactional;


class UserRepository4Test {
    @Autowired
    private UserRepository4 userRepository4;

    @Test
    public void testFindIdByUsername01() {
        UserDo user = userRepository4.findByUsername1("lt");
        System.out.println(user);
    }

    @Test
    public void testFindIdByUsername02() {
        UserDo user = userRepository4.findByUsername2("lt");
        System.out.println(user);
    }

    @Test
    public void testFindIdByUsername03() {
        UserDo user = userRepository4.findByUsername3("lt");
        System.out.println(user);
    }

    @Test
    // 更新操作，需要在事务中。
    // 在单元测试中，事务默认回滚，所以胖友可能怎么测试，事务都不更新。
    @Transactional
    public void testUpdateUsernameById() {
        userRepository4.updateUsernameById(5, "lttest");
    }


}