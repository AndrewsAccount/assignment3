package com.meritamerica.assignment3;

import java.io.IOException;
import java.text.ParseException;

public class AccountHolder {

	private String firstName = "";
	private String middleName = "";
	private String lastName = "";
	private String ssn = "";

	CheckingAccount checkingaccount;
	SavingsAccount savingaccount;

	CheckingAccount[] checkingAccount = new CheckingAccount[0];
	SavingsAccount[] savingsAccount = new SavingsAccount[0];
	CDAccount[] cdAccount = new CDAccount[0];

	// The parameters for the account holder
	public AccountHolder(String firstName, String middleName, String lastName, String ssn) {

		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.ssn = ssn;
	}

	// getters and setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lName) {
		this.lastName = lName;
	}

	public String getSSN() {
		return ssn;
	}

	public void setSSN(String ssn) {
		this.ssn = ssn;
	}

	public CheckingAccount addCheckingAccount(double openingBalance) {
		CheckingAccount cha = new CheckingAccount(openingBalance);
		CheckingAccount[] tempChecking = new CheckingAccount[checkingAccount.length + 1];
		if (getCheckingAccount() + getSavingsAccount() + openingBalance > 250_000) {
			return null;
		}
		for (int i = 0; i < checkingAccount.length; i++) {
			tempChecking[i] = checkingAccount[i];
		}
		checkingAccount = tempChecking;
		checkingAccount[checkingAccount.length - 1] = cha;
		return addCheckingAccount(cha);
	}

	public CheckingAccount addCheckingAccount(CheckingAccount checkingAccount) {
		CheckingAccount[] temp = new CheckingAccount[this.checkingAccount.length + 1];
		if (getCheckingAccount() + getSavingsAccount() + checkingAccount.balance > 250_000) {
			return null;
		}
		for (int i = 0; i < this.checkingAccount.length; i++) {
			temp[i] = this.checkingAccount[i];
		}
		this.checkingAccount = temp;
		this.checkingAccount[this.checkingAccount.length - 1] = checkingAccount;
		return checkingAccount;
	}

	CheckingAccount[] getCheckingAccounts() {
		return checkingAccount;
	}

	public int getNumberOfCheckingAccounts() {
		return checkingAccount.length;
	}

	// total value of this user's total checking accounts
	public double getCheckingAccount() {
		double total = 0;
		for (int i = 0; i < checkingAccount.length; i++) {
			total += checkingAccount[i].getBalance();
		}
		return total;
	}

	public SavingsAccount addSavingsAccount(double openingBalance) {
		// are the accounts combined more than 250k
		// if not, then what
		// if so, then what
		SavingsAccount rho = new SavingsAccount(openingBalance);
		SavingsAccount[] tempChecking = new SavingsAccount[savingsAccount.length + 1];
		if (getCheckingAccount() + getSavingsAccount() + openingBalance > 250_000) {
			return rho;
		}
		for (int i = 0; i < savingsAccount.length; i++) {
			tempChecking[i] = savingsAccount[i];
		}
		savingsAccount = tempChecking;
		savingsAccount[savingsAccount.length - 1] = rho;
		return addSavingsAccount(rho);
	}

	public SavingsAccount addSavingsAccount(SavingsAccount savingsAccount) {
		// are the accounts combined more than 250k
		// if not, then what
		// if so, then what
		// make new array (+1 in length form initial array)
		// loop through old array, adding elements to new array
		// old array = new array
		// add new element to array at length-1 position
		// new array will be deleted when this method finishes execution
		SavingsAccount[] temp = new SavingsAccount[this.savingsAccount.length + 1];
		if (getCheckingAccount() + getSavingsAccount() + savingsAccount.balance > 250_000) {
			return null;
		}
		for (int i = 0; i < this.savingsAccount.length; i++) {
			temp[i] = this.savingsAccount[i];
		}
		this.savingsAccount = temp;
		this.savingsAccount[this.savingsAccount.length - 1] = savingsAccount;
		return savingsAccount;
	}

	SavingsAccount[] getSavingsAccounts() {
		return savingsAccount;
	}

	public int getNumberOfSavingsAccounts() {
		return savingsAccount.length;
	}

	public double getSavingsAccount() {
		double total = 0;
		for (int i = 0; i < savingsAccount.length; i++) {
			total += savingsAccount[i].getBalance();
		}
		return total;
	}

//----------------------------ADD CD ACCOUNT METHODS--------------------------------------

	public CDAccount addCDAccount(CDOffering offering, double openingBalance) {
		CDAccount tao = new CDAccount(offering, openingBalance);
		CDAccount[] tempCD = new CDAccount[this.cdAccount.length + 1];
		for (int i = 0; i < this.cdAccount.length; i++) {
			tempCD[i] = this.cdAccount[i];
		}
		this.cdAccount = tempCD;
		this.cdAccount[cdAccount.length - 1] = tao;
		return tao;
	}

	public void addCDAccount(CDAccount cdAccount) {
		CDAccount[] tempCD = new CDAccount[this.cdAccount.length + 1];
		for (int i = 0; i < this.cdAccount.length; i++) {
			tempCD[i] = this.cdAccount[i];
		}
		this.cdAccount = tempCD;
		this.cdAccount[this.cdAccount.length - 1] = cdAccount;
	}

//---------------------GETTERS---------------------------------------------------
//-------------------------------------------------------------------------------
	CDAccount[] getCDAccounts() {
		return cdAccount;

	}

	public int getNumberOfCDAccounts() {
		return cdAccount.length;
	}

	public double getCDBalance() {
		double total = 0;
		for (int i = 0; i < cdAccount.length; i++) {
			total += cdAccount[i].getBalance();
		}
		return total;
	}

	public double getCombinedBalance() {
		return (getCDBalance() + getSavingsAccount() + getCheckingAccount());
	}

//----------------------TO/FROM STRING-------------------------------------------------
//-------------------------------------------------------------------------------------
	public static AccountHolder readFromString(String readLine) throws ParseException {
		// TODO Auto-generated method stub
		
			String[] randomArray = readLine.split(",");
			return new AccountHolder(randomArray[0], randomArray[1], randomArray[2], randomArray[3]);
	}

	public String writeToString() {
		String accountHolderString = firstName + lastName + String.valueOf(ssn);
		return accountHolderString;
	}

}
