package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandFactory {
    private final Map<String, Class<?>> commandMap = new HashMap<>();
    private final Logger logger = LoggerFactory.getLogger(CommandFactory.class);
    public CommandFactory() throws Exception {
        loadCommandsFromConfig();
        if (commandMap.isEmpty()) {
            throw new NoSuchElementException("Failed to create command factory");
        }
    }

    private void loadCommandsFromConfig() {
        InputStream configStream = getClass().getResourceAsStream("/commands.config");

        if (configStream == null) {
            logger.error("Configuration file not found in resources");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(configStream))) {
            String jarPath;
            while ((jarPath = reader.readLine()) != null) {
                jarPath = jarPath.trim();
                if (jarPath.isEmpty() || jarPath.startsWith("#")) {
                    continue;
                }
                loadCommandsFromJar(jarPath);
            }
        } catch (IOException e) {
            logger.error("Error reading configuration file");
        }
    }

    private void loadCommandsFromJar(String jarPath) {
        try (URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file:" + jarPath)})){
            try (JarFile jarFile = new JarFile(jarPath)) {
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String entryName = entry.getName();
                    if (entryName.endsWith(".class")) {
                        String className = entryName
                                .replace('/', '.')
                                .replace(".class", "");
                        try {
                            Class<?> c = classLoader.loadClass(className);
                            CommandInfo info = c.getAnnotation(CommandInfo.class);
                            if (info != null) {
                                String commandName = info.name();
                                commandMap.put(commandName, c);
                            }
                        } catch (ClassNotFoundException e) {
                            logger.error("Class not found in JAR {}: {}", jarPath, className);
                        }
                    }
                }
            } catch (IOException e) {
                logger.error("Failed to read JAR file: {}", jarPath);
            }
        } catch (Exception e) {
            logger.error("Error with classloader for JAR: {}", jarPath);
        }
    }

    public Command createCommand(String commandName) throws Exception {
        Class<?> commandClass = commandMap.get(commandName);
        if (commandClass == null) {
            throw new NoSuchElementException("Command " + commandName + " not found");
        }
        try {
            return (Command) commandClass.getDeclaredConstructor().newInstance();
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to create command " + commandName);
        }
    }
}
