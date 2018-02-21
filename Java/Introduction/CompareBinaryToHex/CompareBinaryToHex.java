package CompareBinaryToHex;

public class CompareBinaryToHex {

	public static int digitToValue(char c) {
		if (c >= '0' && c <= '9') {
			return c - '0';
		} else if (c >= 'A' && c <= 'F') {
			return 10 + c - 'A';
		} else if (c >= 'a' && c <= 'f') {
			return 10 + c - 'a';
		}
		return -1;
	}
	
	public static int convertFromBase(String number, int base) {
		if (base < 2 || (base > 10 && base != 16)) return -1;
		int value = 0;
		for (int i = number.length() - 1; i >= 0; i--) {
			int digit = digitToValue(number.charAt(i));
			if (digit < 0 || digit >= base) {
				return -1;
			}
			int exp = number.length() - 1 - i;
			value += digit * Math.pow(base, exp);
		}
		return value;
	}
	
   public static int convertFromBase2(String number, int base) {
      int i = 0; 
      int num = 0;
      boolean neg = false;
      if (number.charAt(i) == '-') {
         neg = true;
         i++;
      }
      for (int j=i; j<number.length();j++) {
        int digit = digitToValue(number.charAt(i));
        num = num * base + digit;
      }
      if (neg) {
         num = -1 * num;
      }
      return num;
   }

	
	public static boolean compareBinToHex(String binary, String hex) {
		int n1 = convertFromBase(binary, 2);
		int n2 = convertFromBase(hex, 16);
		if (n1 < 0 || n2 < 0) {
			return false;
		}
		return n1 == n2;
	}
	
   public static boolean compareBinToHex2(String binary, String hex) {
      int n1 = convertFromBase2(binary, 2);
      int n2 = convertFromBase2(hex, 16);
      if (n1 < 0 || n2 < 0) {
         return false;
      }
      return n1 == n2;
   }

	public static void main(String[] args) {
		System.out.println(compareBinToHex("111001011", "1CB"));
      System.out.println(compareBinToHex2("111001011", "1CB"));
	}

}
