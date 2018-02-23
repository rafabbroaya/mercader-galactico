package com.rsanchezg.business.logic;

import com.rsanchezg.business.domain.RomanNumber;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author raasanch
 */
@Service
public class ProcessQueries {

  private Conversions conversions;

  @Autowired
  public ProcessQueries(Conversions conversions) {
    this.conversions = conversions;
  }

  private Map<String, RomanNumber> romanNumbersMap = new HashMap<>();
  private Map<String, Double> scalarMap = new HashMap<>();

  /**
   * Process each line of the file
   *
   * @param lines File's lines
   * @return Output of lines processed
   */
  public List<String> processLines(List<String> lines) {
    List<String> output = new ArrayList<>();
    for (String line : lines) {
      String[] arg = line.trim().split("\\s+");
      String result;
      if (arg.length == 3) {
        result = assignRomanValue(arg);
        if (result != null) {
          output.add(result);
        }
      } else if (line.endsWith(" Credits")) {
        result = assignValueToMissingElements(line);
        if (result != null) {
          output.add(result);
        }
      } else {
        if (line.length() > 0) {
          output.add(query(line));
        }
      }
    }
    return output;
  }

  /**
   * Process lines which have 3 params as assignation
   *
   * @param arg Line splited in an array
   * @return Return null if nothing bad happend
   */
  private String assignRomanValue(String[] arg) {
    RomanNumber romanNumber = RomanNumber.valueOf(arg[2]);
    if (!arg[1].equalsIgnoreCase("is")) {
      return "Invalid assignment operator in roman numbers!! (" + arg[1] + ")";
    }
    romanNumbersMap.put(arg[0], romanNumber);
    return null;
  }

  /**
   * Assign a possible value to missing elements
   *
   * @param line Line splited in an array
   * @return Return null if nothing bad happend
   */
  private String assignValueToMissingElements(String line) {
    String params = line.substring(0, line.indexOf(" is "));
    String[] arg = params.trim().split("\\s+");
    RomanNumber[] romanNumber = new RomanNumber[arg.length - 1];
    for (int i = 0; i < arg.length - 1; i++) {
      romanNumber[i] = romanNumbersMap.get(arg[i]);
    }
    int value = conversions.decodeRoman(romanNumber);
    if (value == -1) {
      return "Assign value to missing elements invalid!!";
    }
    params = line.substring(line.indexOf(" is ") + 3, line.indexOf(" Credits"));
    try {
      Double credits = Double.parseDouble(params.trim());
      scalarMap.put(arg[arg.length - 1], credits / value);
    } catch (NumberFormatException e) {
      return "Assign value to missing elements invalid!!";
    }
    return null;
  }

  /**
   * Process lines which could be queries
   *
   * @param line Line to be processed
   * @return Output of process
   */
  private String query(String line) {
    if (line.startsWith("how much is") && line.endsWith("?")) {
      String params = line.trim().substring(11, line.indexOf('?')).trim();
      String[] arg = params.split("\\s+");
      RomanNumber[] romanNumber = new RomanNumber[arg.length];
      for (int i = 0; i < arg.length; i++) {
        romanNumber[i] = romanNumbersMap.get(arg[i]);
      }
      return params + " is " + conversions.decodeRoman(romanNumber);
    } else if (line.startsWith("how many Credits is") && line.endsWith("?")) {
      String params = line.trim().substring(19, line.indexOf('?')).trim();
      String[] arg = params.split("\\s+");
      RomanNumber[] romanNumber = new RomanNumber[arg.length - 1];
      for (int i = 0; i < arg.length - 1; i++) {
        romanNumber[i] = romanNumbersMap.get(arg[i]);
      }
      Double missingElement = scalarMap.get(arg[arg.length - 1]);
      if (missingElement != null) {
        return params + " is " + (int) (conversions.decodeRoman(romanNumber) * missingElement)
            + " Credits";
      } else {
        return "Missing element value: (" + arg[arg.length - 1] + ")";
      }
    } else {
      return "I have no idea what you are talking about";
    }
  }
}
