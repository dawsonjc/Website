package Final_Project.Website.Pages.LandingPage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import Final_Project.Website.User.User;

@Controller
public class Landing_Page {

    @GetMapping(value = "/")
    public String Login_Form(Model model) {
        System.out.println("Login Form called");
        User user = new User();
        model.addAttribute("User", user);
        return "index";
    }
}
