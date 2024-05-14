package action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.BoardListService;
import vo.ActionForward;
import vo.BoardBean;
import vo.PageInfo;

public class BoardListAction implements Action {
 
 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
	 
	ArrayList<BoardBean> boardList=new ArrayList<BoardBean>();
  	int page=1;
	int limit=10;
	
	if(request.getParameter("page")!=null){
		page=Integer.parseInt(request.getParameter("page"));
	}
	
	BoardListService boardListService = new BoardListService();
	int listCount=boardListService.getListCount(); //총 리스트 수를 받아옴.
	boardList = boardListService.getBoardList(page,limit); //리스트를 받아옴.
	
	int maxPage;    //총 페이지 수
	if(listCount==0) maxPage=1;
	else maxPage=(listCount-1)/limit+1;
		//하단 페이지 목록에서 보여질 시작 페이지 수(1, 11, 21 등...)
		int startPage = (page-1)/10 * 10 + 1;
		//현재 페이지 목록에서 보여질 마지막 페이지 수.(10, 20, 30 등...)
	    int endPage = startPage+10-1;
		if (endPage> maxPage) endPage= maxPage;

		PageInfo pageInfo = new PageInfo();
		pageInfo.setPage(page);
	pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
	pageInfo.setMaxPage(maxPage);
		
	request.setAttribute("pageInfo", pageInfo);
	request.setAttribute("boardList", boardList);
	
	ActionForward forward= new ActionForward();
		forward.setPath("/WEB-INF/views/qna_board_list.jsp");
		return forward;
		
 }
}
