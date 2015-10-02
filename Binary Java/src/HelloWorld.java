
import java.util.Scanner;
public class HelloWorld {	
	public static void main(String[] args) {
		int x; String line;
		BinaryConverter converter = new BinaryConverter();
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Press 0 for int conversion.");
		System.out.println("Press 1 for String conversion.");
		x = Integer.parseInt(keyboard.nextLine());
		switch (x) {
			case(0): {
				System.out.println("Input start number");
				x = Integer.parseInt(keyboard.nextLine());
				String convertedInt = converter.convertInt(x);
				System.out.println(convertedInt);
				System.out.println(converter.reverse_convertInt(convertedInt));
			}break;
			case(1): {
				System.out.println("Input a string"); 
				line = keyboard.nextLine();
				String convertedString = converter.convertString(line);
				System.out.println(convertedString);
				System.out.println(converter.reverse_convertString(convertedString));
			}break;
		}  keyboard.close();
		
	}

}
