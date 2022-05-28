package springboot.demo.springdatajpa.repository;

import org.springframework.data.repository.CrudRepository;
import springboot.demo.springdatajpa.entity.UserDo;

public interface UserRepository1 extends CrudRepository<UserDo,Integer> {

    //继承自CRUD仓库
}
