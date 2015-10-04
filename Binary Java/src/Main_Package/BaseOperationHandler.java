package Main_Package;
public class BaseOperationHandler {
		
		private static int charOffset = 55;
		
		public static int interpret_toInt(char c) {
			int rez = Character.codePointAt(new char[] {c}, 0);
			if(rez >= 65) return rez - charOffset; // Converts the character code into a usable number for the operations
			else return Integer.parseInt(Character.toString(c));
		}
		
		public static String interpret_toString(int rez) {
			if(rez >= 10) {
				rez += charOffset;
				return Character.toString((char) rez);
			} else return Integer.toString(rez);
		}
		
		// Compares two numbers of the same base, regardless of the what the base is
		// Possible results: 
		//		1 - target is greater
		//		0 - comp is greater
		//		2 - target and comp are equal
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
		
		// Adds two numbers of the same base, regardless of what the base is
		// Uses the interclasation code to account for varying string lengths
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
