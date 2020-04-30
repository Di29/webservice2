package repositories.db;

import repositories.interfaces.IDBRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresRepository implements IDBRepository {
    @Override
    public Connection getConnection() {
        try {
            String connStr = "jdbc:postgresql://localhost:5432/webapp";
            Connection conn = DriverManager.getConnection(connStr, "dias", "1212");
            return conn;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

