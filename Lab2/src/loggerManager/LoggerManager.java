package loggerManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerManager {
    private static Logger errorLogger;
    private static final String LOG_DIRECTORY = "logs/";
    private static final String ERROR_LOG_FILE = LOG_DIRECTORY + "error-log.log";

    static {
        try {
            if (!Files.exists(Paths.get(LOG_DIRECTORY))) {
                Files.createDirectories(Paths.get(LOG_DIRECTORY));
                System.out.println("Log directory created successfully.");
            }

            errorLogger = Logger.getLogger("ErrorLogger");

            FileHandler fileHandler = new FileHandler(ERROR_LOG_FILE, false);  // Append mode
            fileHandler.setLevel(Level.SEVERE);
            fileHandler.setFormatter(new SimpleFormatter());

            errorLogger.addHandler(fileHandler);
            //errorLogger.setUseParentHandlers(false);
            errorLogger.setLevel(Level.SEVERE);

            System.out.println("Logger initialized successfully.");
        } catch (IOException e) {
            System.err.println("Failed to initialize error logger: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Logger getErrorLogger() {
        return errorLogger;
    }
}
