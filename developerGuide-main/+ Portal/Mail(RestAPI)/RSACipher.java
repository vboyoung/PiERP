package com.kebi.crypto;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;

public class RSACipher extends GeneralCipher {

	static final String ALGORITHM = "RSA";

	static final String TRANSFORMATION = "RSA/ECB/PKCS1Padding";

	static final String CHARSET = "UTF-8";

	static final int KEYSIZE = 2048;

	private PublicKey publicKey;

	private PrivateKey privateKey;

	private int keysize;

	private KeyFactory keyFactory;

	/**
	 * Creates a new RSACipher.
	 * 
	 * @param transformation
	 * @param publicKey
	 *            the public key.
	 * @param privateKey
	 *            the private key.
	 * @param keysize
	 *            the keysize. This is an algorithm-specific metric, such as
	 *            modulus length, specified in number of bits.
	 * @throws GeneralSecurityException
	 */
	public RSACipher(String transformation, PublicKey publicKey, PrivateKey privateKey, int keysize)
			throws GeneralSecurityException {
		super(transformation);
		if (publicKey != null && privateKey != null) {
			this.publicKey = publicKey;
			this.privateKey = privateKey;
		} else {
			KeyPair keyPair = generateKeyPair(keysize);
			this.publicKey = keyPair.getPublic();
			this.privateKey = keyPair.getPrivate();
		}
		this.keysize = keysize;
		this.keyFactory = KeyFactory.getInstance(ALGORITHM);
	}

	/**
	 * Creates a new RSACipher.
	 */
	public RSACipher() throws GeneralSecurityException {
		this(TRANSFORMATION, null, null, KEYSIZE);
	}

	/**
	 * Generates a key pair.
	 *
	 * @param keysize
	 *            the keysize. This is an algorithm-specific metric, such as
	 *            modulus length, specified in number of bits.
	 * @return the generated key pair
	 * @exception InvalidParameterException
	 *                if the <code>keysize</code> is not supported by this
	 *                KeyPairGenerator object.
	 */
	public KeyPair generateKeyPair(int keysize) throws GeneralSecurityException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM);
		generator.initialize(keysize);
		KeyPair keyPair = generator.generateKeyPair();
		return keyPair;
	}

	/**
	 * Generates a public key object from the provided key specification (key
	 * material).
	 *
	 * @param encodedKey
	 *            the key, which is assumed to be encoded according to the X.509
	 *            standard. The contents of the array are copied to protect
	 *            against subsequent modification.
	 * @return the public key.
	 */
	public PublicKey generatePublicKey(byte[] encodedKey) throws GeneralSecurityException {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encodedKey);
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * Generates a public key object from a new RSAPublicKeySpec.
	 *
	 * @param modulus
	 *            the modulus
	 * @param publicExponent
	 *            the public exponent
	 */
	public PublicKey generatePublicKey(BigInteger modulus, BigInteger publicExponent) throws GeneralSecurityException {
		RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, publicExponent);
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * Generates a private key object from the provided key specification (key
	 * material).
	 *
	 * @param encodedKey
	 *            the key, which is assumed to be encoded according to the PKCS
	 *            #8 standard. The contents of the array are copied to protect
	 *            against subsequent modification.
	 * @return the private key.
	 */
	public PrivateKey generatePrivateKey(byte[] encodedKey) throws GeneralSecurityException {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedKey);
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	/**
	 * Generates a private key object from a new RSAPrivateKeySpec.
	 *
	 * @param modulus
	 *            the modulus
	 * @param privateExponent
	 *            the private exponent
	 */
	public PrivateKey generatePrivateKey(BigInteger modulus, BigInteger privateExponent)
			throws GeneralSecurityException {
		RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(modulus, privateExponent);
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	public void setKeyPair(PublicKey publicKey, PrivateKey privateKey) {
		if (publicKey != null)
			this.publicKey = publicKey;
		if (privateKey != null)
			this.privateKey = privateKey;
	}

	public void setPublicKey(byte[] encodedKey) throws GeneralSecurityException {
		PublicKey publicKey = generatePublicKey(encodedKey);
		setKeyPair(publicKey, null);
	}

	public void setPublicKey(BigInteger modulus, BigInteger publicExponent) throws GeneralSecurityException {
		PublicKey publicKey = generatePublicKey(modulus, publicExponent);
		setKeyPair(publicKey, null);
	}

	public void setPrivateKey(byte[] encodedKey) throws GeneralSecurityException {
		PrivateKey privateKey = generatePrivateKey(encodedKey);
		setKeyPair(null, privateKey);
	}

	public void setPrivateKey(BigInteger modulus, BigInteger privateExponent) throws GeneralSecurityException {
		PrivateKey privateKey = generatePrivateKey(modulus, privateExponent);
		setKeyPair(null, privateKey);
	}

	public byte[] getEncodedPublicKey() throws GeneralSecurityException {
		return publicKey.getEncoded();
	}

	public byte[] getEncodedPrivateKey() throws GeneralSecurityException {
		return privateKey.getEncoded();
	}

	public String getHexEncodedPublicKey() throws GeneralSecurityException {
		return toHexString(getEncodedPublicKey());
	}

	public String getHexEncodedPrivateKey() throws GeneralSecurityException {
		return toHexString(getEncodedPrivateKey());
	}

	/**
	 * Returns the public modulus.
	 *
	 * @return the public modulus
	 */
	public BigInteger getPublicModulus() throws GeneralSecurityException {
		RSAPublicKeySpec keySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
		return keySpec.getModulus();
	}

	/**
	 * Returns the public exponent.
	 *
	 * @return the public exponent
	 */
	public BigInteger getPublicExponent() throws GeneralSecurityException {
		RSAPublicKeySpec keySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
		return keySpec.getPublicExponent();
	}

	/**
	 * Returns the private modulus.
	 *
	 * @return the private modulus
	 */
	public BigInteger getPrivateModulus() throws GeneralSecurityException {
		RSAPrivateKeySpec keySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);
		return keySpec.getModulus();
	}

	/**
	 * Returns the private exponent.
	 *
	 * @return the private exponent
	 */
	public BigInteger getPrivateExponent() throws GeneralSecurityException {
		RSAPrivateKeySpec keySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);
		return keySpec.getPrivateExponent();
	}

	public byte[] encrypt(byte[] input, PublicKey publicKey) throws GeneralSecurityException {
		init(Cipher.ENCRYPT_MODE, publicKey);
		int blockSize = (keysize / 8) - 11;
		int outputLen = keysize / 8;
		if (input.length > blockSize)
			outputLen = outputLen * (int) Math.ceil((double) input.length / (double) blockSize);
		byte[] output = new byte[outputLen];
		int count = 0;
		for (int offset = 0; offset < input.length; offset += blockSize) {
			int inputLen = blockSize;
			if (offset + blockSize > input.length)
				inputLen = input.length - offset;
			byte[] data = doFinal(input, offset, inputLen);
			System.arraycopy(data, 0, output, count, data.length);
			count += data.length;
		}
		return output;
	}

	public byte[] encrypt(byte[] input) throws GeneralSecurityException {
		return encrypt(input, publicKey);
	}

	public byte[] decrypt(byte[] input, PrivateKey privateKey) throws GeneralSecurityException {
		init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decryptData = doFinal(input);
		return decryptData;
	}

	public byte[] decrypt(byte[] input) throws GeneralSecurityException {
		init(Cipher.DECRYPT_MODE, privateKey);
		int blockSize = keysize / 8;
		int outputLen = (keysize / 8) - 11;
		if (input.length > blockSize)
			outputLen = outputLen * (int) Math.ceil((double) input.length / (double) blockSize);
		byte[] output = new byte[outputLen];
		int count = 0;
		for (int offset = 0; offset < input.length; offset += blockSize) {
			byte[] data = doFinal(input, offset, blockSize);
			System.arraycopy(data, 0, output, count, data.length);
			count += data.length;
		}
		return Arrays.copyOf(output, count);
	}

	public String encryptToHexString(String plainText, String encodedPublicKey) throws Exception {
		setPublicKey(toBytes(encodedPublicKey));
		byte[] encryptData = encrypt(plainText.getBytes(CHARSET));
		return toHexString(encryptData);
	}

	public String encryptToHexString(String plainText, String modulus, String publicExponent) throws Exception {
		setPublicKey(new BigInteger(modulus, 16), new BigInteger(publicExponent, 16));
		byte[] encryptData = encrypt(plainText.getBytes(CHARSET));
		return toHexString(encryptData);
	}

	public String decryptFromHexString(String hexString, String encodedPrivateKey) throws Exception {
		setPrivateKey(toBytes(encodedPrivateKey));
		byte[] decryptData = decrypt(toBytes(hexString));
		return new String(decryptData, CHARSET);
	}

	public String decryptFromHexString(String hexString, String modulus, String privateExponent) throws Exception {
		setPrivateKey(new BigInteger(modulus, 16), new BigInteger(privateExponent, 16));
		byte[] decryptData = decrypt(toBytes(hexString));
		return new String(decryptData, CHARSET);
	}

}
