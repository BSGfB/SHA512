package ru.start;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.lang.Integer;
import ru.objects.SHA512;

public class Start {

	public static void main(String[] args) throws IOException {		
		BufferedReader reader = new BufferedReader(new FileReader("src/ru/resource/Text"));
			
		
		CharBuffer buffer = CharBuffer.allocate(20000);
		reader.read(buffer);
		reader.close();
		
		String str = new String(buffer.array());
		System.out.println(str);
		long[] array = SHA512.Hash(str);
		for(int i = 0; i < array.length; ++i) {
			System.out.println(array[i]);
		}
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
