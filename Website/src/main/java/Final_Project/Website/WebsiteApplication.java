/*
* Dawson Chatfield
* Final Project
* Dana Saar
* 05.24.2021
*/

package Final_Project.Website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebsiteApplication {
	public static void main(String[] args) {
		try {
			System.out.println("This will be replaced with the MySQL starter");
		}
		catch(Exception e) {
			return;
		}
		SpringApplication.run(WebsiteApplication.class, args);
	}
}
