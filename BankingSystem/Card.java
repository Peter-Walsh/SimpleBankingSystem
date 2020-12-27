package banking;

import java.util.Random;

public class Card {

    private int id;
    private int balance;
    public static int numAccounts = 0;

    private final long START_NUM = 400_000_436_789_345L;
    private final int CARD_LENGTH = 16;

    private final String NUMBER;
    private final String PIN;

    public Card(int id, String number, String pin, int balance) {
        this.id = id;
        this.NUMBER = number;
        this.PIN = pin;
        this.balance = balance;
    }

    public Card() {
        this.PIN = "" + (new Random().nextInt(9000) + 1000);
        numAccounts++;
        this.NUMBER = buildCardNum();
        this.balance = 0;
        this.id = Bank.numAccounts;
    }

    @Override
    public String toString() {
        String result;
        result = "Id: " + id + "\n";
        result += "Card number: " + NUMBER + "\n";
        result += "Pin: " + PIN + "\n";
        result += "Balance: " + balance + "\n";
        return result;
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

    public int getId() { return this.id; }

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
