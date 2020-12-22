package co.micol.border.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.border.dao.BorderDao;
import co.micol.border.vo.BorderVo;

/**
 * Servlet implementation class BorderDelete
 */
@WebServlet("/BorderDelete.do")
public class BorderDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorderDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 여기다 삭제하는 것 만들어 보세요.
		request.setCharacterEncoding("utf-8");
		BorderDao dao = new BorderDao();
		BorderVo vo = new BorderVo();
		
		vo.setBorderId(Integer.parseInt(request.getParameter("id")));
		int n = dao.delete(vo);
		if(n != 0) {
			response.sendRedirect("/Member/BorderList.do");
		}else {
			String msg = "삭제하지 못했습니다.";
			request.setAttribute("msg", msg);
			String viewPage = "jsp/border/inputError.jsp";
			RequestDispatcher dispacher = request.getRequestDispatcher(viewPage);
			dispacher.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
