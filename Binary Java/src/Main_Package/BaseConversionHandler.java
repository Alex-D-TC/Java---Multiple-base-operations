package Main_Package;
	public class BaseConversionHandler {
		public BaseConversionHandler() {}
		
		public String convertSubstitution(String number, int startBase, int targetBase) {
			String intPart = "0", fracPart = "0";
			int baseIndex = number.indexOf('.');
			float basePow = 1;
			if(baseIndex == -1) baseIndex = number.length();
			for(int i = baseIndex - 1; i >= 0; i--) {
				intPart = BaseOperationHandler.add(intPart, this.convertInt_10(BaseOperationHandler.interpret_toInt(number.charAt(i)) * (int)basePow, targetBase) , targetBase);
				basePow *= startBase;
			} basePow = (float) 1 / startBase;
			for(int i = baseIndex + 1; i < number.length(); i++) {
				fracPart = BaseOperationHandler.addWithFrac(fracPart, this.convertDouble((Float.toString(BaseOperationHandler.interpret_toInt(number.charAt(i)) * basePow)), targetBase, 10, 4), targetBase);
				basePow /= (float) startBase;
			}
			return BaseOperationHandler.addWithFrac(intPart, fracPart, targetBase);
		}
		
		/** Converts a given number from a specified base to another, using the repeated division algorithm.
		 * @param number The number that is converted.
		 * @param targetBase The base to convert to.
		 * @param startBase The base to convert from
		 * @return Returns the result of the conversion, represented in the base equal to the target base.
		 */
		public String convertInt(String number, int targetBase, int startBase) {
			StringBuilder num = new StringBuilder();
			String div_result;
			while(!(number.length() == 1 && number.charAt(0) == '0')) { 								// Checks if we have reached 0 in the string
				div_result = BaseOperationHandler.divide_by_letter(number, targetBase, startBase);
				String[] split_div_result = div_result.split(" ");										// The division by letter returns a number in the form: (result + "remainder" + remainder)
				num.append(split_div_result[2].charAt(0));												// Thus, we split the array in order to separate the result from the remainder
				number = split_div_result[0];
			}
			num.reverse();
			return num.toString();
		}
	
		// Converts a given number x to a specified base using the repeated division algorithm
		// The starting base is always 10
		/**
		 * @deprecated use {@link #convertInt(String, int, int)}} instead
		 * @param number
		 * @param base
		 * @return
		 */
		@Deprecated
		public String convertInt_10(int number, int base) {
			StringBuilder num = new StringBuilder();
			if(number == 0) return "0";
			while(number != 0) {
				num.append(number % base);
				number /= base;
			}
			num.reverse();
			return num.toString();
		}
		
		// Converts a given number x from the given base to base 10
		public int base10_convertInt(String binInt, int base) {
			int rev = 0;
			for(int i = 0 ; i < binInt.length() ; i++) {
				rev = base * rev + Integer.parseInt(Character.toString(binInt.charAt(i)));
			}
			return rev;
		}
		
		// Converts a given a subunitary floating point number x to a specified base using the repeated multiplication algorithm
		// The starting base is always 10
		public String convertDouble(String number, int targetBase, int startBase, int count) {
			StringBuilder num = new StringBuilder();
			num.append("0.");
			number = number.substring(2);
			while(!number.equals("0") && count != 0) {
				int num_length = number.length();
				number = BaseOperationHandler.multiply_by_letter(number, targetBase, startBase);
				if(num_length < number.length()) { 
					num.append(number.charAt(0));
					number = number.substring(number.length() - num_length);
				} else num.append('0');
				count--;
			}
			return num.toString();
		}
		
		// Converts each ASCII representation of each letter of the string to the specified base
		// The starting base is always 10
		public String convertString(String str, int base) {
			StringBuilder convString = new StringBuilder();
			for(int i = 0; i < str.length(); i++) {
				char char_at = str.charAt(i);
				convString.append(convertInt_10(Character.codePointAt(new char[] {char_at}, 0), base));
				convString.append(" ");
			}
			return convString.toString();
		}
		
		// Converts the ASCII representations of the letters of a string from the specified base to base 10
		// Afterwards, the ASCII codes are translated back into characters
		public String reverse_convertString(String binString, int base) {
			StringBuilder original = new StringBuilder();
			String[] splitBin = binString.split(" ");
			for(int i = 0 ; i < splitBin.length ; i++) {
				original.append(Character.toString((char) base10_convertInt(splitBin[i], base)));
			}
			return original.toString();
		}
	}
	