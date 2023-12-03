package edu.hw8.task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuoteClient {
    private static final String HOST = "localhost";
    private static final int PORT = 9999;
    private static final Logger LOGGER = LogManager.getLogger();

    public String handleQuoteRequest(String word) {
        String quote = null;

        try (Socket socket = new Socket(HOST, PORT);
             InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {
            sendWord(outputStream, word);
            quote = getQuote(inputStream);
        } catch (IOException exception) {
            LOGGER.info(exception);
        }

        return quote;
    }

    private void sendWord(OutputStream outputStream, String word) throws IOException {
        byte[] wordBytes = word.getBytes(StandardCharsets.UTF_8);
        outputStream.write(wordBytes);
        outputStream.flush();
    }

    private String getQuote(InputStream inputStream) throws IOException {
        byte[] receivedBytes = inputStream.readAllBytes();

        String quote = new String(receivedBytes, StandardCharsets.UTF_8);

        return quote;
    }

}
