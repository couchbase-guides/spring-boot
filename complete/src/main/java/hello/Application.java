package hello;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(Bucket bucket) {
		return args -> {
			JsonObject jo = JsonObject.create();
			jo.put("firstName", "Alice");
			jo.put("lastName", "Smith");
			JsonDocument document = JsonDocument.create("asmith", jo);
			bucket.upsert(document);

			JsonDocument doc = bucket.get("asmith");
			System.out.println(doc.content());
		};
	}

}
