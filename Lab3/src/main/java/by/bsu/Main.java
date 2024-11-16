package by.bsu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import by.bsu.client.ConsoleClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            ConsoleClient consoleClient = new ConsoleClient();
            consoleClient.start();
        } catch (SQLException | IOException e) {
            logger.error("Failed to create controller manager");
        }
    }
}