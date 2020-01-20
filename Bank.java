/**
 * Based on the ecs100 template.
 * Code for Bank account with GUI
 *
 * @author (NY)
 * @version (a version number or a date)
 */

import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

/**
 * A bank is a GUI to give access to bank accounts --
 * only one at the moment!
 */
public class Bank
{
    // instance variables
    private Account[] theAccounts;
    private final int SIZE = 200;   // max number of accounts
    
    private int current;    // 1 less than current number of accounts

    /**
     * Constructor for objects of class Bank
     */
    public Bank()
    {
        UI.initialise();    // Ensure that the User Interface window is initialised
        
        UI.addButton("Create Account", () -> mkAccount());
        UI.addButton("Deposit", () -> deposit());
        UI.addButton("Balance", () -> getBalance());
        UI.addButton("Withdraw", () -> withdraw());
        UI.addButton("Quit", UI::quit); // Quit always last button
        
        UI.println("Creating the account");
        
        // Setup the accounts
        theAccounts = new Account[SIZE];
        current = -1;   // Track current num of accts
        
    }

    /**
     * Method mkAccount
     * Prompt for PIN and initial balance
     * Fails if array full
     */
    private void mkAccount() {
        if (current + 1 >= SIZE) {
           UI.println("No more accounts can be created." +
           " Use a bank with dynamic structures.");
        } else {
            String newPIN = askForPIN();
            double initialBalance = UI.askDouble("New account's balance");
            UI.clearText();
            current += 1;
            theAccounts[current] = new Account(initialBalance, newPIN);
            UI.println("Account ID: " + current + " Initial Bal: " + 
            initialBalance + " created");
        }
    }
    
    /**
     * Method deposit
     * Make a deposit
     */
    private void deposit() {
        int acctID = askForID();
        if (acctID > current) {
            UI.println("Invalid account ID.");
        } else {
            String PIN = askForPIN();
            double money = UI.askDouble("How much?");
            if (theAccounts[acctID].deposit(money, PIN)) { // PIN is valid
                printBal(PIN, acctID);
            }
            else {
                UI.println("Transaction failed");
            }
        }
    }
    
    /**
     * Method askForPIN
     * Prompt user for a PIN number
     * 
     * @return The entered PIN
     */
    private String askForPIN() {
        String PIN = UI.askString("What is the PIN?");
        UI.clearText();
        return PIN;
    }
    
    /**
     * Method askForID
     * Prompt user for Account ID
     * 
     * @return An int, the ID of the desired account
     */
    private int askForID() {
        return UI.askInt("What is the account ID");
    }
    
    
    /**
     * Method getBalance
     * Prompt user for PIN and show balance or 
     * failure of transaction
     */
    private void getBalance() {
        int acctID = askForID();
        
        if (acctID > current) {
            UI.println("Account does not exist");
        } else {
            String PIN = askForPIN();
            //theAccounts[acctID].getBalance(PIN);
            printBal(PIN, acctID);
        }
    }
    
    /**
     * Method withdraw
     * Make a withdraw
     */
    private void withdraw() {
        int acctID = askForID();
        
        if (acctID > current) {
            UI.println("Account does not exist");
        } else {
            String PIN = askForPIN();
            double money = UI.askDouble("How much?");
            if (theAccounts[acctID].withdraw(money, PIN)) { // PIN is valid
                printBal(PIN, acctID);
            } else {
                UI.println("Transaction failed");
            }
        }
    }
   
    /**
     * Method printBal
     * Does the printing of the balance. Used in methods
     * called from buttons
     * 
     * @param The PIN to use
     */
    private void printBal(String PIN, int acctID) {
        String bal = theAccounts[acctID].getBalance(PIN);
        if (!(bal == null)) {
            UI.println("The balance is now " + bal);
        } else {
            UI.println("Transaction failed :(");
        }
    }

    /**
     * Method main
     * 
     * @param args Ignored!
     */        
    public static void main(String[] args){
        Bank obj = new Bank();
    }
}
