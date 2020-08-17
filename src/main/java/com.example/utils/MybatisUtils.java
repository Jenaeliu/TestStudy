package com.example.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // 1.指定全局配置文件
            String resource = "mybatis-config.xml";
            // 2.读取配置文件
            InputStream inputStream = Resources.getResourceAsStream(resource);
            // 3.构建sqlSessionFactory
             sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //4.构建sqlSession，从sqlSessionFactory获取

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
