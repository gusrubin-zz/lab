package com.gusrubin.lab.java.conversornumerico;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ConversorNumericoTests {

	private static final String INTEIRO_1_EM_ROMANO = "I";
	private static final String INTEIRO_2_EM_ROMANO = "II";
	private static final String INTEIRO_3_EM_ROMANO = "III";
	private static final String INTEIRO_4_EM_ROMANO = "IV";
	private static final String INTEIRO_5_EM_ROMANO = "V";
	private static final String INTEIRO_6_EM_ROMANO = "VI";
	private static final String INTEIRO_7_EM_ROMANO = "VII";
	private static final String INTEIRO_8_EM_ROMANO = "VIII";
	private static final String INTEIRO_9_EM_ROMANO = "IX";
	private static final String INTEIRO_10_EM_ROMANO = "X";
	private static final String INTEIRO_11_EM_ROMANO = "XI";
	private static final String INTEIRO_12_EM_ROMANO = "XII";
	private static final String INTEIRO_13_EM_ROMANO = "XIII";
	private static final String INTEIRO_14_EM_ROMANO = "XIV";
	private static final String INTEIRO_15_EM_ROMANO = "XV";
	private static final String INTEIRO_16_EM_ROMANO = "XVI";
	private static final String INTEIRO_17_EM_ROMANO = "XVII";
	private static final String INTEIRO_18_EM_ROMANO = "XVIII";
	private static final String INTEIRO_19_EM_ROMANO = "XIX";
	private static final String INTEIRO_20_EM_ROMANO = "XX";
	
	private static final int ROMANO_I_EM_INTEIRO = 1;
	private static final int ROMANO_II_EM_INTEIRO = 2;
	private static final int ROMANO_III_EM_INTEIRO = 3;
	private static final int ROMANO_IV_EM_INTEIRO = 4;
	private static final int ROMANO_V_EM_INTEIRO = 5;
	private static final int ROMANO_VI_EM_INTEIRO = 6;
	private static final int ROMANO_VII_EM_INTEIRO = 7;
	private static final int ROMANO_VIII_EM_INTEIRO = 8;
	private static final int ROMANO_IX_EM_INTEIRO = 9;
	private static final int ROMANO_X_EM_INTEIRO = 10;
	private static final int ROMANO_XI_EM_INTEIRO = 11;
	private static final int ROMANO_XII_EM_INTEIRO = 12;
	private static final int ROMANO_XIII_EM_INTEIRO = 13;
	private static final int ROMANO_XIV_EM_INTEIRO = 14;
	private static final int ROMANO_XV_EM_INTEIRO = 15;
	private static final int ROMANO_XVI_EM_INTEIRO = 16;
	private static final int ROMANO_XVII_EM_INTEIRO = 17;
	private static final int ROMANO_XVIII_EM_INTEIRO = 18;
	private static final int ROMANO_XIX_EM_INTEIRO = 19;
	private static final int ROMANO_XX_EM_INTEIRO = 20;

	@Test
	void shouldConvertIntegerBetween_1_and_20_ToRoman() {
		assertEquals(INTEIRO_1_EM_ROMANO, ConversorNumerico.integerToRoman(1));
		assertEquals(INTEIRO_2_EM_ROMANO, ConversorNumerico.integerToRoman(2));
		assertEquals(INTEIRO_3_EM_ROMANO, ConversorNumerico.integerToRoman(3));
		assertEquals(INTEIRO_4_EM_ROMANO, ConversorNumerico.integerToRoman(4));
		assertEquals(INTEIRO_5_EM_ROMANO, ConversorNumerico.integerToRoman(5));
		assertEquals(INTEIRO_6_EM_ROMANO, ConversorNumerico.integerToRoman(6));
		assertEquals(INTEIRO_7_EM_ROMANO, ConversorNumerico.integerToRoman(7));
		assertEquals(INTEIRO_8_EM_ROMANO, ConversorNumerico.integerToRoman(8));
		assertEquals(INTEIRO_9_EM_ROMANO, ConversorNumerico.integerToRoman(9));
		assertEquals(INTEIRO_10_EM_ROMANO, ConversorNumerico.integerToRoman(10));
		assertEquals(INTEIRO_11_EM_ROMANO, ConversorNumerico.integerToRoman(11));
		assertEquals(INTEIRO_12_EM_ROMANO, ConversorNumerico.integerToRoman(12));
		assertEquals(INTEIRO_13_EM_ROMANO, ConversorNumerico.integerToRoman(13));
		assertEquals(INTEIRO_14_EM_ROMANO, ConversorNumerico.integerToRoman(14));
		assertEquals(INTEIRO_15_EM_ROMANO, ConversorNumerico.integerToRoman(15));
		assertEquals(INTEIRO_16_EM_ROMANO, ConversorNumerico.integerToRoman(16));
		assertEquals(INTEIRO_17_EM_ROMANO, ConversorNumerico.integerToRoman(17));
		assertEquals(INTEIRO_18_EM_ROMANO, ConversorNumerico.integerToRoman(18));
		assertEquals(INTEIRO_19_EM_ROMANO, ConversorNumerico.integerToRoman(19));
		assertEquals(INTEIRO_20_EM_ROMANO, ConversorNumerico.integerToRoman(20));
	}

	@Test
	void shouldFailOnConvertIntegerLessThan_1_ToRoman() {
		assertThrows(IllegalArgumentException.class, () -> ConversorNumerico.integerToRoman(0));
	}

	@Test
	void shouldFailOnConvertIntegerGreaterThan_20_ToRoman() {
		assertThrows(IllegalArgumentException.class, () -> ConversorNumerico.integerToRoman(21));
	}

	@Test
	void shouldConvertRomanBetween_I_and_XX_ToInteger() {
		assertEquals(ROMANO_I_EM_INTEIRO, ConversorNumerico.romanToInteger("I"));
		assertEquals(ROMANO_II_EM_INTEIRO, ConversorNumerico.romanToInteger("II"));
		assertEquals(ROMANO_III_EM_INTEIRO, ConversorNumerico.romanToInteger("III"));
		assertEquals(ROMANO_IV_EM_INTEIRO, ConversorNumerico.romanToInteger("IV"));
		assertEquals(ROMANO_V_EM_INTEIRO, ConversorNumerico.romanToInteger("V"));
		assertEquals(ROMANO_VI_EM_INTEIRO, ConversorNumerico.romanToInteger("VI"));
		assertEquals(ROMANO_VII_EM_INTEIRO, ConversorNumerico.romanToInteger("VII"));
		assertEquals(ROMANO_VIII_EM_INTEIRO, ConversorNumerico.romanToInteger("VIII"));
		assertEquals(ROMANO_IX_EM_INTEIRO, ConversorNumerico.romanToInteger("IX"));
		assertEquals(ROMANO_X_EM_INTEIRO, ConversorNumerico.romanToInteger("X"));
		assertEquals(ROMANO_XI_EM_INTEIRO, ConversorNumerico.romanToInteger("XI"));
		assertEquals(ROMANO_XII_EM_INTEIRO, ConversorNumerico.romanToInteger("XII"));
		assertEquals(ROMANO_XIII_EM_INTEIRO, ConversorNumerico.romanToInteger("XIII"));
		assertEquals(ROMANO_XIV_EM_INTEIRO, ConversorNumerico.romanToInteger("XIV"));
		assertEquals(ROMANO_XV_EM_INTEIRO, ConversorNumerico.romanToInteger("XV"));
		assertEquals(ROMANO_XVI_EM_INTEIRO, ConversorNumerico.romanToInteger("XVI"));
		assertEquals(ROMANO_XVII_EM_INTEIRO, ConversorNumerico.romanToInteger("XVII"));
		assertEquals(ROMANO_XVIII_EM_INTEIRO, ConversorNumerico.romanToInteger("XVIII"));
		assertEquals(ROMANO_XIX_EM_INTEIRO, ConversorNumerico.romanToInteger("XIX"));
		assertEquals(ROMANO_XX_EM_INTEIRO, ConversorNumerico.romanToInteger("XX"));
	}

	@Test
	void shouldFailOnConvertRomanGreaterThan_XX_ToInteger() {
		assertThrows(IllegalArgumentException.class, () -> ConversorNumerico.romanToInteger("XXI"));
	}

	@Test
	void shouldFailOnConvertDifferentOfRomanToInteger() {
		assertThrows(IllegalArgumentException.class, () -> ConversorNumerico.romanToInteger("A"));
	}

}
