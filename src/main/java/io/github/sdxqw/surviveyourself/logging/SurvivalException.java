package io.github.sdxqw.surviveyourself.logging;

import io.github.sdxqw.surviveyourself.handling.CrashReport;
import lombok.Getter;

@Getter
public class SurvivalException extends RuntimeException {

    public SurvivalException(String message) {
        super(message);
        handleCrashReport();
    }

    public SurvivalException(String message, Throwable cause) {
        super(message, cause);
        handleCrashReport();
    }

    private void handleCrashReport() {
        CrashReport.makeCrashReport(this, super.getMessage());
    }

    @Override
    public String getMessage() {
        return "SurvivalException: " + super.getMessage();
    }
}
