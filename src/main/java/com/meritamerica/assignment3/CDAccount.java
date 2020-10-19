package com.meritamerica.assignment3;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class CDAccount extends BankAccount{
	
	
	private CDOffering offering;
	public int term;
	double openingBalance;
	
			
/*	public CDAccount(CDOffering offering, double balance, Date dateOpened) {
			super(balance, offering.getInterestRate());
			this.offering = offering;
			this.dateOpened = (java.sql.Date) dateOpened;		
	}
*/
	public CDAccount(long accountNumber, double balance, double interestRate, 
					Date dateOpened, int term) {
		super(accountNumber, balance, interestRate);
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.interestRate = interestRate;
		this.dateOpened = dateOpened;
		this.term = term;	
	}
	
	public CDAccount(CDOffering offering, double openingBalance) {
		super(openingBalance);
		this.offering = offering;
		this.openingBalance = openingBalance;
	}

	@Override
	boolean withdraw(double amount) {
		return false;
	}
	
	@Override
	boolean deposit(double amount) {
		return false;
	}
	
	public double futureValue(){
		double interestRate = offering.getInterestRate();
		int years = offering.getTerm();
		return balance * (Math.pow(1 + interestRate, years));
		
	}
	public CDOffering getOffering() {
		return offering;
	}	
	public int getTerm() {
		return offering.getTerm();
	}
	public static CDAccount readFromString(String account) throws ParseException{
		
		CDAccount cdAccountInfo;
		try {
		ArrayList<String> accountInfo = new ArrayList<>(Arrays.asList(account.split(",")));
		long accountNumber = Long.parseLong((accountInfo.get(0)));
		double balance = Double.parseDouble(accountInfo.get(1));
		double interestRate = Double.parseDouble(accountInfo.get(2));
		SimpleDateFormat formatter = new SimpleDateFormat();
		Date dateOpened = formatter.parse(accountInfo.get(3));
		int term = Integer.parseInt(accountInfo.get(4));
		cdAccountInfo = new CDAccount(accountNumber, balance,
											interestRate,
											dateOpened, term);
		return cdAccountInfo;
		}
		catch(ParseException ex){
			throw new java.lang.NumberFormatException();
		}
	
	}
	@Override
	public String writeToString() {
		String accountInfo = String.valueOf(term) + String.valueOf(interestRate) ;
		return accountInfo;
		
	}
}

