package com.hrc.commons.utils;

import java.security.Key;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class EncryptionDecryptionUtils {

	private static final String ALGO = "AES";
	
	private static final String KEY = "thebestsecretkey";

	public static String encrypt(String data, String encryptionKey) throws Exception {
		Key key = generateKey(data, encryptionKey);
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(data.getBytes());
		Encoder base64 =  Base64.getEncoder();
		byte[] encryptedBytes = base64.encode(encVal);
		return new String(encryptedBytes);
	}

	public static String decrypt(String encryptedData, String encryptionKey) throws Exception {
		Key key = generateKey(encryptedData, encryptionKey);
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}

	private static Key generateKey(String toBeEncrypted, String encryptionKey) throws Exception {
		Key key = new SecretKeySpec(encryptionKey.getBytes(), ALGO);
		return key;
	}
	
	public static void main(String[] args) throws Exception {
		String test = "test";
		String enc = encrypt(test, KEY);
		System.out.println(enc);
		System.out.println(decrypt(enc, KEY));
	}
}
