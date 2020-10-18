package com.meritamerica.assignment3;

import java.io.*;
import java.util.*;


import com.meritamerica.assignment3.AccountHolder;

public class MeritBank {
	private static AccountHolder[] accounts = new AccountHolder[0];
	private static AccountHolder accountHolder;
	private static CDOffering[] cdofferings;
	private static CDOffering bestCDOffering;
	private static CDOffering secondBestCDOffering;
	private double depositAmount;
	private static long NextAccountNumber;
	
	
	static void addAccountHolder(AccountHolder accountHolder){
		
		AccountHolder[] newaccounts = new AccountHolder[accounts.length+1];
		for(int i = 0; i < accounts.length; i++) {
			newaccounts[i] = accounts[i];
			
		}
		accounts = newaccounts;
		accounts[accounts.length-1] = accountHolder; 
	}
	
	static AccountHolder[] getAccountHolders(){
		return accounts;
	}
	
	static CDOffering[] getCDOfferings(){
		return cdofferings;
	}
	
	static CDOffering getBestCDOffering(double depositAmount){
		double value = 0;
		CDOffering currentOffer = null;
		if(cdofferings == null) {
			return null;
			}
		for(int i = 0; i < cdofferings.length; i++) {
			if(futureValue(depositAmount, cdofferings[i].getInterestRate(), cdofferings[i].getTerm()) 
					> value) {
				value = futureValue(depositAmount, cdofferings[i].getInterestRate(), cdofferings[i].getTerm());
			currentOffer = cdofferings[i];	
			}
		}
		
		return currentOffer;
	}
	
	static CDOffering getSecondBestCDOffering(double depositAmount){
		double value = 0;
		CDOffering currentOffer = null;
		CDOffering highestOffer = null;
		if(cdofferings == null) {
			return null;
			}
		for(int i = 0; i < cdofferings.length; i++) {
			if(futureValue(depositAmount, cdofferings[i].getInterestRate(), cdofferings[i].getTerm()) 
					> value) {
				value = futureValue(depositAmount, cdofferings[i].getInterestRate(), cdofferings[i].getTerm());
				currentOffer = highestOffer;
				highestOffer = cdofferings[i];
			}	
		}
		return currentOffer;
		
	
	}
	
	static void clearCDOfferings() {
		cdofferings = null;
	}
	
	static void setCDOfferings(CDOffering[] offerings) {
		cdofferings = offerings;
	}
	
	static long getNextAccountNumber(){
		long acctNumber = NextAccountNumber;
		NextAccountNumber++;
		return acctNumber;
	}
	
	static double totalBalances(){
		double allBalances = 0;
		for(int i = 0; i< accounts.length; i++) {
			allBalances += accounts[i].getCombinedBalance();
		}
		return allBalances;
	}
	
	static double futureValue(double presentValue, double interestRate, int term){
		double futureValue =  Math.pow(1 + interestRate, term);
		return futureValue;
		
	}
	
	static boolean readFromFile(String fileName) throws IOException {
		CDOffering[] cdOffering;
		
		try {
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		
		NextAccountNumber = Long.valueOf(br.readLine());
		
		int numberOfCDOfferings = Integer.valueOf(br.readLine());
		
		cdOffering = new CDOffering[numberOfCDOfferings];
			for(int i = 0; i < numberOfCDOfferings; i++) {
				cdOffering[i] = CDOffering.readFromString(br.readLine());
			}
		int numberOfAccountHolders = Integer.valueOf(br.readLine());
		AccountHolder[] accountHolders = new AccountHolder[numberOfAccountHolders];
			for(int i = 0; i < accountHolders.length; i++) {
				accountHolders[i] = AccountHolder.
						readFromString(br.readLine());
				int numberOfCheckingAccounts = Integer.valueOf(br.readLine());
				for(int j = 0; j < numberOfCheckingAccounts; j++) {
					accountHolders[i].addCheckingAccount(CheckingAccount.readFromString(br.readLine()));
				}
				int numberOfSavingsAccounts = Integer.valueOf(br.readLine());
				for(int k = 0; k < numberOfSavingsAccounts; k++) {
					accountHolders[i].addSavingsAccount(SavingsAccount.readFromString(br.readLine()));;
				}
				int numberOfCDAccounts = Integer.valueOf(br.readLine());
				for(int g = 0; g < numberOfCDAccounts; g++) {
					accountHolders[i].addCDAccount(CDAccount.readFromString(br.readLine()));
				}
				br.close();
			}
			return true;	
		}	
		catch(FileNotFoundException ex) {
			System.out.println("File not found.");
			return false;
			}
	}
	static boolean writeToFile(String fileName) {
		
		try {
		FileWriter fr = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(fr);
		
		bw.write(String.valueOf(NextAccountNumber));
		bw.newLine();
		bw.write(String.valueOf(cdofferings.length));
		bw.newLine();
		for(int i = 0; i < cdofferings.length; i++) {
			bw.write(cdofferings[i].writeToString());
			bw.newLine();
		}
		bw.write(String.valueOf(accounts.length));
		bw.newLine();
		for(int i = 0; i < accounts.length; i++) {
			bw.write(accounts[i].writeToString());
			bw.newLine();
			bw.write(accounts[i].getNumberOfCheckingAccounts());
			for(int j = 0; j < accounts[i].getNumberOfCheckingAccounts(); j++) {
				bw.write(String.valueOf(accounts[i].getCheckingAccounts()[j].writeToString()));	
			}
			for(int k = 0; k < accounts[i].getNumberOfSavingsAccounts(); k++) {
				bw.write(String.valueOf(accounts[i].getSavingsAccounts()[k].writeToString()));
			}
			for(int g = 0; g < accounts[i].getNumberOfCDAccounts(); g++) {
				bw.write(String.valueOf(accounts[i].getCDAccounts()));
			}
		}
		return true;
		}
		catch(IOException ex) {
			return false;
		}
		
	}
	static AccountHolder[] sortAccountHolders() {
		Arrays.sort(accounts);
		return accounts;	
	}
	static void setNextAccountNumber(long nextAccountNumber) {
		NextAccountNumber = nextAccountNumber;
		
	}
	
}
