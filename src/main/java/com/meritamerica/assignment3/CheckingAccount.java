package com.meritamerica.assignment3;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;


public class CheckingAccount extends BankAccount{
	public CheckingAccount(double startBalance) {
		super(startBalance);
	}
	
	public CheckingAccount(long accountNumber, double balance, double interestRate, 
							Date dateOpened) {
		// TODO Auto-generated constructor stub
		super(accountNumber, balance, interestRate, dateOpened);
	}
	
	public static CheckingAccount readFromString(String account) {
		try {
		ArrayList<String> accountInfo = new ArrayList<>(Arrays.asList(account.split(",")));
		
		long accountNumber = Long.parseLong(accountInfo.get(0));
		double balance = Double.parseDouble(accountInfo.get(1));
		double interestRate = Double.parseDouble(accountInfo.get(2));
		SimpleDateFormat formatter = new SimpleDateFormat();
		Date dateOpened = formatter.parse(accountInfo.get(3));
		CheckingAccount checkingAccountInfo = new CheckingAccount(accountNumber, balance,
											interestRate,
											dateOpened);
		return checkingAccountInfo;
		}
		catch(ParseException ex) {
			throw new java.lang.NumberFormatException();
		}
	}
	
}