package Final_Project.Website.Pages.AccountPage;

import Final_Project.Website.Object.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

@Controller
public class AccountPage {
    private User user;

    @GetMapping(value = "/Account/login")
    public String Login_Form(Model model) {
        User UserLogin = new User();
        model.addAttribute("User", UserLogin);
        return "Login";
    }

    @PostMapping(value="/Account/user")
    public String Log_In(@ModelAttribute("User") User user1) {
        final String url = "jdbc:mysql://localhost:3306/clients";
        final String username = "root";
        final String password = "password";

        // connect to database
        try(Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement query = conn.createStatement();

            // does the user exist?
            String sql = "SELECT COUNT(*) FROM user WHERE Username = ? AND Password = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user1.getUsername());
            ps.setString(2, user1.getPassword());
            ResultSet rs = ps.executeQuery();
            //
            rs.next();
            if(rs.getInt(1) == 0) {
                return "redirect:/Account/login";
            }

            // user exists and now to link user to user object
            sql = "SELECT * FROM user WHERE Username = \"" + user1.getUsername() + "\" AND Password = \"" + user1.getPassword() + "\";";
            ResultSet user_Info = query.executeQuery(sql);
            if(user_Info.next()) {
                this.user = User.getUserFromServer(user_Info);
            }
            return "redirect:/locations";
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(value="/ping_Server")
    public String test() {
        System.out.println("The server has been pinged");
        return "redirect:/Account";
    }
}
