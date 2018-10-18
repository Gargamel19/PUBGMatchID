package trendelenburg.de;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MatchIDConverter {

	static HttpURLConnection connection;
	
	String matchID;
	URL url;
	
	public MatchIDConverter(String matchID) {
		this.matchID = matchID;
		try {
			this.url = new URL("https://api.pubg.com/shards/pc-eu/matches/"+matchID);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public void getRequest() throws IOException {
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/vnd.api+json");
		System.out.println(connection.getResponseCode());
		if (connection.getResponseCode()==200) {
			System.out.println(connection.getContentType());
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String content = in.readLine();
			String fileName = content.substring(content.indexOf("\"match\",\"id\":\"")+14, content.indexOf("\"match\",\"id\":\"")+50);
			File file = new File("C:/Users/FerdinandTrendelenbu/Documents/FaceOff/Maches/"+fileName+".json");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		    writer.write(content);
		    writer.close();
			
			System.out.println("Match: " + matchID + " erfolgreich als JSON-Datei gespeichert!");
		}else {
			System.out.println("fehler");
			
		}
		
	}
}
