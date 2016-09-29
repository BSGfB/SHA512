package ru.objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SHA512 {	
	public static byte[][] get128BytesBlocks(byte[] array) {
		int mod = array.length % 128;
		int amount = array.length / 128 + (128 - mod > 8 ? 1: 2);
		byte[][] blocks = new byte[amount][];
		
		System.out.println("Mod: " + mod + " amount: " + amount);
		
		for(int i = 0; i < array.length / 128; i++) {
			blocks[i] = Arrays.copyOfRange(array, i * 128, (i + 1) * 128);
		}
		
		if(128 - mod > 8) {
			blocks[amount - 1] = new byte[128];
			Arrays.fill(blocks[amount - 1], (byte)0);
			
			for(int i = 0; i < 128 - mod; i++) {
				blocks[amount - 1][i] = array[(amount - 1) * 128 + i];
			}
			blocks[amount - 1][128 - mod] = (byte)-128;
			
			byte[] temp = new BigInteger("" + array.length).multiply(BigInteger.valueOf(8)).toByteArray();
			for(int i = temp.length - 1, j = 127; i >= 0; i--, j--) {
				blocks[amount - 1][j] = temp[i];
			}
		} else {
			blocks[amount - 2] = new byte[128];
			Arrays.fill(blocks[amount - 2], (byte)0);
			
			for(int i = 0; i < 128 - mod; i++) {
				blocks[amount - 2][i] = array[(amount - 2) * 128 + i];
			}
			blocks[amount - 2][128 - mod] = (byte)-128;
			
			
			blocks[amount - 1] = new byte[128];
			Arrays.fill(blocks[amount - 1], (byte)0);
			
			byte[] temp = new BigInteger("" + array.length).multiply(BigInteger.valueOf(8)).toByteArray();
			for(int i = temp.length - 1, j = 127; i >= 0; i--, j--) {
				blocks[(amount - 1) - 1][j] = temp[i];
			}
		}
		
		return blocks;
	}
	public static byte[][] get8BytesBlocks(byte[] array) {
		byte[][] blocks = new byte[16][];		
		for(int i = 0; i < blocks.length; ++i) {
			blocks[i] = Arrays.copyOfRange(array, i * 8, (i + 1) * 8);
		}
		
		return blocks;
	}
	
	// "../resource/StartProperties.txt"
	public static JsonObject readStartProperties(String address) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(address));
		/*
		CharBuffer buffer = CharBuffer.allocate(4096);
		bufferedReader.read(buffer);
		bufferedReader.close();
		*/
		JsonObject jObj = (JsonObject)new JsonParser().parse(bufferedReader);
		bufferedReader.close();
		
		return jObj;
	}
	
	public static byte[] rightCyclicShift(byte[] base, int n) {
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
	
	public static byte[] rightShift(byte[] base, int n) {
		char[] temp = new BigInteger(base).toString(2).toCharArray();
		
		for(int i = temp.length - 4; i >= 0; i--) {
			temp[i + 3] = temp[i];
			temp[i] = '0';
		}
	
		return new BigInteger(String.valueOf(temp), 2).toByteArray();		
	}
	
	
	
	
	
	
	public static byte[] Maj(byte[] x, byte[] y, byte[] z) {
		return Or(Or(And(x, y), And(x, z)), And(x, z));
	}
	
	public static byte[] Ch(byte[] x, byte[] y, byte[] z) {
		return Or(And(x, y), And(No(x), z));
	}
	
	public static byte[] Eps_512_0(byte[] array) {
		return Xor(Xor(rightCyclicShift(array, 28), rightCyclicShift(array, 34)), rightCyclicShift(array, 39));
	}
	
	public static byte[] Eps_512_1(byte[] array) {
		return Xor(Xor(rightCyclicShift(array, 14), rightCyclicShift(array, 18)), rightCyclicShift(array, 41));
	}
		
	
	public static byte[] Ro_512_0(byte[] array) {
		return Xor(Xor(rightCyclicShift(array, 1), rightCyclicShift(array, 8)), rightShift(array, 7));
	}
	
	public static byte[] Ro_512_1(byte[] array) {
		return Xor(Xor(rightCyclicShift(array, 19), rightCyclicShift(array, 61)), rightShift(array, 6));
	}
	
	public static byte[] Xor(byte[] a, byte[] b) {
		int minSize = a.length > b.length ? b.length: a.length;
		byte[] max = a.length > b.length ? a: b;
		byte[] array = new byte[a.length > b.length ? a.length: b.length];
		for(int i = 0; i < array.length; i++) {
			array[i] = max[i];
		}
		
		for(int i = 0; i < minSize; i++) {
			array[i] = (byte) (a[i] ^ b[i]);
		}
		
		return array;
	}
	
	public static byte[] Or(byte[] a, byte[] b) {
		int minSize = a.length > b.length ? b.length: a.length;
		byte[] max = a.length > b.length ? a: b;
		byte[] array = new byte[a.length > b.length ? a.length: b.length];
		for(int i = 0; i < array.length; i++) {
			array[i] = max[i];
		}
		
		for(int i = 0; i < minSize; i++) {
			array[i] = (byte) (a[i] | b[i]);
		}
		
		return array;
	}
	
	public static byte[] And(byte[] a, byte[] b) {
		int minSize = a.length > b.length ? b.length: a.length;
		byte[] max = a.length > b.length ? a: b;
		byte[] array = new byte[a.length > b.length ? a.length: b.length];
		for(int i = 0; i < array.length; i++) {
			array[i] = max[i];
		}
				
		for(int i = 0; i < minSize; i++) {
			array[i] = (byte) (a[i] & b[i]);
		}
		
		return array;
	}
	
	public static byte[] No(byte[] a) {
		byte[] array = new byte[a.length];
		
		for(int i = 0; i < array.length; i++) {
			array[i] =  (byte) ((byte) a[i] ^ (-128));
		}
		
		return array;
	}
	
	public void Hash(String text) {	
		
		
	}
}
