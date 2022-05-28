package springboot.demo.springdatajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import springboot.demo.springdatajpa.entity.UserDo;

import java.util.Date;

public interface UserRepository3 extends PagingAndSortingRepository<UserDo,Integer> {

    UserDo findByUsername(String username);

    Page<UserDo> findByCreateTimeAfter(Date createTime, Pageable pageable);
}
