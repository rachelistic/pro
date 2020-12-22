package co.micol.notice.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import co.micol.notice.dao.NoticeDao;
import co.micol.notice.vo.NoticeVo;


/**
 * Servlet implementation class AjaxNoticeList
 */
@WebServlet("/AjaxNoticeList.do")
public class AjaxNoticeList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxNoticeList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Json 형태의 데이터를 리턴 한다.
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();   //html 코드를 담기 위해
		
		NoticeDao dao = new NoticeDao();
		ArrayList<NoticeVo> list = new ArrayList<NoticeVo>();
		list = dao.selectAll();
		
		JSONObject jsonObject = new JSONObject();  //전체 데이터의 저장을 위해
		JSONObject data;     //하나의 레코드를 위해
		JSONArray jlist = new JSONArray();      //레코드 배열을 위해
				
		for(NoticeVo vo : list) {   //json 객체 생성
			data = new JSONObject();
			data.put("noticeId", vo.getNoticeId());
			data.put("noticeTitle", vo.getNoticeTitle());
			data.put("noticeDate", vo.getNoticeDate().toString());
			data.put("noticeWriter", vo.getNoticeWriter());
			data.put("noticeHit", vo.getNoticeHit());
			data.put("noticeAttech", vo.getNoticeAttech());
			jlist.add(data);
		}
		jsonObject.put("data",jlist);
		out.print(jsonObject);
		out.flush();
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
