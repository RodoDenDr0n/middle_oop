package smth.midf.parsingdata;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
public class CompanyInfo {
    private String name;
    private String twitter;
    private String facebook;
    private String logo;
    private String icon;
    private String employees;
    private String address;
}
