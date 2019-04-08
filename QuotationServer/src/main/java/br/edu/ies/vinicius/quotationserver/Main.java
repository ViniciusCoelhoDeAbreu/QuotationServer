package br.edu.ies.vinicius.quotationserver;

import br.edu.ies.vinicius.quotationserver.firebase.Firebase;
import br.edu.ies.vinicius.quotationserver.firebase.FirebaseManager;
import br.edu.ies.vinicius.quotationserver.runnable.CurrencyRunnable;

public class Main {

	protected static final String JSON_FILE = new String(
			"C:/Users/kickp/OneDrive/Documentos/cotacao-moedas-firebase-adminsdk-calix-7c8fad2a60.json");
	protected static final String DATABASE_URL = new String("https://cotacao-moedas.firebaseio.com/");
	protected static final String API_URL = new String("https://api.hgbrasil.com/finance?key=a8a2deaf");

	private static Firebase firebase;
	private static FirebaseManager firebaseManager;

	public static void main(String[] args) {
		firebase = new Firebase(JSON_FILE, DATABASE_URL);
		firebaseManager = new FirebaseManager(firebase);
		
		firebaseManager.initialize();
		initializeThread();
	}

	private static void initializeThread() {
		Thread thread = new Thread(new CurrencyRunnable(API_URL));
		thread.start();
	}

	public static FirebaseManager getFirebaseManager() {
		return firebaseManager;
	}
}
