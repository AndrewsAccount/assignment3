package com.meritamerica.assignment3;
import java.sql.Date;


public class SavingsAccount extends BankAccount{
	public SavingsAccount(double StartBalance){
		super(StartBalance);
	}
	
	public SavingsAccount(long accountNumber, double balance, double interestRate, 
							Date dateOpened) {
		// TODO Auto-generated constructor stub
		super(accountNumber, balance, interestRate, dateOpened);
	}
	
	public static SavingsAccount readFromString(String account) {
		String[] accountInfo = account.split(",");
		long accountNumber = Long.valueOf(accountInfo[0]);
		double balance = Double.valueOf(accountInfo[1]);
		double interestRate = Double.valueOf(accountInfo[2]);
		Date dateOpened = Date.valueOf(accountInfo[3]);
		SavingsAccount savingsAccountInfo = new SavingsAccount(accountNumber, balance,
											interestRate,
											dateOpened);
		return savingsAccountInfo;
	}
}