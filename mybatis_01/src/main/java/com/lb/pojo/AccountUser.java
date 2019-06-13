package com.lb.pojo;

/**
 * @author LB
 * @create 2019-06-12 13:54
 */
public class AccountUser extends Account {
    private String userName;
    private String userAddress;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public String toString() {
        return super.toString()+"  AccountUser{" +
                "userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }
}
