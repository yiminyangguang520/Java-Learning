package org.spring.springboot.domain;


import java.util.Date;
import java.util.List;

/**
 * Created by litz-a on 2017/5/24.
 */
public class User {

    private Integer id;
    private String userName;
    private String userNumber;
    private Date loginName;
    private String password;
    private String sex;
    private String birthday;
    private List<Order> orderList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public Date getLoginName() {
        return loginName;
    }

    public void setLoginName(Date loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return password;
    }

    public void setLoginPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

}
