package dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import db.JdbcUtil;
import vo.BoardBean;
 
public class BoardDAO {
 
	private Connection con;
	private static BoardDAO boardDAO; //외부 클래스에서 접근하지 못하도록 private로 지정
 
	public BoardDAO() {  
		this.con = JdbcUtil.getConnection();
	}
	
    //싱글톤 패턴
	public static BoardDAO getInstance(){   
		if(boardDAO == null){       
			boardDAO = new BoardDAO();
		}
		return boardDAO;
	}
    
    //con 연결
	public void setConnection(Connection con){
		this.con = con;
	}
 
 
 
	public int selectListCount() {
		 
		int listCount= 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query="select count(*) from board";
 
		try{
			pstmt=con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				listCount=rs.getInt(1);
			}
			
		}catch(Exception ex){
			System.out.println("getListCount 에러: " + ex);			
		}finally{
			close(rs);
			close(pstmt);
		}
 
		return listCount;
	}
    
	public ArrayList<BoardBean> selectBoardList(int page,int limit){
		 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String listQuery="select * from board order by BOARD_RE_REF desc,BOARD_RE_SEQ asc limit ?,10";
		ArrayList<BoardBean> boardList = new ArrayList<BoardBean>();
		BoardBean board = null;
		int startrow=(page-1)*10; //각 page 첫 번째 목록 글 번호	
 
		try{
			pstmt = con.prepareStatement(listQuery);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();
 
			while(rs.next()){
				board = new BoardBean();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_FILE(rs.getString("BOARD_FILE"));
				board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
				boardList.add(board);
			}
 
		}catch(Exception ex){
			System.out.println("getBoardList error : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}
		return boardList;
	}
	
	public BoardBean selectBoard(int board_num){
		 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardBean boardBean = null;
		String query = "select * from board where BOARD_NUM = ?";
 
		try{
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, board_num);
			rs= pstmt.executeQuery();
 
			if(rs.next()){
				boardBean = new BoardBean();
				boardBean.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				boardBean.setBOARD_NAME(rs.getString("BOARD_NAME"));
				boardBean.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				boardBean.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				boardBean.setBOARD_FILE(rs.getString("BOARD_FILE"));
				boardBean.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				boardBean.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				boardBean.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				boardBean.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				boardBean.setBOARD_DATE(rs.getDate("BOARD_DATE"));
			}
		}catch(Exception ex){
			System.out.println("getDetail error : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}
		return boardBean;
	}
	
	//게시글 등록.
		public int insertBoard(BoardBean board){
	 
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int num =0;
			String query="";
			int insertCount=0;
	 
			try{
				//새로 글이 등록될 게시글 번호
				pstmt=con.prepareStatement("select max(board_num) from board");
				rs = pstmt.executeQuery();
				if(rs.next())
					num =rs.getInt(1)+1;
				else
					num=1;
	 
				query="insert into board (BOARD_NUM,BOARD_NAME,BOARD_PASS,BOARD_SUBJECT,"
						+ "BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF,"
						+ "BOARD_RE_LEV,BOARD_RE_SEQ,BOARD_READCOUNT,"
						+ "BOARD_DATE) values(?,?,?,?,?,?,?,?,?,?,now())";
	 
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, num);
				pstmt.setString(2, board.getBOARD_NAME());
				pstmt.setString(3, board.getBOARD_PASS());
				pstmt.setString(4, board.getBOARD_SUBJECT());
				pstmt.setString(5, board.getBOARD_CONTENT());
				pstmt.setString(6, board.getBOARD_FILE());
				pstmt.setInt(7, num);
				pstmt.setInt(8, 0);
				pstmt.setInt(9, 0);
				pstmt.setInt(10, 0);
	 
				insertCount=pstmt.executeUpdate();
	 
			}catch(Exception ex){
				System.out.println("boardInsert 에러 : "+ex);
			}finally{
				close(rs);
				close(pstmt);
			}
	 
			return insertCount;
	 
		}
		
		//게시글 답변
		public int insertReplyBoard(BoardBean board){
	 
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String maxBoardNumQuery="select max(board_num) from board";
			String query="";
			int num=0;
			int insertCount=0;
			int re_ref=board.getBOARD_RE_REF();
			int re_lev=board.getBOARD_RE_LEV();
			int re_seq=board.getBOARD_RE_SEQ();
	 
			try{
				pstmt=con.prepareStatement(maxBoardNumQuery);
				rs = pstmt.executeQuery();
				if(rs.next())num =rs.getInt(1)+1;
				else num=1;
				
				//답변글의 번호가 입력되는 부분
				query="update board set BOARD_RE_SEQ=BOARD_RE_SEQ+1 "
						+ "where BOARD_RE_REF=? and BOARD_RE_SEQ>?";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1,re_ref);
				pstmt.setInt(2,re_seq);
				int updateCount=pstmt.executeUpdate();
	 
				if(updateCount > 0){
					commit(con);
				}
	 
				re_seq = re_seq + 1;
				re_lev = re_lev + 1;
	            
				query="insert into board (BOARD_NUM,BOARD_NAME,BOARD_PASS,BOARD_SUBJECT,"
						+ "BOARD_CONTENT, BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,BOARD_RE_SEQ,"
						+ "BOARD_READCOUNT,BOARD_DATE) values(?,?,?,?,?,?,?,?,?,?,now())";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, num);
				pstmt.setString(2, board.getBOARD_NAME());
				pstmt.setString(3, board.getBOARD_PASS());
				pstmt.setString(4, board.getBOARD_SUBJECT());
				pstmt.setString(5, board.getBOARD_CONTENT());
				pstmt.setString(6, ""); //답변에는 파일 업로드 기능 없음.
				pstmt.setInt(7, re_ref);
				pstmt.setInt(8, re_lev);
				pstmt.setInt(9, re_seq);
				pstmt.setInt(10, 0);
				insertCount = pstmt.executeUpdate();
			}catch(SQLException ex){
				System.out.println("boardReply error : "+ex);
			}finally{
				close(rs);
				close(pstmt);
			}
	 
			return insertCount;
	 
		}
		
		//게시글 수정
		public int updateBoard(BoardBean board){
	 
			int updateCount = 0;
			PreparedStatement pstmt = null;
			String query="update board set BOARD_SUBJECT=?,BOARD_CONTENT=? where BOARD_NUM=?";
	 
			try{
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, board.getBOARD_SUBJECT());
				pstmt.setString(2, board.getBOARD_CONTENT());
				pstmt.setInt(3, board.getBOARD_NUM());
				updateCount = pstmt.executeUpdate();
			}catch(Exception ex){
				System.out.println("boardModify 에러 : " + ex);
			}finally{
				close(pstmt);
			}
			return updateCount;
		}
		
		//게시글 삭제
		public int deleteBoard(int board_num){
	 
			PreparedStatement pstmt = null;
			String deleteQuery="delete from board where BOARD_num=?";
			int deleteCount=0;
	 
			try{
				pstmt=con.prepareStatement(deleteQuery);
				pstmt.setInt(1, board_num);
				deleteCount=pstmt.executeUpdate();
			}catch(Exception ex){
				System.out.println("boardDelete 에러 : "+ex);
			}	finally{
				close(pstmt);
			}
			return deleteCount;
		}
		
		//조회수 업데이트.
		public int updateReadCount(int board_num){
	 
			PreparedStatement pstmt = null;
			int updateCount = 0;
			String sql="update board set BOARD_READCOUNT = "
					+ "BOARD_READCOUNT+1 where BOARD_NUM = "+ board_num;
	 
			try{
				pstmt=con.prepareStatement(sql);
				updateCount = pstmt.executeUpdate();
			}catch(SQLException ex){
				System.out.println("setReadCountUpdate 에러 : "+ex);
			}
			finally{
				close(pstmt);
	 
			}
			return updateCount;
		}
		
		//글쓴이인지 확인.
		public boolean isBoardWriter(int board_num,String pass){
	 
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String boardQuery="select * from board where BOARD_NUM=?";
			boolean isWriter = false;
	 
			try{
				pstmt=con.prepareStatement(boardQuery);
				pstmt.setInt(1, board_num);
				rs=pstmt.executeQuery();
				rs.next();
	 
				if(pass.equals(rs.getString("BOARD_PASS"))){
					isWriter = true;
				}
			}catch(SQLException ex){
				System.out.println("isBoardWriter 에러 : "+ex);
			}
			finally{
				close(pstmt);
			}
			return isWriter;
		}
		
		public ArrayList<BoardBean> search(HashMap<String, String> searchMap) {
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;

		    String column = searchMap.get("searchField");
	        String keyword = searchMap.get("searchText");
	        
	        System.out.println(column);
	        System.out.println(keyword); 
	        String sql = "SELECT * FROM board WHERE " + column + " LIKE ?";
	        System.out.println(sql); // 여기까진 문제 X
	        
		    try {
		        pstmt = con.prepareStatement(sql);
		        pstmt.setString(1, "%" + keyword + "%");
		        rs = pstmt.executeQuery();
		 

		        ArrayList<BoardBean> list = new ArrayList<>();

		        while (rs.next()) {
		            BoardBean dto = new BoardBean();
		            dto.setBOARD_NUM(rs.getInt(1));
		            dto.setBOARD_NAME(rs.getString(2));
		            dto.setBOARD_SUBJECT(rs.getString(4));
		            dto.setBOARD_READCOUNT(rs.getInt(10));
		            dto.setBOARD_DATE(rs.getDate(11));

		            list.add(dto);
		        }

		        return list;

		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        // 사용한 자원을 반납
		        try {
		            if (rs != null) rs.close();
		            if (pstmt != null) pstmt.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    return null;
		}



    
}
