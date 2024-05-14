package action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import service.BoardListService;
import vo.ActionForward;
import vo.BoardBean;
import vo.PageInfo;

//BoardSearchAction.java
public class BoardSearchAction implements Action {
 @Override
 public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
     // 사용자의 검색 요청을 처리하는 코드 작성
     // BoardDAO의 list 메서드를 활용하여 검색 결과를 가져온다.
     // 가져온 결과를 request에 저장하고, 검색 결과를 표시할 JSP로 포워딩한다.

     String column = request.getParameter("column");
     String search = request.getParameter("search");

     // 검색 관련 파라미터를 HashMap에 담기
     HashMap<String, String> searchMap = new HashMap<>();
     searchMap.put("isSearch", "y");
     searchMap.put("column", column);
     searchMap.put("search", search);

     // BoardDAO를 통해 검색 결과를 얻기
     BoardDAO boardDAO = new BoardDAO();
     ArrayList<BoardBean> searchResult = boardDAO.search(searchMap);

     // 검색 결과를 request에 저장
     request.setAttribute("searchResult", searchResult);

     // 검색 결과를 표시할 JSP 파일로 포워딩
     ActionForward forward = new ActionForward();
     forward.setPath("/WEB-INF/views/qna_board_search.jsp");
     return forward;
 }
}

