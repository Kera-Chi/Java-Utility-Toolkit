package com.juk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Java Utility Kit Application Entry Point
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.juk", "com.example.utility_toolkit" })
public class JUKApplication {
      /**
       * Application main entry point
       * 
       * @param args Command line arguments
       */
      public static void main(String[] args) {
            SpringApplication.run(JUKApplication.class, args);
      }
}