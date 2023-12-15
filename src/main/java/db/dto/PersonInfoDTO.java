package db.dto;

public class PersonInfoDTO {
	
	public int id; // 숫자 NUMBER
	public String name; // 문자 VARCHAR2
	
	//생성자
	public PersonInfoDTO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	
	// get, set
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
