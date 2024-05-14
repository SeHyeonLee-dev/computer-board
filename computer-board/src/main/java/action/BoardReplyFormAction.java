package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.BoardDetailService;
import vo.ActionForward;
import vo.BoardBean;
 
public class BoardReplyFormAction implements Action {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 	ActionForward forward = new ActionForward();
	   		String nowPage = request.getParameter("page");
	   		int board_num=Integer.parseInt(request.getParameter("board_num"));
	   		
	   		BoardDetailService boardDetailService = new BoardDetailService();
	   		BoardBean board = boardDetailService.getBoard(board_num);	
	   		request.setAttribute("board", board);
	   		request.setAttribute("page", nowPage);
	   		forward.setPath("/WEB-INF/views/qna_board_reply.jsp");
	   		return forward;
	}
}
