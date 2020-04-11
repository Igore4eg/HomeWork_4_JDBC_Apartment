package prog;

import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/apartments?serverTimezone=Europe/Kiev";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "Asusk52j";

    static Connection conn;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            try {
                conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

                while (true) {
                    System.out.println("1: add apartment");
                    System.out.println("2: delete apartment");
                    System.out.println("3: change apartment");
                    System.out.println("4: apartment by district");
                    System.out.println("5: apartment by address");
                    System.out.println("6: apartment by area");
                    System.out.println("7: apartment by number of rooms");
                    System.out.println("8: apartment by price");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            addApartment(sc);
                            break;
                        case "2":
                            deleteApartment(sc);
                            break;
                        case "3":
                            changeApartment(sc);
                            break;
                        case "4":
                            apartmentByDistrict(sc);
                            break;
                        case "5":
                            apartmentByAddress(sc);
                            break;
                        case "6":
                            apartmentByArea(sc);
                            break;
                        case "7":
                            apartmentByNumberOfRooms(sc);
                            break;
                        case "8":
                            apartmentByPrice(sc);
                            break;
                        default:
                            return;
                    }
                }
            } finally {
                sc.close();
                if (conn != null) conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return;
        }
    }

    private static void addApartment(Scanner sc) throws SQLException {
        System.out.print("Enter district: ");
        String district = sc.nextLine();
        System.out.print("Enter address: ");
        String address = sc.nextLine();
        System.out.print("Enter area: ");
        String sArea = sc.nextLine();
        double area = Double.parseDouble(sArea);
        System.out.print("Enter number of rooms: ");
        String sNumberOfRooms = sc.nextLine();
        int numberOfRooms = Integer.parseInt(sNumberOfRooms);
        System.out.print("Enter price: ");
        String sPrice = sc.nextLine();
        double price = Double.parseDouble(sPrice);


        //String sql = "INSERT INTO Clients (name, age) " +
          //      "VALUES(" + name +", " + age + ")";

        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO Apartment (district, address, area, NumberOfRooms, price) VALUES(?, ?, ?, ?, ?)");
        try {
            ps.setString(1, district);
            ps.setString(2, address);
            ps.setDouble(3, area);
            ps.setInt(4, numberOfRooms);
            ps.setDouble(5, price);
            ps.executeUpdate();
        } finally {
            ps.close();
        }
    }

    private static void deleteApartment(Scanner sc) throws SQLException {
        System.out.print("Enter address: ");
        String name = sc.nextLine();

        PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM Apartment WHERE address = ?");
        try {
            ps.setString(1, name);
            ps.executeUpdate();
        } finally {
            ps.close();
        }
    }

    private static void changeApartment(Scanner sc) throws SQLException {
        System.out.print("Enter address: ");
        String name = sc.nextLine();
        System.out.print("Enter new price: ");
        String sAge = sc.nextLine();
        double price = Double.parseDouble(sAge);

        PreparedStatement ps = conn.prepareStatement(
                "UPDATE apartment SET price = ? " +
                        "WHERE address = ?");
        try {
            ps.setDouble(1, price);
            ps.setString(2, name);
            ps.executeUpdate();
        } finally {
            ps.close();
        }
    }


    private static void apartmentByDistrict(Scanner sc) throws SQLException {
        System.out.print("Enter district: ");
        String district = sc.nextLine();
        PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM apartment WHERE district = ?");
        try {
            ps.setString(1, district);
            ResultSet rs = ps.executeQuery();
            try {

                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(md.getColumnName(i) + "\t\t");
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            } finally {
                rs.close();
            }
        } finally {
            ps.close();
        }
    }


    private static void apartmentByAddress(Scanner sc) throws SQLException {
        System.out.print("Enter address: ");
        String address = sc.nextLine();
        PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM apartment WHERE address = ?");
        try {
            ps.setString(1, address);
            ResultSet rs = ps.executeQuery();
            try {

                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(md.getColumnName(i) + "\t\t");
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            } finally {
                rs.close();
            }
        } finally {
            ps.close();
        }
    }

    private static void apartmentByArea(Scanner sc) throws SQLException {
        System.out.print("Enter area: ");
        String area = sc.nextLine();
        PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM apartment WHERE area = ?");
        try {
            ps.setString(1, area);
            ResultSet rs = ps.executeQuery();
            try {

                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(md.getColumnName(i) + "\t\t");
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            } finally {
                rs.close();
            }
        } finally {
            ps.close();
        }
    }

    private static void apartmentByNumberOfRooms(Scanner sc) throws SQLException {
        System.out.print("Enter number of rooms: ");
        String number = sc.nextLine();
        PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM apartment WHERE NumberOfRooms = ?");
        try {
            ps.setString(1, number);
            ResultSet rs = ps.executeQuery();
            try {

                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(md.getColumnName(i) + "\t\t");
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            } finally {
                rs.close();
            }
        } finally {
            ps.close();
        }
    }

    private static void apartmentByPrice(Scanner sc) throws SQLException {
        System.out.print("Enter price: ");
        String price = sc.nextLine();
        PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM apartment WHERE price = ?");
        try {
            ps.setString(1, price);
            ResultSet rs = ps.executeQuery();
            try {

                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(md.getColumnName(i) + "\t\t");
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            } finally {
                rs.close();
            }
        } finally {
            ps.close();
        }
    }


}
