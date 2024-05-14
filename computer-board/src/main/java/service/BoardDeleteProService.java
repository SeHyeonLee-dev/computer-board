package service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;
import java.sql.Connection;
import dao.BoardDAO;
 
public class BoardDeleteProService {
 
	public boolean isBoardWriter(int board_num, String pass) throws Exception {
		// 사용자가 해당 글을 작성한 사용자인지를 판단
		
		boolean isBoardWriter = false;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		isBoardWriter = boardDAO.isBoardWriter(board_num, pass);
		close(con);
		return isBoardWriter;
		
	}
 
	public boolean removeBoard(int board_num) throws Exception{
		// 글 삭제 요청을 처리하는 메소드
		
		boolean isRemoveSuccess = false;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		int deleteCount = boardDAO.deleteBoard(board_num);
		//트랜잭션 처리하는 부분
		if(deleteCount > 0){
			commit(con);
			isRemoveSuccess=true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isRemoveSuccess;
	}
 
}
