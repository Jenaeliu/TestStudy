package com.example.dao;

import com.example.entity.User;

import java.util.List;
//操作接口
public interface UserDao {
    //int表示执行后，影响数据库的行数
    public void addUser(User user);

    public void deleteUser(User user);

    public void updateUser(User user);

    public User queryUserById(Integer id);

    public List<User> selectByUserSelective(User user);

    public List<User> queryUserDepMapper();
    public List<User> queryUserDepMapper2(Integer id);
    public List<User> selectById();

    public List<User> selectByPage(int pageIndex,int pageSize);
//查询表中所有数据，返回一个集合，保存User对象
    public List<User> queryUserAll();
}
