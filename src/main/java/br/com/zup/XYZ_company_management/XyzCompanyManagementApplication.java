package br.com.zup.XYZ_company_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class XyzCompanyManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(XyzCompanyManagementApplication.class, args);
	}

}
