package ru.objects;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SHA512 {
	private static final BigInteger[] H = new BigInteger[] {
			new BigInteger("6a09e667f3bcc908"),
			new BigInteger("bb67ae8584caa73b"),
			new BigInteger("3c6ef372fe94f82b"),
			new BigInteger("a54ff53a5f1d36f1"),
			new BigInteger("510e527fade682d1"),
			new BigInteger("9b05688c2b3e6c1f"),
			new BigInteger("1f83d9abfb41bd6b"),
			new BigInteger("5be0cd19137e2179")
	}; 
	
	
	public void get1024BitBlocks(BigInteger base) {
		int Size = base.bitLength() / 1024 + 1;
		BigInteger[] Blocks_1024 = new BigInteger[Size];
		byte[] array = base.toByteArray();
		
		for(int i = 0; i < Size - 1; i++) {
			byte[] tempArray = Arrays.copyOfRange(array, i * 128, (i + 1) * 128);
			Blocks_1024[i] = new BigInteger(tempArray);
		}
		
		if(64 < (base.bitLength() % 1024)) {
			byte[] tempArray = Arrays.copyOfRange(array, (Size - 1) * 128, array.length - 1);
			byte[] Array128 = new byte[128];
			Arrays.fill(Array128, (byte)0);
			for(int i = 0; i < tempArray.length; i++) {
				Array128[i] = tempArray[i];
			}
			Array128[tempArray.length] = -128;
						
			try {
				MessageDigest.getInstance("SHA-512");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			
		}
	}
	
	public void Hash(String text) {
		BigInteger bitText = new BigInteger(text.getBytes());
		
		
	}
}
