package io.github.sdxqw.surviveyourself.logging;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_GREEN = "\u001B[32m";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private static void log(Type level, Object message) {
        String color = switch (level) {
            case INFO -> ANSI_GREEN;
            case WARNING -> ANSI_YELLOW;
            case ERROR -> ANSI_RED;
        };
        LocalDateTime now = LocalDateTime.now();
        String logLevel = String.format("%s", level.name());
        String logMessage = String.format("[%s] [%s] > %s%s%s", now.format(formatter), logLevel, color, message, ANSI_RESET);
        System.out.println(logMessage);
        logToFile(logMessage);
    }

    private static void log(Type level, String format, Object... args) {
        String message = String.format(format, args);
        log(level, message);
    }

    private static void logToFile(String logMessage) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("log.txt", true))) {
            writer.println(stripANSI(logMessage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String stripANSI(String text) {
        return text.replaceAll("\u001B\\[[;\\d]*m", "");
    }

    public static void info(String format, Object... message) {
        log(Type.INFO, format, message);
    }

    public static void info(Object message) {
        log(Type.INFO, message);
    }

    public static void warn(String format, Object... message) {
        log(Type.WARNING, format, message);
    }

    public static void warn(Object message) {
        log(Type.WARNING, message);
    }

    public static void error(String format, Object... message) {
        log(Type.ERROR, format, message);
        System.exit(0);
    }

    public static void error(Object message) {
        log(Type.ERROR, message);
        System.exit(0);
    }

    public enum Type {
        ERROR,
        WARNING,
        INFO,
    }
}
