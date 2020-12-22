package co.micol.member.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.micol.member.dao.MemberDao;
import co.micol.member.vo.MemberVo;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login.do")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 여기서 로그인과 관련된것을 처리 한다
		request.setCharacterEncoding("utf-8");		
		MemberDao dao = new MemberDao();
		MemberVo vo = new MemberVo();
		HttpSession session = request.getSession(false);  //세션객체를 가져온다.
		
		vo.setMemberId(request.getParameter("mid"));
		vo.setPassword(request.getParameter("password"));
		
		vo = dao.memberLoginCheck(vo);
		
		if(vo.getMemberAuth() != "") {
			session.setAttribute("id", vo.getMemberId());
			session.setAttribute("auth", vo.getMemberAuth());
			session.setAttribute("name", vo.getMemberName());
		}
		request.setAttribute("vo", vo);
		String viewPage = "jsp/member/loginResult.jsp";
		RequestDispatcher dispacther = request.getRequestDispatcher(viewPage);
		dispacther.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
