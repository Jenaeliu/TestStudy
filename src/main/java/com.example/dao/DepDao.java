package com.example.dao;

import com.example.entity.Dep;
import com.example.entity.User;

import java.util.List;

public interface DepDao {
    public List<Dep> selectAll(Dep dep);
}
