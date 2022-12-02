package parsingdata;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.json.JSONObject;

import javax.swing.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
//import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BrandFetchParser {
    String API_KEY = "l2SS4e5dPxPBfV6ATcSXg64SyoOcaeiD6lOQuSjD+54=";

    public static void main(String[] args) {
        CompanyInfo companyInfo = CompanyInfo.builder().build();
        getData("ucu.edu.ua", companyInfo);
        System.out.println(companyInfo);
    }


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
