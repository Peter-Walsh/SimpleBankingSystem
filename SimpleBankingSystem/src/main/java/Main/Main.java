package Main;

import bank.Bank;
import bank.Interface;

/**
 * This is the main method that runs the banking system. All you have to do to run it is
 * run the main method in this file. Hopefully it works.
 *
 * The field below <code>filename</code> indicates the  name of the database file that the
 * system will read and write data to. It's currently set to "database.db", but feel free to
 * change it.
 *
 *
 */

public class Main {
    public static void main(String[] args) {
        String filename = "database.db";

        Bank bank = new Bank(filename);
        Interface ui = new Interface(bank);
        ui.run();
    }
}
