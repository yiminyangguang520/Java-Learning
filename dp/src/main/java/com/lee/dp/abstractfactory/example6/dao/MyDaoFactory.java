package com.lee.dp.abstractfactory.example6.dao;

import com.lee.dp.abstractfactory.example6.dao.dao.DAOFactory;
import com.lee.dp.abstractfactory.example6.dao.impl.RdbDAOFactory;
import com.lee.dp.abstractfactory.example6.dao.impl.XmlDAOFactory;

public class MyDaoFactory {

  public static DAOFactory createDAOFactory(int type) {
    if (type == 1) {
      return new RdbDAOFactory();
    } else if (type == 2) {
      return new XmlDAOFactory();
    }
    return null;
  }
}
