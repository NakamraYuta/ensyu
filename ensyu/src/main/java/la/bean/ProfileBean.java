package la.bean;

public class ProfileBean {
	private int user_id;
	private String email;
	private String password;
	private String name;
	
	public ProfileBean(int user_id, String email, String password, String name) {
		this.user_id = user_id;
		this.email = email;
		this.password = password;
		this.name = name;
	}
	public ProfileBean() {
		
	}
	
	public ProfileBean(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public int getUser_id() {
		return user_id;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
