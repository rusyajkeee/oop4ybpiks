package org.example;

import org.example.models.Ticket;
import org.example.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


public class MyApplication {
    public static void main(String[] args) {

        final String JDBC_URL = "jdbc:postgresql://localhost:5432/based";
        final String USERNAME = "postgres";
        final String PASSWORD = "1709";
        ArrayList<Ticket> tickets = new ArrayList<>();
        ArrayList<Ticket> mytickets = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        Connection con = null;

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            addUserInDB(con, users);
            String sql = "SELECT id, name, price FROM tickets ORDER BY id;";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                int price = rs.getInt("price");
                Ticket ticket = new Ticket(name, price);
                tickets.add(ticket);
            }
            users.get(0).buyTicket(tickets.get(1));

        } catch (SQLException e) {
            System.out.println("connection error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("driver error: " + e.getMessage());
        }

        finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("could not close the connection: " + e.getMessage());
                }
            }
        }

        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }
    }

    public static void addUserInDB(Connection con, ArrayList<User> users) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Login: ");
        String login = sc.nextLine();
        login = "'" + login + "'";
        System.out.println("Password: ");
        String password = sc.nextLine();
        User user = new User(login, password);
        users.add(user);
        String sql = "INSERT INTO users (login, password) VALUES (?, ?)";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, login);
        stm.setString(2, password);
        stm.executeUpdate();
    }
}


