package upf.at.s4.server;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import upf.at.s4.client.RestClient;
import upf.at.s4.covid.Covid;
import upf.at.s4.covid.UserCovid;
import upf.at.s4.data.*;
import upf.at.s4.user.*;

@Path("/bicing")
public class Services {
	//URLs
	//First we declare the URL of the apis and the caches lists.
	static String bicingURL = "https://api.bsmsa.eu/";
	static String bicingPath = "ext/api/bsm/gbfs/v2/en/station_status";
	static String CovidURL = "https://analisi.transparenciacatalunya.cat/";
	static String CovidPath = "resource/jvut-jxu8.json";
	
	static List<Station> stations = new ArrayList<Station>();
	static List<User> users = new ArrayList<User>();
	static List<UserCovid> usersCovid = new ArrayList<UserCovid>();
	static List<Covid> covids = new ArrayList<Covid>();
	
	
	
	@GET
	@Path("/getallStations")
	@Produces(MediaType.APPLICATION_JSON)
	public Data getAllStations(){
		//Here, we check if our cache list is empty, is not we fill with the api information
		if(stations.isEmpty()) {
			Client client = ClientBuilder.newClient();
			WebTarget targetGet = client.target(bicingURL).path(bicingPath); 
			//Request on the bicing api
			Data stationsData = targetGet.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<Data>(){});
			stations = stationsData.getData().getStations();
			//Here we decide to return the structure Data where contains the JSON parsed into a list of stations
			return stationsData;
		}
		//We decide to implement here as well the telegram bot by indicating us that our cache is already with the api information.
		RestClient.TelegramMessage("stations not empty");
		Stations stations_class = new Stations();
		stations_class.setStations(stations);
		Data stationsData = new Data();
		stationsData.setData(stations_class);	
		return stationsData;

	}
	@POST
	@Path("/addUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response create(User user1) {
		users.add(user1);
		// Thanks to the return we are able to see through the web the user added.
		return Response.status(200).entity(user1).build();
	}
	
	
	@GET
	@Path("/getallUsers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers(){
		return users;
	}
	
	@POST
	@Path("/notifySlots")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public void getUserStations(User user1) {

		List<Integer> subs_stations = new ArrayList<Integer>();
		List<Station> user_stations = new ArrayList<Station>();
		String text = new String();
		
		for(int i=0; i< users.size();i++) {
			//We compare if the user which we are asking for is previously added by the phone number which in irl is a unique identificator.
			if(user1.getPhone()== users.get(i).getPhone()) {
				subs_stations = users.get(i).getSubs_IDStations();
			}
		}
		
		for(int j=0; j < subs_stations.size();j++) {
			for(int i=0; i < stations.size(); i++) {
				if(subs_stations.get(j) == stations.get(i).getStation_id()) {
					//with the double for we are able to identificate the subscribed stations from the cache and save in a void list.
					user_stations.add(stations.get(i));
				}
			}
		}

		for(int j=0; j < user_stations.size();j++) {
			Station curr_station = user_stations.get(j);
			text += curr_station.toString();
			//With the to.string function we have in a string type all the variables of the station 
		}
		
		RestClient.TelegramMessage(text);
		// Finally we send the message of the subs stations through the telegram bot.
	}
	
	@GET
	@Path("/getCovid")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Covid> getCovid(){
		if(covids.isEmpty()) {
			Client client = ClientBuilder.newClient();
			WebTarget targetGet = client.target(CovidURL).path(CovidPath); 
			
			List<Covid> covidsList = targetGet.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<Covid>>(){});
			covids = covidsList;
			return covids;
			//As we did with the stations here we do the request but in the covid api and we save in the cache.
		}
		return covids;

	}
	
	@POST
	@Path("/notifyCovid")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Covid getUserCovid( UserCovid user1) {
		
		Covid structurecovid = new Covid();
		Covid req_covid = new Covid();
		User usuari = new User();
		String date = new String();
		List<Covid> region_covid = new ArrayList<Covid>();
		
		for(int i=0; i < covids.size();i++) {
			structurecovid = covids.get(i);
			
			if(user1.getMyRegion().equals(structurecovid.getNom())) {
				
				region_covid.add(structurecovid);
			}
		}

				
		for(int j=0; j < region_covid.size();j++) {
			
			date = region_covid.get(j).getData_fi();

			if(date.equals("2022-03-14T00:00:00.000") || date.equals("2021-05-21T00:00:00.000") ||date.equals("2022-03-12T00:00:00.000")
					||date.equals("2022-03-11T00:00:00.000")||date.equals("2022-03-10T00:00:00.000")||date.equals("2022-03-09T00:00:00.000")
					||date.equals("2022-03-08T00:00:00.000")||date.equals("2022-03-07T00:00:00.000")||date.equals("2022-03-06T00:00:00.000")
					||date.equals("2022-03-05T00:00:00.000")||date.equals("2022-03-04T00:00:00.000") ||date.equals("2022-03-03T00:00:00.000")
					||date.equals("2022-03-02T00:00:00.000") ||date.equals("2022-03-01T00:00:00.000")){
			
					req_covid = region_covid.get(j);
					
	            }
		}	
		if (req_covid.getNom() == null) { 
			RestClient.TelegramMessage("There is no info of the last week");
			
			}else {
		
				for(int j=0; j < users.size();j++) {
					usuari = users.get(j);
					
					if(usuari.getPhone() == user1.getPhone()){
						String text = req_covid.toString();
						RestClient.TelegramMessage(text);
						return req_covid;
					}
		
				}
			}
	
		return null;

	}
	

}
