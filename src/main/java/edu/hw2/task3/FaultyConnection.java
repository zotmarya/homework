package edu.hw2.task3;

public class FaultyConnection implements Connection {
    @Override
    public void execute(String command) {
        if (RandomNumGenerator.randomNum() == 1) {
            throw new ConnectionException("Connection interrupted.");
        }
    }

    @Override
    public void close() throws Exception {
    }
}
