package com.meritamerica.assignment3;


import java.util.Date;



public class CDAccount extends BankAccount{
	
	private int accInt = 1;
	private CDOffering offering;
	Date date;
	private long accountNumber;
		
	
	CDAccount(CDOffering offering, double balance) 
	{
			super(balance, offering.getInterestRate());
			this.offering = offering;
			this.date = date;
			
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
	
	public java.util.Date getStartDate(){
		return date;
	}
	
	public long getAccountNumber() {
		return accountNumber;	
	}
	
	public double futureValue() {
		return balance*Math.pow(1 + getInterestRate(), getTerm());
	}

