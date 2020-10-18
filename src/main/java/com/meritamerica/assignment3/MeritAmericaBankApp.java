package com.meritamerica.assignment3;

import java.io.IOException;

public class MeritAmericaBankApp {
	
	public static void main(String[] args) {
		try {
			MeritBank.readFromFile("src/test/testMeritBank_good.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}