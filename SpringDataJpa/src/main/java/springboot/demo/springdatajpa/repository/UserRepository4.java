package springboot.demo.springdatajpa.repository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import springboot.demo.springdatajpa.entity.UserDo;

public interface UserRepository4 extends PagingAndSortingRepository<UserDo,Integer> {
    @Query("SELECT u FROM UserDo u WHERE u.username = ?1")       //使用 @Query 自定义了一个 SQL 操作，并且参数使用占位符(?) + 参数位置的形式
    UserDo findByUsername1(String username);

    @Query("SELECT u FROM UserDo u WHERE u.username = :username")     //使用占位符(:) + **参数名字(需要使用 @Param 声明)**的形式
    UserDo findByUsername2(@Param("username") String username);

    @Query(value = "SELECT * FROM user u WHERE  u.username = : username", nativeQuery = true)      //增加了 nativeQuery = true ，表示在 @Query 自定义的是原生 SQL，而非上面自定义的是 JPQL
    UserDo findByUsername3(@Param("username") String username);

    @Query("UPDATE UserDo u SET u.username = : username WHERE u.id = : id")       //定义了更新操作，需要加上 @Modifying 注解
    @Modifying
    int updateUsernameById(Integer id, String Username);
}
