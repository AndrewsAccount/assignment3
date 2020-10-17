package com.meritamerica.assignment3;
import java.util.*;
import java.sql.Date;
import java.text.SimpleDateFormat;


public class CheckingAccount extends BankAccount{
	CheckingAccount(double checkingAccountStartBal) {
		super(checkingAccountStartBal);
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