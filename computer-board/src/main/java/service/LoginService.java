package service;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.LoginDAO;
import db.JdbcUtil;
import vo.User;

public class LoginService {
    public User getLoginUser(String id, String passwd) {
        LoginDAO loginDAO = LoginDAO.getInstance();
        try (Connection con = JdbcUtil.getConnection()) {
            loginDAO.setConnection(con);
            User loginUser = LoginDAO.selectLoginUser(id, passwd);
            return loginUser;
        } catch (Exception e) {
            e.printStackTrace();
            // 예외 처리를 프로젝트에 맞게 수정해주세요.
        }
        return null;
    }

}
