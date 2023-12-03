package edu.hw8.task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuoteServer {
    private static final int PORT = 9999;
    private static final int MAX_CONNECTIONS = 5;
    private static final Set<String> QUOTES;
    private static final Logger LOGGER = LogManager.getLogger();

    private ExecutorService executorService;

    static {
        QUOTES = new HashSet<>();
        QUOTES.add("Не переходи на личности там, где их нет");
        QUOTES.add("Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами");
        QUOTES
            .add("А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.");
        QUOTES.add("Чем ниже интеллект, тем громче оскорбления");
    }

    public QuoteServer() {
        executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);
    }

    public void runServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {
                Socket clientSocket = serverSocket.accept();
                executorService.submit(() -> handleClient(clientSocket));
            }

        } catch (IOException exception) {
            LOGGER.info(exception);
        } finally {
            executorService.shutdown();
        }
    }

    private void handleClient(Socket clientSocket) {
        try (InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {
            String word = getWord(inputStream);

            String quote = getQuote(word);

            sendResponse(outputStream, quote);
        } catch (IOException exception) {
            LOGGER.info(exception);
        }
    }

    private String getWord(InputStream inputStream) throws IOException {
        byte[] wordBytes = inputStream.readNBytes(inputStream.available());

        return new String(wordBytes, StandardCharsets.UTF_8).trim();
    }

    public String getQuote(String word) {
        String responseQuote = null;

        for (String quote : QUOTES) {
            if (quote.contains(word)) {
                responseQuote = quote;
            }
        }

        return responseQuote;
    }

    private void sendResponse(OutputStream outputStream, String quote) throws IOException {
        byte[] quoteBytes = quote.getBytes(StandardCharsets.UTF_8);

        outputStream.write(quoteBytes);
    }
}
