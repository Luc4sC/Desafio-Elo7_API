package com.desafio.elo7.api;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
public class DesafioElo7Application {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DesafioElo7Application.class, args);

		FileInputStream serviceAccount =
				new FileInputStream("desafio-elo7-key.json");

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();

		FirebaseApp.initializeApp(options);
	}

}
