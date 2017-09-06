package com.tripayweb.util;

public class CommonUtil {

	/**
	 * Random Number Generator for MObile OTP return 6 digit random number
	 * 
	 * @return
	 */
	public static String generateSixDigitNumericString() {
		return "" + (int) (Math.random() * 1000000);
	}
	
	public static String generateNineDigitNumericString() {
		return "" + (int) (Math.random() * 1000000000);
	}

	public static String generateNDigitNumericString(long n){
		double mul = Math.pow(10,n);
		long result = (long) (Math.random() * mul);
		String number = String.valueOf(result);
		if(number.length() != n){
			return generateNDigitNumericString(n+1);
		}
		return number;
	}

	public static int generateNDigitNumber(long n){
		double mul = Math.pow(10,n);
		int result = (int) (Math.random() * mul);
		String number = String.valueOf(result);
		if(number.length() != n){
			return generateNDigitNumber(n+1);
		}
		return result;
	}

	public static void sleep(long timeInMS) {
		try {
			Thread.sleep(timeInMS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String generateRandomCity(){
		String city = "";
		int choice = generateNDigitNumber(1);
		switch(choice){
			case 1:
				city = "Jaipur";
				break;
			case 2:
				city= "Chennai";
				break;
			case 3:
				city = "Delhi";
				break;
			case 4:
				city = "Hyderabad";
				break;
			case 5:
				city="Kolkata";
				break;
			case 6:
				city="Mumbai";
				break;
			case 7:
				city="Ahemdabad";
				break;
			case 8:
				city="Bengaluru";
				break;
			case 9:
				city="Pune";
				break;
			default:
				city="Kanpur";
				break;
		}
		return city;
	}

//	public static void main(String... args){
//		System.err.print(generateRandomCity());
//	}

}
