package Final_Project.Website.Pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Landing_Page {
    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    public boolean Login(String username, Object password) {
        return (username.equals("") && password.equals(""));
    }
}
