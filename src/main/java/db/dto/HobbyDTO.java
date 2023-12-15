package db.dto;

public class HobbyDTO {
	int id;
	int no;
	String hobby;
	int prefer;
	
	
	//생성자
	public HobbyDTO() {}
	
	public HobbyDTO(int id, int no, String hobby, int prefer) {
		super();
		this.id = id;
		this.no = no;
		this.hobby = hobby;
		this.prefer = prefer;
	}

	
	
	//get, set
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public int getPrefer() {
		return prefer;
	}

	public void setPrefer(int prefer) {
		this.prefer = prefer;
	}
	
	
	
	
}
