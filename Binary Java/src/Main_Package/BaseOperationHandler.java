package Main_Package;
public class BaseOperationHandler {
		
		private static int charOffset = 55;
		
		/**
		 * Converts a character c into a usable number for operations
		 * <p> Designed for use with the characters 0 - 9, and A - F. Alphabetical letters beyond F are inherently supported, but not recommended
		 * @param toConvert The character to interpret
		 * @return Returns the Integer coresponding to the given parameter
		 */
		public static int interpret_toInt(char toConvert) {
			int rez = Character.codePointAt(new char[] {toConvert}, 0);
			if(rez >= 65) return rez - charOffset; // Converts the character code into a usable number for the operations
			else return Integer.parseInt(Character.toString(toConvert));
		}
		
		/**
		 * Removes the unnecesary 0's from a given number.
		 * <p> The given 0's are the ones in front of the Integer part, and the ones at the end of the Fractionary part
		 * <p> Example: 
		 * 	<p>	003.400 -> 3.4 ; 3.040 -> 3.04 ; 3.14 -> 3.14
		 * @param toNormalize The number to be normalized
		 * @return Returns the normalized number. Returns the given number if the number doesn't need to be normalized
		 */
		public static String normalize(String toNormalize) {
			if(toNormalize.equals("0")) return toNormalize;
			StringBuilder temp = new StringBuilder();
			temp.append(toNormalize);
			while((char) temp.charAt(0) == '0') temp.deleteCharAt(0);
			if((char) temp.charAt(0) == '.') {
				temp.insert(0, '0');
			}
			if(temp.indexOf(".") != -1) {
				while((char) temp.charAt(temp.length() - 1) == '0') temp.deleteCharAt(temp.length() - 1);
				if((char) temp.charAt(temp.length() - 1) == '.') temp.deleteCharAt(temp.length() - 1);
			}
			return temp.toString();
		}
		
		/**
		 * Converts an integer into it's corresponding character, for use in numeral representation beyond the decimal base.
		 * @param toConvert The integer to convert.
		 * @return Returns the corresponding character of the given integer. <p>
		 */
		public static String interpret_toString(int toConvert) {
			if(toConvert >= 10) {
				toConvert += charOffset;
				return Character.toString((char) toConvert);
			} else return Integer.toString(toConvert);
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
			else for(int i = 0; i <= target.length() - 1; i++) {
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
		 * <p> <b> If you use floating point numbers, use {@link #addWithFrac(String, String, int)} instead!
		 * @param augend	   The first number of the operation.
		 * @param addend    The second number of the operation.
		 * @param base The base in which the operation is handles.
		 * @return	   The resulting number, represented in the base of operations.
		 */
		public static String add(String augend, String addend, int base) {
			StringBuilder res = new StringBuilder();
			int t = 0, i = augend.length() - 1, j = addend.length() - 1, aCode, bCode, cCode;
			do {
				aCode = BaseOperationHandler.interpret_toInt(augend.charAt(i));
				bCode = BaseOperationHandler.interpret_toInt(addend.charAt(j));
				cCode = (aCode + bCode + t) % base;
				res.append(BaseOperationHandler.interpret_toString(cCode));
				t = (aCode + bCode + t) / base;
				i--; j--;
			}while(i >= 0 && j >= 0);
			while(i >= 0) {
				aCode = BaseOperationHandler.interpret_toInt(augend.charAt(i));
				cCode = (aCode + t) % base;
				res.append(BaseOperationHandler.interpret_toString(cCode));
				t = (aCode + t) / base;
				i--;
			}
			while(j >= 0) {
				bCode = BaseOperationHandler.interpret_toInt(addend.charAt(j));
				cCode = (bCode + t) % base;
				res.append(BaseOperationHandler.interpret_toString(cCode));
				t = (bCode + t) / base;
				j--;
			}
			if(t != 0) res.append(BaseOperationHandler.interpret_toString(t));
			return res.reverse().toString();
		}
		/**
		 * Allows the addition of floating point numbers in any given base. 
		 * <p> The floating point numbers must be of the following form: <b> IntegerPart.FractionalPart
		 * <p> For more information, see: {@link #add(String, String, int)}
		 */
		public static String addWithFrac(String augend, String addend, int base) {
			String[] augendSplit = augend.split("\\.");
			String[] addendSplit = addend.split("\\.");
			String intPart = BaseOperationHandler.add(augendSplit[0], addendSplit[0], base);
			String subPart = BaseOperationHandler.add(augendSplit.length == 2 ? augendSplit[1] : "0", addendSplit.length == 2 ? addendSplit[1] : "0", base);
			if(subPart.length() > Math.max(augendSplit.length == 2 ? augendSplit[1].length() : 0, addendSplit.length == 2 ? addendSplit[1].length() : 0)) {
				String carryString = Character.toString(subPart.charAt(0));
				intPart = BaseOperationHandler.add(intPart, carryString, base);
				subPart = subPart.substring(1);
			}
			return BaseOperationHandler.normalize(intPart.concat(".").concat(subPart));
		}
		
		/**
		 * Subtracts from number a the number b, regardless of said base.
		 * <p> a and b must be of the same base, and a must be greater than or equal to b. If a is smaller than b, a and b are switched with one another.
		 * @param minuend	The minuend
		 * @param subtrahend The subtrahend
		 * @param base The base in which the operation is handled.
		 * @return Returns the result of the equation, represented in a base equal with the base of the two parameters
		 */
		public static String subtract(String minuend, String subtrahend, int base) {
				switch(compare(minuend,subtrahend)) {
					case 0: { 
						String t = minuend;
						minuend = subtrahend;
						subtrahend = t;
					} break;
					case 2: { return "0"; }
				}
				StringBuilder result = new StringBuilder();
				int t = 0, i = minuend.length() - 1, j = subtrahend.length() - 1, aCode, bCode, cCode;
				do {
					aCode = interpret_toInt(minuend.charAt(i));
					bCode = interpret_toInt(subtrahend.charAt(j));
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
		
		/** Allows the subtractions
		 * 
		 * @param minuend
		 * @param subtrahend
		 * @param base
		 * @return
		 */
		public static String subWithFrac(String minuend, String subtrahend, int base) {
			String[] minuendSplit = minuend.split("\\.");
			String[] subtrahendSplit = subtrahend.split("\\.");
			switch(compare(minuendSplit[0], subtrahendSplit[0])) {
				case 0: {
					String[] aux = minuendSplit;
					minuendSplit = subtrahendSplit;
					subtrahendSplit = aux;
				} break;
				case 2: {
					if(minuendSplit.length < subtrahendSplit.length) {
						String[] aux = minuendSplit;
						minuendSplit = subtrahendSplit;
						subtrahendSplit = aux;
					} else if(minuendSplit.length == subtrahendSplit.length) {
						switch(compare(minuendSplit[1], subtrahendSplit[1])) {
							case 0: {
								String[] aux = minuendSplit;
								minuendSplit = subtrahendSplit;
								subtrahendSplit = aux;
							} break;
							case 2: return "0";
						}
					}
				} break;
			}
			int maxFloat = Math.max(minuendSplit[0].length(), subtrahendSplit[0].length());
			minuend = minuendSplit[0] + (minuendSplit.length == 2 ? minuendSplit[1] : "");
			subtrahend = subtrahendSplit[0] + (subtrahendSplit.length == 2 ? subtrahendSplit[1] : "");
			while(minuend.length() < subtrahend.length()) minuend = minuend.concat("0");
			while(subtrahend.length() < minuend.length()) subtrahend = subtrahend.concat("0");
			StringBuilder temp = new StringBuilder();
			temp.append(BaseOperationHandler.subtract(minuend, subtrahend, base));
			temp.insert(maxFloat, ".");
			return BaseOperationHandler.normalize(temp.toString());
		}
		
		/**
		 * Multiplies a number by a given letter.
		 * @param number The number to multiply.
		 * @param mult	 The number to divide with.
		 * @param base   The base of operations.
		 * @return Returns the result of the multiplication, represented in the base of operations.
		 */
		public static String multiply_by_letter(String number, int mult, int base) {
			StringBuilder rez = new StringBuilder();
			int transport = 0;
			for(int index = number.length() - 1; index >= 0; index--) {
				int aCode = interpret_toInt(number.charAt(index));
				rez.append(interpret_toString((mult * aCode + transport) % base));
				transport = (mult * aCode + transport) / base;
			}
			if(transport > 0) rez.append(interpret_toString(transport));
			return rez.reverse().toString();
		}
		
		/**
		 * Divides a number by a given letter.
		 * @param dividend The number that is divided.
		 * @param divisor  
		 * @param base
		 * @return
		 */
		public static String divide_by_letter(String dividend, int divisor, int base) {
			StringBuilder rez = new StringBuilder();
			int t = 0;
			for(int i = 0; i < dividend.length(); i++) {
				int c = interpret_toInt(dividend.charAt(i)), div;
				div = (t * base + c) / divisor;
				rez.append(interpret_toString(div));
				t = (t * base + c) % divisor;
			}
			rez.replace(0, rez.length(), BaseOperationHandler.normalize(rez.toString()));
			rez.append(" remainder " + t);
			return rez.toString();
		}
		
		public static String multiply(String number, String mult, int base) {
			String resultString = "0";
			for(int i = mult.length() - 1; i >= 0; i--) {
				String multResult = BaseOperationHandler.multiply_by_letter(number, BaseOperationHandler.interpret_toInt(mult.charAt(i)), base);
				int add_count = mult.length() - 1 - i;
				while(add_count > 0) {
					multResult = multResult.concat("0");
					--add_count;
				}
				resultString = BaseOperationHandler.add(resultString, multResult, base);
			}
			return resultString;
		}
}
