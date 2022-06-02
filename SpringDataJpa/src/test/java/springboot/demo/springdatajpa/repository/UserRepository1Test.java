package springboot.demo.springdatajpa.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import springboot.demo.springdatajpa.entity.UserDo;

import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest
 class UserRepository1Test {

    @Autowired
    private UserRepository1 userRepository1;

    @Test   //插入一条记录
    public void testInsert() {
        UserDo user = new UserDo().setUsername(UUID.randomUUID().toString())
                .setPassword("4dadas6125").setCreateTime(new Date());
        userRepository1.save(user);
    }

    @Test  //更新一条记录

    public void testUpdate() {
        //先查询
        Optional<UserDo> userDo = userRepository1.findById(1);
        Assert.isTrue(userDo.isPresent(), "记录不能为空");
        //更新
        UserDo updateUser = userDo.get();
        updateUser.setPassword("mima666");
        userRepository1.save(updateUser);
    }

    @Test //根据id删除记录
    public void testDelete() {
        userRepository1.deleteById(10);
    }

    @Test //根据id查询
    public void testSelectById() {
        Optional<UserDo> userDo = userRepository1.findById(1);
        System.out.println(userDo.get());
    }

    @Test  //根据id编号数据查询多条记录
    public void testSelectByIds() {
        Iterable<UserDo> users = userRepository1.findAllById(Arrays.asList(1,4));
        users.forEach(System.out::println);
    }

}