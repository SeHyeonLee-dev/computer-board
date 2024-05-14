package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import action.BoardDeleteProAction;
import action.BoardDetailAction;
import action.BoardListAction;
import action.BoardModifyFormAction;
import action.BoardModifyProAction;
import action.BoardReplyFormAction;
import action.BoardReplyProAction;
import action.BoardSearchAction;
import action.BoardWriteProAction;
import dao.BoardDAO;
import vo.ActionForward;
import vo.BoardBean;
 
@WebServlet("*.bo")  //마지막 url이 *.bo로 끝나는 요청을 매핑하는 서블릿으로 지정하는 부분
public class BoardFrontController extends javax.servlet.http.HttpServlet 
{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		ActionForward forward=null;
		Action action=null;
 
		if(command.equals("/boardWriteForm.bo")){
			forward=new ActionForward();
			forward.setPath("/WEB-INF/views/qna_board_write.jsp");
		}
        else if(command.equals("/boardWritePro.bo")){
			action  = new BoardWriteProAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        else if(command.equals("/*.bo")){
			action = new BoardListAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/boardList.bo")){
			action = new BoardListAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/boardDetail.bo")){
			action = new BoardDetailAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/boardReplyForm.bo")){
			action = new BoardReplyFormAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/boardReplyPro.bo")){
			action = new BoardReplyProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/boardModifyForm.bo")){
			action = new BoardModifyFormAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
        else if(command.equals("/boardModifyPro.bo")){
			action = new BoardModifyProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
        //게시판 글 삭제 후 원래 목록 보기 페이지로 되돌아 가야한다.
        //때문에 게시판 삭제를 위해서 비밀번호를 입력하는 페이지(qna_board_delete.jsp)로
        //페이지 번호를 공유한다. (request.setAttribute("page",nowPage);)
        else if(command.equals("/boardDeleteForm.bo")){
			String nowPage = request.getParameter("page");
			request.setAttribute("page", nowPage);
			int board_num=Integer.parseInt(request.getParameter("board_num"));
			request.setAttribute("board_num",board_num);
			forward=new ActionForward();
			forward.setPath("/WEB-INF/views/qna_board_delete.jsp");
		}
		else if(command.equals("/boardDeletePro.bo")){
			action = new BoardDeleteProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		// BoardFrontController.java
		// ...

		else if (command.equals("/boardSearch.bo")) {
		    String column = request.getParameter("searchField");
		    String keyword = request.getParameter("searchText");

		    // 검색어와 컬럼 정보를 HashMap에 담아서 전달
		    HashMap<String, String> searchMap = new HashMap<>();
		    searchMap.put("searchField", column);
		    searchMap.put("searchText", keyword);

		    ArrayList<BoardBean> searchResult = new BoardDAO().search(searchMap);

		    // 검색 결과를 request에 저장
		    request.setAttribute("searchResult", searchResult);

		    // 검색 결과를 표시할 JSP 파일로 포워딩
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/board_search_result.jsp");
		    dispatcher.forward(request, response);
		}
		
		else if (command.equals("/LoginForm.bo")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/loginForm.jsp");
	    	dispatcher.forward(request, response);
		}
		
		else if (command.equals("/RegisterForm.bo")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/register.jsp");
	    	dispatcher.forward(request, response);
		}

		// ...

		
		if(forward != null){
			
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher dispatcher=
						request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
			
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request,response);
	}  	
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request,response);
	}   
	
}
