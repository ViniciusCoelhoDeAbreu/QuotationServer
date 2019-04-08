package br.edu.ies.vinicius.quotationserver.type;

public enum CurrencyType {
	EUR("Euro"), USD("Dólar");

	private String name;

	private CurrencyType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
