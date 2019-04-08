package br.edu.ies.vinicius.quotationserver.runnable;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.edu.ies.vinicius.quotationserver.Main;
import br.edu.ies.vinicius.quotationserver.connection.JsonManager;
import br.edu.ies.vinicius.quotationserver.connection.UrlConnectionManager;
import br.edu.ies.vinicius.quotationserver.type.CurrencyType;

public class CurrencyRunnable implements Runnable {

	private String url;

	public CurrencyRunnable(String url) {
		this.url = url;
	}

	public void run() {
		try {
			UrlConnectionManager urlConnectionManager = new UrlConnectionManager(url);
			JsonManager jsonManager = new JsonManager(urlConnectionManager.getStringFromUrl());
			HashMap<String, Double> price_per_currency = (HashMap<String, Double>) Stream.of(CurrencyType.values())
					.collect(Collectors.toMap(CurrencyType::getName, c -> jsonManager.getValue(c)));

			price_per_currency.entrySet().forEach(c -> System.out.println(c.getKey() + ": " + c.getValue()));
			Main.getFirebaseManager().insert(price_per_currency);
			Thread.sleep(TimeUnit.MINUTES.toMillis(10));
			run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
