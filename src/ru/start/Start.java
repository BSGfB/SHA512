package ru.start;

import java.math.BigInteger;
import java.util.Arrays;

public class Start {

	public static void main(String[] args) {
		byte[] array = new byte[3];
		Arrays.fill(array, (byte)1);
		
		
		BigInteger number = new BigInteger(array);
		System.out.println(number);
		System.out.println(number.bitCount());
		System.out.println(number.bitLength());
		System.out.println(number.toByteArray().length);
		
	}

}
