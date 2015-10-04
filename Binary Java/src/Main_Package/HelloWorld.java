package Main_Package;

import java.util.Scanner;
public class HelloWorld {	
	public static void main(String[] args) {
		int x = 0, base; String line;
		BinaryConverter converter = new BinaryConverter();
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Press 0 for int conversion.");
		System.out.println("Press 1 for String conversion.");
		System.out.println("Press 2 for Double conversion");
		x = Integer.parseInt(keyboard.nextLine());
			switch (x) {
				case(0): {
					System.out.println("Input start number and the base of operations");
					x = Integer.parseInt(keyboard.nextLine());
					base = Integer.parseInt(keyboard.nextLine());
					String convertedInt = converter.convertInt(x,base);
					System.out.println(convertedInt);
					System.out.println(converter.base10_convertInt(convertedInt,base));
				}break;
				case(1): {
					System.out.println("Input a string and the base of operations"); 
					line = keyboard.nextLine();
					base = Integer.parseInt(keyboard.nextLine());
					String convertedString = converter.convertString(line,base);
					System.out.println(convertedString);
					System.out.println(converter.reverse_convertString(convertedString,base));
				}break;
				case(2): {
					System.out.println("Input starting number and the base of operations and how many multiplications you require");
					double num = Double.parseDouble(keyboard.nextLine());
					base = Integer.parseInt(keyboard.nextLine());
					int count = Integer.parseInt(keyboard.nextLine());
					String convertedDouble = converter.convertDouble(num, base, count);
					System.out.println(convertedDouble);
				}
			}  keyboard.close();
	}

}
