package upf.at.s4.covid;

public class UserCovid {
	private int phone;
	private String myRegion;
	
	public UserCovid(int phone, String myRegion) {
		super();
		this.phone = phone;
		this.myRegion = myRegion;
	}
	public UserCovid() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getMyRegion() {
		return myRegion;
	}
	public void setMyRegion(String myRegion) {
		this.myRegion = myRegion;
	}
	@Override
	public String toString() {
		return "UserCovid [phone=" + phone + ", myRegion=" + myRegion + "]";
	}
	
	
}
