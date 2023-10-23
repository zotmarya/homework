package edu.hw3.task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class AtbashEncryptorTest {

    private AtbashEncryptor atbashEncryptor;

    @BeforeEach
    void setUp() {
        atbashEncryptor = new AtbashEncryptor();
    }

    @ParameterizedTest
    @CsvSource(value = {"Hello world!@Svool dliow!",
        "Any fool can write code that a computer can understand. " +
            "Good programmers write code that humans can understand. ― Martin Fowler@" +
            "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. " +
            "Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi"}, delimiter = '@')
    void useAtbashEncryption_WhenGivenString_ReturnEncryptedWord(String word, String expectedWord) {
        String encryptedWord = atbashEncryptor.useAtbashEncryption(word);

        assertThat(encryptedWord).isEqualTo(expectedWord);
    }
}
