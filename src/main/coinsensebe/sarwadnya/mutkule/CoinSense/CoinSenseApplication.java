package sarwadnya.mutkule.CoinSense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoinSenseApplication {

	public static void main(String[] args) {
		 SpringApplication.run(CoinSenseApplication.class, args);
	}

}
