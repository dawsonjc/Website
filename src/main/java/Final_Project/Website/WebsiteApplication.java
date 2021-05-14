/*
* Dawson Chatfield
* Final Project
* Dana Saar
* 04.24.2021 - 05.14.2021
*/

package Final_Project.Website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.*;

@SpringBootApplication
public class WebsiteApplication {
	@RequestMapping(value = "/")
	public String landing_Page() {
		return "index";
	}
	public static void main(String[] args) {

		// for future implementation
		final String url = "jdbc:mysql://localhost:3306";
		final String username = "root";
		final String password = "password";
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			System.out.println("Successful Connection");
		} catch (SQLException e) {
			System.out.println("MySQL server has not been implemented yet");
		}

		// if the mysql server exists and the connection is able to be established, test if tables exist, if not
		// create tables?

		/*
		* implement nginx start up
		*/

		// run current server for testing
		SpringApplication.run(WebsiteApplication.class, args);
	}
}
