package com.meritamerica.assignment3;

import java.sql.Date;

public class BankAccount {
	protected static double interestRate;  
	protected static double balance;
	protected static long accountNumber;
	protected static Date dateOpened;
	
	
	public BankAccount(double balance) {
		this.balance = balance;
	}
	public BankAccount(double balance, double interestRate)
	{
		 this.interestRate = interestRate;
	     this.balance = balance;
	}
	
	
	public BankAccount(long accountNumber, double balance, double interestRate)
	{
		accountNumber = getAccountNumber();
		this.interestRate = interestRate;
	    this.balance = balance;
	}
	public long getAccountNumber()
	{
		return MeritBank.getNextAccountNumber();
	}
	double getBalance()
	{
		return balance;
	}
	double getInterestRate()
	{
		return interestRate;
	}
	
	boolean withdraw(double amount)
	{
		if (amount > 0) {
			balance += amount;
			System.out.println("This is your new balance $" + balance );
			return true;
		} else {
			System.out.println("You dont have any money to deposit");
			return false;
		}	
	}
	boolean deposit(double amount)
	{
		if (amount > 0 && amount <= balance) {
			balance -= amount;
			System.out.println("This is your new balance $" + balance);
			return true;
		} else {
			System.out.println("You dont have enough money to withdrawl. You have $" + balance + " in your account");
			return false;
		}
	}
	double futureValue(int years)
	{

	double FV = Math.pow((1 + getInterestRate()), years);
	return FV;

	}
	public BankAccount(long accountNumber, double balance, double interestRate,
						Date dateOpened) {
		accountNumber = getAccountNumber();
		this.interestRate = interestRate;
	    this.balance = balance;
	    this.dateOpened = dateOpened;
	}
	public static BankAccount readFromString(String account) {
		String[] accountInfo = account.split(",");
		long accountNumber = Long.valueOf(accountInfo[0]);
		double balance = Double.valueOf(accountInfo[1]);
		double interestRate = Double.valueOf(accountInfo[2]);
		Date dateOpened = Date.valueOf(accountInfo[3]);
		BankAccount bankAccountInfo = new BankAccount(accountNumber, balance,
											interestRate,
											dateOpened);
		return bankAccountInfo;
	}
	public static String writeToString() {
		String accountInfo = accountNumber + "," + balance + "," + interestRate;
		return accountInfo;
		
	}
}