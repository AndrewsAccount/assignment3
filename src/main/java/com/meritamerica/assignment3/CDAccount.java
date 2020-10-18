package com.meritamerica.assignment3;

import java.util.Date;

public class CDAccount extends BankAccount{
	
	
	private CDOffering offering;
	Date dateOpened;
	private long accountNumber;
		
	public CDAccount(CDOffering offering, double balance, Date dateOpened) {
			super(balance, offering.getInterestRate());
			this.offering = offering;
			this.dateOpened = dateOpened;		
	}

	public CDOffering getOffering() {
		return offering;
	}
	
	public double getInterestRate() {
		return offering.getInterestRate();
	}
	
	public int getTerm() {
		return offering.getTerm();
	}
	
	public Date getDateOpened(){
		return dateOpened;
	}
	
	public long getAccountNumber() {
		return accountNumber;	
	}
	
	public double futureValue() {
		return balance*Math.pow(1 + getInterestRate(), getTerm());
	}
	public static CDAccount readFromString(String account) {
		String[] accountInfo = account.split(",");
		long accountNumber = Long.valueOf(accountInfo[0]);
		double balance = Double.valueOf(accountInfo[1]);
		double interestRate = Double.valueOf(accountInfo[2]);
		Date dateOpened = Date.valueOf(accountInfo[3]);
		CDAccount cdAccountInfo = new CDAccount(accountNumber, balance,
											interestRate,
											dateOpened);
		return cdAccountInfo;
	}
}

