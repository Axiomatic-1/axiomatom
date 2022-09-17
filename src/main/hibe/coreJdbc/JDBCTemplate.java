package hibe.coreJdbc;

import org.postgresql.ds.PGConnectionPoolDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCTemplate {
    PGConnectionPoolDataSource ds = new PGConnectionPoolDataSource();
    Connection connection = null;

    private void setUp() {
        ds.setServerNames(new String[]{"localhost"});
        ds.setDatabaseName("hibe");
        ds.setUser("postgres");
        ds.setPassword("password");
    }

    public Connection getConnect() {
        try {
            setUp();
            connection = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
