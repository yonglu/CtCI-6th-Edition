package Q5_00_Basic_Bit_Operation;

import CtCILibrary.AssortedMethods;

public class MyAnswer {

    public static int reverseBits2(int n) {
        for (int i = 0; i < 16; i++) { 
            n = swapBits(n, i, 32 - i - 1); 
        } 
        return n; 
    } 
    
    // 
    public static boolean getBit(int n, int i) {
       int mask = 1 << i;      
       return ((n & mask) != 0);
    }
    
    public static int setBit(int n, int i) {
       int mask = 1 << i;      
       return (n | mask);
     }
     
    public static int clearBit(int n, int i) {
    	int mask = ~(1 << i);   	
    	return (n & mask);
    }
    
    public static int updateBit(int n, int i, boolean flag) {
    	if (flag) {
    		return setBit(n, i);
    	} else {
    		return clearBit(n, i);
    	}
    }

    public static int swapBits(int n, int i, int j) { 
    	
       int maskA = 1 << i;      
       int maskB = 1 << j;      

        int a = n & maskA;
        int b = n & maskB;
        
        // only swap if they are different
        if (a != b) {
        	int bitMask = (1 << i) | (1 << j);
        	// XOR ^ basically flip the bit
            return n = n ^ bitMask;
        }
        return n;
    }
    
    public static int reverseBits(int n) {
    	for (int i = 0; i < 16; i++) {
    		n = swapBits(n, i, 32-i- 1);
    	}
    	return n;
    }

	public static void main(String[] args) {
		int b = 6;
		System.out.println("Expect answer is true? " + getBit(b, 2));
		System.out.println("Expect answer is false? " + getBit(b, 7));
	
		System.out.println("Expect answer is 2? " + clearBit(b, 2));
		System.out.println("Expect answer is 6? " + clearBit(b, 3));

		System.out.println("Expect answer is 7? " + setBit(b, 0));
		System.out.println("Expect answer is 14? " + setBit(b, 3));
		
		System.out.println("Expect answer is 2? " + updateBit(b, 2, false));
		System.out.println("Expect answer is 14? " + updateBit(b, 3, true));
		
		System.out.println("Expect answer is 130? " + swapBits(b, 2, 7));
		int a = 43261596;
		System.out.println("Expect answer is 964176192? " + reverseBits(a));
	}

}
