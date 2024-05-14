package service;

import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.BoardDAO;
import vo.BoardBean;

public class BoardReplyProService {

public boolean replyBoard(BoardBean board) throws Exception{
	
	boolean isReplySuccess = false;
	int insertCount = 0;
	Connection con = getConnection();
	BoardDAO boardDAO = BoardDAO.getInstance();
	boardDAO.setConnection(con);
	insertCount = boardDAO.insertReplyBoard(board);
	
	if(insertCount > 0){
		commit(con);
		isReplySuccess = true;
	}
	else{
		rollback(con);
	}
	
	close(con);
	return isReplySuccess;
	
}

}
