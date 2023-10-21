package edu.project1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameHandlerTest {

    private GameHandler gameHandler;

    @BeforeEach
    void setUp() {
        gameHandler = GameHandler.getInstance();
        gameHandler.initialize();
        gameHandler.prepareForNewGame("WORD");
    }

    @Test
    void makeReactionToGuessedLettersAmount_WhenGuessedAllTheWords_StopGame() {
        gameHandler.makeReactionToGuessedLettersAmount(4);

        assertThat(gameHandler.getPlayer().isPlaying()).isFalse();
    }

    @Test
    void makeReactionToGuessedLettersAmount_WhenGuessedSomeLettersAndMadeNotMaxMistakes_ContinueGame() {
        gameHandler.makeReactionToGuessedLettersAmount(-1);
        gameHandler.makeReactionToGuessedLettersAmount(-1);
        gameHandler.makeReactionToGuessedLettersAmount(2);

        assertThat(gameHandler.getPlayer().isPlaying()).isTrue();
    }

    @Test
    void makeReactionToGuessedLettersAmount_WhenMadeMaxMistakes_StopGame() {
        gameHandler.makeReactionToGuessedLettersAmount(-1);
        gameHandler.makeReactionToGuessedLettersAmount(-1);
        gameHandler.makeReactionToGuessedLettersAmount(-1);
        gameHandler.makeReactionToGuessedLettersAmount(-1);
        gameHandler.makeReactionToGuessedLettersAmount(-1);

        assertThat(gameHandler.getPlayer().isPlaying()).isFalse();
    }
}
