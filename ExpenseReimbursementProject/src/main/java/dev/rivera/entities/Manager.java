package dev.rivera.entities;

public class Manager {
	private int mId;
	private String username;
	private String password;
	private String workForce;
	public Manager() {
		super();
	}

	public Manager(int mId, String username, String password, String workForce) {
		super();
		this.mId = mId;
		this.username = username;
		this.password = password;
		this.workForce = workForce;
	}

	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getWorkForce() {
		return workForce;
	}

	public void setWorkForce(String workForce) {
		this.workForce = workForce;
	}

	@Override
	public String toString() {
		return "Manager [mId=" + mId + ", username=" + username + ", password=" + password + ", workForce=" + workForce
				+ "]";
	}


	
}
