package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.BoardDetailService;
import vo.ActionForward;
import vo.BoardBean;
 
public class BoardModifyFormAction implements Action {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 	ActionForward forward = new ActionForward();
			int board_num=Integer.parseInt(request.getParameter("board_num"));   //글 번호 받아옴
			
			BoardDetailService boardDetailService = new BoardDetailService();	
		   	BoardBean board = boardDetailService.getBoard(board_num);             //글 번호를 토대로 게시글 받아옴
		   	System.out.println(board);
		   	request.setAttribute("board", board);
	   		forward.setPath("/WEB-INF/views/qna_board_modify.jsp");
	   		return forward;
	   		
	 }
}
