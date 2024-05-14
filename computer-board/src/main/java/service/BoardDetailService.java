package service;

import java.sql.Connection;
import dao.BoardDAO;
import vo.BoardBean;
import static db.JdbcUtil.*;
 
public class BoardDetailService {
 
	public BoardBean getBoard(int board_num) throws Exception{
		// TODO Auto-generated method stub
		
		BoardBean board = null;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		//조회수 증가 update 작업의 트랜잭션 처리해주는 부분
		int updateCount = boardDAO.updateReadCount(board_num);
		
		if(updateCount > 0){
			commit(con);
		}
		else{
			rollback(con);
		}
		
		board = boardDAO.selectBoard(board_num);
		close(con);
		return board;
		
	}
 
}
