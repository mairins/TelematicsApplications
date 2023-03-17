package upf.at.s4.user;

import java.util.List;

public class Users {
	private List<User> users;

	public Users(List<User> users) {
		super();
		this.users = users;
	}
	public Users() {
		super();
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
  
	
}
