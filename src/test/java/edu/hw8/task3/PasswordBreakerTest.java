package edu.hw8.task3;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PasswordBreakerTest {

    private         Map<String, String> expectedMap =
        Map.of("a.v.petrov", "cats", "v.v.belov", "1234",
            "a.s.ivanov", "abcd", "k.p.maslov", "l3b3"
        );

    private List<String> info = List.of(
        "a.v.petrov  0832c1202da8d382318e329a7c133ea0",
        "v.v.belov   81dc9bdb52d04dc20036dbd8313ed055",
        "a.s.ivanov  e2fc714c4727ee9395f324cd2e7f331f",
        "k.p.maslov  ee49fde835bb1b2238d1aa6883c2a32c"
    );

    @Test
    void singleThreadPasswordBreak_WhenGivenUserInfo_ReturnsPasswords() {
        PasswordBreaker passwordBreaker = new PasswordBreaker(info);

        Map<String, String> result = passwordBreaker.singleThreadPasswordBreak();

        assertThat(result.entrySet()).containsExactlyInAnyOrderElementsOf(expectedMap.entrySet());
    }

    @Test
    void multiThreadPasswordBreak_WhenGivenUserInfoAndThreadsAmount_ReturnsPasswords() {
        PasswordBreaker passwordBreaker = new PasswordBreaker(info);

        Map<String, String> result = passwordBreaker.multiThreadPasswordBreak(50);

        assertThat(result.entrySet()).containsExactlyInAnyOrderElementsOf(expectedMap.entrySet());
    }
}
