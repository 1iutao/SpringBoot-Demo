package springboot.demo.demo01.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // 创建Security配置类继承WebSecurityConfiguration抽象类

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth
                //使用内存中的inMemoryUserDetailsManager
                .inMemoryAuthentication()
                //不使用PasswordEncoder编辑器
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                //配置管理员账户
                .withUser("admin").password("123456").roles("ADMIN")
                //配置用户账户
                .and().withUser("user").password("123456").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //配置地址请求权限
                .authorizeRequests()
                .antMatchers("/test/echo").permitAll()  //ALL
                .antMatchers("/test/admin").hasRole("ADMIN")  //ADMIN
                .antMatchers("/test/user").access("hasRole('ROLE_USER')")  //USER
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")  //登录的url地址
                .permitAll() //all可以访问

                .and()
                .logout()
                .logoutUrl("/logout")  //退出url地址
                .permitAll();
    }
}
