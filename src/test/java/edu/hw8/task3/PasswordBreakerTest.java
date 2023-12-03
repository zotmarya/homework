package edu.hw8.task3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class PasswordBreakerTest {

    private static Stream<Arguments> userAndPassword() {
        return Stream.of(
            Arguments.of("a.v.petrov", "12345"),
            Arguments.of("v.v.belov", "abcde"),
            Arguments.of("a.s.ivanov", "cat14"),
            Arguments.of("k.p.maslov", "katze")
        );
    }

    @ParameterizedTest
    @MethodSource("userAndPassword")
    void singleThreadPasswordBreak_WhenGivenUserInfo_ReturnsPasswords() {
        List<String> info = new ArrayList<>(List.of(
            "a.v.petrov  827ccb0eea8a706c4c34a16891f84e7b",
            "v.v.belov   d8578edf8458ce06fbc5bb76a58c5ca4",
            "a.s.ivanov  482c811da5d5b4bc6d497ffa98491e38",
            "k.p.maslov  ab56b4d92b40713acc5af89985d4b786"
            ));

        PasswordBreaker passwordBreaker = new PasswordBreaker(info);

        Map<String, String> result = passwordBreaker.singleThreadPasswordBreak();
    }
}
