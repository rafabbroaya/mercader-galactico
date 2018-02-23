package com.rsanchezg.business.logic;

import static org.junit.Assert.*;

import com.rsanchezg.App;

import static com.rsanchezg.business.domain.RomanNumber.*;

import com.rsanchezg.business.domain.RomanNumber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = App.class)
public class ConversionsTest {

  @Autowired
  Conversions conversions;

  @Test
  public void decodeRomanSuccess() throws Exception {
    assertEquals(39, conversions.decodeRoman("XXXIX"));
    assertEquals(39, conversions.decodeRoman(new RomanNumber[]{X, X, X, I, X}));
  }

  @Test
  public void decodeRomanFail() throws Exception {
    assertNotEquals(40, conversions.decodeRoman("XXXX"));
    assertNotEquals(40, conversions.decodeRoman(new RomanNumber[]{X, X, X, X}));
    assertNotEquals(1000, conversions.decodeRoman("DD"));
    assertNotEquals(99, conversions.decodeRoman("IC"));
  }

}