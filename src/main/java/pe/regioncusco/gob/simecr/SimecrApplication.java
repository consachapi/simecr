package pe.regioncusco.gob.simecr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SimecrApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimecrApplication.class, args);
	}

}
