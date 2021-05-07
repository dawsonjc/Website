/*
* Dawson Chatfield
* Final Project
* Dana Saar
* 05.24.2021
*/

package Final_Project.Website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.*;
import java.util.Random;

@SpringBootApplication
public class WebsiteApplication {

	public static void main(String[] args) {

		// for future implementation
		String url = "jdbc:mysql://localhost:3306";
		String username = "root";
		String password = "password";
		try(Connection connection = DriverManager.getConnection(url, username, password)) {
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

	public static String sentence() {
		int test = new Random().nextInt();
		return "hello world";
	}
}
