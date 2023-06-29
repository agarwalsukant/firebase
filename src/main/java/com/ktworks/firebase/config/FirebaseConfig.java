package com.ktworks.firebase.config;

import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import jakarta.annotation.PostConstruct;

@Configuration
public class FirebaseConfig {

	@PostConstruct
	public void init() {

		try {
			InputStream serviceAccount = new FileInputStream(
					"C:\\Users\\sukan\\Code\\firebase\\src\\main\\resources\\socialbean-poc-firebase-adminsdk-995ij-d89f4044ed.json");

			FirebaseOptions options = FirebaseOptions.builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();

			FirebaseApp.initializeApp(options);
		} catch (Exception e) {
			System.out.println("Failed Initialization");
		}
	}
	
	@Bean
	public Firestore firestore() {
		return FirestoreClient.getFirestore();
	}

}
