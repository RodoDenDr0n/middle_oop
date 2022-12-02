package smth.midf.parsingdata;


import lombok.*;

import java.io.IOException;

@Getter
@Setter
@Builder
@ToString
public class CompanyInfo {
    public static void main(String[] args) throws IOException {
        CompanyInfo companyInfo = CompanyInfo.builder().build();
        String domain = "amazon.com";
        Parser.getData(domain, companyInfo);
        System.out.println(companyInfo);
    }
    private String name;
    private String twitter;
    private String facebook;
    private String logo;
    private String icon;
    private String employees;
    private String address;
}
