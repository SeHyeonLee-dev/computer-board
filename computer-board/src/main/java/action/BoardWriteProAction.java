package action;

import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.BoardWriteProService;
import vo.ActionForward;
import vo.BoardBean;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
 
public class BoardWriteProAction implements Action {
 
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
 
		ActionForward forward=null;
		BoardBean boardBean = null;
		String realFolder="";              //서버 상의 파일 경로를 저장할 실제 경로를 저장할 변수
		String saveFolder="/boardUpload";  //파일을 업로드할 디렉토리 명 지정한 부분
		int fileSize=5*1024*1024;          //업로드할 파일 사이즈를 정의한 부분
		ServletContext context = request.getServletContext();
		realFolder=context.getRealPath(saveFolder);           //서버 상의 실제 경로
		MultipartRequest multi=new MultipartRequest(          //파일 업로드 담당 클래스
				request,
				realFolder,
				fileSize,
				"UTF-8",
				new DefaultFileRenamePolicy());
		
		boardBean = new BoardBean();       //새로 등록할 게시글 정보를 BoardBean 객체 할당
		boardBean.setBOARD_NAME(multi.getParameter("BOARD_NAME"));
		boardBean.setBOARD_PASS(multi.getParameter("BOARD_PASS"));
		boardBean.setBOARD_SUBJECT(multi.getParameter("BOARD_SUBJECT"));
		boardBean.setBOARD_CONTENT(multi.getParameter("BOARD_CONTENT"));
		boardBean.setBOARD_FILE(
		multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		
		
		BoardWriteProService boardWriteProService = new BoardWriteProService();
		//게시글 등록 성공 여부
		boolean isWriteSuccess = boardWriteProService.registBoard(boardBean);
		if(!isWriteSuccess){  //글 등록 작업이 실패했을 등록실패 했다 출력 후, 이전 페이지로 되돌아가게 처리하는 부분
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back();");
			out.println("</script>");
		}
		else{   //글 등록 성공 후 목록 보기 요청 하는 부분.
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("boardList.bo");
		}
		return forward;
	}  	
}
