package service;

import java.sql.Connection;

import dao.RegisterDAO;
import db.JdbcUtil;
import vo.User;

//RegisterService.java

public class RegisterService {
 public boolean registerUser(User newMember) {
     RegisterDAO registerDAO = RegisterDAO.getInstance();
     Connection con = JdbcUtil.getConnection();
     registerDAO.setConnection(con);
     
     boolean isSuccess = registerDAO.registerMember(newMember);
     
     JdbcUtil.close(con);
     return isSuccess;
 }
}

