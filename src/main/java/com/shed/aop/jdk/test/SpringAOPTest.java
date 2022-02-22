package com.shed.aop.jdk.test;

import com.shed.aop.jdk.factory.ProxyFactory;
import com.shed.aop.jdk.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringAOPTest {

    @Autowired
    private UserService userService;

    @Test
    public void test1() {
        ApplicationContext app = new AnnotationConfigApplicationContext("com.shed");
        UserService userService = (UserService) app.getBean("userService");
        userService.addUser();
    }

    @Test
    public void test2() throws SQLException {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = (DataSource) app.getBean("dataSource");
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void test3() {
        UserService userService = ProxyFactory.getServiceInstance(
                                            new AnnotationConfigApplicationContext("com.shed"));
        userService.addUser();
    }
}
