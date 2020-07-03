package com.michael.demo.mybatis.simple;

import org.apache.ibatis.executor.result.DefaultMapResultHandler;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2020/7/1 9:25
 */
public class Main {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config-simple.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            System.err.println("####################################");
            List<Employee> all = employeeMapper.getAll();
            System.err.println("####################################");
            sqlSession2.getMapper(EmployeeMapper.class).getAll();
            for (Employee item : all) {
                System.out.println(item);
            }
        } finally {
            sqlSession.close();
            sqlSession2.close();
        }
    }
}
