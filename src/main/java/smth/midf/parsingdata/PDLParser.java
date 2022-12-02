package smth.midf.parsingdata;

import kong.unirest.json.JSONObject;
import smth.midf.parsingdata.CompanyInfo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class PDLParser {
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
        JSONObject data = new JSONObject(text).getJSONArray("data").getJSONObject(0);
        companyInfo.setName(data.get("name").toString()); // get name
        companyInfo.setEmployees(data.get("employee_count").toString()); // get employees
        JSONObject locations = ((JSONObject)data.get("location"));
        for (String locationType : locations.keySet()) {
            if (locations.get(locationType) != null) {
                companyInfo.setAddress(locations.get(locationType).toString());
                break;
            }
        }
    }
}
