/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author nhhag
 */
public class Wallet {

    private int walletID;
    private double balance;
    private String username;
    private double hold;

    // Constructor
    public Wallet() {
    }

    public Wallet(int walletID, double balance, String username, double hold) {
        this.walletID = walletID;
        this.balance = balance;
        this.username = username;
        this.hold = hold;
    }
    
    public double getHold() {
        return hold;
    }

    // Getters and Setters
    public void setHold(double hold) {    
        this.hold = hold;
    }

    public int getWalletID() {
        return walletID;
    }

    public void setWalletID(int walletID) {
        this.walletID = walletID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Optional: ToString method for better readability
    @Override
    public String toString() {
        return "Wallet{"
                + "walletID=" + walletID
                + ", balance=" + balance
                + ", username='" + username + '\''
                + '}';
    }
}
