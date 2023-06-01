package io.github.sdxqw.surviveyourself.handling;

import io.github.sdxqw.surviveyourself.logging.Logger;
import lombok.Getter;
import lombok.ToString;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

@Getter
@ToString
public class CrashReport {

    private final String description;
    private final Throwable cause;
    private final Map<String, String> crashSections;

    public CrashReport(String description, Throwable cause) {
        this.description = description;
        this.cause = cause;
        this.crashSections = new LinkedHashMap<>();
        populateEnvironment();
    }

    private static String getWittyComment() {
        String[] comments = new String[]{
                "Looks like your survival skills need some work.",
                "Whoops! Better luck next time.",
                "The odds were not in your favor.",
                "Surviving is hard, isn't it?",
                "The game fought back and won this time.",
                "Remember, failure is the first step towards success.",
                "You can't win them all, but you can try again.",
                "The game just wanted to test your resilience.",
                "Embrace the crash and rise from the ashes.",
                "Survival is a journey, and this is just a detour.",
                "Failure is the condiment that gives success its flavor.",
                "Don't worry, even the best survivors stumble sometimes.",
                "Crashes happen, but so does learning.",
                "Your resilience will shine through the crash.",
                "Surviving is an art, and crashes are just brushstrokes.",
                "Every crash is an opportunity for improvement.",
                "Remember, crashes are temporary, determination is permanent.",
                "The crash might have won this round, but not the game.",
                "You're one step closer to becoming an expert survivor.",
                "Crashes are like plot twists in the survival story."
        };
        int index = new Random().nextInt(comments.length);
        return comments[index];
    }

    public static void makeCrashReport(Throwable cause, String description) {
        CrashReport crashReport = new CrashReport(description, cause);
        String completeReport = crashReport.getCompleteReport();
        Logger.error("\n" + completeReport);
    }

    private void populateEnvironment() {
        addCrashSection("Survive Yourself", "1.0");
        addCrashSection("Operating System", System.getProperty("os.name") + " (" +
                System.getProperty("os.arch") + ") version " + System.getProperty("os.version"));
        addCrashSection("Java Version", System.getProperty("java.version") + ", " +
                System.getProperty("java.vendor"));
        addCrashSection("Java VM Version", System.getProperty("java.vm.name") + " (" +
                System.getProperty("java.vm.info") + "), " + System.getProperty("java.vm.vendor"));
        addCrashSection("Memory", getMemoryInformation());
        addCrashSection("JVM Flags", getJvmFlags());
    }

    private String getMemoryInformation() {
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory() / (1024L * 1024L);
        long totalMemory = runtime.totalMemory() / (1024L * 1024L);
        long freeMemory = runtime.freeMemory() / (1024L * 1024L);
        return freeMemory + " MB / " + totalMemory + " MB (Max: " + maxMemory + " MB)";
    }


    private String getJvmFlags() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        return runtimeMXBean.getInputArguments().toString();
    }

    public void addCrashSection(String key, String value) {
        crashSections.put(key, value);
    }

    public String getCompleteReport() {
        StringBuilder builder = new StringBuilder();
        builder.append("---- Survive Yourself Crash Report ----\n");
        builder.append("// ").append(getWittyComment()).append("\n\n");
        builder.append("Time: ").append(System.currentTimeMillis()).append("\n");
        builder.append("Description: ").append(description).append("\n\n");
        builder.append(getCauseStackTraceOrString()).append("\n\n");
        builder.append("A detailed walkthrough of the error, its code path, and all known details is as follows:\n");
        builder.append("----------------------------------------\n\n");
        for (Map.Entry<String, String> entry : crashSections.entrySet()) {
            builder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return builder.toString();
    }

    private String getCauseStackTraceOrString() {
        if (cause == null) {
            return "";
        } else {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            cause.printStackTrace(pw);
            return sw.toString();
        }
    }
}
