package br.edu.ies.vinicius.quotationserver.firebase;

public class Firebase {

	private String jsonFile;
	private String databaseUrl;

	public Firebase(String jsonFile, String databaseUrl) {
		this.jsonFile = jsonFile;
		this.databaseUrl = databaseUrl;
	}

	public String getJsonFile() {
		return jsonFile;
	}

	public String getDatabaseUrl() {
		return databaseUrl;
	}

}
