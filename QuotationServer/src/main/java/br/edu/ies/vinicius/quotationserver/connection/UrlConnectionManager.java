package br.edu.ies.vinicius.quotationserver.connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UrlConnectionManager {

	private String url;

	public UrlConnectionManager(String url) {
		this.url = url;
	}

	public String getStringFromUrl() {
		try {
			URL website = new URL(url);
			URLConnection request = website.openConnection();
			request.addRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF8"));

			StringBuilder response = new StringBuilder();
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				response.append(inputLine);

			in.close();
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
