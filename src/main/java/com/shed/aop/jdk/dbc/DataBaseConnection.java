package com.shed.aop.jdk.dbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;

public class DataBaseConnection {

    private static final ThreadLocal<Connection> CONNECTION_THREAD_LOCAL = new ThreadLocal<>();

    private DataBaseConnection() {}

    public static void rebuildConnection() {
        try {
            ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
            DataSource dataSource = (DataSource) app.getBean("dataSource");
            Connection conn = dataSource.getConnection();
            CONNECTION_THREAD_LOCAL.set(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = CONNECTION_THREAD_LOCAL.get();
        if (conn == null) {
            try {
                rebuildConnection();
                conn = CONNECTION_THREAD_LOCAL.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void close() {
        Connection conn = CONNECTION_THREAD_LOCAL.get() ;
        if (conn != null) {
            try {
                conn.close() ;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        CONNECTION_THREAD_LOCAL.remove() ;
    }
}
