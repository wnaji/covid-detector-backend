package be.ipl.pfe.services;

import be.ipl.pfe.models.Note;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class NotificationService {

	private final FirebaseMessaging firebaseMessaging;

	public NotificationService() throws IOException {
		GoogleCredentials googleCredentials = GoogleCredentials.fromStream(new ClassPathResource("firebase-service-account.json")
				.getInputStream());
		FirebaseOptions firebaseOptions = FirebaseOptions.builder().setCredentials(googleCredentials).build();
		FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions);
		this.firebaseMessaging = FirebaseMessaging.getInstance(app);

	}

	void sendNotifications(Note note, List<String> tokens) {
		Notification notification = Notification.builder()
		                                        .setTitle(note.getSubject())
		                                        .setBody(note.getContent())
                                                .setImage(note.getImage())
		                                        .build();

		MulticastMessage multicastMessage = MulticastMessage.builder()
		                                                    .addAllTokens(tokens)
		                                                    .setNotification(notification)
		                                                    .build();
		try {
			this.firebaseMessaging.sendMulticast(multicastMessage);
		} catch (FirebaseMessagingException e) {
			// do nothing
		}
	}
}
