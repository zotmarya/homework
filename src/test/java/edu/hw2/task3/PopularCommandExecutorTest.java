package edu.hw2.task3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PopularCommandExecutorTest {

    @Test
    void updatePackages_WhenDefaultConnectionManager_ReturnsNoException(){
        PopularCommandExecutor executor = new PopularCommandExecutor(new DefaultConnectionManager());


        assertThatNoException().isThrownBy(() -> executor.updatePackages());
    }

    @Test
    void updatePackages_WhenFaultyConnectionManager_ReturnsException(){
        PopularCommandExecutor executor = new PopularCommandExecutor(new FaultyConnectionManager());


        assertThatThrownBy(() -> executor.updatePackages()).isInstanceOf(ConnectionException.class);
    }
}
