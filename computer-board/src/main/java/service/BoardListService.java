package service;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import dao.BoardDAO;
import vo.BoardBean;
 
public class BoardListService {
 
	public int getListCount() throws Exception{
		// TODO Auto-generated method stub
		
		int listCount = 0;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		listCount = boardDAO.selectListCount();  //DB에서 총 게시판 글의 개수를 반환받음
		close(con);
		return listCount;
		
	}
 
	public ArrayList<BoardBean> getBoardList(int page, int limit) throws Exception{
		
		ArrayList<BoardBean> boardList = null;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		boardList = boardDAO.selectBoardList(page,limit);
		close(con);
		return boardList;
		
	}
 
}
