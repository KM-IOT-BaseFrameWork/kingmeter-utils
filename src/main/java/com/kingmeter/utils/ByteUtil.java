package com.kingmeter.utils;

/**
 *
 */
public class ByteUtil {
	public static byte[] toByteArray(String contents) {
		if (StringUtil.isNotBlank(contents)) {
			String[] content = contents.split(" ");
			byte[] baKeyword = new byte[content.length];
			for (int i = 0; i < baKeyword.length; i++) {
				try {
					baKeyword[i] = (byte) Integer.parseInt(content[i], 16);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return baKeyword;
		}
		return new byte[] {};
	}

//	/**
//	 * Convert char to byte
//	 *
//	 * @param c
//	 *            char
//	 * @return byte
//	 */
//	private static byte charToByte(char c) {
//		return (byte) "0123456789ABCDEF".indexOf(c);
//	}

//	/**
//	 * @param hexString
//	 * @return
//	 */
//	public static byte[] hexStringToBytes(String hexString) {
//		if (hexString == null || hexString.equals("")) {
//			return new byte[0];
//		}
//		hexString = hexString.toUpperCase();
//		int length = hexString.length() / 2;
//		char[] hexChars = hexString.toCharArray();
//		byte[] d = new byte[length];
//		for (int i = 0; i < length; i++) {
//			int pos = i * 2;
//			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
//		}
//		return d;
//	}

	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv.toUpperCase()).append(" ");
		}
		return stringBuilder.subSequence(0, stringBuilder.length() - 1).toString();
	}
}
