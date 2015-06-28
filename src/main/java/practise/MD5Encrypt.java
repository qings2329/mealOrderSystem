/**
 * 
 */
package practise;

import java.security.MessageDigest;

/**
 * @author Administrator
 *
 */
public class MD5Encrypt {

	
	public MD5Encrypt() {
	}

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };

	public static String byteArrayToString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i])); // 若使用本函数转换则可得到加密结果的16进制表示，即数字字母混合的形式
			// resultSb.append(byteToNumString(b[i]));//使用本函数则返回加密结果的10进制数字字串，即全数字形式
			// resultSb.append(byteToTotal(b[i]));//使用本函数则返回加密结果的10进制数字的总和
		}
		return resultSb.toString();
	}

	/*
	 * private static String byteToNumString(byte b) {
	 * 
	 * int _b = b; if (_b < 0) { _b = 256 + _b; } return String.valueOf(_b); }
	 */

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/*
	 * private static int byteToTotal(byte[] b) {
	 * 
	 * int sum = 0; for (int i = 0; i < b.length; i++) { sum += b[i]; } return
	 * sum; }
	 */

	public static String encrypt(String strSrc) {
		byte[] returnByte = null;
		String returnStr = null;
		if (strSrc == null) throw new java.lang.NullPointerException("加密字符串为空！");
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			returnByte = md5.digest(strSrc.getBytes());
			returnStr = byteArrayToString(returnByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnStr;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	}

}
