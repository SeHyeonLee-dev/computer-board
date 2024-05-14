package service;

import static db.JdbcUtil.*;
import java.sql.Connection;
import vo.BoardBean;
import dao.BoardDAO;
 
public class BoardModifyProService {
 
	public boolean isBoardWriter(int board_num, String pass) throws Exception {
		//사용자가 해당 글을 작성한 사용자인지를 판단
		
		boolean isBoardWriter = false;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		isBoardWriter = boardDAO.isBoardWriter(board_num, pass);
		close(con);
		return isBoardWriter;
		
	}
 
	public boolean modifyBoard(BoardBean board) throws Exception {
		//글 수정 작업을 처리하는 메소드
        
		boolean isModifySuccess = false;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		int updateCount = boardDAO.updateBoard(board);
		//트랜잭션 처리하는 부분
		if(updateCount > 0){
			commit(con);
			isModifySuccess=true;
		}
		else{
			rollback(con);
		}
		close(con);
		return isModifySuccess;
	}
 
}
