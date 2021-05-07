package Final_Project.Website.Pages.LandingPage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import Final_Project.Website.User.User;

@Controller
public class Landing_Page {
    @GetMapping(value = "/")
    public String index(Model model) {
        User user = new User();
        model.addAttribute("User", user);
        return "index";
    }

    @PostMapping(value="/")
    public String Log_In(@ModelAttribute("User") User user) {
        // implement a login System
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        return "redirect:/Account";
    }
}
