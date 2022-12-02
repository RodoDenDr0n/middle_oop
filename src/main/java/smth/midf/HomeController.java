package smth.midf;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import smth.midf.parsingdata.CompanyInfo;
import smth.midf.parsingdata.Parser;

import java.io.IOException;
import java.util.Optional;

//@RestController
//@RequestMapping
@Controller
public class HomeController {
    private CompanyInfo companyInfo = CompanyInfo.builder().build();

    @GetMapping(value = "/")
    public String base() {
        return "index.html";
    }

    @GetMapping(path = "/{domain}")
    public Optional<CompanyInfo> sendResponse(@PathVariable String domain) throws IOException, JSONException {
        companyInfo = CompanyInfo.builder().build();
        Parser.getData(domain, companyInfo);
        return Optional.ofNullable(companyInfo);
    }

    @PostMapping
    public void addCompany(@RequestBody String domain) throws IOException, JSONException {
        companyInfo = CompanyInfo.builder().build();
        Parser.getData(domain, companyInfo);
    }


    @ExceptionHandler({IOException.class, JSONException.class})
    @GetMapping (path = "/ex")
    public String handle(){
        return "exception.html";
    }


}
