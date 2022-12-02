package smth.midf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class HomeController {
    @GetMapping(value = "/")
    public String base() {
        return "index.html";
    }

    @PostMapping
    public void addCompany(@RequestBody String url) {

    }

}
