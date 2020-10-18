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

    public boolean hasCardAccess(String cardNumber, String pin) {
        ResultSet resultSet = dataBase.getAll();
        String nextCardNumber, nextPin;
        try {
            while (resultSet.next()) {
                nextCardNumber = resultSet.getString("number");
                nextPin = resultSet.getString("pin");
                if (nextCardNumber.equals(cardNumber) && nextPin.equals(pin)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while parsing data");
        }
        return false;
    }

    static class Card {

        private int id;
        private int balance;

        private final long START_NUM = 400_000_436_789_345L;
        private final int CARD_LENGTH = 16;

        private final String NUMBER;
        private final String PIN;

        public Card() {
            this.PIN = "" + new Random().nextInt(9000) + 1000;
            this.NUMBER = buildCardNum();
            this.balance = 0;
            this.id = Bank.numAccounts;
        }

        public String getNUMBER() {
            return this.NUMBER;
        }

        public String getPIN() {
            return this.PIN;
        }

        public int getBalance() {
            return this.balance;
        }

        private String buildCardNum() {
            StringBuilder number = new StringBuilder();
            number.append(START_NUM + numAccounts);
            number.append(getCheckSum(number.toString()));
            return number.toString();
        }

        private int getCheckSum(String number) {
            String[] numbers = number.split("");
            int[] nums = new int[CARD_LENGTH - 1];
            int checkSum = 0;

            for (int i = 0; i < nums.length; i++) {
                if (i % 2 == 0) {
                    nums[i] = Integer.parseInt(numbers[i]) * 2;
                } else {
                    nums[i] = Integer.parseInt(numbers[i]);
                }
                if (nums[i] > 9) {
                    nums[i] -= 9;
                }
                checkSum += nums[i];
            }
            checkSum = 10 - checkSum % 10 == 10 ? 0 : 10 - checkSum % 10;
            return checkSum;
        }
    }


}


