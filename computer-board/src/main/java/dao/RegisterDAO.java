package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import vo.User;

public class RegisterDAO {

    private static RegisterDAO registerDAO;
    private Connection con;

    private RegisterDAO() {
        // 생성자 내용
    }

    // 싱글톤 패턴 적용
    public static RegisterDAO getInstance() {
        if (registerDAO == null) {
            registerDAO = new RegisterDAO();
        }
        return registerDAO;
    }

    // 외부에서 Connection을 주입하기 위한 메서드
    public void setConnection(Connection con) {
        this.con = con;
    }

    // 회원가입 정보를 DB에 저장하는 메서드
    public boolean registerMember(User newMember) {
        PreparedStatement pstmt = null;
        boolean isSuccess = false;

        try {
            // SQL 쿼리 작성 (테이블명, 컬럼명은 실제 상황에 맞게 수정)
            String sql = "INSERT INTO users (name, id, passwd) VALUES (?, ?, ?)";

            // PreparedStatement 객체 생성
            pstmt = con.prepareStatement(sql);

            // 매개변수로 받은 Member 객체에서 값을 꺼내와서 SQL 쿼리에 설정
            pstmt.setString(1, newMember.getName());
            pstmt.setString(2, newMember.getId());
            pstmt.setString(3, newMember.getPasswd());

            // SQL 실행 (INSERT, UPDATE, DELETE는 executeUpdate 사용)
            int result = pstmt.executeUpdate();

            // 결과가 1이면 성공
            if (result == 1) {
                isSuccess = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // 필요에 따라 예외처리 추가
        } finally {
            // 자원 해제
            close(pstmt);
        }

        return isSuccess;
    }

    // 자원을 닫는 메서드 (Connection, Statement, ResultSet 등)
    private void close(AutoCloseable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
