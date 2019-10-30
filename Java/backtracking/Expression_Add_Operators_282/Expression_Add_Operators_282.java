package Expression_Add_Operators_282;

import java.util.*;

/*
 * Leetcode # 282. Expression Add Operators
 * 
 * 

Given a string that contains only digits 0-9 and a target value, 
return all possibilities to add binary operators (not unary) 
+, -, or * between the digits so they evaluate to the target value.

Example 1:

Input: num = "123", target = 6
Output: ["1+2+3", "1*2*3"] 
Example 2:

Input: num = "232", target = 8
Output: ["2*3+2", "2+3*2"]
Example 3:

Input: num = "105", target = 5
Output: ["1*0+5","10-5"]
Example 4:

Input: num = "00", target = 0
Output: ["0+0", "0-0", "0*0"]
Example 5:

Input: num = "3456237490", target = 9191
Output: []
*/

/*
 * See solution at: 
 *   https://leetcode.com/problems/expression-add-operators/solution/
 * 
 * Note this problem is more complicated because multiple digits can 
 * form the operand
 */


public class Expression_Add_Operators_282 {

//  char ops[] = {'+', '-', '*'};

  public static List<String> addOperators2(String num, int target) {

      List<List<String>> lists = new ArrayList<>();
      List<String> result = new ArrayList<String>();
      
      if (num == null || num.isEmpty()) {
          return result;
      }


      /*
       * Backtrack Decision Tree:
       * 	1. Order doesn't matter -> i=start
       * 	2. Digits use only once -> i+1
       * 	3. Need support multiple digits ->           
       * 		long currentDigitsValue = Long.parseLong(num.substring(start, i + 1));
       */
      addOperators(lists, new ArrayList<String>(),  num, target, 0, 0, 0);

	  for (List<String> temp : lists) {
		  StringBuilder sb = new StringBuilder();
		  for (String s : temp) {
			  sb.append(s);
		  }
		  result.add(new String(sb.toString()));
	  }
	  
     return result;


  }

  /**
   * @param lists
   * @param tempList
   * @param num
   * @param target
   * @param currentValue
   * @param lastValue
   * @param start
   */
  private static void addOperators(List<List<String>> lists, ArrayList<String> tempList, String num, int target, long currentValue, long lastValue, int start ) {

      /**
       * Our Constraints:
       * 1. We can't take more numbers than given in input string ( index >= input.length)
       */

      if (start == num.length()) {

          /**
           * Out Goal:
           * 1. once we form a expression, if that expression evaluates to our "target" then this is our solution.
           */

          if (currentValue == target) {
              //then this is our solution.
              lists.add(new ArrayList<String>(tempList));

          } //2. if not, we discard


          return;

      }

      /**
       * Our choices:
       * 1. We can choose a single digits as operands Or multi digits as operand (  1 + 2 or 12 + 34 )
       */
      for (int i = start; i < num.length(); i++) {

          /**
           * We don't consider a operand which is 0 as single digit operand, as operand like 0 or 01 , 023... does not make sense
           *  To avoid cases where we have 1 + 05 or 1 * 05 since 05 won't be a
           */
          if (i != start && num.charAt(start) == '0')
              break;

          // This allows multi digits operand.  Similar to palindromePartition
          long currentDigitsValue = Long.parseLong(num.substring(start, i + 1));
          
          // This is for single digits operand
//          long currentDigitsValue = Long.parseLong(num.substring(start, start + 1));

          /**
           * Our Constraints:
           * 4. We need two operands for a operator and operator can't be apply on single operand
           */

          if (start == 0) {
              // as this is the first digit only, then don't apply any operator

        	  tempList.add(Long.toString(currentDigitsValue));
              addOperators(lists, tempList, num, target, currentDigitsValue, currentDigitsValue, i + 1 );
              tempList.remove(tempList.size()-1);

          } else {
              //We have two operands, last and current

              /**
               * Plus operator application '+'; Current value become = so far value + current digit value and last value would be the current digit value
               * current Value = 12
               * last Value = 2 ( say we did like 10 + 2 )
               * currentDigitvalue = 5 then expression is 10 + 2 + 5 = 17
               * So last value would be 5
               */
        	  tempList.add("+");
        	  tempList.add(Long.toString(currentDigitsValue));
              addOperators(lists, tempList, num, target, currentValue + currentDigitsValue, currentDigitsValue, i + 1);
              tempList.remove(tempList.size()-1);
              tempList.remove(tempList.size()-1);

              /**
               * Minus operator application '-'; Current value become = so far value - current digit value and last value would be the -current digit value
               * current Value = 12
               * last Value = 2 ( say we did like 10 + 2 )
               * currentDigitvalue = 5 then expression is 10 + 2 - 5 = 7
               * So last value would be -5
               */
        	  tempList.add("-");
        	  tempList.add(Long.toString(currentDigitsValue));
              addOperators(lists, tempList, num, target, currentValue - currentDigitsValue, -currentDigitsValue, i + 1);
              tempList.remove(tempList.size()-1);
              tempList.remove(tempList.size()-1);


              /**
               * Multiply operator application '*'; As this has the highest precedence then + and -, we simply can't apply * on last and current value.
               * Current value become = currentValue - lastValue + last*currentDigitvalue;
               * For example
               * current value = 12 ,
               * last value = 2 ( let say we applied + as 10 + 2 )
               * currendDigitValue = 4
               * so expression become : 10 + 2 * 4
               * if we simply do 12 * 4 = 24 which is wrong as 10 + 2 * 4 = 10 + 8 = 18
               *
               * New value = 10 + 2 * 4 = 18
               * last value = 2*4 = 8
               *
               *
               */
        	  tempList.add("*");
        	  tempList.add(Long.toString(currentDigitsValue));
              addOperators(lists, tempList, num, target, currentValue - lastValue + lastValue * currentDigitsValue, lastValue * currentDigitsValue, i + 1);
              tempList.remove(tempList.size()-1);
              tempList.remove(tempList.size()-1);
          }
      }
  }	
	
	public static void main(String[] args) {

		List<String> result = addOperators2("105", 5);
		System.out.println("addOperators 105, 5 : ");
		for (String s : result) {
			System.out.println("    " + s);
		}

		result = addOperators2("115", 6);
		System.out.println("addOperators 115, 6 : ");
		for (String s : result) {
			System.out.println("    " + s);
		}
		
		result = addOperators2("232", 8);
		System.out.println("addOperators 232, 8 : ");
		for (String s : result) {
			System.out.println("    " + s);
		}
	}
}
