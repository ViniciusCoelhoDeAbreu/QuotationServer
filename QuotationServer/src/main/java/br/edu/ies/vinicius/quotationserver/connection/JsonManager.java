package br.edu.ies.vinicius.quotationserver.connection;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import br.edu.ies.vinicius.quotationserver.type.CurrencyType;

public class JsonManager {

	private JSONObject currenciesObject;

	public JsonManager(String readLine) {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(readLine);
			JSONObject resultObject = (JSONObject) jsonObject.get("results");
			this.currenciesObject = (JSONObject) resultObject.get("currencies");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public double getValue(CurrencyType currencyType) {
		JSONObject currencyObject = (JSONObject) currenciesObject.get(currencyType.name());
		Double value = Double.parseDouble(currencyObject.get("buy").toString());

		return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
	}

}
