package com.lb.Dao;

import com.lb.pojo.Account;
import com.lb.pojo.AccountUser;

import java.util.List;

/**
 * @author LB
 * @create 2019-06-12 13:32
 */
public interface AccountMapper {

    List<Account> findAll();

    List<AccountUser> findAllAccountUser();
}
