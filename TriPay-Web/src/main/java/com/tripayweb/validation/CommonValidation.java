package com.tripayweb.validation;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonValidation {
	
	 public static void main(String[] args) {
         
         String str = "abc";
        
         if(!str.matches("(.*)[a-z](.*)") && !str.matches("(.*)[0-9](.*)")){
             System.err.println("true");
         }
         else{
        	 System.err.println("false");
         }
     }

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Checks String with length greater than 6
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean checkLength6(String str) {
		String temp = str.trim();
		if (temp.length() >= 6) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 *
	 *
	 * @param str
	 * @return boolean
     */

	public static boolean isValidIP(String str){
		boolean valid = false;
		String ip = str.trim();
		int flag = 0;
		String []parts = ip.split("\\.");
		System.err.println("parts length ::"+parts.length);
		if(parts.length == 4){
			for(String n:parts){
				System.err.println("n is "+n);
				if(isNumeric(n)){
					flag = 1;
				}else{
					flag = 0;
				}
			}
		}
		if(flag == 1){
			valid = true;
		}
		return valid;
	}

//	public static void main(String... args) {
//		String url = "http://127.0.0.1:8080/ws/api/First";
//		System.err.println(isValidURL(url));
//	}
	/**
	 *
	 * @param str
	 * @return boolean
     */
	public static boolean isValidURL(String str){
		boolean valid = false;
		try {
			URL url = new URL(str);
			System.out.println("host is "+url.getHost()+" protocol is"+url.getProtocol()+" port is "+url.getPort());
			valid = true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return valid;
	}

	public static boolean checkLength8(String str) {
		String temp = str.trim();
		if (temp.length() >= 8) {
			return true;
		} else {
			return false;
		}
	}



	/**
	 * Checks String with length equal to 4
	 * 
	 * @param
	 * @return boolean
	 */
	public static boolean checkLength4(String str) {

		String temp = str.trim();
		if (temp.length() == 4) {
			return true;
		} else {
			return false;
		}

	}
	
	public static boolean checkAccountNoLength15(String str) {
		String temp = str.trim();
		if (temp.length() == 15) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean checkOTPLength6(String str) {

		String temp = str.trim();
		if (temp.length() == 6) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks whether a String is alphanumeric
	 * 
	 * @param str
	 * @return boolean
	 * 
	 */
	public static boolean isAlphanumeric(String str) {
		String temp = str.trim();
		if (temp.matches("[A-Za-z0-9]+")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * checks whether the string is completely numeric
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isNumeric(String str) {
		String temp = str.trim();
		boolean isNumber = false;
		/*if (temp.matches("[0-9]+")) {*/
			if (temp.matches("[0-9]+")) {
			isNumber = true;
		}
		return isNumber;
	}

	public static boolean isValidLoadMoneyTransaction(String str) {
		int amount = Integer.parseInt(str);
		if((amount >= 1) && (amount <=10000)) {
			return true;
		}
		return false;
	}
	/**
	 * checks the length of string is equal to 10
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkLength10(String str) {
		String temp = str.trim();
		int length = temp.length();
		
		System.err.println(length);
		boolean isValid = true;
		if (length==10)
		{
			isValid =true;
		}
		return isValid;
	}


	/**
	 * checks the number must be greater than 10
	 * 
	 * @param number
	 * @return boolean
	 */

	public static boolean isGreaterthan10(double number) {
		if (number >= 10)
			return true;
		else
			return false;
	}

	/**
	 * checks the validity of email
	 * 
	 * @param str
	 * @return boolean
	 * 
	 */

	public static boolean isValidMail(String str) {
		boolean isValid = false;
		if (str.contains("@") || str.contains(".")) {
			isValid = true;
		}
		return isValid;
	}

	/**
	 * checks whether String is null or not
	 * 
	 * @param str
	 * @return boolean
	 * 
	 */
	public static boolean isNull(String str)
	{
		if (str == "" || str == null || str.isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * checks whether String contains only alphabets
	 * 
	 * @param str
	 * @return boolean
	 * 
	 */

	public static boolean containsAlphabets(String str) {
		if (str.matches("[A-Za-z]+")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * checks whether String contains valid image extension
	 * 
	 * @param str
	 * @return boolean
	 * 
	 * 
	 */
	public static boolean isValidImageExtension(String str) {
		String temp = str.trim();
		if (temp.contains(".jpg") || temp.contains(".png") || temp.contains(".tiff") || temp.contains(".gif"))
			return true;
		else
			return false;
	}

	/**
	 * Checks transaction amount is less than or equal to user balance
	 * 
	 * @param userBalance
	 * @param transactionAmount
	 * @return
	 */
	public static boolean balanceCheck(double userBalance, double transactionAmount) {
		if (transactionAmount <= userBalance) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if user daily transaction limit is to mark or not if the transaction is carried out
	 * 
	 * @param dailyTransactionLimit
	 * @param totalDailyTransaction
	 * @param transactionAmount
	 * @return
	 */
	public static boolean dailyLimitCheck(double dailyTransactionLimit, double totalDailyTransaction, double transactionAmount) {
		if (dailyTransactionLimit >= (totalDailyTransaction + transactionAmount)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if user monthly transaction limit is to mark or not if the transaction is carried out
	 * 
	 * @param monthlyTransactionLimit
	 * @param totalMonthlyTransaction
	 * @param transactionAmount
	 * @return
	 */
	public static boolean monthlyLimitCheck(double monthlyTransactionLimit, double totalMonthlyTransaction, double transactionAmount) {
		if (monthlyTransactionLimit >= (totalMonthlyTransaction + transactionAmount)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if receiver balance limit is to mark or not if the transaction is carried out
	 * 
	 * @param balanceLimit
	 * @param receiverBalance
	 * @param transactionAmount
	 * @return
	 */

	public static boolean receiverBalanceLimit(double balanceLimit, double receiverBalance, double transactionAmount) {
		if (balanceLimit >= (receiverBalance + transactionAmount)) {
			return true;
		} else {
			return false;
		}
	}
	/*public static boolean isPositive(String str) {
		
		String temp = str.trim();
		int num = Integer.parseInt(str);
//		int num = temp.length();
		
		if (num > 0) {
			return true;
		}
		return false;
		
		}*/
	public static final boolean isDateValid(String str) {
		String regex = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
	    if (!str.matches(regex))
	        return false;
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    try {
	        sdf.parse(str);
	        return true;
	    } catch (ParseException e) {
	        return false;
	    }
	}

	/**
	 * @param str
	 * @return valid
	 *
	 */

	public static final boolean isValidPassword(String str){
		boolean valid = true;
		String password = str.trim();
		if(password.length()== 6){
			valid = false;
		}
		return valid;
	}
	
	public static boolean isValidAmount(double amount) {
		boolean valid = false;
		double minAmount = 1.0;
		double maxAmount = 10000.0;
		if(amount > minAmount && amount < maxAmount) {
			valid = true;
		}
		return false;
	}
	
}
