package com.lb.dao;

import com.lb.pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author LB
 * @create 2019-06-13 7:50
 */
public interface UserMapper {

    /**
     * 查询所有用户
     * @return
     */
    @Select("select *from user")
    List<User> findAll();

    /**
     * 根据id查询用户
     * @return
     */
    @Select("select *from user where user_id = #{id}")
    User findById();

    /**
     * 保存用户
     * @param user
     */
    @Insert(" insert into user (user_name,user_address,user_sex,user_birthday)values (#{userName},#{userAddress},#{userSex},#{userBirthday})")
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    @Update("update user set user_name=#{userName} where user_id = #{userId}")
    void updateUser(User user);

    @Delete("delete from user where user_id = #{id}")
    void deleteUser(int user_id);

    @Select("select * from user")
    @Results({
            @Result(id = true,column = "user_id",property = "userId"),
            @Result(column = "user_sex",property = "userSex"),
            @Result(column = "user_name",property = "userName"),
            @Result(column = "user_address",property = "userAddress"),
            @Result(column = "user_birthday",property = "userBirthday"),
            @Result(column = "user_id",property = "accounts",
                many = @Many(
                        select = "com.lb.dao.AccountMapper.findAccountByUid",
                        //一对多一般使用延迟加载
                        fetchType = FetchType.LAZY
                )

            )
    })
    List<User> findAllByAccount();
}
