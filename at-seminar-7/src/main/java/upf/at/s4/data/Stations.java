package upf.at.s4.data;

import java.util.List;


public class Stations {
	private List<Station> stations;

	public Stations(List<Station> stations) {
		super();
		this.setStations(stations);
	}

	public Stations() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Station> getStations() {
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}
	
	public Station getStationById(int id) {
		Station statonID = new Station();
		for(int i=0; i<stations.size(); i++) {
			Station curr_station = stations.get(i);
			if(curr_station.getStation_id() == id) {
				statonID = curr_station;
			}
		}
		return statonID;
	}

	@Override
	public String toString() {
		String st = null;
		for (int i=0; i<stations.size(); i++) {
			st = st + stations.get(i).toString();
		}
		return st;
	
	}
	
}
