package service;

import org.postgresql.ds.PGConnectionPoolDataSource;
import org.postgresql.ds.PGSimpleDataSource;
import org.postgresql.jdbc3.Jdbc3ConnectionPool;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCTemplate {
    PGConnectionPoolDataSource ds = new PGConnectionPoolDataSource();
    Connection connection = null;

    private void setUp() throws NamingException {
        try {
            ds.setServerNames(new String[]{"localhost"});
            ds.setDatabaseName("hibe");
            ds.setUser("postgres");
            ds.setPassword("password");
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public Connection getConnect() {

        try {
            setUp();
            connection = ds.getConnection();

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
