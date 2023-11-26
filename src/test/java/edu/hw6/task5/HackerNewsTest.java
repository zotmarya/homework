package edu.hw6.task5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class HackerNewsTest {
    private HackerNews hackerNews;

    @BeforeEach
    void setUp() {
        hackerNews = new HackerNews();
    }

    @Test
    void hackerNewsTopStories_WhenNoException_ReturnsNotEmptyArray() {
        long[] newsIds = hackerNews.hackerNewsTopStories();

        assertThat(newsIds).hasSizeGreaterThan(0);
    }

    @ParameterizedTest
    @CsvSource({
        "37570037,JDK 21 Release Notes",
        "38327017,Show HN: YouTube banned adblockers so I built an extension to skip their ads",
        "38322880,B.C. looks to standardize multiplex designs as latest fix to housing crisis"})
    void news_WhenGivenPresentId_ReturnsTitle(long id, String expectedTitle) {
        String title = hackerNews.news(id);

        assertThat(title).isEqualTo(expectedTitle);
    }

    @ParameterizedTest
    @ValueSource(longs = {-1, 0, 99999999})
    void news_WhenGivenInvalidId_ReturnsNull(long id) {
        String title = hackerNews.news(id);

        assertThat(title).isNull();
    }

}
