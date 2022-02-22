package com.shed.aop.cglib.test;

import com.shed.aop.cglib.bean.Message;
import com.shed.aop.cglib.factory.ProxyFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringTest {

    @Test
    public void test() {
        ApplicationContext app = new AnnotationConfigApplicationContext("com.shed.aop.cglib");
        Message msg = (Message) app.getBean("message");
        msg.send("114514");
    }

    @Test
    public void test1() {
        Message msg = ProxyFactory.getInstance(new AnnotationConfigApplicationContext("com.shed.aop.cglib"));
        msg.send("啊啊啊");
    }
}
