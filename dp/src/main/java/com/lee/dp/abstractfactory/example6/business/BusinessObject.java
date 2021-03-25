package com.lee.dp.abstractfactory.example6.business;

import com.lee.dp.abstractfactory.example6.dao.MyDaoFactory;
import com.lee.dp.abstractfactory.example6.dao.dao.DAOFactory;
import com.lee.dp.abstractfactory.example6.dao.impl.OrderDetailDAO;
import com.lee.dp.abstractfactory.example6.dao.impl.OrderMainDAO;

public class BusinessObject {

  public static void main(String[] args) {
    //创建DAO的抽象工厂
    DAOFactory df = MyDaoFactory.createDAOFactory(2);

    //通过抽象工厂来获取需要的DAO接口
    OrderMainDAO mainDAO = df.createOrderMainDAO();
    OrderDetailDAO detailDAO = df.createOrderDetailDAO();
    //调用DAO来完成数据存储的功能
    mainDAO.saveOrderMain();
    detailDAO.saveOrderDetail();
  }
}
