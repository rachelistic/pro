package co.micol.border.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.border.dao.BorderDao;
import co.micol.border.vo.BorderVo;
import co.micol.common.BorderCommand;


public class BorderList implements BorderCommand {

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		//TODO
		int pageSize = 5;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null){ // 클릭한게 없으면 1번 페이지
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;

		//==================================
		BorderDao dao = new BorderDao();
		//==================================
		dao = new BorderDao();
		int count = dao.count();
		request.setAttribute("count", count);
		//==================================
		dao = new BorderDao();
		ArrayList<BorderVo> glist = null;
		if(count>0) {
			glist =  dao.getList(startRow, endRow);
			request.setAttribute("glist", glist);
		}
		//=======================================
			
		//request객체에 결과를 담는다.

		return "jsp/border/borderList.jsp";  //보여줄페이지 정의
	
	}

}
