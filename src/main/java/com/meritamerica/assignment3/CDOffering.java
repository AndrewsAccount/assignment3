package com.meritamerica.assignment3;



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
		
	}

	public String writeToString() {
		String cdOfferingInfo;
		cdOfferingInfo = term + "," + interestRate;
		return cdOfferingInfo;
	}
}