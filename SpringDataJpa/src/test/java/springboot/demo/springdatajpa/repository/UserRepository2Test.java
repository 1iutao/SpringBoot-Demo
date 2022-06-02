package springboot.demo.springdatajpa.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import springboot.demo.springdatajpa.entity.UserDo;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepository2Test {
    @Autowired
    private UserRepository2 userRepository2;

    @Test  //排序
    public void testFindAll() {
        Sort sort = Sort.by(Sort.Direction.DESC , "id");
        Iterable<UserDo> iterable = userRepository2.findAll(sort);
        iterable.forEach(System.out::println);
    }

    @Test // 分页
    public void testFindPage() {
        // 创建排序条件
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        // 创建分页条件
        Pageable pageable = PageRequest.of(1, 10, sort);
        // 执行分页操作
        Page<UserDo> page = userRepository2.findAll(pageable);
        // 打印
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
    }

}