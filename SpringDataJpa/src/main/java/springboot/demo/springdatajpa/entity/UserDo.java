package springboot.demo.springdatajpa.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class UserDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,// 设置数据库主键自增；
            generator = "JDBC") // generator 设置插入完成后，查询最后生成的 ID 填充到该属性中
    private Integer id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "create_time",nullable = false)
    private Date createTime;


}
