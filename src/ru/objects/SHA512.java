package ru.objects;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Arrays;

public class SHA512 {	
	/*
	private static final BigInteger[] K = new BigInteger[] {
			new BigInteger("428a2f98d728ae22", 16),
		    new BigInteger("7137449123ef65cd", 16),
		    new BigInteger("b5c0fbcfec4d3b2f", 16),
		    new BigInteger("e9b5dba58189dbbc", 16),
		    new BigInteger("3956c25bf348b538", 16),
		    new BigInteger("59f111f1b605d019", 16),
		    new BigInteger("923f82a4af194f9b", 16),
		    new BigInteger("ab1c5ed5da6d8118", 16),
		    new BigInteger("d807aa98a3030242", 16),
		    new BigInteger("12835b0145706fbe", 16),
		    new BigInteger("243185be4ee4b28c", 16),
		    new BigInteger("550c7dc3d5ffb4e2", 16),
		    new BigInteger("72be5d74f27b896f", 16),
		    new BigInteger("80deb1fe3b1696b1", 16),
		    new BigInteger("9bdc06a725c71235", 16),
		    new BigInteger("c19bf174cf692694", 16),
		    new BigInteger("e49b69c19ef14ad2", 16),
		    new BigInteger("efbe4786384f25e3", 16),
		    new BigInteger("0fc19dc68b8cd5b5", 16),
		    new BigInteger("240ca1cc77ac9c65", 16),
		    new BigInteger("2de92c6f592b0275", 16),
		    new BigInteger("4a7484aa6ea6e483", 16),
		    new BigInteger("5cb0a9dcbd41fbd4", 16),
		    new BigInteger("76f988da831153b5", 16),
		    new BigInteger("983e5152ee66dfab", 16),
		    new BigInteger("a831c66d2db43210", 16),
		    new BigInteger("b00327c898fb213f", 16),
		    new BigInteger("bf597fc7beef0ee4", 16),
		    new BigInteger("c6e00bf33da88fc2", 16),
		    new BigInteger("d5a79147930aa725", 16),
		    new BigInteger("06ca6351e003826f", 16),
		    new BigInteger("142929670a0e6e70", 16),
		    new BigInteger("27b70a8546d22ffc", 16),
		    new BigInteger("2e1b21385c26c926", 16),
		    new BigInteger("4d2c6dfc5ac42aed", 16),
		    new BigInteger("53380d139d95b3df", 16),
		    new BigInteger("650a73548baf63de", 16),
		    new BigInteger("766a0abb3c77b2a8", 16),
		    new BigInteger("81c2c92e47edaee6", 16),
		    new BigInteger("92722c851482353b", 16),
		    new BigInteger("a2bfe8a14cf10364", 16),
		    new BigInteger("a81a664bbc423001", 16),
		    new BigInteger("c24b8b70d0f89791", 16),
		    new BigInteger("c76c51a30654be30", 16),
		    new BigInteger("d192e819d6ef5218", 16),
		    new BigInteger("d69906245565a910", 16),
		    new BigInteger("f40e35855771202a", 16),
		    new BigInteger("106aa07032bbd1b8", 16),
		    new BigInteger("19a4c116b8d2d0c8", 16),
		    new BigInteger("1e376c085141ab53", 16),
		    new BigInteger("2748774cdf8eeb99", 16),
		    new BigInteger("34b0bcb5e19b48a8", 16),
		    new BigInteger("391c0cb3c5c95a63", 16),
		    new BigInteger("4ed8aa4ae3418acb", 16),
		    new BigInteger("5b9cca4f7763e373", 16),
		    new BigInteger("682e6ff3d6b2b8a3", 16),
		    new BigInteger("748f82ee5defb2fc", 16),
		    new BigInteger("78a5636f43172f60", 16),
		    new BigInteger("84c87814a1f0ab72", 16),
		    new BigInteger("8cc702081a6439ec", 16),
		    new BigInteger("90befffa23631e28", 16),
		    new BigInteger("a4506cebde82bde9", 16),
		    new BigInteger("bef9a3f7b2c67915", 16),
		    new BigInteger("c67178f2e372532b", 16),
		    new BigInteger("ca273eceea26619c", 16),
		    new BigInteger("d186b8c721c0c207", 16),
		    new BigInteger("eada7dd6cde0eb1e", 16),
		    new BigInteger("f57d4f7fee6ed178", 16),
		    new BigInteger("06f067aa72176fba", 16),
		    new BigInteger("0a637dc5a2c898a6", 16),
		    new BigInteger("113f9804bef90dae", 16),
		    new BigInteger("1b710b35131c471b", 16),
		    new BigInteger("28db77f523047d84", 16),
		    new BigInteger("32caab7b40c72493", 16),
		    new BigInteger("3c9ebe0a15c9bebc", 16),
		    new BigInteger("431d67c49c100d4c", 16),
		    new BigInteger("4cc5d4becb3e42b6", 16),
		    new BigInteger("597f299cfc657e2a", 16),
		    new BigInteger("5fcb6fab3ad6faec", 16),
		    new BigInteger("6c44198c4a475817", 16)
	};
	*/
	private static final long[] K = new long[] {
            0x428a2f98d728ae22L,
		    0x7137449123ef65cdL,
		    0xb5c0fbcfec4d3b2fL,
		    0xe9b5dba58189dbbcL,
		    0x3956c25bf348b538L,
		    0x59f111f1b605d019L,
		    0x923f82a4af194f9bL,
		    0xab1c5ed5da6d8118L,
		    0xd807aa98a3030242L,
		    0x12835b0145706fbeL,
		    0x243185be4ee4b28cL,
		    0x550c7dc3d5ffb4e2L,
		    0x72be5d74f27b896fL,
		    0x80deb1fe3b1696b1L,
		    0x9bdc06a725c71235L,
		    0xc19bf174cf692694L,
		    0xe49b69c19ef14ad2L,
		    0xefbe4786384f25e3L,
		    0x0fc19dc68b8cd5b5L,
		    0x240ca1cc77ac9c65L,
		    0x2de92c6f592b0275L,
		    0x4a7484aa6ea6e483L,
		    0x5cb0a9dcbd41fbd4L,
		    0x76f988da831153b5L,
		    0x983e5152ee66dfabL,
		    0xa831c66d2db43210L,
		    0xb00327c898fb213fL,
		    0xbf597fc7beef0ee4L,
		    0xc6e00bf33da88fc2L,
		    0xd5a79147930aa725L,
		    0x06ca6351e003826fL,
		    0x142929670a0e6e70L,
		    0x27b70a8546d22ffcL,
		    0x2e1b21385c26c926L,
		    0x4d2c6dfc5ac42aedL,
		    0x53380d139d95b3dfL,
		    0x650a73548baf63deL,
		    0x766a0abb3c77b2a8L,
		    0x81c2c92e47edaee6L,
		    0x92722c851482353bL,
		    0xa2bfe8a14cf10364L,
		    0xa81a664bbc423001L,
		    0xc24b8b70d0f89791L,
		    0xc76c51a30654be30L,
		    0xd192e819d6ef5218L,
		    0xd69906245565a910L,
		    0xf40e35855771202aL,
		    0x106aa07032bbd1b8L,
		    0x19a4c116b8d2d0c8L,
		    0x1e376c085141ab53L,
		    0x2748774cdf8eeb99L,
		    0x34b0bcb5e19b48a8L,
		    0x391c0cb3c5c95a63L,
		    0x4ed8aa4ae3418acbL,
		    0x5b9cca4f7763e373L,
		    0x682e6ff3d6b2b8a3L,
		    0x748f82ee5defb2fcL,
		    0x78a5636f43172f60L,
		    0x84c87814a1f0ab72L,
		    0x8cc702081a6439ecL,
		    0x90befffa23631e28L,
		    0xa4506cebde82bde9L,
		    0xbef9a3f7b2c67915L,
		    0xc67178f2e372532bL,
		    0xca273eceea26619cL,
		    0xd186b8c721c0c207L,
		    0xeada7dd6cde0eb1eL,
		    0xf57d4f7fee6ed178L,
		    0x06f067aa72176fbaL,
		    0x0a637dc5a2c898a6L,
		    0x113f9804bef90daeL,
		    0x1b710b35131c471bL,
		    0x28db77f523047d84L,
		    0x32caab7b40c72493L,
		    0x3c9ebe0a15c9bebcL,
		    0x431d67c49c100d4cL,
		    0x4cc5d4becb3e42b6L,
		    0x597f299cfc657e2aL,
		    0x5fcb6fab3ad6faecL,
		    0x6c44198c4a475817L
	};
	
	public static long[] Hash(String text) throws UnsupportedEncodingException {	         
         long[][] M = getM(text.getBytes("ASCII"));
         
         for(int i = 0; i < M[0].length; i++) {
        	 System.out.println(Long.toHexString(M[0][i]));
         }
         
         
         
         long[] W = new long[80];
         long[] H = new long[] {
        		 0x6a09e667f3bcc908L,
        		 0xbb67ae8584caa73bL,
        		 0x3c6ef372fe94f82bL,
        		 0xa54ff53a5f1d36f1L,
        		 0x510e527fade682d1L,
        		 0x9b05688c2b3e6c1fL,
        		 0x1f83d9abfb41bd6bL,
        		 0x5be0cd19137e2179L
         };

		for(int i = 0; i < M.length; i++) {
			for(int j = 0; j < 16; j++) {
				W[j] = M[i][j];
			}
			 
			for(int j = 16; j < 80; j++) {
				W[j] = Ro1(W[j - 2]) + W[j - 7] + Ro0(W[j - 15]) + W[j - 16];
				//W[j] = new BigInteger("" + Ro1(W[j - 2])).add(new BigInteger("" + W[j - 7])).add(new BigInteger("" + Ro0(W[j - 15]))).add(new BigInteger("" + W[j - 16])).longValue();
			}
			 
			long a = H[0];
			long b = H[1];
			long c = H[2];
			long d = H[3];
			long e = H[4];
		    long f = H[5];
		    long g = H[6];
		    long h = H[7];			
		    
		    System.out.println("Init: " +
					Long.toHexString(a) + " " + 
					Long.toHexString(b) + " " + 
					Long.toHexString(c) + " " + 
					Long.toHexString(d) + " " + 
					Long.toHexString(e) + " " + 
					Long.toHexString(f) + " " + 
					Long.toHexString(g) + " " + 
					Long.toHexString(h) + " ");
		    
		    long T1 = 0, T2 = 0;
		    
		    for(int j = 0; j < 80; j++) {
		    	
		    	T1 = h + Eps1(e) + Ch(e, f, g) + K[j] + W[j];
		    	T2 = Eps0(a) + Maj(a, b, c);
		    	 
		    	//T1 = new BigInteger("" + h).add(new BigInteger("" + Eps1(e))).add(new BigInteger("" + Ch(e, f, g))).add(new BigInteger(K[j] + "")).add(new BigInteger("" + W[j])).longValue();
		    	//T2 = new BigInteger("" + Eps0(a)).add(new BigInteger("" + Maj(a, b, c))).longValue();
		    	
		    	h = g;
				g = f;
				f = e;
				e = d + T1;
				d = c;
				c = b;
				b = a;
				a = T1 + T2;
				
				
				System.out.println("t = " + j + " " +
						Long.toString(a, 16) + "   " + 
						Long.toString(b, 16) + "   " + 
						Long.toString(c, 16) + "   " + 
						Long.toString(d, 16) + "   " + 
						Long.toString(e, 16) + "   " + 
						Long.toString(f, 16) + "   " + 
						Long.toString(g, 16) + "   " + 
						Long.toString(h, 16) + "   ");
						
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
		for(int i = 0; i < array.length; ++i) 
			full[i] = array[i];
		
		full = setLastBit(full);
		
		long[][] Result = new long[amount][];
		for(int i = 0, k = 0; i < amount; ++i) {
			Result[i] = new long[16];
			for(int j = 0; j < 16; j++, k += 8) {
				Result[i][j] = toLong(Arrays.copyOfRange(full, k, k + 8));
			}
		}
		Result[amount - 1][15] = (long) (array.length * 8);
		
		return Result;
	}
	
	public static long Rotr(long x, int n) {
		return ((x >>> n) | (x << 64 - n));	
	}
	
	public static long Ch(long x, long y, long z) {
		return (x & y) ^ ((x ^ Long.MAX_VALUE) & z);
	}
	
	public static long Maj(long x, long y, long z) {
		return (x & y) ^ (x & z) ^ (y & z);
	}
	
	public static long Eps0(long x) {
		return (Rotr(x, 28) ^ Rotr(x, 34) ^ Rotr(x, 39));
	}
	
	public static long Eps1(long x) {
		return (Rotr(x, 14) ^ Rotr(x, 18) ^ Rotr(x, 41));
	}
	
	public static long Ro0(long x) {
		return (Rotr(x, 1) ^ Rotr(x, 8) ^ (x >>> 7));
	}
	
	public static long Ro1(long x) {
		return (Rotr(x, 19) ^ Rotr(x, 61) ^ (x >>> 6));
	}
	
	public static long toLong(byte[] array) {
		long a = 0;
		for(int i = 0; i < array.length; ++i) {
			a <<= 8;
			a |= (array[i] < 0) ? (long)((array[i] * -1)): array[i];
		}
		
		return a;
	}
	
	public static byte[] toBytes(long a) {
		byte[] array = new byte[8];
		Arrays.fill(array, (byte)0);
		
		for(int i = 7, j = 0; i >= 0; i--, j++) {
			array[j] |= (a >>> i * 8);
		}
		
		return array;
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
			if(getBit(array[i - 1], j) == 1 && j == 0) {
				array[i] = -128;
				break;
			} else if(getBit(array[i - 1], j) == 1 && i > 0) {
				array[i - 1] = setBit(array[i - 1], j - 1);
				break;
			}
		}
				
		return array;
	}
	
	/*
	public static byte[] setLastBit(byte[] array) {
		if(array.length == 0) {
			array = new byte[1];
			array[0] = -128;
			return array;
		}
		
		for(int i = 0; i < 8; i++) {
			if(getBit(array[array.length - 1], i) == 1 && i == 0) {
				array = Arrays.copyOf(array, array.length + 1);
				array[array.length - 1] = -128;
				break;
			} else if(getBit(array[i], i) == 1 && i > 0) {
				array[array.length - 1] = setBit(array[array.length - 1], i - 1);
				break;
			}
		}
		
		return array;
	}
	*/
	public static byte getBit(byte a, int i) {
		return (byte) ((a >> i) & 1); 
	}
	public static byte setBit(byte a, int i) {
		return (byte) ((byte) (1 << i) | a); 
	}
}