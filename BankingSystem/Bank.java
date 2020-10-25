package BankingSystem;
import java.util.*;

import javafx.scene.control.Tab;
import org.sqlite.SQLiteDataSource;
import java.sql.*;

public class Bank {

    protected DataBase dataBase;
    protected static int numAccounts = 0;

    public Bank(String fileName) {
        this.dataBase = new DataBase(fileName);
    }

    public Card createCard() {
        Card card = new Card();
        this.dataBase.add(card.id, card.NUMBER, card.PIN, card.balance);
        numAccounts++;
        return card;
    }

    public Card getCard(String number, String pin) {
        ResultSet row = dataBase.getRow(number, pin);
        try {
            String id = row.getString("id");
            String cardNum = row.getString("number");
            String pinNum = row.getString("pin");
            String balance = row.getString("balance");
            return new Card(Integer.parseInt(id), cardNum, pinNum, Integer.parseInt(balance));
        } catch (SQLException e) {
            return null;
        }
    }

}


