package edu.hw1.task8;

public class Task8 {

    private final int chessBoardSize = 8;

    public boolean isOnBoard(int x, int y) {
        return (x >= 0 && x < chessBoardSize && y >= 0 && y < chessBoardSize);
    }

    private final int[][] moves = {{-1, -2}, {-2, -1}, {1, -2}, {2, -1}};

    public boolean areKnightsSafe(int[][] array) {

        for (int i = 0; i < chessBoardSize; i++) {
            for (int j = 0; j < chessBoardSize; j++) {
                if (array[i][j] == 1) {

                    for (int m = 0; m < moves.length; m++) {
                        int x = i + moves[m][0];
                        int y = j + moves[m][1];

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
