	public class BinaryConverter {
		public BinaryConverter() {}
		
		public String convertInt(int x) {
			StringBuilder num = new StringBuilder();
			while(x != 0) {
				num.append(x%2);
				x /= 2;
			}
			num.reverse();
			return num.toString();
		}
		
		public int reverse_convertInt(String binInt) {
			int rev = 0;
			for(int i = 0 ; i < binInt.length() ; i++) {
				rev = 2 * rev + Integer.parseInt(Character.toString(binInt.charAt(i)));
			}
			return rev;
		}
		
		public String convertString(String str) {
			StringBuilder convString = new StringBuilder();
			for(int i = 0; i < str.length(); i++) {
				char char_at = str.charAt(i);
				convString.append(convertInt(Character.codePointAt(new char[] {char_at}, 0)));
				convString.append(" ");
			}
			return convString.toString();
		}
		
		public String reverse_convertString(String binString) {
			StringBuilder original = new StringBuilder();
			String[] splitBin = binString.split(" ");
			for(int i = 0 ; i < splitBin.length ; i++) {
				original.append(Character.toString((char) reverse_convertInt(splitBin[i])));
			}
			return original.toString();
		}
	}
	