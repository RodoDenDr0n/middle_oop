package smth.midf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import smth.midf.parsingdata.CompanyInfo;
import smth.midf.parsingdata.Parser;

import java.io.IOException;

@RestController
//@RequestMapping
@Controller
public class HomeController {
    private CompanyInfo companyInfo;
    @GetMapping(value = "/")
    public String base() {
        return "index.html";
    }

    @PostMapping
    public void addCompany(@RequestBody String url) throws IOException {
        companyInfo = CompanyInfo.builder().build();
        Parser.getData(url, companyInfo);
    }

    @GetMapping(path = "/{url}")
    public CompanyInfo yahoo(@PathVariable String url){
        return companyInfo;
    }


}
