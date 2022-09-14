package main.hibe.testStuff;

import main.hibe.coreJdbc.JDBCTemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {


    public static void main(String[] args) {
        JDBCTemplate jdbc = new JDBCTemplate();

        try (
            Connection connection = jdbc.getConnect();
            Statement statement =  connection.createStatement();
            ){
            statement.execute("CREATE TABLE Pe (\n" +
                    "    PersonID int,\n" +
                    "    LastName varchar(255),\n" +
                    "    FirstName varchar(255),\n" +
                    "    Address varchar(255),\n" +
                    "    City varchar(255)\n" +
                    ");");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
