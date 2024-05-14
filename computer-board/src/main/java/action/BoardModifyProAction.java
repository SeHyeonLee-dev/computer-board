package action;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.BoardModifyProService;
import vo.ActionForward;
import vo.BoardBean;

public class BoardModifyProAction implements Action {
	 
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
 
		ActionForward forward = null;
		boolean isModifySuccess = false;
		int board_num=Integer.parseInt(request.getParameter("BOARD_NUM"));
		BoardBean board = new BoardBean();
		BoardModifyProService boardModifyProService = new BoardModifyProService();
		
		boolean isRightUser=boardModifyProService.isBoardWriter(board_num, request.getParameter("BOARD_PASS"));
		if(!isRightUser){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		else{
			board.setBOARD_NUM(board_num);
			board.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
			board.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT")); 
			isModifySuccess = boardModifyProService.modifyBoard(board);
 
			if(!isModifySuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('수정실패');");
				out.println("history.back()");
				out.println("</script>");
			}
			else{
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("boardDetail.bo?board_num="+board.getBOARD_NUM()); 
			}
		}
		return forward;
	}
}
