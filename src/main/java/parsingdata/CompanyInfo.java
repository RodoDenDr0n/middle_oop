package parsingdata;


import lombok.*;

@Getter
@Setter
@Builder
@ToString
public class CompanyInfo {
    public static void main(String[] args) {
        CompanyInfo data = CompanyInfo.builder()
                .name("name")
                .twitter("twit")
                .facebook("mordoknyzhka")
                .logo("O(log)")
                .icon("icona")
                .employees("-1")
                .address("sserdda")
                .build();
        System.out.println(data);
    }

    private String name;
    private String twitter;
    private String facebook;
    private String logo;
    private String icon;
    private String employees;
    private String address;
}
