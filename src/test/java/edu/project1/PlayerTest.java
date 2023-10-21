package edu.project1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = Player.getInstance();
        player.newGame();
    }

    @Test
    void increaseMistakesMadeByOne_WhenMadeMistake_IncreaseByOne() {
        player.increaseMistakesMadeByOne();
        player.increaseMistakesMadeByOne();

        assertThat(player.getMistakesMade()).isEqualTo(2);
    }

    @Test
    void increaseGuessedLettersAmount_WhenGuessedLetter_IncreaseByGuessedAmount() {
        player.increaseGuessedLettersAmount(3);

        assertThat(player.getGuessedLettersAmount()).isEqualTo(3);
    }
}
