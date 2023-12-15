package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.dto.PersonInfoDTO;
import db.util.DBconnectionManager;

public class PersonInfoDAO {
	
	//필드변수
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;

	// select... find... read... 을 앞에 붙여서 메소드 명을 써주는게 좋음
	public List<PersonInfoDTO> findPersonInfoList(){
		// 결과가 한개인지 여러개(List) 인지 인지시켜주기 위해 리스트면 list 작성

		//DBconnectionManager 가 만들어준 connection 을 활용
		conn = DBconnectionManager.connectDB();
	
		String sql = " SELECT * FROM t_person_info ORDER BY id ";

		List<PersonInfoDTO> personInfoList = null;
		
		try {
			psmt = conn.prepareStatement(sql);
			// Connection을 활용해서 sql 명령을 실행하는 객체

			rs = psmt.executeQuery(); // 준비된 sql 쿼리문 실행!
			personInfoList = new ArrayList<PersonInfoDTO>();
			
			while (rs.next()) {
				
				PersonInfoDTO personInfoDTO = new PersonInfoDTO(rs.getInt("id"), rs.getString("name"));
		
				personInfoList.add(personInfoDTO);
				

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconnectionManager.closeDB(conn, psmt, rs);
		}
		
		return personInfoList;

		
		
	}
	
	public PersonInfoDTO findPersonInfoListById(int id) {
		//
		
		conn = DBconnectionManager.connectDB();
		
		String sql =  " SELECT * FROM t_person_info "
				+ " WHERE id = ? " ;  //매개변수를 활용한 sql 쿼리문

		PersonInfoDTO personInfo = null;
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, id); //(?위치 인덱스, 세팅할 값)
			
			rs = psmt.executeQuery(); //준비된 sql 쿼리문 실행!

			if(rs.next()) { 
				personInfo = new PersonInfoDTO(rs.getInt("id"), rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DBconnectionManager.closeDB(conn, psmt, rs);
		}
		
		return personInfo;
		
	}
	
	//저장1
	public int savePersonInfo(int id, String name) {
		conn = DBconnectionManager.connectDB();
		
		String sql =  " INSERT INTO t_person_info "
				+ " VALUES ( ?, ?) " ;  //매개변수를 활용한 sql 쿼리문

		int result = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			

			psmt.setInt(1,  id);
			psmt.setString(2,  name);
			
			//rs = psmt.executeQuery(); //준비된 sql 쿼리문 실행!
			result = psmt.executeUpdate();
			
			/*
			 SELECT 쿼리 : psmt.executeQuery(); → 결과로 ResultSet
			 INSERT, UPDATE, DELETE 쿼리 : psmt.exetudeUpdate();
			 								→ 결과로 적용된 한 행의 숫자
			 */

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DBconnectionManager.closeDB(conn, psmt, rs);
		}
		
		return result;
	}
	
	//저장2
	public int savePersonInfo(String name) {
		conn = DBconnectionManager.connectDB();
		
		String sql =  " INSERT INTO t_person_info "
				+ " VALUES ( (SELECT NVL(MAX(id), 0) + 1 FROM t_person_info), ?) " ;  //매개변수를 활용한 sql 쿼리문

		int result = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			

			psmt.setString(1,  name);
			
			//rs = psmt.executeQuery(); //준비된 sql 쿼리문 실행!
			result = psmt.executeUpdate();
			
			/*
			 SELECT 쿼리 : psmt.executeQuery(); → 결과로 ResultSet
			 INSERT, UPDATE, DELETE 쿼리 : psmt.exetudeUpdate();
			 								→ 결과로 적용된 한 행의 숫자
			 */

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DBconnectionManager.closeDB(conn, psmt, rs);
		}
		
		return result;

	}
	
	// 저장3
	public int savePersonInfo(PersonInfoDTO personInfo) {
		conn = DBconnectionManager.connectDB();
		
		String sql =  " INSERT INTO t_person_info "
				+ " VALUES ( ?, ?) " ;  //매개변수를 활용한 sql 쿼리문

		int result = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			

			psmt.setInt(1,  personInfo.getId());
			psmt.setString(2,  personInfo.getName());
			
			//rs = psmt.executeQuery(); //준비된 sql 쿼리문 실행!
			result = psmt.executeUpdate();
			
			/*
			 SELECT 쿼리 : psmt.executeQuery(); → 결과로 ResultSet
			 INSERT, UPDATE, DELETE 쿼리 : psmt.exetudeUpdate();
			 								→ 결과로 적용된 한 행의 숫자
			 */

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DBconnectionManager.closeDB(conn, psmt, rs);
		}
		
		return result;

	}

	/*
	select → find
	insert → save
	update → modify
	delete → remove
	 */
	// 수정
	public int modifyPersonInfo(PersonInfoDTO personInfo) {
		//해당 아이디에 맞는 사람의 이름을 수정!
		
		conn = DBconnectionManager.connectDB();
		
		String sql =  " UPDATE t_person_info "
				+ " SET name = ? " 
				+ " WHERE id = ? ";  //매개변수를 활용한 sql 쿼리문 // ?는 매개변수가 배치될 자리..

		int result = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, personInfo.getName());
			psmt.setInt(2, personInfo.getId());
			
			//rs = psmt.executeQuery(); //준비된 sql 쿼리문 실행!
			result = psmt.executeUpdate(); // 1, 0
			
			/*
			 SELECT 쿼리 : psmt.executeQuery(); → 결과로 ResultSet
			 INSERT, UPDATE, DELETE 쿼리 : psmt.exetudeUpdate();
			 								→ 결과로 적용된 한 행의 숫자
			 */

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconnectionManager.closeDB(conn, psmt, rs);
		}
		
		return result;
	}
	
	
	// 삭제
	
	public int removePersonInfo(int id) {
		//해당 아이디에 맞는 사람의 정보를 삭제!
		
		conn = DBconnectionManager.connectDB();
		
		String sql =  " DELETE FROM t_person_info "
				+ " WHERE id = ? ";  //매개변수를 활용한 sql 쿼리문 
		// ?는 매개변수가 배치될 자리..printf의 %s %d를 생각하면 됨

		int result = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, id);
			
			//rs = psmt.executeQuery(); //준비된 sql 쿼리문 실행!
			result = psmt.executeUpdate(); // 1, 0

			
			/*
			 SELECT 쿼리 : psmt.executeQuery(); → 결과로 ResultSet
			 INSERT, UPDATE, DELETE 쿼리 : psmt.exetudeUpdate();
			 								→ 결과로 적용된 한 행의 숫자
			 */

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconnectionManager.closeDB(conn, psmt, rs);
		}
		
		return result;
	}

	
	
}


//DAO 활용하기 연습문제
//*  PersonInfoDAO 를 생성하고, 데이터를 조회해서 가져오는 메소드를 작성하세요.
//1) t_person_info 에 있는 전체 정보를 조회해오는 메소드 생성 (return 필수)
//2) id 값을 기준으로 한 개의 사람 정보를 가져오는 메소드 생성 (return 필수)
//3) 메인에서 해당 메소드를 활용하여 데이터를 읽어오고, 콘솔 창에 출력하세요.