package Q1_03_URLify;

import CtCILibrary.AssortedMethods;

public class MyAnswer {
	// Assume string has sufficient free space at the end
	public static void replaceSpaces(char[] str, int trueLength) {
		int spaceCount = 0;
		for (int i = 0; i < trueLength; i++) {
			if (str[i] == ' ') {
				spaceCount++;
			}
		}
		int newLength = trueLength + 2 * spaceCount;
		int newIndex = newLength - 1;
		for (int i = trueLength - 1; i >= 0; i--) {
			if (str[i] == ' ') {
				str[newIndex--] = '0';
				str[newIndex--] = '2';
				str[newIndex--] = '%';
			} else {
				str[newIndex--] = str[i];
			}
		}
	}
	
	public static int findLastCharacter(char[] str) {
		for (int i = str.length - 1; i >= 0; i--) {
			if (str[i] != ' ') {
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		String str = "  Mr John Smith         ";
		char[] arr = str.toCharArray();
		int trueLength = findLastCharacter(arr) + 1;
		replaceSpaces(arr, trueLength);	
		System.out.println("\"" + AssortedMethods.charArrayToString(arr) + "\"");
	}
}
