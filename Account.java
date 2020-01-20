
/**
 * A very simple bank account.
 * Has a balance (always +ve) and a PIN
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Account
{
    // instance variables - replace the example below with your own
    private double balance;
    private String PIN;

    /**
     * Constructor for objects of class Accounts
     * 
     * @param balance The initial balance in dollars
     * @param PIN The PIN -- just stored as a plain string
     */
    public Account(double balance, String PIN)
    {
        // initialise instance variables
        this.balance = balance;
        this.PIN = PIN;
    }

    /**
     * Method isPIN
     *
     * @param  possiblePIN Is this the account's PIN?
     * @return true or false, depending on whether the PossiblePIN is Correct
     */
    public boolean isPIN(String possiblePIN)
    {
        // put your code here
        return PIN.equals(possiblePIN);
    }
    
    /**
     * Method deposit
     * 
     * @param   money   Amount to deposit
     * @param   possiblePIN The (supposed) PIN for the account
     * @return  true or false, depending on whether the 
     *  transaction succeeded or not
     */
    public boolean deposit(double money, String possiblePIN) {
        if (isPIN(possiblePIN)) {
            balance += money;
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Method withdraw
     * 
     * @param   money   Amount to withdraw
     * @param   possiblePIN The (supposed) PIN for the account
     * @return  true or false, depending on whether the 
     *  transaction succeeded or not
     */
    public boolean withdraw(double money, String possiblePIN) {
        if (isPIN(possiblePIN) && (balance > money)) {
            balance -= money;
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Method getBalance
     * 
     * @param   possiblePIN The (supposed) PIN for the account
     * @return  balance The amount of money in the account 
     */
    public String getBalance(String possiblePIN){
        if (isPIN(possiblePIN)){
            return String.format("%.2f", balance);
        }
        else {
            return null;
        }
    }
}
