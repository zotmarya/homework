package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {

    private static final String TOP_STORIES_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String NEWS_INFO_URL = "https://hacker-news.firebaseio.com/v0/item/";
    private static final String JSON_EXT = ".json";
    private static final String TITLE_REGEX = "\"title\":\"([^\"]+)\"";
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    public long[] hackerNewsTopStories() {
        long[] newsIds;

        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(TOP_STORIES_URL))
                .GET()
                .build();

            HttpResponse<String> response =
                HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            String receivedText = response.body();
            String[] news = receivedText.substring(1, receivedText.length() - 1).split(",");
            newsIds = new long[news.length];

            for (int i = 0; i < news.length; i++) {
                newsIds[i] = Long.parseLong(news[i]);
            }

        } catch (URISyntaxException | IOException | InterruptedException exception) {
            newsIds = new long[0];
        }

        return newsIds;
    }

    public String news(long id) {
        String newsTitle = null;

        try {
            String url = NEWS_INFO_URL + id + JSON_EXT;

            HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

            HttpResponse<String> response =
                HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            String receivedText = response.body();

            Pattern pattern = Pattern.compile(TITLE_REGEX);
            Matcher matcher = pattern.matcher(receivedText);

            if (matcher.find()) {
                newsTitle = matcher.group(1);
            }

        } catch (URISyntaxException | IOException | InterruptedException exception) {
            newsTitle = null;
        }

        return newsTitle;
    }
}
