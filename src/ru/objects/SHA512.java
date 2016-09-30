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
	private static final long[] K = new long[] {
			new BigInteger("428a2f98d728ae22", 16).longValue(),
		    new BigInteger("7137449123ef65cd", 16).longValue(),
		    new BigInteger("b5c0fbcfec4d3b2f", 16).longValue(),
		    new BigInteger("e9b5dba58189dbbc", 16).longValue(),
		    new BigInteger("3956c25bf348b538", 16).longValue(),
		    new BigInteger("59f111f1b605d019", 16).longValue(),
		    new BigInteger("923f82a4af194f9b", 16).longValue(),
		    new BigInteger("ab1c5ed5da6d8118", 16).longValue(),
		    new BigInteger("d807aa98a3030242", 16).longValue(),
		    new BigInteger("12835b0145706fbe", 16).longValue(),
		    new BigInteger("243185be4ee4b28c", 16).longValue(),
		    new BigInteger("550c7dc3d5ffb4e2", 16).longValue(),
		    new BigInteger("72be5d74f27b896f", 16).longValue(),
		    new BigInteger("80deb1fe3b1696b1", 16).longValue(),
		    new BigInteger("9bdc06a725c71235", 16).longValue(),
		    new BigInteger("c19bf174cf692694", 16).longValue(),
		    new BigInteger("e49b69c19ef14ad2", 16).longValue(),
		    new BigInteger("efbe4786384f25e3", 16).longValue(),
		    new BigInteger("0fc19dc68b8cd5b5", 16).longValue(),
		    new BigInteger("240ca1cc77ac9c65", 16).longValue(),
		    new BigInteger("2de92c6f592b0275", 16).longValue(),
		    new BigInteger("4a7484aa6ea6e483", 16).longValue(),
		    new BigInteger("5cb0a9dcbd41fbd4", 16).longValue(),
		    new BigInteger("76f988da831153b5", 16).longValue(),
		    new BigInteger("983e5152ee66dfab", 16).longValue(),
		    new BigInteger("a831c66d2db43210", 16).longValue(),
		    new BigInteger("b00327c898fb213f", 16).longValue(),
		    new BigInteger("bf597fc7beef0ee4", 16).longValue(),
		    new BigInteger("c6e00bf33da88fc2", 16).longValue(),
		    new BigInteger("d5a79147930aa725", 16).longValue(),
		    new BigInteger("06ca6351e003826f", 16).longValue(),
		    new BigInteger("142929670a0e6e70", 16).longValue(),
		    new BigInteger("27b70a8546d22ffc", 16).longValue(),
		    new BigInteger("2e1b21385c26c926", 16).longValue(),
		    new BigInteger("4d2c6dfc5ac42aed", 16).longValue(),
		    new BigInteger("53380d139d95b3df", 16).longValue(),
		    new BigInteger("650a73548baf63de", 16).longValue(),
		    new BigInteger("766a0abb3c77b2a8", 16).longValue(),
		    new BigInteger("81c2c92e47edaee6", 16).longValue(),
		    new BigInteger("92722c851482353b", 16).longValue(),
		    new BigInteger("a2bfe8a14cf10364", 16).longValue(),
		    new BigInteger("a81a664bbc423001", 16).longValue(),
		    new BigInteger("c24b8b70d0f89791", 16).longValue(),
		    new BigInteger("c76c51a30654be30", 16).longValue(),
		    new BigInteger("d192e819d6ef5218", 16).longValue(),
		    new BigInteger("d69906245565a910", 16).longValue(),
		    new BigInteger("f40e35855771202a", 16).longValue(),
		    new BigInteger("106aa07032bbd1b8", 16).longValue(),
		    new BigInteger("19a4c116b8d2d0c8", 16).longValue(),
		    new BigInteger("1e376c085141ab53", 16).longValue(),
		    new BigInteger("2748774cdf8eeb99", 16).longValue(),
		    new BigInteger("34b0bcb5e19b48a8", 16).longValue(),
		    new BigInteger("391c0cb3c5c95a63", 16).longValue(),
		    new BigInteger("4ed8aa4ae3418acb", 16).longValue(),
		    new BigInteger("5b9cca4f7763e373", 16).longValue(),
		    new BigInteger("682e6ff3d6b2b8a3", 16).longValue(),
		    new BigInteger("748f82ee5defb2fc", 16).longValue(),
		    new BigInteger("78a5636f43172f60", 16).longValue(),
		    new BigInteger("84c87814a1f0ab72", 16).longValue(),
		    new BigInteger("8cc702081a6439ec", 16).longValue(),
		    new BigInteger("90befffa23631e28", 16).longValue(),
		    new BigInteger("a4506cebde82bde9", 16).longValue(),
		    new BigInteger("bef9a3f7b2c67915", 16).longValue(),
		    new BigInteger("c67178f2e372532b", 16).longValue(),
		    new BigInteger("ca273eceea26619c", 16).longValue(),
		    new BigInteger("d186b8c721c0c207", 16).longValue(),
		    new BigInteger("eada7dd6cde0eb1e", 16).longValue(),
		    new BigInteger("f57d4f7fee6ed178", 16).longValue(),
		    new BigInteger("06f067aa72176fba", 16).longValue(),
		    new BigInteger("0a637dc5a2c898a6", 16).longValue(),
		    new BigInteger("113f9804bef90dae", 16).longValue(),
		    new BigInteger("1b710b35131c471b", 16).longValue(),
		    new BigInteger("28db77f523047d84", 16).longValue(),
		    new BigInteger("32caab7b40c72493", 16).longValue(),
		    new BigInteger("3c9ebe0a15c9bebc", 16).longValue(),
		    new BigInteger("431d67c49c100d4c", 16).longValue(),
		    new BigInteger("4cc5d4becb3e42b6", 16).longValue(),
		    new BigInteger("597f299cfc657e2a", 16).longValue(),
		    new BigInteger("5fcb6fab3ad6faec", 16).longValue(),
		    new BigInteger("6c44198c4a475817", 16).longValue()
	};
	
	public static long[] Hash(String text) {	         
         long[][] M = getM(text.getBytes());
         long[] W = new long[80];
         long[] H = new long[] {
     		    0xc1059ed8,
     		    0x367cd507,
     		    0x3070dd17,
     		    0xf70e5939,
     		    0xffc00b31,
     		    0x68581511,
     		    0x64f98fa7,
     		    0xbefa4fa4
     	};

		for(int i = 0; i < M.length; i++) {
			for(int j = 0; j < 16; j++) {
				W[j] = M[i][j];
			}
			 
			for(int j = 16; j < 80; j++) {
				W[j] = Ro1(W[j - 2]) + W[j - 7] + Ro0(W[j - 15]) + W[j - 16];
			}
			 
			long a = H[0];
			long b = H[1];
			long c = H[2];
			long d = H[3];
			long e = H[4];
		    long f = H[5];
		    long g = H[6];
		    long h = H[7];			
		    
		    long T1 = 0, T2 = 0;
		    
		    for(int j = 0; j < 80; j++) {
		    	T1 = h + Eps1(e) + Ch(e, f, g) + K[j] + W[j];
		    	T2 = Eps0(a) + Maj(a, b, c);
		    	 
		    	h = g;
				g = f;
				f = e;
				e = d + T1;
				d = c;
				c = b;
				b = a;
				a = T1 + T2;
		    }
		     
		    H[0] += a; 
		    H[1] += b; 
		    H[2] += c; 
		    H[3] += d; 
		    H[4] += e; 
		    H[5] += f; 
		    H[6] += g; 
		    H[7] += h; 
		 }
		 
		 return H.clone();
	}
	
	public static long[][] getM(byte[] array) {
		int mod = array.length % 128;
		int amount = array.length / 128 + (128 - mod > 8 ? 1: 2);
		
		byte[] full = new byte[amount * 128];
		Arrays.fill(full, (byte)0);
		for(int i = 0; i < array.length; ++i) {
			full[i] = array[i];
		}
		full[array.length] = (byte)-128;
		
		long[][] Result = new long[amount][];
		for(int i = 0, k = 0; i < amount; ++i) {
			Result[i] = new long[16];
			for(int j = 0; j < 16; j++, k += 8) {
				Result[i][j] = new BigInteger(Arrays.copyOfRange(full, k, k + 8)).longValue();
			}
		}
		Result[amount - 1][15] = (long) (array.length * 8);
		
		return Result;
	}
	
	public static long Rotr(long x, int n) {
		return ((x >> n) | (x << 64 - n));	
	}
	
	public static long Ch(long x, long y, long z) {
		return (x & y) | ((~x) & z);
	}
	
	public static long Maj(long x, long y, long z) {
		return (x & y) | (x & z) | (y & z);
	}
	
	public static long Eps0(long x) {
		return (Rotr(x, 28) ^ Rotr(x, 34) ^ Rotr(x, 39));
	}
	
	public static long Eps1(long x) {
		return (Rotr(x, 14) ^ Rotr(x, 18) ^ Rotr(x, 41));
	}
	
	public static long Ro0(long x) {
		return (Rotr(x, 1) ^ Rotr(x, 8) ^ Rotr(x, 7));
	}
	
	public static long Ro1(long x) {
		return (Rotr(x, 19) ^ Rotr(x, 61) ^ Rotr(x, 6));
	}
}

/*
public class SHA512 {	
	byte[][] H = null;
	byte[][] K = null;
	
	public static void Hash(String text) {	
		try {
			JsonObject jObj = readStartProperties("src/ru/resource/StartProperties");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
			
	}	
	
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
	
	public static JsonObject readStartProperties(String address) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(address));

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
}
*/