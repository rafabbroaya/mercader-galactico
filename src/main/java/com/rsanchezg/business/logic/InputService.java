package com.rsanchezg.business.logic;

import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author raasanch
 */
@Service
public class InputService {

  private static final Logger LOGGER = Logger.getLogger(InputService.class);

  private ProcessQueries processQueries;

  @Autowired
  public InputService(ProcessQueries processQueries) {
    this.processQueries = processQueries;
  }

  /**
   * Read input file
   *
   * @param filePath Path's file
   */
  public void processInput(String filePath) throws IOException {
    if (filePath != null) {
      final List<String> lines = Files.readAllLines(Paths.get(filePath));
      print(processQueries.processLines(ImmutableList.copyOf(lines)));
    } else {
      LOGGER.error("USE: <filePath>");
    }
  }

  /**
   * Print processed lines
   */
  private void print(List<String> output) {
    for (String line : output) {
      System.out.println(line);
    }
  }


}
