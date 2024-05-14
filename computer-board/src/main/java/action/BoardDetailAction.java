package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.BoardDetailService;
import vo.ActionForward;
import vo.BoardBean;
 
 public class BoardDetailAction implements Action {
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
	   	
		int board_num=Integer.parseInt((request.getParameter("board_num")).trim());  //글 번호 받아옴
		String page = request.getParameter("page");                         //페이지 번호 받아옴
		
		BoardDetailService boardDetailService = new BoardDetailService();   
		BoardBean board = boardDetailService.getBoard(board_num);           //글 번호를 토대로 게시글 받아옴
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
	   	request.setAttribute("board", board);
   		forward.setPath("/WEB-INF/views/qna_board_view.jsp");
   		return forward;
 
	 }
}
