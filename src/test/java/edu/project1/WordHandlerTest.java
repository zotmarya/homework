package edu.project1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class WordHandlerTest {

    private WordHandler wordHandler;

    @BeforeEach
    void setUp() {
        wordHandler = WordHandler.getInstance();
        wordHandler.setNewWord("COFFEE");
    }

    @Test
    void setNewWord_WhenNewWordSet_ChangesWord() {
        wordHandler.setNewWord("LOVE");

        assertThat(wordHandler.getWord()).isEqualTo("LOVE");
    }

    @Test
    void checkIfPlayerGuessedLetter_WhenCorrectLetter_ReturnsAmountOfLettersGuessed() {
        int guessedAmount = wordHandler.checkIfPlayerGuessedLetter('E');

        assertThat(guessedAmount).isEqualTo(2);
    }

}
