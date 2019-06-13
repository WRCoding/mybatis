package com.lb.dao;

import com.lb.pojo.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author LB
 * @create 2019-06-13 8:47
 */
public interface AccountMapper {

    @Select("select *from account")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "uid",property = "uid"),
            @Result(column = "money",property = "money"),
            //通过account的uid查找user表的用户
            @Result(column = "uid",property = "user",
                one = @One(
                        select = "com.lb.dao.UserMapper.findById",
                        //一对一一般使用立即加载
                        fetchType = FetchType.EAGER
                )
            )
    })
    List<Account> findAll();

    @Select("select * from account where uid = #{userId}")
    List<Account> findAccountByUid(int userId);
}
