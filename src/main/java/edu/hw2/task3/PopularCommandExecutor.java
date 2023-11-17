package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PopularCommandExecutor {
    private static final Logger LOGGER = LogManager.getLogger();
    private ConnectionManager manager;
    private static final int MAX_ATTEMPTS = 3;

    public PopularCommandExecutor(ConnectionManager connectionManager) {
        manager = connectionManager;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    public void tryExecute(String command) throws ConnectionException {
        Throwable cause = null;

        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                return;
            } catch (Exception exception) {
                LOGGER.info(exception);
                cause = exception;
            }
        }

        throw new ConnectionException("Connection interrupted.", cause);
    }
}
