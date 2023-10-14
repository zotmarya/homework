package edu.hw1.task8;

public class Task8 {

    private final static int CHESS_BOARD_SIZE = 8;

    private boolean isOnBoard(int x, int y) {
        return (x >= 0 && x < CHESS_BOARD_SIZE && y >= 0 && y < CHESS_BOARD_SIZE);
    }

    private final static int[][] MOVES = {{-1, -2}, {-2, -1}, {1, -2}, {2, -1}};

    public boolean areKnightsSafe(int[][] array) {

        for (int i = 0; i < CHESS_BOARD_SIZE; i++) {
            for (int j = 0; j < CHESS_BOARD_SIZE; j++) {
                if (array[i][j] == 1) {

                    for (int m = 0; m < MOVES.length; m++) {
                        int x = i + MOVES[m][0];
                        int y = j + MOVES[m][1];

                        if (isOnBoard(x, y) && array[x][y] == 1) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

}
