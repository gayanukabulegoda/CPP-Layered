package lk.grb.ceylonPottersPaletteLayered.util;

import lk.grb.ceylonPottersPaletteLayered.db.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionConnection {
    public static Connection getDbConnection() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        return connection;
    }

    public static void rollBack(Connection connection) throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }

    public static void commit(Connection connection) throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
    }
}
