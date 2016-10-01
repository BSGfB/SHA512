package ru.start;

import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import ru.objects.SHA512;

public class Start {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		
		
		long[] array = SHA512.Hash("abc");
		for(int i = 0; i < array.length; i++) {
			System.out.println(Long.toHexString(array[i]));
		}
		
		
	}
	
	public static long[][] getM(byte[] array) {
		int mod = array.length % 128;
		int amount = array.length / 128 + (128 - mod > 8 ? 1: 2);
			
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		} System.out.println();
		
		byte[] full = new byte[amount * 128];
		Arrays.fill(full, (byte)0);
		for(int i = 0; i < array.length; ++i) 
			full[i] = array[i];
		
		full = setLastBit(full);
		
		for(int i = 0; i < full.length; i++) {
			System.out.print(Long.toBinaryString(full[i]) + " ");
		} System.out.println();
		
		long[][] Result = new long[amount][];
		for(int i = 0, k = 0; i < amount; ++i) {
			Result[i] = new long[16];
			for(int j = 0; j < 16; j++, k += 8) {
				// Result[i][j] = new BigInteger(Arrays.copyOfRange(full, k, k + 8)).longValue();
				
				Result[i][j] = toLong(Arrays.copyOfRange(full, k, k + 8));
				//System.out.println(j + " k: " + k + " Result[i][j] = " + Result[i][j]);
			}
		}
		Result[amount - 1][15] = (long) (array.length * 8);
		
		for(int i = 0; i < Result[0].length; i++) {
			System.out.print(Long.toBinaryString(Result[0][i]) + " ");
		} System.out.println();
		
		return Result;
	}
	
	public static byte[] toBytes(long a) {
		byte[] array = new byte[8];
		Arrays.fill(array, (byte)0);
		
		for(int i = 7, j = 0; i >= 0; i--, j++) {
			array[j] |= (a >>> i * 8);
		}
		
		return array;
	}
	
	public static long toLong(byte[] array) {
		long a = 0;
		for(int i = 0; i < array.length; ++i) {
			a <<= 8;
			a |= (array[i] < 0) ? (long)((array[i] * -1)): array[i];
		}
		
		return a;
	}
	
	public static byte[] setLastBit(byte[] array) {		
		int i = 0;
		for(; i < array.length; i++) 
			if(array[i] == 0)
				break;
		
		if(i == 0 && array[i] == 0) {
			array[i] = -128;
			return array;
		}
		
		for(int j = 0; j < 8; j++) {
			byte a = getBit(array[i - 1], j);
			
			if(a == 1 && j == 0) {
				array[i] = -128;
				break;
			} else if(a == 1 && i > 0) {
				array[i - 1] = setBit(array[i - 1], j - 1);
				System.out.println(" J: " + j + " / 8");
				break;
			}
		}
				
		return array;
	}
	
	
	
	public static byte getBit(byte a, int i) {
		return (byte) ((a >> i) & 1); 
	}
	public static byte setBit(byte a, int i) {
		return (byte) ((byte) (1 << i) | a); 
	}

}
