
package com.company;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class Main {

    public static void  main (String[] args) throws FileNotFoundException {
        new Frame("Connexion");

        Properties props = new Properties ();
        try (fileInputStream fis = new
                FileInputStream ("conf.prosperties")) {
            props.load(fis);
        }
        String url = props.getProperty ("jdbc.url");
        String login = props.getProperty("jdbc.login");
        String password = props.getProperty("jdbc.password");
        try (Connection connection =
        DriverManager.getConnection(url, login, password)) {
            String strSQL = "SELECT * FROM  limit 10;";
            try (Statement statement = connection.createStatement() )
            {
                ResultSet resultSet =
                statement.executeQuery(strSQL);
while (resultSet.next()) { String user_id =
                resultSet.getString ("user_id");
                           String user_id =
                resultSet.getString ("user_login");
                           String userPassword =
                resultSet.getString ("user_password"); String user_password =

System.out.printf("%s %s %s \n", user_id, user_login, user_password);
            } }
        } }
}


