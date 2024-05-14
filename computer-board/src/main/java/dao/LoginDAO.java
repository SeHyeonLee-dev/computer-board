package dao;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.JdbcUtil;
import vo.User;

public class LoginDAO {
	
	private static LoginDAO loginDAO;
	private Connection con;
	
	private LoginDAO() {
		
	}
	
	public static LoginDAO getInstance() {
		if(loginDAO == null) {
			loginDAO = new LoginDAO();
		}
		return loginDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public static User selectLoginUser(String id, String passwd) {
        User loginUser = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = JdbcUtil.getConnection();
            pstmt = con.prepareStatement("SELECT * FROM users WHERE id = ? AND passwd = ?");
            pstmt.setString(1, id);
            pstmt.setString(2, passwd);
            rs = pstmt.executeQuery();

            if (rs.next()) {
            	loginUser = new User();
            	loginUser.setId(rs.getString("id"));
            	loginUser.setPasswd(rs.getString("passwd"));
            	loginUser.setName(rs.getString("name"));
                // 나머지 필드도 가져와서 설정
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // close 등의 자원 해제 로직 추가
        }

        return loginUser;
    }

	
	private void close(ResultSet rs, PreparedStatement pstmt) {
		try {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
