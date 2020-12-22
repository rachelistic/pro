package co.micol.border.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.border.command.BorderList;
import co.micol.border.dao.BorderDao;
import co.micol.common.BorderCommand;
import co.micol.page.PagingVo;

/**
 * Servlet implementation class BorderController
 */
@WebServlet("/BorderList.do")
public class BorderListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorderListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 여기가 게시판 관련 처리
		request.setCharacterEncoding("utf-8");   //한글 처리
		BorderCommand command = new BorderList(); //실행명령 선언
	
	    try {
	        // (Before) Doing...
	    	BorderDao dao = new BorderDao();
			int totalCount = dao.count();
			
			request.getParameter("pageNum");
			
			
	        PagingVo paging = new PagingVo();
	        paging.setPageNo(1);
	        paging.setPageSize(5);
	        paging.setTotalCount(totalCount);

	        
	        request.setAttribute("params", paging);
	        
	        // (After) Doing...
	    } catch (Exception e) {
	        throw e;
	    }

			
		
		String viewPage = command.action(request, response);  //실행명령 호출
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);  //보여줄 페이지 선택
		dispatcher.forward(request, response);
	}

}
