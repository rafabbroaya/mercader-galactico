package com.rsanchezg.business.logic;

import com.rsanchezg.business.domain.RomanNumber;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author raasanch
 */
@Service
public class Conversions {

  private static final Logger LOGGER = Logger.getLogger(Conversions.class);

  public int decodeRoman(RomanNumber[] number) {
    StringBuilder romanNumber = new StringBuilder();
    for (RomanNumber rNumber : number) {
      romanNumber.append(rNumber);
    }
    return decodeRoman(romanNumber.toString());
  }

  /**
   * Recursive algorithm to decode Roman number to decimal
   *
   * @param romanNumber Roman number
   * @return Decimal number
   */
  public int decodeRoman(String romanNumber) {
    if (romanNumber.length() == 0) {
      return 0;
    }
    if (!romanNumber.matches("^(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$")) {
      LOGGER.info("Invalid roman number (" + romanNumber + ")");
      return -1;
    }
    if (romanNumber.startsWith(RomanNumber.M.name())) {
      return RomanNumber.M.getValue() + decodeRoman(romanNumber.substring(1));
    } else if (romanNumber.startsWith(RomanNumber.CM.name())) {
      return RomanNumber.CM.getValue() + decodeRoman(romanNumber.substring(2));
    } else if (romanNumber.startsWith(RomanNumber.D.name())) {
      return RomanNumber.D.getValue() + decodeRoman(romanNumber.substring(1));
    } else if (romanNumber.startsWith(RomanNumber.CD.name())) {
      return RomanNumber.CD.getValue() + decodeRoman(romanNumber.substring(2));
    } else if (romanNumber.startsWith(RomanNumber.C.name())) {
      return RomanNumber.C.getValue() + decodeRoman(romanNumber.substring(1));
    } else if (romanNumber.startsWith(RomanNumber.XC.name())) {
      return RomanNumber.XC.getValue() + decodeRoman(romanNumber.substring(2));
    } else if (romanNumber.startsWith(RomanNumber.L.name())) {
      return RomanNumber.L.getValue() + decodeRoman(romanNumber.substring(1));
    } else if (romanNumber.startsWith(RomanNumber.XL.name())) {
      return RomanNumber.XL.getValue() + decodeRoman(romanNumber.substring(2));
    } else if (romanNumber.startsWith(RomanNumber.X.name())) {
      return RomanNumber.X.getValue() + decodeRoman(romanNumber.substring(1));
    } else if (romanNumber.startsWith(RomanNumber.IX.name())) {
      return RomanNumber.IX.getValue() + decodeRoman(romanNumber.substring(2));
    } else if (romanNumber.startsWith(RomanNumber.V.name())) {
      return RomanNumber.V.getValue() + decodeRoman(romanNumber.substring(1));
    } else if (romanNumber.startsWith(RomanNumber.IV.name())) {
      return RomanNumber.IV.getValue() + decodeRoman(romanNumber.substring(2));
    } else if (romanNumber.startsWith(RomanNumber.I.name())) {
      return RomanNumber.I.getValue() + decodeRoman(romanNumber.substring(1));
    }
    throw new IllegalArgumentException("Invalid roman number");
  }
}
