package com.kebi.crypto;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * <a href=
 * "http://download.oracle.com/javase/6/docs/technotes/guides/security/crypto/CryptoSpec.html#Cipher"
 * >Java &trade; Cryptography Architecture(JCA) Reference Guide</a>
 * 
 * @author YongChan Kim
 */
public abstract class GeneralCipher {

	Cipher cipher;

	/**
	 * Create a Cipher object that implements the specified transformation.
	 * 
	 * @param transformation
	 * @throws GeneralSecurityException
	 */
	public GeneralCipher(String transformation) throws GeneralSecurityException {
		try {
			cipher = Cipher.getInstance(transformation);
		} catch (NoSuchAlgorithmException e) {
			throw e;
		} catch (NoSuchPaddingException e) {
			throw e;
		}
	}

	public Cipher getCipher() {
		return cipher;
	}

	/**
	 * Initializes this cipher with a key.
	 * 
	 * @param opmode
	 *            the operation mode of this cipher (this is one of the
	 *            following: <code>ENCRYPT_MODE</code>,
	 *            <code>DECRYPT_MODE</code>, <code>WRAP_MODE</code> or
	 *            <code>UNWRAP_MODE</code>)
	 * @param key
	 *            the key
	 * @see javax.crypto.Cipher#init(int, Key)
	 */
	public void init(int opmode, Key key) throws GeneralSecurityException {
		try {
			cipher.init(opmode, key);
		} catch (InvalidKeyException e) {
			throw e;
		}
	}

	/**
	 * Initializes this cipher with a key and a set of algorithm parameters.
	 * 
	 * @param opmode
	 *            the operation mode of this cipher (this is one of the
	 *            following: ENCRYPT_MODE, DECRYPT_MODE, WRAP_MODE or
	 *            UNWRAP_MODE)
	 * @param key
	 *            the encryption key
	 * @param params
	 *            the algorithm parameters
	 * @throws GeneralSecurityException
	 * @see javax.crypto.Cipher#init(int, Key, AlgorithmParameterSpec)
	 */
	public void init(int opmode, Key key, AlgorithmParameterSpec params) throws GeneralSecurityException {
		try {
			cipher.init(opmode, key, params);
		} catch (InvalidKeyException e) {
			throw e;
		} catch (InvalidAlgorithmParameterException e) {
			throw e;
		}
	}

	/**
	 * Encrypts or decrypts data in a single-part operation, or finishes a
	 * multiple-part operation. The data is encrypted or decrypted, depending on
	 * how this cipher was initialized.
	 * 
	 * <p>
	 * Note: if any exception is thrown, this cipher object may need to be reset
	 * before it can be used again.
	 * 
	 * @param input
	 *            the input buffer
	 * @return the new buffer with the result
	 * @see javax.crypto.Cipher#doFinal(byte[])
	 */
	public byte[] doFinal(byte[] input) throws GeneralSecurityException {
		try {
			return cipher.doFinal(input);
		} catch (IllegalBlockSizeException e) {
			throw e;
		} catch (BadPaddingException e) {
			throw e;
		}
	}

	/**
	 * Encrypts or decrypts data in a single-part operation, or finishes a
	 * multiple-part operation. The data is encrypted or decrypted, depending on
	 * how this cipher was initialized.
	 * 
	 * @param input
	 *            the input buffer
	 * @param inputOffset
	 *            the offset in <code>input</code> where the input starts
	 * @param inputLen
	 *            the input length
	 * @return the new buffer with the result
	 * @see javax.crypto.Cipher#doFinal(byte[], int, int)
	 */
	public final byte[] doFinal(byte[] input, int inputOffset, int inputLen) throws GeneralSecurityException {
		try {
			return cipher.doFinal(input, inputOffset, inputLen);
		} catch (IllegalBlockSizeException e) {
			throw e;
		} catch (BadPaddingException e) {
			throw e;
		}
	}

	public static String encodeBASE64(byte[] bytes) throws IOException {
		BASE64Encoder encoder = new BASE64Encoder();
		String base64 = encoder.encode(bytes);
		base64 = base64.replaceAll("\\r\\n|\\r|\\n", "");
		return base64;
	}

	public static byte[] decodeBASE64(String base64Text) throws IOException {
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b = null;
		try {
			b = decoder.decodeBuffer(base64Text);
		} catch (IOException e) {
			throw e;
		}
		return b;
	}

	public static String toHexString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		String hexString = null;
		for (int i = 0; i < bytes.length; i++) {
			hexString = Integer.toHexString(0xFF & bytes[i]);
			if (hexString.length() == 1) {
				sb.append('0');
			}
			sb.append(hexString);
		}
		return sb.toString();
	}

	public static byte[] toBytes(String hexString) {
		byte[] bytes = new byte[hexString.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			String hex = hexString.substring(i * 2, (i * 2) + 2);
			bytes[i] = (byte) (Integer.parseInt(hex, 16));
		}
		return bytes;
	}

}
