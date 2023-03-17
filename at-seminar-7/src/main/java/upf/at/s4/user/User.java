package upf.at.s4.user;

import java.util.List;

public class User {
	private int phone;
	private long telegram;
	private List<Integer> subs_IDStations;
	
	public User(int phone, long telegram, List<Integer> subs_IDStations) {
		super();
		this.phone = phone;
		this.telegram = telegram;
		this.subs_IDStations = subs_IDStations;
		
	}
	public User() {
		super();
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public long getTelegram() {
		return telegram;
	}

	public void setTelegram(long telegram) {
		this.telegram = telegram;
	}

	public List<Integer> getSubs_IDStations() {
		return subs_IDStations;
	}

	public void setSubs_IDStations(List<Integer> subs_IDStations) {
		this.subs_IDStations = subs_IDStations;
	}
	
	
}
