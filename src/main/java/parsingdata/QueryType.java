package parsingdata;

public enum QueryType {
    NAME("name"),
    TWITTER("twitter"),
    FACEBOOK("facebook"),
    LOGO("logo"),
    ICON("icon"),
    EMPLOYEES("employees"),
    ADDRESS("address");

    private final String string;

    QueryType(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
