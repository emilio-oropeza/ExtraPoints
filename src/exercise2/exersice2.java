
package exercise2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Scanner;

public class exersice2 {
    public static void main(String args[]){
        try {
            try (Connection con = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/sample", "app", "app")) {
                Scanner sc = new Scanner(System.in);
                boolean end = false;
                System.out.println("Hi I'm a phone book ");
                System.out.println("Type search to find a person ");
                System.out.println("Type quit to close this phone book");
                while (!end) {
                    String cmd = sc.next();
                    switch (cmd) {
                        case "search": {
                            System.out.println("Type the name to search");
                            String name = sc.next();
                            search(con, name);
                            break;
                        }
                        case "quit":
                            end = true;
                            break;
                        default:
                            System.out.printf("unknown command: %s%n", cmd);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void search(Connection con, String search) {
        try (Statement st = con.createStatement()) {
            try (ResultSet rs = st.executeQuery("select * from PHONE_BOOK where PHONE_BOOK.NAME='"+search+"'")) {
                while (rs.next()) {
                    String name = rs.getString("NAME");
                    String phone_number = rs.getString("PHONE_NUMBER");
                    String time_zone = rs.getString("TIME_ZONE");
                    ZoneId zone = ZoneId.of(time_zone);
                    LocalTime time = LocalTime.now(zone);
                    System.out.printf("name: %s, phone: %s, current time: %s%n",
                            name, phone_number, time.toString());
                }
            }
        }catch( SQLException e){
            System.out.println("name not found");
        }
    }
}
