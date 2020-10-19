package com.meritamerica.assignment3;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BankAccount {
	public double interestRate;  
	public double balance;
	public long accountNumber;
	public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	public Date dateOpened;

	
//--------------------------------BANKACCOUNT CONSTRUCTORS----------------------------------------
//------------------------------------------------------------------------------------------
	public BankAccount(double balance) {
		
		this.balance = balance;
	}
	public BankAccount(double balance, double interestRate){
		
		this.interestRate = interestRate;
	    this.balance = balance;
	}
	public BankAccount(double balance, double interestRate, Date dateOpened) {
		
		this.balance = balance;
		this.interestRate = interestRate;
		this.dateOpened = dateOpened;
	}
	public BankAccount(long accountNumber, double balance, double interestRate){
		
		accountNumber = getAccountNumber();
		this.interestRate = interestRate;
	    this.balance = balance;
	}
	public BankAccount(long accountNumber, double balance, double interestRate,
						Date dateOpened) {
		
		accountNumber = getAccountNumber();
		this.interestRate = interestRate;
		this.balance = balance;
		this.dateOpened = dateOpened;
	}
	
	
//----------------------------------GETTERS-------------------------------------------------
//------------------------------------------------------------------------------------------
	public long getAccountNumber() {
		return MeritBank.getNextAccountNumber();
	}
	
	double getBalance() {
		return balance;
	}
	double getInterestRate() {
		return interestRate;
	}
	Date getOpenedOn() {
		return dateOpened;
	}
	

	
//----------------------------------BOOLEANS------------------------------------------------
//------------------------------------------------------------------------------------------	
	boolean withdraw(double amount) {
		if (amount > 0 && amount <= balance) {
			balance -= amount;
			return true;
		} else {
			return false;
		}	
	}
	boolean deposit(double amount) {
		if (amount > 0) {
			balance += amount;
			return true;
		} else {
			return false;
		}
	}
	
//----------------------------------FUTURE VALUE--------------------------------------------
//------------------------------------------------------------------------------------------
	double futureValue(int years){
		double FV = Math.pow((1 + getInterestRate()), years);
		return FV;

	}

//---------------------------TO/FROM STRING-------------------------------------------------
//------------------------------------------------------------------------------------------
	public static BankAccount readFromString(String account) throws ParseException, NumberFormatException {
		String[] accountInfo = account.split(",");
		
		long accountNumber = Long.parseLong(accountInfo[0]);
		double balance = Double.parseDouble(accountInfo[1]);
		double interestRate = Double.parseDouble(accountInfo[2]);
		Date dateOpened = formatter.parse(accountInfo[3]);
		
		BankAccount bankAccountInfo = new BankAccount(accountNumber, balance,
											interestRate,
											dateOpened);
		return bankAccountInfo;
	}
	public String writeToString() {
		String accountInfo = accountNumber + "," + balance + "," + interestRate + "," + dateOpened;
		return accountInfo;
		
	}
}