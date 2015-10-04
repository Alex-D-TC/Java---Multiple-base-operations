	public class BinaryConverter {
		public BinaryConverter() {}
		
		// Converts a given number x to a specified base using the repeated division algorithm
		public String convertInt(String x, int targetBase, int startBase) {
			StringBuilder num = new StringBuilder();
			String div_result;
			while(!(x.length() == 1 && x.charAt(0) == '0')) { // Checks if we have reached 0 in the string
				div_result = BaseOperationHandler.divide_by_letter(x, targetBase, startBase);
				String[] split_div_result = div_result.split(" ");
				num.append(split_div_result[2].charAt(0));
				x = split_div_result[0];
			}
			num.reverse();
			return num.toString();
		}
	
		// Converts a given number x to a specified base using the repeated division algorithm
		// The starting base is always 10
		public String convertInt(int x, int base) {
			StringBuilder num = new StringBuilder();
			while(x != 0) {
				num.append(x % base);
				x /= base;
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
		public String convertDouble(double x, int base, int count) {
			StringBuilder num = new StringBuilder();
			if((int) x != 0) return "The number is not subunitary";
			num.append((int) x);
			num.append(',');
			while(x != 0 && count != 0) {
				x *= base;
				num.append((int) x);
				x = x - (int) x;
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
				convString.append(convertInt(Character.codePointAt(new char[] {char_at}, 0), base));
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
	