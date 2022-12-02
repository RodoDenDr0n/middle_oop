package smth.midf.parsingdata;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import smth.midf.parsingdata.CompanyInfo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static void getData(String domain, CompanyInfo companyInfo) throws IOException {
        BrandFetchParser.getData(domain, companyInfo);
        PDLParser.getData(domain, companyInfo);
        JSOUPParser.getData(domain, companyInfo);

    }
    static class BrandFetchParser {
        String API_KEY = "l2SS4e5dPxPBfV6ATcSXg64SyoOcaeiD6lOQuSjD+54=";
        public static void getData(String domain, CompanyInfo companyInfo) {
            HttpResponse<String> response = Unirest.get(String.format("https://api.brandfetch.io/v2/brands/%s", domain))
                    .header("Authorization", "Bearer l2SS4e5dPxPBfV6ATcSXg64SyoOcaeiD6lOQuSjD+54=")
                    .asString();

            JSONObject body = new JSONObject(response.getBody());

            JSONArray links = body.getJSONArray("links");
            JSONArray logos = body.getJSONArray("logos");

            // gets links
            for (int i = 0; i < links.length(); i++) {
                JSONObject link = (JSONObject)links.get(i);
                switch (link.get("name").toString()) {
                    case "facebook" ->
                            companyInfo.setFacebook(link.get("url").toString());
                    case "twitter" ->
                            companyInfo.setTwitter(link.get("url").toString());
                }
            }

            // gets icons
            for (int i = 0; i < logos.length(); i++) {
                JSONObject logo = (JSONObject)logos.get(i);
                switch (logo.get("type").toString()) {
                    case "icon" ->
                            companyInfo.setIcon(((JSONObject)((JSONArray)logo.get("formats")).get(0)).get("src").toString());
                    case "logo" ->
                            companyInfo.setLogo(((JSONObject)((JSONArray)logo.get("formats")).get(0)).get("src").toString());
                }
            }
        }
    }

    static class JSOUPParser {
        public static final String GOOGLE_SEARCH = "https://www.google.com/search?q=%s&num=10";
        public static final String MAPS_SEARCH = "https://www.google.com/maps/place/";
        public static void getData(String domain, CompanyInfo companyInfo) throws IOException {
            Document document = Jsoup.connect(String.format(GOOGLE_SEARCH, domain + " twitter")).userAgent("Mozilla/5.0").get();
            Pattern pattern = Pattern.compile("https:\\/\\/twitter\\.com\\/.+?(?=&)");
            Matcher matcher = pattern.matcher(document.toString());
            matcher.find();
            companyInfo.setTwitter(matcher.group());
        }
    }

    static class PDLParser {
        // People Data Labs Parser
        private static final String API_KEY = "e3fe73744e76c30ab541b0a09cc30a615c757511dac7a13990a56a796e7e6a25";
        public static void main(String[] args) throws IOException {

        }

        public static void getData(String domain, CompanyInfo companyInfo) throws IOException {
            String query = URLEncoder.encode(String.format("SELECT NAME FROM COMPANY WHERE WEBSITE='%s'", domain), StandardCharsets.UTF_8);

            URL url = new URL("https://api.peopledatalabs.com/v5/company/search?sql=" + query);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Api-Key", API_KEY);
            connection.connect();
            String text = new Scanner(connection.getInputStream()).useDelimiter("\\Z").next();
            kong.unirest.json.JSONObject data = new kong.unirest.json.JSONObject(text).getJSONArray("data").getJSONObject(0);
            companyInfo.setName(data.get("name").toString()); // get name
            companyInfo.setEmployees(data.get("employee_count").toString()); // get employees
            kong.unirest.json.JSONObject locations = ((kong.unirest.json.JSONObject)data.get("location"));
            for (String locationType : locations.keySet()) {
                if (locations.get(locationType) != null) {
                    companyInfo.setAddress(locations.get(locationType).toString());
                    break;
                }
            }
        }
    }
}
