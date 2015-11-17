/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Scanner;

/**
 *
 * @author vocho
 */

public class Exercise3 {
    public static void main(String args[]){
        try {
            try (Connection con = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/sample", "app", "app")) {
                Scanner sc = new Scanner(System.in);
                boolean end = false;
                System.out.println("Hi I'm a news board ");
                System.out.println("Type 1 to find a news by place ");
                System.out.println("Type 2 to find a news by time");
                System.out.println("Type 3 for quit");
                while (!end) {
                    int cmd = sc.nextInt();
                    switch (cmd) {
                        case 1: {
                            System.out.println("Type the place to search");
                            String name = sc.next();
                            searchPlace(con, name);
                            break;
                        }
                        case 2:{
                            System.out.println("Type the local time to search");
                            String name = sc.next();
                            searchTime(con, name);
                            break;
                        }
                        case 3:
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

    private static void searchPlace(Connection con, String search) {
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
    
    private static void searchTime(Connection con, String search) {
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
