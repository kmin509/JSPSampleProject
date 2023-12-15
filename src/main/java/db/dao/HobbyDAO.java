package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.dto.HobbyDTO;
import db.dto.PersonInfoDTO;
import db.util.DBconnectionManager;

public class HobbyDAO {

	//필드변수
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	
	public List<HobbyDTO> findHobbyList(){
		
		// DBconnectionManager 가 만들어준 connection 을 활용
		conn = DBconnectionManager.connectDB();

		String sql = " SELECT * FROM t_hobby_list ORDER BY id, no ";

		List<HobbyDTO> hobbyList = null; 
		// if(hobbyLIst == null) → 정상인가 아닌가 체크하는 방법1

		try {
			psmt = conn.prepareStatement(sql);
			// Connection을 활용해서 sql 명령을 실행하는 객체

			rs = psmt.executeQuery(); // 준비된 sql 쿼리문 실행!
			
			//hobbyList = new ArrayList<HobbyDTO>(); 
			// if(hobbyList.size() > 0 → 정상인가 아닌가 체크하는 방법 2

			while (rs.next()) {
				
				if(hobbyList == null) { // null 이면 데이터가 없는거고 null 이 아니면 뭐라도 담겨있는거니까 새로 생성되지 않음.
					hobbyList = new ArrayList<HobbyDTO>();
				}

				HobbyDTO hobbyDTO = new HobbyDTO(rs.getInt("id"), rs.getInt("no"), rs.getString("hobby"),
						rs.getInt("prefer"));

				hobbyList.add(hobbyDTO);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconnectionManager.closeDB(conn, psmt, rs);
		}

		return hobbyList;
		
	}
	
}
