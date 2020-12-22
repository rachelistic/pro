package co.micol.border.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.micol.border.vo.BorderVo;
import co.micol.common.DAO;

public class BorderDao extends DAO {
	private PreparedStatement psmt;
	private ResultSet rs;
	
	private final String SELECT_ALL = 
			"SELECT * FROM BORDER ORDER BY BORDERID DESC";
	private final String SELECT_ONE = 
			"SELECT * FROM BORDER WHERE BORDERID = ?";
	private final String INSERT = 
			"INSERT INTO BORDER(BORDERID,BORDERWRITER,BORDERTITLE,BORDERCONTENT,BORDERDATE)" + 
			" VALUES(BORDER_SEQ.NEXTVAL,?,?,?,?)";
	private final String HIT_UPDATE = 
			"UPDATE BORDER SET BORDERHIT = BORDERHIT + 1 WHERE BORDERID = ?";
	private final String UPDATE = 
			"UPDATE BORDER SET BORDERDATE = ?, BORDERCONTENT = ? WHERE BORDERID = ?";
	private final String DELETE = 
			"DELETE FROM BORDER WHERE BORDERID = ?";
	
	private final String COUNT = "SELECT COUNT(*) FROM BORDER";

	private final String GETLIST = "SELECT B.*  FROM(  SELECT A.*, ROWNUM RN FROM(" 
			+ "SELECT * FROM BORDER ORDER BY 1 DESC) A ) B WHERE RN BETWEEN ? AND ?";
	
	
	
	//===============================================================
	
   public int count() {
	   int count =0;
	   try {
		   psmt = conn.prepareStatement(COUNT);
		   rs = psmt.executeQuery();
		   if(rs.next()) {
			   count = rs.getInt(1);
		   }
	   }catch(SQLException e) {
		   e.printStackTrace();
	   }finally {
		  close();
	   }
	   System.out.println(count +"입니다");
	   return count;
	  
   }
   
   
   
	
	public ArrayList<BorderVo> getList(int startRow, int endRow){   //전체 데이터 가져오기
		ArrayList<BorderVo> list = new ArrayList<BorderVo>();
		BorderVo vo;
		try {
			psmt = conn.prepareStatement(GETLIST);
			psmt.setInt(1, startRow);
			psmt.setInt(2, endRow);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				vo = new BorderVo();
				vo.setBorderId(rs.getInt("borderid"));
				vo.setBorderWrite(rs.getString("borderwriter"));
				vo.setBorderTitle(rs.getString("bordertitle"));
				vo.setBorderContent(rs.getString("bordercontent"));
				vo.setBorderDate(rs.getDate("borderdate"));
				vo.setBorderHit(rs.getInt("borderhit"));
				list.add(vo);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return list;
	}

	
	public ArrayList<BorderVo> selectAll(){   //전체 데이터 가져오기
		ArrayList<BorderVo> list = new ArrayList<BorderVo>();
		BorderVo vo;
		try {
			psmt = conn.prepareStatement(SELECT_ALL);
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new BorderVo();
				vo.setBorderId(rs.getInt("borderid"));
				vo.setBorderWrite(rs.getString("borderwriter"));
				vo.setBorderTitle(rs.getString("bordertitle"));
				vo.setBorderContent(rs.getString("bordercontent"));
				vo.setBorderDate(rs.getDate("borderdate"));
				vo.setBorderHit(rs.getInt("borderhit"));
				list.add(vo);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return list;
	}

	
	public BorderVo selectOne(BorderVo vo) {  //한 레코드 검색 하고 조회수 증가
		try {
			psmt = conn.prepareStatement(SELECT_ONE);
			psmt.setInt(1, vo.getBorderId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				psmt=conn.prepareStatement(HIT_UPDATE);
				psmt.setInt(1, vo.getBorderId());
				psmt.execute();  //조회수를 1 증가 한다.
				vo.setBorderId(rs.getInt("borderid"));
				vo.setBorderWrite(rs.getString("borderwriter"));
				vo.setBorderTitle(rs.getString("bordertitle"));
				vo.setBorderContent(rs.getString("bordercontent"));
				vo.setBorderDate(rs.getDate("borderdate"));
				vo.setBorderHit(rs.getInt("borderhit"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return vo;
	}
	
	public BorderVo selectSearch(BorderVo vo) {  //한 레코드 검색
		try {
			psmt = conn.prepareStatement(SELECT_ONE);
			psmt.setInt(1, vo.getBorderId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setBorderId(rs.getInt("borderid"));
				vo.setBorderWrite(rs.getString("borderwriter"));
				vo.setBorderTitle(rs.getString("bordertitle"));
				vo.setBorderContent(rs.getString("bordercontent"));
				vo.setBorderDate(rs.getDate("borderdate"));
				vo.setBorderHit(rs.getInt("borderhit"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return vo;
	}
	
	public int insert(BorderVo vo) {   //게시글 입력
		int n = 0;
		try {
			psmt = conn.prepareStatement(INSERT);
			psmt.setString(1, vo.getBorderWrite());
			psmt.setString(2, vo.getBorderTitle());
			psmt.setString(3, vo.getBorderContent());
			psmt.setDate(4, vo.getBorderDate());
			n = psmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}
	
	public int update(BorderVo vo) {   //게시글 수정
		int n = 0;
		try {
			psmt = conn.prepareStatement(UPDATE);
			psmt.setDate(1, vo.getBorderDate());
			psmt.setString(2, vo.getBorderContent());
			psmt.setInt(3, vo.getBorderId());
			n = psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}
	
	public int delete(BorderVo vo) {   //게시글 삭제
		int n = 0;
		try {
			psmt = conn.prepareStatement(DELETE);
			psmt.setInt(1, vo.getBorderId());
			n = psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
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
}
