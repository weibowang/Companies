package Coinbase;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
 * https://www.1point3acres.com/bbs/thread-807965-1-1.html
 * https://leetcode.com/discuss/interview-experience/923447/coinbase-sde-bay-area-2020-reject
 * https://www.1point3acres.com/bbs/thread-814478-1-1.html
 */

public class CurrencyExchange {
	public static void main(String[] args) {
		try {
			getJson();
		} catch (IOException | InterruptedException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// https://api.pro.coinbase.com/products
	private static void getJson() throws IOException, InterruptedException, ParseException {
		System.out.println("start");
		String requestURL = "https://api.pro.coinbase.com/products";
		URL wikiRequest = new URL(requestURL);
		Scanner scanner = new Scanner(wikiRequest.openStream());
		//String response = scanner.useDelimiter("\\Z").next();
		String response = "";
		while (scanner.hasNext()) {
			response += scanner.nextLine();
		}
		scanner.close();
		System.out.println(response);
		JSONParser parser = new JSONParser();  
		JSONArray jsonArray = (JSONArray) parser.parse(response);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = (JSONObject) jsonArray.get(i);
			System.out.println(jsonObject.get("id"));
		}
		
		
		System.out.println("end");
	}
}
