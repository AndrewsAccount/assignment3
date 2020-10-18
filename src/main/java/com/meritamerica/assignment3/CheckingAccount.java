package com.meritamerica.assignment3;
import java.sql.Date;


public class CheckingAccount extends BankAccount{
	public CheckingAccount(double startBalance) {
		super(startBalance);
	}
	
	public CheckingAccount(long accountNumber, double balance, double interestRate, Date dateOpened) {
		// TODO Auto-generated constructor stub
		super(accountNumber, balance, interestRate, dateOpened);
	}

	public static CheckingAccount readFromString(String account) {
		String[] accountInfo = account.split(",");
		long accountNumber = Long.valueOf(accountInfo[0]);
		double balance = Double.valueOf(accountInfo[1]);
		double interestRate = Double.valueOf(accountInfo[2]);
		Date dateOpened = Date.valueOf(accountInfo[3]);
		CheckingAccount checkingAccountInfo = new CheckingAccount(accountNumber, balance,
											interestRate,
											dateOpened);
		return checkingAccountInfo;
	}
	
}