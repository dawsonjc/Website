package Final_Project.Website.Pages.AccountPage;

import Final_Project.Website.Object.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
public class AccountPage {
    private User user;
    private final HashMap<String, String> valid_User = new HashMap<>() {
        {
            put("root", "password");
        }
    };


    @PostMapping(value="/Login/user")
    public String Log_In(@ModelAttribute("User") User user1) {
        // implement a login System
        if(!(user1.getUsername().equals("root") &&
                this.valid_User.get("root").equals(user1.getPassword()))) {
          return "redirect:/";
        }

        // set as class variable
        this.user = user1;
        return "redirect:/locations";
    }

    @RequestMapping(value="/Account")
    public String Account_Page() {
        System.out.println(this.user);
        return "Account";
    }
    
    @GetMapping(value="/ping_Server")
    public String test() {
        System.out.println("The server has been pinged");
        return "redirect:/Account";
    }
}
