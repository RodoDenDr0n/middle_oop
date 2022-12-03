package smth.midf;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {
    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("postgres://imtmzwdzirswla:a683e7242742083f40e16fd8ab628ffd6ad9283490606166a54973276774ab9e@ec2-54-208-104-27.compute-1.amazonaws.com:5432/dcshckdcfnv6fv"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        return DriverManager.getConnection(dbUrl, username, password);
    }

    public static void main(String[] args) throws SQLException, URISyntaxException {
        try{        System.out.println(getConnection().toString());
        }
        catch (URISyntaxException e){
            System.out.println("Exception");
        }
    }
}
