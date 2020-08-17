package com.example.test;

import com.example.dao.UserDao;
import com.example.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class testdemoTest{
    @Test
    public void test() throws IOException {
        // 1.指定全局配置文件
        String resource = "mybatis-config.xml";
        // 2.读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 3.构建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //4.构建sqlSession，从sqlSessionFactory获取

        SqlSession sqlSession=sqlSessionFactory.openSession();
//构建CURD

        try{
            //5.【重要】指定要执行的sql语句的标识，sql映射文件中的namespace+"."+标签的id值
            String sqlId="com.example.dao.UserDao"+"."+"queryUserAll";
            //6.执行sql语句
          List<User> userList= sqlSession.selectList(sqlId,2);

          //7.输出结果
           // for(User user:userList) {
                System.out.println(userList);

           // }

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            sqlSession.close();

        }

    }
}