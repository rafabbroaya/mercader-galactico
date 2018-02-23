package com.rsanchezg;

import com.rsanchezg.business.logic.InputService;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author raasanch
 */
@ComponentScan()
public class App {

  private static final Logger LOGGER = Logger.getLogger(App.class);

  private InputService inputService;

  @Autowired
  public App(InputService inputService) {
    this.inputService = inputService;
  }

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
    App app = context.getBean(App.class);
    app.start(args);
  }

  private void start(String[] args) {
    String filePath = null;
    if (args.length > 0) {
      filePath = args[0];
    }
    try {
      inputService.processInput(filePath);
    } catch (IOException e) {
      LOGGER.error("File not found!!");
    }


  }


}
