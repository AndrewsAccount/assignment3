package com.meritamerica.assignment3;

import java.text.ParseException;

public class CDOffering {
	
	private int term;
	private double interestRate;

	public CDOffering(int term, double interestRate) {
		this.term = term;
		this.interestRate = interestRate;
	}

	public int getTerm() {
		return term;
	}

	public double getInterestRate() {
		return interestRate;
	}

	static CDOffering readFromString(String cdOfferingDataString) {
		
		String[] accountInfo = cdOfferingDataString.split(",");
		int term = Integer.parseInt(accountInfo[0]);
		double interestRate = Double.parseDouble(accountInfo[1]);
		
		return new CDOffering(term, interestRate);
		
	}

	public String writeToString() {
		String cdOfferingInfo;
		cdOfferingInfo = term + "," + interestRate;
		return cdOfferingInfo;
	}
}