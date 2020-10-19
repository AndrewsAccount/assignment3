package com.meritamerica.assignment3;

import java.text.ParseException;

public class MeritAmericaBankApp {
	
	public static void main(String[] args) throws NumberFormatException, ParseException {
		try {
			MeritBank.readFromFile("src/test/testMeritBank_good.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}