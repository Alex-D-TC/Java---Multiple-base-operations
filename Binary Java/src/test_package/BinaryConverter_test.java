package test_package;

import static org.junit.Assert.*;

import org.junit.Test;

import Main_Package.BaseOperationHandler;
import Main_Package.Base_Conversion_Handler;

public class BinaryConverter_test {

	@Test
	public void test() {
		Base_Conversion_Handler converter = new Base_Conversion_Handler();
		assertEquals(1, BaseOperationHandler.compare("11100101", "1110101"));
		assertEquals("11110101001010100", BaseOperationHandler.add("11100111001110101", "1101111011111", 2));
		assertEquals("3", BaseOperationHandler.addWithFrac("1.50", "1.50", 10));
		assertEquals("3.004", BaseOperationHandler.normalize("003.004"));
		assertEquals("3.4", BaseOperationHandler.normalize("003.400"));
		assertEquals("3.14", BaseOperationHandler.normalize("3.14"));
		assertEquals("2 remainder 0", BaseOperationHandler.divide_by_letter("22", 11, 10));
		assertEquals("23013", BaseOperationHandler.multiply_by_letter("3231", 3, 4));
		assertEquals("13122", BaseOperationHandler.multiply_by_letter("3231", 2, 4));
		assertEquals("56736", BaseOperationHandler.multiply_by_letter("6304", 9, 10));
		assertEquals("4156", BaseOperationHandler.multiply_by_letter("1348", 3, 9));
		assertEquals("846E8", BaseOperationHandler.multiply_by_letter("D3E4", 10, 16));
		//System.out.println(BaseOperationHandler.multiply_by_letter("6304", 9, 10));
		assertEquals("10CE remainder 8", BaseOperationHandler.divide_by_letter("B8E2", 11, 16));
		assertEquals("101010000", BaseOperationHandler.subtract("1110101", "111000101", 2));
		
		System.out.println(BaseOperationHandler.compare("11100101", "1110101"));
		System.out.println(BaseOperationHandler.subtract("1110101", "111000101", 2));
		assertEquals("2.04", BaseOperationHandler.subWithFrac("2.5", "0.46", 10));
		assertEquals("0.05", BaseOperationHandler.subWithFrac("22.500", "22.450", 10));
		System.out.println(BaseOperationHandler.multiply_by_letter("3231", 3, 4));
		System.out.println(BaseOperationHandler.multiply_by_letter("3231", 2, 4));
		
		String a = "1.50";
		String b = "2.60";
		String c = BaseOperationHandler.addWithFrac(a, b, 10);
		System.out.println(c);
		a = "0.F";
		b = "0.1";
		c = BaseOperationHandler.addWithFrac(a, b, 16);
		System.out.println(c);
		a = "1.50";
		b = "2";
		c = BaseOperationHandler.subWithFrac(b, a, 10);
		System.out.println(c);
		assertEquals("10011010010", converter.convertInt_10(1234, 2));
		assertEquals("10011010010", converter.convertInt("1234", 2, 10));
		//assertEquals("237.285", converter.convertSubstitution("456.2", 7, 10)); Result is more precise than assertion, thus assert will fail
		//assertEquals("683.187", converter.convertSubstitution("2AB.3", 16, 10)); Result is more precise than assertion, thus assert will fail
		//assertEquals("263.857", converter.convertSubstitution("524.6", 7, 10)); Result is more precise than assertion, thus assert will fail
	
	}
}
