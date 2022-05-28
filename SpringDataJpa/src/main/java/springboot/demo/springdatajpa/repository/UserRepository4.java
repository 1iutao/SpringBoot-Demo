package springboot.demo.springdatajpa.repository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import springboot.demo.springdatajpa.entity.UserDo;

public interface UserRepository4 extends PagingAndSortingRepository<UserDo,Integer> {
    @Query("SELECT u FROM UserDo u WHERE u.username = ?1")
    UserDo findByUsername1(String username);

    @Query("SELECT u FROM UserDo u WHERE u.username = :username")
    UserDo findByUsername2(@Param("username") String username);

    @Query(value = "SELECT * FROM user u WHERE  u.username = : username", nativeQuery = true)
    UserDo findByUsername3(@Param("username") String username);

    @Query("UPDATE UserDo u SET u.username = : username WHERE u.id = : id")
    @Modifying
    int updateUsernameById(Integer id, String Username);
}
