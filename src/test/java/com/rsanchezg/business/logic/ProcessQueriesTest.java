package com.rsanchezg.business.logic;

import static org.junit.Assert.assertTrue;

import com.google.common.collect.Lists;
import com.rsanchezg.App;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author raasanch
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = App.class)
public class ProcessQueriesTest {

  @Autowired
  ProcessQueries processQueries;

  @Test
  public void processLines() throws Exception {
    List<String> input = Lists
        .newArrayList("glob is I", "prok is V", "pish is X", "tegj is L",
            "glob glob Silver is 34 Credits",
            "glob prok Gold is 57800 Credits", "pish pish Iron is 3910 Credits",
            "how much is pish tegj glob glob ?",
            "how many Credits is glob prok Silver ?", "how many Credits is glob prok Gold ?",
            "how many Credits is glob prok Iron ?",
            "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
    List<String> expected = Lists
        .newArrayList("pish tegj glob glob is 42", "glob prok Silver is 68 Credits",
            "glob prok Gold is 57800 Credits", "glob prok Iron is 782 Credits",
            "I have no idea what you are talking about");
    List<String> actualValues = processQueries.processLines(input);
    assertTrue(expected.equals(actualValues));
  }

  @Test
  public void missingElement() throws Exception {
    List<String> input = Lists
        .newArrayList("glob is I", "prok is V", "glob glob prok Silver is 34 Credits",
            "how many Credits is glob prok Silver ?");
    List<String> expected = Lists
        .newArrayList("Assign value to missing elements invalid!!",
            "Missing element value: (Silver)");
    List<String> actualValues = processQueries.processLines(input);
    assertTrue(expected.equals(actualValues));
  }

  @Test
  public void processLine() throws Exception {
    List<String> input = Lists
        .newArrayList("max is M", "calc is C", "dead is D", "lion is L", "xenon is X", "venom is V",
            "icu is I",
            "how much is max calc dead xenon ?", "how much is calc max xenon calc ?",
            "how much is dead calc lion venom icu ?");
    List<String> expected = Lists
        .newArrayList("max calc dead xenon is 1410", "calc max xenon calc is 990",
            "dead calc lion venom icu is 656");
    List<String> actualValues = processQueries.processLines(input);
    assertTrue(expected.equals(actualValues));
  }

  @Test
  public void assignation() throws Exception {
    List<String> input = Lists
        .newArrayList("max are M");
    List<String> expected = Lists
        .newArrayList("Invalid assignment operator in roman numbers!! (are)");
    List<String> actualValues = processQueries.processLines(input);
    assertTrue(expected.equals(actualValues));
  }

}