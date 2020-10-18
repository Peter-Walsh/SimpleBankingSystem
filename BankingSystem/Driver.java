package BankingSystem;
import org.sqlite.*;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Driver {

    public static void main(String[] args) {
        Bank bank = new Bank("database10.db");
        Interface ui = new Interface(bank);


    }
}
