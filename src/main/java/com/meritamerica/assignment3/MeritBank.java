package com.meritamerica.assignment3;

import java.io.*;
import java.text.ParseException;
import java.util.*;

import com.meritamerica.assignment3.AccountHolder;

public class MeritBank {
	private static AccountHolder[] accounts = new AccountHolder[0];
	private static CDOffering[] cdofferings;
	private static long NextAccountNumber;

	static void addAccountHolder(AccountHolder accountHolder) {

		AccountHolder[] newaccounts = new AccountHolder[accounts.length + 1];
		for (int i = 0; i < accounts.length; i++) {
			newaccounts[i] = accounts[i];

		}
		accounts = newaccounts;
		accounts[accounts.length - 1] = accountHolder;
	}

	static AccountHolder[] getAccountHolders() {
		return accounts;
	}

	static CDOffering[] getCDOfferings() {
		return cdofferings;
	}

	static CDOffering getBestCDOffering(double depositAmount) {
		double value = 0;
		CDOffering currentOffer = null;
		if (cdofferings == null) {
			return null;
		}
		for (int i = 0; i < cdofferings.length; i++) {
			if (futureValue(depositAmount, cdofferings[i].getInterestRate(), cdofferings[i].getTerm()) > value) {
				value = futureValue(depositAmount, cdofferings[i].getInterestRate(), cdofferings[i].getTerm());
				currentOffer = cdofferings[i];
			}
		}

		return currentOffer;
	}
	
	public static CDOffering[] getSpecifiedCDOfferingArray(int numberOfOfferings) {
		CDOffering[] newOfferings = new CDOffering[numberOfOfferings];

		return newOfferings;
		}



	static CDOffering getSecondBestCDOffering(double depositAmount) {
		double value = 0;
		CDOffering currentOffer = null;
		CDOffering highestOffer = null;
		if (cdofferings == null) {
			return null;
		}
		for (int i = 0; i < cdofferings.length; i++) {
			if (futureValue(depositAmount, cdofferings[i].getInterestRate(), cdofferings[i].getTerm()) > value) {
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

	static long getNextAccountNumber() {
		long acctNumber = NextAccountNumber;
		NextAccountNumber++;
		return acctNumber;
	}

	static double totalBalances() {
		double allBalances = 0;
		for (int i = 0; i < accounts.length; i++) {
			allBalances += accounts[i].getCombinedBalance();
		}
		return allBalances;
	}

	static double futureValue(double presentValue, double interestRate, int term) {
		double futureValue = Math.pow(1 + interestRate, term);
		return futureValue;

	}

	public static boolean readFromFile(String fileName) throws ParseException  {
		ArrayList<String> fileArray = new ArrayList<String>();

		int counter = 0;
		AccountHolder accHolder;
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

			String line;
			while ((line = br.readLine()) != null) {
				fileArray.add(line);
			}
			// get next account number

			MeritBank.NextAccountNumber = Long.parseLong(fileArray.get(counter));
			counter++;

			// get number of CD Offerings

			// reserve memory for the new CDOfferings Array
			int numberOfCDOfferingObject = Integer.parseInt(fileArray.get(counter));
			counter++;
			MeritBank.setCDOfferings(MeritBank.getSpecifiedCDOfferingArray(numberOfCDOfferingObject));
			// initialize all cd offerings in the cdOfferings array static variable
			for (int i = counter; i < numberOfCDOfferingObject + counter; i++) {

				System.out.println(fileArray.get(i));
				CDOffering newOffering = CDOffering.readFromString(fileArray.get(i));

				cdofferings[i - counter] = newOffering;

			}

			counter += numberOfCDOfferingObject;
			int numOfAccountHolders = Integer.parseInt(fileArray.get(counter));
			counter++;

			for (int i = counter; i < numOfAccountHolders + counter; i++) {
				if (counter == fileArray.size()) {
					return true;
				}

				addAccountHolder(accHolder = AccountHolder.readFromString(fileArray.get(counter)));
				counter++;

				// AccountHolder.readFromString(fileArray.get(counter));
				System.out.println("AccountHolder = ");
				int numOfCheckingAccounts = Integer.parseInt(fileArray.get(counter));
				counter++;

				for (int j = counter; j < numOfCheckingAccounts + counter; j++) {
					accHolder.addCheckingAccount(CheckingAccount.readFromString(fileArray.get(j)));
					System.out.println("checking = " + fileArray.get(j));
				}

				counter += numOfCheckingAccounts;

				int numOfSavingsAccounts = Integer.parseInt(fileArray.get(counter));
				counter++;

				for (int k = counter; k < numOfSavingsAccounts + counter; k++) {
					accHolder.addSavingsAccount(SavingsAccount.readFromString(fileArray.get(k)));
				}

				counter += numOfSavingsAccounts;
				int numOfCDAccounts = Integer.parseInt(fileArray.get(counter));
				counter++;

				for (int g = counter; g < numOfCDAccounts + counter; g++) {
					accHolder.addCDAccount(CDAccount.readFromString(fileArray.get(g)));
				}
				counter += numOfCDAccounts;
			}

			br.close();

		} catch (NumberFormatException e) {
			System.out.println("The connection to the file was lost.");
			return false;
		} catch (IOException e) {
			e.getStackTrace();
			return false;
		}

		return true;
	}

//	static boolean readFromFile(String fileName) throws IOException, NumberFormatException, ParseException {
//		ArrayList<String> fileArray = new ArrayList<String>();
//		
//		try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
//		
//		
//		NextAccountNumber = Long.valueOf(br.readLine());
//		
//		int numberOfCDOfferings = Integer.valueOf(br.readLine());
//		
//		fileArray = new CDOffering[numberOfCDOfferings];
//			for(int i = 0; i < numberOfCDOfferings; i++) {
//				fileArray.get(i) = CDOffering.readFromString(br.readLine());
//			}
//		int numberOfAccountHolders = Integer.valueOf(br.readLine());
//		AccountHolder[] accountHolders = new AccountHolder[numberOfAccountHolders];
//			for(int i = 0; i < accountHolders.length; i++) {
//				addAccountHolder(accountHolders[i] = AccountHolder.readFromString(br.readLine()));
//						
//				int numberOfCheckingAccounts = Integer.valueOf(br.readLine());
//				for(int j = 0; j < numberOfCheckingAccounts; j++) {
//					accountHolders[i].addCheckingAccount(CheckingAccount.readFromString(br.readLine()));
//				}
//				int numberOfSavingsAccounts = Integer.valueOf(br.readLine());
//				for(int k = 0; k < numberOfSavingsAccounts; k++) {
//					accountHolders[i].addSavingsAccount(SavingsAccount.readFromString(br.readLine()));;
//				}
//				int numberOfCDAccounts = Integer.valueOf(br.readLine());
//				for(int g = 0; g < numberOfCDAccounts; g++) {
//					accountHolders[i].addCDAccount(CDAccount.readFromString(br.readLine()));
//				}
//				br.close();
//			}
//			return true;	
//		}	
//		catch(FileNotFoundException ex) {
//			System.out.println("File not found.");
//			return false;
//			}
//		catch(ParseException ex) {
//			return false;
//		}
//		catch(IOException ex) {
//			return false;
//	}
	static boolean writeToFile(String fileName) {

		try {
			FileWriter fr = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fr);

			bw.write(String.valueOf(NextAccountNumber));
			bw.newLine();
			bw.write(String.valueOf(cdofferings.length));
			bw.newLine();
			for (int i = 0; i < cdofferings.length; i++) {
				bw.write(cdofferings[i].writeToString());
				bw.newLine();
			}
			bw.write(String.valueOf(accounts.length));
			bw.newLine();
			for (int i = 0; i < accounts.length; i++) {
				bw.write(accounts[i].writeToString());
				bw.newLine();
				bw.write(accounts[i].getNumberOfCheckingAccounts());
				for (int j = 0; j < accounts[i].getNumberOfCheckingAccounts(); j++) {
					bw.write(String.valueOf(accounts[i].getCheckingAccounts()[j].writeToString()));
					bw.newLine();
				}
				for (int k = 0; k < accounts[i].getNumberOfSavingsAccounts(); k++) {
					bw.write(String.valueOf(accounts[i].getSavingsAccounts()[k].writeToString()));
					bw.newLine();
				}
				for (int g = 0; g < accounts[i].getNumberOfCDAccounts(); g++) {
					bw.write(String.valueOf(accounts[i].getCDAccounts()));
					bw.newLine();
				}
			}
			bw.close();
			return true;
		} catch (IOException ex) {
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
