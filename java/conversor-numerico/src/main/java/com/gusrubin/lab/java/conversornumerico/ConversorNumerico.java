package com.gusrubin.lab.java.conversornumerico;

public class ConversorNumerico {
	
	private ConversorNumerico() {
	    throw new IllegalStateException("Utility class");
	  }

	public static String integerToRoman(int number) {
		IntegerRomanDictionary romanName = IntegerRomanDictionary.valueOfLabel(number);
		if (romanName == null) {
			throw new IllegalArgumentException("Cannot convert integer out of range from 1 to 20.");
		}
		return romanName.toString();
	}

	public static Integer romanToInteger(String roman) {
		IntegerRomanDictionary integer = IntegerRomanDictionary.valueOf(roman);
		if (integer == null) {
			throw new IllegalArgumentException("Cannot convert roman out of range from I to XX.");
		}
		return integer.getValue();
	}

}
