package rkj.clientRepo.clientRepo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ClientRepoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientRepoApplication.class, args);
	}

}
