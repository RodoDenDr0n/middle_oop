package smth.midf;

import lombok.SneakyThrows;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;
import smth.midf.parsingdata.CompanyInfo;
import smth.midf.parsingdata.Parser;


@RestController
@RequestMapping(path = "/result", method = RequestMethod.POST)
public class ResponseController {

    private String first_part = "<!DOCTYPE html> <html lang='en'> <head> <meta charset='UTF-8'><title>DataFinder</title></head><body><h1>DataFinder</h1><form><form id='contact-form' action='/result' method='POST'><input type='text' id='domain' name='domain'><input type='submit' formaction='/result'></form><p id='result'>";
    private String second_part = "</p></form></body></html>";

    @SneakyThrows
    @GetMapping
    public String sendResponse(@RequestParam("domain") String domain) throws JSONException {
        CompanyInfo companyInfo = CompanyInfo.builder().build();
        Parser.getData(domain, companyInfo);
        return first_part + companyInfo.toString().replaceAll(",", "<br><br>") + second_part;

    }

}
