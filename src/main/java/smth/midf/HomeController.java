package smth.midf;

import org.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping
public class HomeController {

    @GetMapping(path = "/")
    public String base() {
        return "index.html";
    }

    @ExceptionHandler({IOException.class, JSONException.class})
    @GetMapping (path = "/ex")
    public String handle(){
        return "Something went wrong... :=(";
    }

}
