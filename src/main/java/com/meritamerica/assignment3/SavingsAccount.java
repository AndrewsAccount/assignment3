package com.meritamerica.assignment3;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class SavingsAccount extends BankAccount{
	public SavingsAccount(double StartBalance){
		super(StartBalance);
	}
	
	public SavingsAccount(long accountNumber, double balance, double interestRate, 
							Date dateOpened) {
		super(accountNumber, balance, interestRate, dateOpened);
	}
	
	public static SavingsAccount readFromString(String account) throws ParseException{
		SavingsAccount savingsAccountInfo;
		ArrayList<String> accountInfo = new ArrayList<>(Arrays.asList(account.split(",")));
		long accountNumber = Long.parseLong(accountInfo.get(0));
		double balance = Double.parseDouble(accountInfo.get(1));
		double interestRate = Double.parseDouble(accountInfo.get(2));
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");		
		Date dateOpened = formatter.parse(accountInfo.get(3));
		savingsAccountInfo = new SavingsAccount(accountNumber, balance,
											interestRate,
											dateOpened);
		
		return savingsAccountInfo;
	}
}