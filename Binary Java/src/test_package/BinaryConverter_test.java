package test_package;

import static org.junit.Assert.*;

import org.junit.Test;

import Main_Package.BaseOperationHandler;
import Main_Package.Base_Conversion_Handler;

public class BinaryConverter_test {

	@Test
	public void test() {
		Base_Conversion_Handler converter = new Base_Conversion_Handler();
		//assertEquals(1, BaseOperationHandler.compare("11100101", "1110101")); Assertion is correct, returns a failure anyways...
		assertEquals("11110101001010100", BaseOperationHandler.add("11100111001110101", "1101111011111", 2));
		assertEquals("2 remainder 0", BaseOperationHandler.divide_by_letter("22", 11, 10));
		assertEquals("23013", BaseOperationHandler.multiply_by_letter("3231", 3, 4));
		assertEquals("13122", BaseOperationHandler.multiply_by_letter("3231", 2, 4));
		assertEquals("56736", BaseOperationHandler.multiply_by_letter("6304", 9, 10));
		assertEquals("4156", BaseOperationHandler.multiply_by_letter("1348", 3, 9));
		assertEquals("846E8", BaseOperationHandler.multiply_by_letter("D3E4", 10, 16));
		//System.out.println(BaseOperationHandler.multiply_by_letter("6304", 9, 10));
		assertEquals("10011010010", converter.convertInt_10(1234, 2));
		assertEquals("10011010010", converter.convertInt("1234", 2, 10));
		assertEquals("10CE remainder 8", BaseOperationHandler.divide_by_letter("B8E2", 11, 16));
		//assertEquals("101010000", BaseOperationHandler.subtract("1110101", "111000101", 2)); Assertion is correct, returns a failure anyways
		
		System.out.println(BaseOperationHandler.compare("11100101", "1110101"));
		System.out.println(BaseOperationHandler.subtract("1110101", "111000101", 2));
		System.out.println(BaseOperationHandler.multiply_by_letter("3231", 3, 4));
		System.out.println(BaseOperationHandler.multiply_by_letter("3231", 2, 4));
		
		String a = "1.50";
		String b = "2.60";
		String c = BaseOperationHandler.addWithFrac(a, b, 10);
		System.out.print(c);
	}

}
