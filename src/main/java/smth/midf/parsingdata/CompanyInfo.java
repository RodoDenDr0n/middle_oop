package smth.midf.parsingdata;

import lombok.*;

import java.io.IOException;

@Getter
@Setter
@Builder
@ToString
public class CompanyInfo {
    public static void main(String[] args) throws IOException {
//        CompanyInfo companyInfo = CompanyInfo.builder().build();
//        BrandFetchParser.getData("ucu.edu.ua", companyInfo);
////        PDLParser.getData("ucu.edu.ua", companyInfo);
//        JSOUPParser.getTwitter("ucu.edu.ua", companyInfo);
//        JSOUPParser.getAddress("ucu.edu.ua", companyInfo);
//        System.out.println(companyInfo);
    }
    private String name;
    private String twitter;
    private String facebook;
    private String logo;
    private String icon;
    private String employees;
    private String address;
}
