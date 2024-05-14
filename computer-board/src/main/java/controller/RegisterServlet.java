package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.RegisterService;
import vo.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
    }

    // 회원가입 폼 요청 처리
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 회원가입 폼 페이지로 포워딩
        RequestDispatcher dispatcher = request.getRequestDispatcher("registerForm.jsp");
        dispatcher.forward(request, response);
    }

    // 회원가입 요청 처리
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 사용자로부터 입력받은 회원가입 정보 추출
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        String passwd = request.getParameter("passwd");

        // 회원가입 정보를 담은 Member 객체 생성
        User newMember = new User();
        newMember.setName(name);
        newMember.setId(id);
        newMember.setPasswd(passwd);

        // RegisterService를 통해 회원가입 처리
        RegisterService registerService = new RegisterService();
        boolean isSuccess = registerService.registerUser(newMember);

        if (isSuccess) {
            // 회원가입 성공 시 로그인 페이지로 리다이렉트
            response.sendRedirect("login.jsp");
        } else {
            // 회원가입 실패 시 실패 페이지로 포워딩
            RequestDispatcher dispatcher = request.getRequestDispatcher("registerFail.jsp");
            dispatcher.forward(request, response);
        }
    }
}


