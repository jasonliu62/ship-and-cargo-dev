package us.dev.shipandcargo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("us.dev.shipandcargo.dao")
public class ShipAndCargoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShipAndCargoApplication.class, args);
	}

}
