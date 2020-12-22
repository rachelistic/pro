package co.micol.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.micol.member.vo.MemberVo;

public class MemberDao {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "micol";
	private String password = "1234";
	
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	//이부분에 sql 작성
	private final String MEMBERLOGIN = 
			"SELECT * FROM MEMBER WHERE MEMBERID =? AND PASSWORD =?";
	private final String MEMBERS = "SELECT * FROM MEMBER";
	private final String MEMBER = "SELECT * FROM MEMBER WHERE MEMBERID = ?";
	
	public MemberDao() {
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
	
	public ArrayList<MemberVo> selectAll() {
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		MemberVo vo;
		try {
			psmt = conn.prepareStatement(MEMBERS);
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new MemberVo();
				vo.setMemberId(rs.getString("memberid"));
				vo.setMemberName(rs.getString("membername"));
				vo.setMemberAuth(rs.getString("memberauth"));
				vo.setMemberPoint(rs.getInt("memberpoint"));
				list.add(vo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
	
	public MemberVo select(MemberVo vo) {
		try {
			psmt = conn.prepareStatement(MEMBER);
			psmt.setString(1, vo.getMemberId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setMemberName(rs.getString("membername"));
				vo.setPassword(rs.getString("password"));
				vo.setMemberAuth(rs.getString("memberauth"));
				vo.setMemberPoint(rs.getInt("memberpoint"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return vo;
	}
	
	public MemberVo memberLoginCheck(MemberVo vo) {   //login check
		try {
			psmt = conn.prepareStatement(MEMBERLOGIN);
			psmt.setString(1, vo.getMemberId());
			psmt.setString(2, vo.getPassword());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setMemberName(rs.getString("membername"));
				vo.setMemberAuth(rs.getString("memberauth"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return vo;
	}
	
	public int insert(MemberVo vo) {
		int n = 0;
		
		return n;
	}
	
	public int update(MemberVo vo) {
		int n = 0;
		
		return n;
	}
	
	public int delete(MemberVo vo) {
		int n = 0;
		
		return n;
	}
}
