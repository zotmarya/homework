package edu.project1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameHandlerTest {

    private GameHandler gameHandler;

    @BeforeEach
    void setUp() {
        gameHandler = GameHandler.getInstance();
        gameHandler.prepareForNewGame("WORD");
    }

    @Test
    void makeReactionToGuessedLettersAmount_WhenGuessedAllTheWords_StopGame() {
        gameHandler.reactToGuessedLettersAmount(4);

        assertThat(gameHandler.getPlayer().isPlaying()).isFalse();
    }

    @Test
    void makeReactionToGuessedLettersAmount_WhenGuessedSomeLettersAndMadeNotMaxMistakes_ContinueGame() {
        gameHandler.reactToGuessedLettersAmount(-1);
        gameHandler.reactToGuessedLettersAmount(-1);
        gameHandler.reactToGuessedLettersAmount(2);

        assertThat(gameHandler.getPlayer().isPlaying()).isTrue();
    }

    @Test
    void makeReactionToGuessedLettersAmount_WhenMadeMaxMistakes_StopGame() {
        gameHandler.reactToGuessedLettersAmount(-1);
        gameHandler.reactToGuessedLettersAmount(-1);
        gameHandler.reactToGuessedLettersAmount(-1);
        gameHandler.reactToGuessedLettersAmount(-1);
        gameHandler.reactToGuessedLettersAmount(-1);

        assertThat(gameHandler.getPlayer().isPlaying()).isFalse();
    }
}
