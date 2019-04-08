package br.edu.ies.vinicius.quotationserver.firebase;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseManager {

	private Firebase firebase;
	private FirebaseApp firebaseApp;
	private FirebaseDatabase firebaseDatabase;

	public FirebaseManager(Firebase firebase) {
		this.firebase = firebase;
	}

	public void initialize() {
		try {
			FileInputStream serviceAccount = new FileInputStream(firebase.getJsonFile());
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl(firebase.getDatabaseUrl()).build();

			this.firebaseApp = FirebaseApp.initializeApp(options);
			this.firebaseDatabase = FirebaseDatabase.getInstance(this.firebaseApp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insert(HashMap<String, Double> price_per_currency) {
		try {
			DatabaseReference databaseReference = firebaseDatabase.getReference();

			ApiFuture<Void> future = databaseReference.setValueAsync(price_per_currency);
			future.get(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FirebaseApp getFirebaseApp() {
		return firebaseApp;
	}

}
