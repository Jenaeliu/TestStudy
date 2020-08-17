package com.example.test;

import com.example.dao.UserDao;
import com.example.entity.User;
import com.example.utils.MybatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {

    /**
     * 查询数据
     */
    @org.junit.Test
    public void queryUserAll() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {

            //5.【重要】指定要执行的sql语句的标识，sql映射文件中的namespace+"."+标签的id值
            String sqlId="com.example.dao.UserDao"+"."+"queryUserAll";
            //6.执行sql语句
            List<User> userList = sqlSession.selectList(sqlId);

            //7.遍历结果
            for (User user : userList) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

    }

    /**
     * 查询数据
     */
    @org.junit.Test
    public void queryUserDepMapper() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao=sqlSession.getMapper(UserDao.class);
        try {


           // List<User> userList = userDao.queryUserDepMapper();
           // List<User> userList2 = userDao.queryUserDepMapper2(1);
            List<User> userList2 = userDao.selectById();
            //7.遍历结果
            for (User user : userList2) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

    }
    /**
     * 查询一条数据
     */
    @org.junit.Test
    public void findById(){
        SqlSession sqlSession=MybatisUtils.getSqlSession();
        try {
            String sqlId="com.example.dao.UserDao"+"."+"findById";
            User userList=sqlSession.selectOne(sqlId,1);
            System.out.println(userList);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 添加数据
     */
    @org.junit.Test
    public void addUser() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {

            //5.【重要】指定要执行的sql语句的标识，sql映射文件中的namespace+"."+标签的id值
            String sqlId = "com.example.dao.UserDao" + "." + "addUser";
            //执行sql语句


            User user = new User();
            //user.setId(3);
            user.setUser_name("xl");
            user.setAge(24);
            user.setName("小刘");
            user.setPsd("1233");
            user.setSex(1);
            String time = "1993-12-13";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            date = sdf.parse(time);
           user.setBirthday(date);
            int flag=sqlSession.insert(sqlId,user);

            //7.输出结果
            System.out.println(user);
//mybatis默认不提交事务，需要在insert,update,delete后手动提交
            sqlSession.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

    }


    /**
     * 修改数据
     */
@org.junit.Test
    public void updateUser() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {

            //5.【重要】指定要执行的sql语句的标识，sql映射文件中的namespace+"."+标签的id值
            String sqlId = "com.example.dao.UserDao" + "." + "updateUser";
            //执行sql语句
            User user = new User();
            user.setName("李");
            user.setUser_name("ls");
            //user.setAge(26);
            user.setSex(2);
            user.setId(12);
            user.setPsd("111111");
           String time = "1993-12-13";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            date = sdf.parse(time);
            user.setBirthday(date);
            int flag=sqlSession.update(sqlId,user);

            //7.输出结果
            System.out.println(user);
//mybatis默认不提交事务，需要在insert,update,delete后手动提交
            sqlSession.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

    }


    /**
     * 删除数据
     */
    @org.junit.Test
    public void deleteUser() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {

            //5.【重要】指定要执行的sql语句的标识，sql映射文件中的namespace+"."+标签的id值
            String sqlId="com.example.dao.UserDao"+"."+"deleteUser";
            //6.执行sql语句
           int num= sqlSession.delete(sqlId,12);

                System.out.println(num);
                sqlSession.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

    }

    /**
     * 分页查询
     */
    @org.junit.Test
    public void selectByPage(){
        SqlSession sqlSession=MybatisUtils.getSqlSession();
        UserDao userDao=sqlSession.getMapper(UserDao.class);
        List<User> userList=new ArrayList<User>();
        int pageSize=2;
        int pageIndex=0;
        try{
            String sql="com.example.dao.UserDao"+"."+"selectCount";
            int total=sqlSession.selectOne(sql);
            int totalPage=(int)Math.ceil(total/pageSize);
            int startIndex=pageIndex*pageSize;
           // Map<String,Object> params = new LinkedHashMap<String,Object>();
            //当sql的条件有模糊匹配时，参数需前后带上%
          //  params.put("param1", startIndex);
           // params.put("param2", pageSize);
            userList=userDao.selectByPage(startIndex,pageSize);
          //  String sql2="com.example.dao.UserDao"+"."+"findByPage1";
          //  userList=sqlSession.selectList(sql2,params);
            for (User user:userList){
                System.out.println(user);
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }

    }


    /**
     * sql动态查询
     */
    @org.junit.Test
    public void selectByUserSelective(){
        SqlSession sqlSession=MybatisUtils.getSqlSession();
        UserDao userDao=sqlSession.getMapper(UserDao.class);
        User search=new User();
        search.setName("张");

        System.out.println("只有名字时的查询");
        List<User> userList=userDao.selectByUserSelective(search);
        for (int i=0;i<userList.size();i++){
            System.out.println(userList.get(i));
        }

        search.setName(null);
        search.setSex(1);
        System.out.println("只有性别时的查询：");
        List<User> userBySex=userDao.selectByUserSelective(search);
        for (int i=0;i<userBySex.size();i++){
            System.out.println(userBySex.get(i));
        }



        search.setName("小");
        search.setSex(2);
        System.out.println("性别、性别都有时的查询：");
        List<User> userBySexAndName=userDao.selectByUserSelective(search);
        for (int i=0;i<userBySexAndName.size();i++){
            System.out.println(userBySexAndName.get(i));
        }
        sqlSession.close();
    }
}



