package com.lb.Dao;

import com.lb.pojo.Employee;
import com.lb.pojo.User;

import java.util.List;

/**
 * @author LB
 * @create 2019-06-09 15:40
 */
public interface UserMapper {
    List<User> findAll();
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
    User findByid(int id);
    List<User> findByName(String name);
    List<User> findByEmployee(Employee employee);
    List<User> findByCondition(User user);
    List<User> findByForEach(Employee employee);

    /**
     * 一对多
     * @return
     */
    List<User> findAccountUser();
}
