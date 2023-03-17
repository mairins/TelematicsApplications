package upf.at.s4.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import upf.at.s4.user.Message;

public class RestClient {

	public static void TelegramMessage(String m) {
			
			long chatID = 6106510658L;
			Client client = ClientBuilder.newClient();
			Message message = new Message(chatID, m );
			String TOKEN = "5984465066:AAF28gzsR-fIdVdkhJL-OsgN3-xp7FEAEYQ";
			
			WebTarget targetSendMessage = client.target("https://api.telegram.org").path("/bot"+TOKEN+"/sendMessage");
			String response = targetSendMessage.request()
			.post(Entity.entity(message, MediaType.APPLICATION_JSON_TYPE), String.class);
			

	}
}
