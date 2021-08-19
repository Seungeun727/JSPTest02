package com.test.phonebook.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.phonbook.vo.PhoneBookVo;

public class PhoneBookDaoImpl implements PhoneBookDao {
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection 가져오기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", // DBURL
					"C##BITUSER", 
					"bituser"); 
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버 로드 실패!");
			e.printStackTrace();
		}

		return conn;
	}

	@Override
	public List<PhoneBookVo> getList() {
		List<PhoneBookVo> list = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.createStatement();

			String sql = "SELECT no, name, hp, tell "
			+ " FROM PhoneBook ORDER BY no DESC";

		    
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// 데이터 받기
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String tel = rs.getString(4);
				

				// VO 객체 생성
			    PhoneBookVo vo = new PhoneBookVo();
				vo.setName(name);
				vo.setHp(hp);
				vo.setTel(tel);

				// 리스트에 추가
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 정리
			try {
				rs.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List<PhoneBookVo>search(PhoneBookVo vo) {
		List<PhoneBookVo> list = new ArrayList<>();
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			//	실행 계획
			String sql = "SELECT INTO PhoneBook" +
					" WHERE name LIKE ? ORDER BY no";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+vo+"%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 데이터 받기
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String tel = rs.getString(4);
				

				// VO 객체 생성
			    PhoneBookVo vo = new PhoneBookVo();
				vo.setName(name);
				vo.setHp(hp);
				vo.setTel(tel);

				// 리스트에 추가
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 정리
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}



	@Override
	public int insert(PhoneBookVo vo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			//	실행 계획
			String sql = "INSERT INTO PhoneBook" +
					" VALUES(seq_PhoneBook.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			//	파라미터 바인딩
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getHp());
			pstmt.setString(3, vo.getTel());
			
			//	쿼리 수행
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	@Override
	public int correct(PhoneBookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int correctCount = 0;
		
		try {
			conn = getConnection();
			String sql = "UPDATE PhoneBook SET name =?, hp=?, tel=? WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getHp());
			pstmt.setString(3, vo.getTel());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return correctCount;
	}
	@Override
	public int delete(Long pk) {
		int deletedCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "DELETE FROM PhoneBook WHERE no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, pk);
			
			//	쿼리 수행
			deletedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return deletedCount;
	}

}
