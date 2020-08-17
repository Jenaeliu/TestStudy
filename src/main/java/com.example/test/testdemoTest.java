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
        // 1.ָ��ȫ�������ļ�
        String resource = "mybatis-config.xml";
        // 2.��ȡ�����ļ�
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 3.����sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //4.����sqlSession����sqlSessionFactory��ȡ

        SqlSession sqlSession=sqlSessionFactory.openSession();
//����CURD

        try{
            //5.����Ҫ��ָ��Ҫִ�е�sql���ı�ʶ��sqlӳ���ļ��е�namespace+"."+��ǩ��idֵ
            String sqlId="com.example.dao.UserDao"+"."+"queryUserAll";
            //6.ִ��sql���
          List<User> userList= sqlSession.selectList(sqlId,2);

          //7.������
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