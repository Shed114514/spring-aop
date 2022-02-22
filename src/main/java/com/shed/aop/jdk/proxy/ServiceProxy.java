package com.shed.aop.jdk.proxy;

import com.shed.aop.jdk.dbc.DataBaseConnection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ServiceProxy implements InvocationHandler {
    // 目标业务类
    private Object target;

    // 定义需要执行事务控制的方法头名称
    private static final List<String> TRANSACTION_METHOD_HEAD = List.of("add","create","edit","update",
                                                                        "remove","delete");

    // 匹配需要执行事务控制的方法
    public boolean openTransaction(String methodName) {
        for (String head : TRANSACTION_METHOD_HEAD) {
            if (methodName.startsWith(head)) {
                return true;
            }
        }
        return false;
    }

    //绑定目标业务类对象,并返回代理对象
    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(this.target.getClass().getClassLoader(),
                                      this.target.getClass().getInterfaces(),
                                   this);
    }

    // Pointcut与Advice结合形成一个切面Aspect
    // 实现数据库的事务控制
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object returnValue = null;
        // 拦截需要开启事务控制方法(Joincut)
        boolean transactionFlag = this.openTransaction(method.getName());
        if (transactionFlag) {
            DataBaseConnection.getConnection().setAutoCommit(false);
        }
        try {
            // 若无异常则执行事务提交
            returnValue = method.invoke(this.target,args);
            if (transactionFlag) {
                System.out.println("[Transaction]执行事务提交...");
                DataBaseConnection.getConnection().commit();
            }
        } catch (Exception e) {
            // 若有异常则执行事务回滚
            if (transactionFlag) {
                System.out.println("[Transaction]执行事务回滚...");
                DataBaseConnection.getConnection().rollback();
            }
            e.printStackTrace();
        } finally {
            DataBaseConnection.close();
        }
        return returnValue;
    }
}
