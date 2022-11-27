package parsingdata;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataParser {
    public static void main(String[] args) throws IOException {
        // https://www.linkedin.com/company/epam-systems/
        // https://ucu.edu.ua/
        // https://lnu.edu.ua/en
        // https://lpnu.ua/
        Document document = fetchHTML("https://lnu.edu.ua/en");
        document.title();
        String data = String.valueOf(document);
        CompanyInfo info = getData(data);
        System.out.println(info);
    }

    public static Document fetchHTML(String URL) throws IOException {
        // token = l2SS4e5dPxPBfV6ATcSXg64SyoOcaeiD6lOQuSjD+54=
        return Jsoup.connect(URL)
                .data("query", "Java")
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.38 Safari/537.36")
                .cookie("auth", "token")
                .timeout(3000)
                .ignoreHttpErrors(true)
//                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.38 Safari/537.36")
//                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .post();
    }

    public static CompanyInfo getData(String data) {
        return CompanyInfo.builder()
                .name(findName(data))
                .twitter(findTwitter(data))
                .facebook(findFacebook(data))
                .logo(findLogo(data))
                .icon(findIcon(data))
                .employees(findEmployees(data))
                .address(findAddress(data))
                .build();
    }

    private static String findName(String data) {
        String name = null;
        Pattern pattern = Pattern.compile("");
        Matcher matcher = pattern.matcher(data);
        matcher.find();
        name = matcher.group();
        return name;
    }

    private static String findTwitter(String data) {
        // facebook-twitter regex:
        // https:\/\/(www\.)?(facebook|twitter)(\.com)\/\w+
        String twitter = null;
        Pattern pattern = Pattern.compile("");
        Matcher matcher = pattern.matcher(data);
        matcher.find();
        twitter = matcher.group();
        return twitter;
    }

    private static String findFacebook(String data) {
        String facebook = null;
        Pattern pattern = Pattern.compile("");
        Matcher matcher = pattern.matcher(data);
        matcher.find();
        facebook = matcher.group();
        return facebook;
    }

    private static String findLogo(String data) {
        // logo/icon regex:
        // https:\/\/(.*?)\/(logo|icon)\.(png|jpg|jpeg)
        String logo = null;
        Pattern pattern = Pattern.compile("");
        Matcher matcher = pattern.matcher(data);
        matcher.find();
        logo = matcher.group();
        return logo;
    }

    private static String findIcon(String data) {
        String icon = null;
        Pattern pattern = Pattern.compile("");
        Matcher matcher = pattern.matcher(data);
        matcher.find();
        icon = matcher.group();
        return icon;
    }

    private static String findEmployees(String data) {
        String employees = null;
        Pattern pattern = Pattern.compile("");
        Matcher matcher = pattern.matcher(data);
        matcher.find();
        employees = matcher.group();
        return employees;
    }

    private static String findAddress(String data) {
        String address = null;
        Pattern pattern = Pattern.compile("");
        Matcher matcher = pattern.matcher(data);
        matcher.find();
        address = matcher.group();
        return address;
    }
}
