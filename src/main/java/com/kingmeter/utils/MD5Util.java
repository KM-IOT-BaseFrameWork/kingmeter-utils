package com.kingmeter.utils;
import java.security.MessageDigest;

public class MD5Util {

	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]).toUpperCase());

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}


	public static String MD5Encode(byte[] passwordArray) {
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			return byteArrayToHexString(md.digest(passwordArray));
		}catch (Exception e){
			e.printStackTrace();
		}
		return "";
	}

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };


//	public static void main(String[] args) {
//		byte[] passwordArray = {49, 50, 51, 52, 53, 54};
//		String passwordMd5 = MD5Util.MD5Encode(passwordArray);
//		System.out.println(passwordMd5);
//	}
}
