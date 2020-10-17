package com.meritamerica.assignment3;

import java.sql.Date;
import java.text.*;


public class SavingsAccount extends BankAccount{
	
	
	public SavingsAccount(double StartBalance){
		super(StartBalance);
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