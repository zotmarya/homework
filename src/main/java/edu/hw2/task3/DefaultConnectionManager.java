package edu.hw2.task3;

public class DefaultConnectionManager implements ConnectionManager {
    @Override
    public Connection getConnection() {
        if (RandomNumGenerator.randomNum() == 1) {
            return new FaultyConnection();
        } else {
            return new StableConnection();
        }
    }
}
