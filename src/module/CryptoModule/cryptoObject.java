package Module.cryptoModule;

import java.math.BigInteger;
import java.security.MessageDigest;

public class cryptoObject {
	
	private String normalStr = null;
	private String hashStr = null;
	
	private cryptoObject(String str) {
		normalStr = str;
	}
	
	public static cryptoObject getInstence(String str) {
		return new cryptoObject(str);
	}
	
	public String getHashString() {
		if(hashStr == null) setSHA256String();
		return hashStr;
	}
	
	
	public void setSHA512String() {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(normalStr.getBytes());
			hashStr = String.format("%0128x", new BigInteger(1, md.digest()));
			
		} catch (Exception e) {
			System.out.println("cryptoObject ERROR : " + e);
			System.out.println(e.getStackTrace());
			hashStr = null;
		}
	}
	public void setSHA256String() {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(normalStr.getBytes());
			hashStr = String.format("%064x", new BigInteger(1, md.digest()));
			
		} catch (Exception e) {
			System.out.println("cryptoObject ERROR : " + e);
			System.out.println(e.getStackTrace());
			hashStr = null;
		}
	}
}
