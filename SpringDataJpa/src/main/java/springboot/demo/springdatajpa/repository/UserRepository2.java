package springboot.demo.springdatajpa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import springboot.demo.springdatajpa.entity.UserDo;

public interface UserRepository2 extends PagingAndSortingRepository<UserDo,Integer> {

    //继承自分页和排序接口
}
