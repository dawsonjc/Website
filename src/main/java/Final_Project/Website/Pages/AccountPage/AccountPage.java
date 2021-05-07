package Final_Project.Website.Pages.AccountPage;

import Final_Project.Website.User.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountPage {
    User user;

    @GetMapping(value = "/Account")
    public String Account_Page(@RequestParam(value = "User") User user1) {
        System.out.println("redirected successfully");
        this.user = user1;
        System.out.println("user created successfully");
        return "Account";
    }
}
