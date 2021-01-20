package bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import exceptions.InvalidCardException;

public class Bank {

    protected DataBase dataBase;
    protected static int numAccounts = 0;

    public Bank(String fileName) {
        this.dataBase = new DataBase(fileName);
    }

    public Card createCard() {
        Card card = new Card();
        this.dataBase.add(card.getId(), card.getNUMBER(), card.getPIN(), card.getBalance());
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

    public void deleteAccount(String cardNumber) {
        dataBase.deleteRow(cardNumber);
    }

    public void deposit(String cardNumber, int amount) throws InvalidCardException {
        if (!isValidCard(cardNumber)) {
            throw new InvalidCardException();
        }
        Card card = getCard(cardNumber);
        if (card == null) {
            throw new NoSuchElementException();
        }
        dataBase.updateBalance(amount, cardNumber);
    }

    public Card getCard(String cardNumber) {
        ResultSet row = dataBase.getRow(cardNumber);
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

    public boolean isValidCard(String cardNumber) {
        int sum = 0;
        int next;
        for (int i = 0; i < cardNumber.length() - 1; i++) {
            next = Integer.parseInt(String.valueOf(cardNumber.charAt(i)));
            if (i % 2 == 0) {
                next *= 2;
                if (next > 9) {
                    next -= 9;
                }
            }
            sum += next;
        }
        sum += Integer.parseInt(String.valueOf(cardNumber.charAt(cardNumber.length() - 1)));
        return sum % 10 == 0;
    }
}
