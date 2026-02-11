package org.example;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;
import java.util.Map;
import java.util.HashMap;

public class LoggerManager {
   private static FileHandler fileHandler;
   private static final Map<String, Logger> loggers = new HashMap<>();

   static {
       try {
           fileHandler = new FileHandler("game.log", false);
           fileHandler.setFormatter(new SimpleFormatter());
       } catch (IOException e) {
           System.err.println("Failed to create file handler: " + e.getMessage());
       }
   }

   public static Logger getLogger(String className) {
       if (!loggers.containsKey(className)) {
           Logger logger = Logger.getLogger(className);
           logger.addHandler(fileHandler);
           logger.setUseParentHandlers(false);
           loggers.put(className, logger);
       }
       return loggers.get(className);
   }
}
