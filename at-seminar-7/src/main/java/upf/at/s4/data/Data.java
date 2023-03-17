package upf.at.s4.data;


public class Data {
	private Stations data;
	private int stations_status;
	private int ttl;

	public Data(Stations data, int stations_status, int ttl) {
		super();
		this.data = data;
		this.stations_status = stations_status;
		this.ttl = ttl;
	}
	
	public Data() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Stations getData() {
		return data;
	}

	public void setData(Stations data) {
		this.data = data;
	}

	public int getStations_status() {
		return stations_status;
	}

	public void setStations_status(int stations_status) {
		this.stations_status = stations_status;
	}

	public int getTtl() {
		return ttl;
	}

	public void setTtl(int ttl) {
		this.ttl = ttl;
	}
}
