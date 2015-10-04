package Main_Package;
public class BaseOperationHandler {
		
		private static int charOffset = 55;
		
		/**
		 * Converts a character c into a usable number for operations
		 * <p> Designed for use with the characters 0 - 9, and A - F. Alphabetical letters beyond F are inherently supported, but not recommended
		 * @param c The character to interpret
		 * @return Returns the Integer coresponding to the given parameter
		 */
		public static int interpret_toInt(char c) {
			int rez = Character.codePointAt(new char[] {c}, 0);
			if(rez >= 65) return rez - charOffset; // Converts the character code into a usable number for the operations
			else return Integer.parseInt(Character.toString(c));
		}
		
		/**
		 * Converts an integer into it's corresponding character, for use in numeral representation beyond the decimal base.
		 * @param rez The integer to convert.
		 * @return Returns the corresponding character of the given integer. <p>
		 */
		public static String interpret_toString(int rez) {
			if(rez >= 10) {
				rez += charOffset;
				return Character.toString((char) rez);
			} else return Integer.toString(rez);
		}
		
		/**
		 * Compares two numbers of the same base, regardless of what the base is.
		 * <p> Possible results:
		 * 1 - target is greater, 
		 * 0 - comp is greater, 
		 * 2 - target and comp are equal
		 *
		 * @param  target the target number to which comp is compared
		 * @param  comp the number with which target is compared
		 * @return      Returns the code of the comparison
		 */
		public static int compare(String target, String comp) {
			if(target.length() > comp.length()) return 1;
			else if(target.length() < comp.length()) return 0;
			else for(int i = target.length(); i > 0; i--) {
				int targetCode = BaseOperationHandler.interpret_toInt(target.charAt(i));
				int compCode = BaseOperationHandler.interpret_toInt(comp.charAt(i));
				if(targetCode > compCode) return 1;
				else if(targetCode < compCode) return 0;
			}
			return 2;
		}
		
		/**
		 * Adds two numbers of the same base, regardless of what the base is <p>
		 * Uses the intercalation algorithm to account for varying string lengths
		 * @param a	   The first number of the operation.
		 * @param b    The second number of the operation.
		 * @param base The base in which the operation is handles.
		 * @return	   The resulting number, represented in the base of operations.
		 */
		public static String add(String a, String b, int base) {
			StringBuilder res = new StringBuilder();
			int t = 0, i = a.length() - 1, j = b.length() - 1, aCode, bCode, cCode;
			do {
				aCode = BaseOperationHandler.interpret_toInt(a.charAt(i));
				bCode = BaseOperationHandler.interpret_toInt(b.charAt(j));
				cCode = (aCode + bCode + t) % base;
				res.append(BaseOperationHandler.interpret_toString(cCode));
				t = (aCode + bCode + t) / base;
				i--; j--;
			}while(i >= 0 && j >= 0);
			while(i >= 0) {
				aCode = BaseOperationHandler.interpret_toInt(a.charAt(i));
				cCode = (aCode + t) % base;
				res.append(BaseOperationHandler.interpret_toString(cCode));
				t = (aCode + t) / base;
				i--;
			}
			while(j >= 0) {
				bCode = BaseOperationHandler.interpret_toInt(b.charAt(j));
				cCode = (bCode + t) % base;
				res.append(BaseOperationHandler.interpret_toString(cCode));
				t = (bCode + t) / base;
				j--;
			}
			if(t != 0) res.append(BaseOperationHandler.interpret_toString(t));
			return res.reverse().toString();
		}
		
		/**
		 * Subtracts from number a the number b, regardless of said base.
		 * <p> a and b must be of the same base, and a must be greater than or equal to b. If a is smaller than b, a and b are switched with one another.
		 * @param a	The minuend
		 * @param b The subtrahend
		 * @param base The base in which the operation is handled.
		 * @return Returns the result of the equation, represented in a base equal with the base of the two parameters
		 */
		public static String subtract(String a, String b, int base) {
				switch(compare(a,b)) {
					case 0: { 
						String t = a;
						a = b;
						b = t;
					} break;
					case 2: { return "0"; }
				}
				StringBuilder result = new StringBuilder();
				int t = 0, i = a.length() - 1, j = b.length() - 1, aCode, bCode, cCode;
				do {
					aCode = interpret_toInt(a.charAt(i));
					bCode = interpret_toInt(b.charAt(j));
					if(aCode + t >= bCode) {
						cCode = aCode + t - bCode;
						t = 0;
					} else {
						cCode = aCode + t - bCode + base;
						t = -1;
					} i--; j--;
					result.append(interpret_toString(cCode));
				}while(i >= 0 && j >= 0);
				while( i >= 0) {
					result.append(interpret_toString(aCode + t));
					i--; t = 0;
				}
				while( j >= 0) {
					result.append(interpret_toString(bCode + t));
					j--; t = 0;
				}
				return result.reverse().toString();
		}
		
		public static String divide_by_letter(String a, int quot, int base) {
			StringBuilder rez = new StringBuilder();
			int t = 0;
			for(int i = 0; i < a.length(); i++) {
				int c = interpret_toInt(a.charAt(i)), div;
				div = (t * base + c) / quot;
				rez.append(interpret_toString(div));
				t = (t * base + c) % quot;
			}
			while(rez.length() > 1 && rez.charAt(0) == '0' ) rez.deleteCharAt(0); 
			rez.append(" remainder " + t);
			return rez.toString();
		}
}
