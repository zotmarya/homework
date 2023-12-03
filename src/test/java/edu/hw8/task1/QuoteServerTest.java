package edu.hw8.task1;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class QuoteServerTest {
    private static Stream<Arguments> wordsAndQuotes() {
        return Stream.of(
            Arguments.of("личности", "Не переходи на личности там, где их нет"),
            Arguments.of(
                "оскорбления",
                "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами"
            ),
            Arguments.of(
                "глупый",
                "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."
            ),
            Arguments.of("интеллект", "Чем ниже интеллект, тем громче оскорбления")
        );
    }

    @ParameterizedTest
    @MethodSource("wordsAndQuotes")
    void handleQuoteRequest_WhenGivenWord_ReturnsQuote(String word, String expectedQuote) {
        QuoteServer quoteServer = new QuoteServer();

        String resultQuote = quoteServer.getQuote(word);

        assertThat(resultQuote).isEqualTo(expectedQuote);
    }

}
