package com.tripayweb.util;

import java.io.*;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.*;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.tripayweb.model.app.response.MerchantTransactionResponse;
import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.json.XML;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import static java.security.Security.getProviders;

public class SecurityUtil {


	private static final String KEY = "adkj@#$02#@adflkj)(*jlj@#$#@LKjasdjlkj<.,mo@#$@#kljlkdsu343";

	public static void main(String[] args) throws Exception {

		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		Cipher c = Cipher.getInstance("AES");
	//demo of provider class
//		Provider[] listProviders = Security.getProviders();
//		for(Provider p : listProviders) {
//			System.err.println("Name::"+p.getName()+"  Info::"+p.getInfo());
//			Provider provider = Security.getProvider(p.getName());
//			System.err.println(provider);
//
//		}
//		Provider provider = Security.getProvider("SunPCSC");

//		System.err.println(provider);
		//Demo of secure random generator
		String email = "ggupta@firstglobalmoney.com";
//		System.err.print("email :" +email);
//		System.err.print("url encoded :" +URLEncoder.encode(email));

		SecureRandom secureRandom = new SecureRandom();

//		SecureRandom secureRandom1 = SecureRandom.getInstanceStrong();
//		secureRandom1.setSeed(20);
//		System.err.println(secureRandom1.generateSeed(1));
//		System.err.println(messageDigest);
//		System.err.println(md5("9880488759"));
		System.out.println("DONE");
	}

	/**
	 * Encrypts and encodes the Object and IV for url inclusion
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String[] encryptObject(Object obj) throws Exception {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ObjectOutput out = new ObjectOutputStream(stream);
		try {
			// Serialize the object
			out.writeObject(obj);
			byte[] serialized = stream.toByteArray();

			// Setup the cipher and Init Vector
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			byte[] iv = new byte[cipher.getBlockSize()];
			new SecureRandom().nextBytes(iv);
			IvParameterSpec ivSpec = new IvParameterSpec(iv);

			// Hash the key with SHA-256 and trim the output to 128-bit for the
			// key
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(KEY.getBytes());
			byte[] key = new byte[16];
			System.arraycopy(digest.digest(), 0, key, 0, key.length);
			SecretKeySpec keySpec = new SecretKeySpec(key, "AES");

			// encrypt
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

			// Encrypt & Encode the input
			byte[] encrypted = cipher.doFinal(serialized);
			byte[] base64Encoded = Base64.encodeBase64(encrypted);
			String base64String = new String(base64Encoded);
			String urlEncodedData = URLEncoder.encode(base64String, "UTF-8");

			// Encode the Init Vector
			byte[] base64IV = Base64.encodeBase64(iv);
			String base64IVString = new String(base64IV);
			String urlEncodedIV = URLEncoder.encode(base64IVString, "UTF-8");

			return new String[] { urlEncodedData, urlEncodedIV };
		} finally {
			stream.close();
			out.close();
		}
	}

	/**
	 * Decrypts the String and serializes the object
	 * 
	 * @param base64Data
	 * @param base64IV
	 * @return
	 * @throws Exception
	 */
	public static Object decryptObject(String base64Data, String base64IV) throws Exception {
		// Decode the data
		byte[] encryptedData = Base64.decodeBase64(base64Data.getBytes());

		// Decode the Init Vector
		byte[] rawIV = Base64.decodeBase64(base64IV.getBytes());

		// Configure the Cipher
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		IvParameterSpec ivSpec = new IvParameterSpec(rawIV);
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		digest.update(KEY.getBytes());
		byte[] key = new byte[16];
		System.arraycopy(digest.digest(), 0, key, 0, key.length);
		SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

		byte[] decrypted = cipher.doFinal(encryptedData);

		ByteArrayInputStream stream = new ByteArrayInputStream(decrypted);
		ObjectInput in = new ObjectInputStream(stream);
		Object obj = null;
		try {
			obj = in.readObject();
		} finally {
			stream.close();
			in.close();
		}
		return obj;
	}

	public static String md5(String str) throws Exception {

		MessageDigest m = MessageDigest.getInstance("MD5");

		byte[] data = str.getBytes();

		m.update(data, 0, data.length);

		BigInteger i = new BigInteger(1, m.digest());

		String hash = String.format("%1$032X", i);

		return hash;
	}


	public static boolean isHashMatches(Object obj, String hash) {
		boolean isValid = true;
		ObjectWriter ow = new ObjectMapper().writer();
		try {
			String json = ow.writeValueAsString(obj);
			System.err.println("JSON ::" + json);
			String encryptedHash = "";
			try {
				encryptedHash = md5(json);
				System.err.println("Calculated hash ::" + encryptedHash);
			} catch (Exception e) {
				System.err.println("Internal Server Error While Using MD5");
			}
			if (encryptedHash.equals(hash)) {
				System.err.println("Both hash are equal");
				isValid = true;
			} else {
				System.err.println("Hash are not equal ::" + hash);
			}
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isValid;
	}


}
