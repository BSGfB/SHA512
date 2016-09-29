package ru.start;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.lang.Integer;
import ru.objects.SHA512;

public class Start {

	public static void main(String[] args) {
		byte a = 1;
		System.out.print(a ^ (-128));
		
		/*
		byte[] array = new byte[1020];
		for(int i = 0; i < array.length; i++) {
			array[i] = (byte)(i % 128);
		}
		
		byte[][] cube = SHA512.get128BytesBlocks(array);
		for(int i = 0; i < cube.length; i++) {
			for(int j = 0; j < cube[i].length; j++) {
				System.out.print(cube[i][j] + " ");				
			}
			System.out.println();	
		}
		
		byte[][] cube2 = SHA512.get8BytesBlocks(cube[0]);
		for(int i = 0; i < cube2.length; i++) {
			for(int j = 0; j < cube2[i].length; j++) {
				System.out.print(cube2[i][j] + " ");				
			}
			System.out.println();	
		}
		*/
		/*
		BigInteger a = new BigInteger("101000001000000001", 2);
		System.out.println("a: " + a.toString(2));
		
		BigInteger b = new BigInteger(SHA512.No(a.toByteArray()));
		System.out.println("b: " + b.toString(2));
		*/
	}
	
	public static byte[] f(byte[] base, int n) {
		char[] temp = new BigInteger(base).toString(2).toCharArray();
		char[] full = new char[base.length * 8];
		Arrays.fill(full, '0');
		for(int i = full.length - 1; i >= full.length - temp.length; i--) {
			full[i] = temp[i - (full.length - temp.length)];
		}
		
		char[] part1 = Arrays.copyOfRange(full, 0, full.length - n);
		char[] part2 = Arrays.copyOfRange(full, full.length - n, full.length);

		for(int i = 0; i < part2.length; ++i) {
			full[i] = part2[i];
		}
		
		for(int i = 0; i < part1.length; ++i) {
			full[n + i] = part1[i];
		}
	
		return new BigInteger(String.valueOf(full), 2).toByteArray();
	}
}
