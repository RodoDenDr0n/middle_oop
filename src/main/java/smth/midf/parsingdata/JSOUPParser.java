package smth.midf.parsingdata;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import static parsingdata.QueryType.TWITTER;

public class JSOUPParser {
    public static final String GOOGLE_SEARCH = "https://www.google.com/search?q=%s&num=10";
    public static void main(String[] args) throws IOException {
        // https://www.linkedin.com/company/epam-systems/
        // https://ucu.edu.ua/
        // https://lnu.edu.ua/en
        // https://lpnu.ua/
//        Document document = fetchHTML("https://lnu.edu.ua/en");
//        document.title();
//        String data = String.valueOf(document);
//        CompanyInfo info = getData(data);
//        System.out.println(info);

    }

    public static void getTwitter(String domain, CompanyInfo companyInfo) throws IOException {
        Document document = Jsoup.connect(String.format(GOOGLE_SEARCH, domain + " twitter")).userAgent("Mozilla/5.0").get();
        Pattern pattern = Pattern.compile("https:\\/\\/twitter\\.com\\/.+?(?=&)");
        Matcher matcher = pattern.matcher(document.toString());
        matcher.find();
        companyInfo.setTwitter(matcher.group());
    }

    public static void getAddress(String domain, CompanyInfo companyInfo) throws IOException {
        Document document = Jsoup.connect(String.format(GOOGLE_SEARCH, domain + " address")).userAgent("Mozilla/5.0").get();
        System.out.println(document);
//        Pattern pattern = Pattern.compile("https:\\/\\/twitter\\.com\\/.+?(?=&)");
//        Matcher matcher = pattern.matcher(document.toString());
//        matcher.find();
//        companyInfo.setAddress(matcher.group());
    }
}
