package co.micol.notice.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.micol.notice.vo.NoticeVo;

public class NoticeDao {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "rachel";
	private String password = "1234";
	
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	//이부분에 sql 작성
	private final String NOTICELIST = "SELECT * FROM NOTICE ORDER BY NOTICEID DESC";
	private final String INSERT = 
			"INSERT INTO NOTICE(NOTICEID,NOTICEWRITER,NOTICETITLE,NOTICECONTENT,NOTICEATTACH) VALUES(NO_VAL.NEXTVAL,?,?,?,?)";
	
	public NoticeDao() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		}catch(ClassNotFoundException | SQLException e) {
			System.out.println("DB 연결실패!!!!!");
		}
	}
	
	private void close() {
		try {
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<NoticeVo> selectAll(){
		ArrayList<NoticeVo> list = new ArrayList<NoticeVo>();
		NoticeVo vo;
		try {
			psmt = conn.prepareStatement(NOTICELIST);
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new NoticeVo();
				vo.setNoticeId(rs.getInt("noticeid"));
				vo.setNoticeWriter(rs.getString("noticewriter"));
				vo.setNoticeTitle(rs.getString("noticetitle"));
			//	vo.setNoticeContent(rs.getString("noticecontent"));
				vo.setNoticeDate(rs.getDate("noticedate"));
				vo.setNoticeHit(rs.getInt("noticehit"));
				vo.setNoticeAttech(rs.getString("noticeattach"));
				list.add(vo);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
	
	public NoticeVo select(NoticeVo vo) {
		
		return vo;
	}
	
	public int insert(NoticeVo vo) {
		int n=0;
		try {
			psmt = conn.prepareStatement(INSERT);
			psmt.setString(1,vo.getNoticeWriter());
			psmt.setString(2, vo.getNoticeTitle());
			psmt.setString(3, vo.getNoticeContent());
			psmt.setString(4, vo.getNoticeAttech());
			n = psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}
	
	public int update(NoticeVo vo) {
		int n=0;
		
		return n;
	}
	
	public int delete(NoticeVo vo) {
		int n=0;
		
		return n;
	}
}
