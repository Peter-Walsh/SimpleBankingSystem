package BankingSystem;

import org.sqlite.SQLiteDataSource;

import javax.swing.plaf.TabbedPaneUI;
import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.concurrent.Callable;

public class DataBase {

    private final String url;
    private final String TABLE_NAME = "card";

    private final String SETUP = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                                 "id INTEGER," +
                                 "number TEXT," +
                                 "pin TEXT," +
                                 "balance INTEGER DEFAULT 0);";

    private final Statement statement;

    public DataBase(String fileName) {
        this.url = "jdbc:sqlite:/Users/peteylumpkins/desktop/" + fileName;
        statement = getStatement();
        executeUpdate(SETUP);
    }

    public void clearDataBase() {
        executeUpdate("DROP TABLE IF EXISTS " + TABLE_NAME);
        executeUpdate(SETUP);
    }

    public ResultSet getAll() {
        String update = "SELECT * FROM " + TABLE_NAME;
        return executeQuery(update);
    }
    
    public ResultSet getRow(String cardNumber, String pin) {
        String update = "SELECT id, number, pin, balance FROM " + TABLE_NAME +
                " WHERE number = " + cardNumber +
                " AND pin = " + pin + ";";
        return executeQuery(update);
    }

    public ResultSet getRow(int id) {
        String update = "SELECT id FROM " + TABLE_NAME + " WHERE id = " + id + ";";
        return executeQuery(update);
    }

    public void add(int id, String cardNumber, String pin, int balance) {
        String update = "INSERT INTO " + TABLE_NAME + " VALUES " +
                "(" + id + ", " + "'" + cardNumber + "'" + ", " +
                "'" + pin + "'" + ", " + balance + ")";
        executeUpdate(update);
    }
    
    @Override
    public String toString() {
        
        // todo
        
        StringBuilder result = new StringBuilder();
        ResultSet database = this.getAll();
        result.append("      | id |     CardNumber    |  Pin  |    Balance    |\n");
        result.append("-----------------------------------------------------\n");
        try {
            int row = 1;
            String id, num, pin, balance, r;
            while(database.next()) {
                r = "Row " + row + " |";
                id = " " + database.getString("id") + " |";
                num = database.getString("num") + "|";
                pin = " " + database.getString("pin");
                balance = database.getString("balance");

                result.append(r);
                result.append(id);
                result.append(num);
                result.append(pin);
            }
        } catch (SQLException e) {
            System.out.println("Error while getting database");
        }
        return result.toString();
    }

    private Statement getStatement() {
        SQLiteDataSource source = getDataSource();
        Connection connection = getConnection(source);
        if (connection == null) {
            return null;
        }
        return getStatement(connection);
    }

    private SQLiteDataSource getDataSource() {
        SQLiteDataSource temp = new SQLiteDataSource();
        temp.setUrl(url);
        return temp;
    }

    private Connection getConnection(SQLiteDataSource source) {
        try {
            return source.getConnection();
        } catch (SQLException e) {
            System.out.println("Connection error");
            return null;
        }
    }

    private Statement getStatement(Connection con) {
        try {
            return con.createStatement();
        } catch (SQLException e) {
            System.out.println("Statement error");
            return null;
        }
    }

    private void executeUpdate(String update) {
        if (statement != null) {

            try {
                statement.executeUpdate(update);
            } catch (SQLException e) {
                System.out.println("Execute Statement error");
            }
        }
    }

    private ResultSet executeQuery(String query) {
        if (statement != null) {
            try {
                return statement.executeQuery(query);
            } catch (SQLException e) {
                System.out.println("Execute Query Error");
            }
        }
        return null;
    }

}
